import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.Math; 

public class RayTracer {
  public static void main(String args[]) {
    System.out.println("Starting tracer"); 
    long startTime = System.nanoTime();
    long lastTime = startTime; 
    long timeOnWriting = 0; 
    long timeOnTracing = 0; 
    int nx = 400; 
    int ny = 200; 
    int ns = 100; 

    BufferedWriter out; 
    try {
      File imageFile = new File("test.ppm");
      if (!imageFile.exists()) {
         imageFile.createNewFile();
      }

      FileWriter fw = new FileWriter(imageFile);
      out = new BufferedWriter(fw);

      out.write("P3\n"); 
      out.write(tos(nx)); 
      out.write(" "); 
      out.write(tos(ny)); 
      out.write("\n225\n"); 

      timeOnWriting += System.nanoTime() - lastTime;
      lastTime = System.nanoTime();

      Camera camera = new Camera(); 

      Form[] forms = new Form[4]; 
      forms[0] = new Sphere(new Vector(0, 0, -1), 0.5, new Lambertian(new Vector(0.8, 0.2, 0.8))); 
      forms[1] = new Sphere(new Vector(0, -100.5, -1), 100, new Lambertian(new Vector(0.8, 0.8, 0.0))); 
      forms[2] = new Sphere(new Vector(1, 0, -1), 0.5, new Metal(new Vector(0.8, 0.6, 0.8))); 
      forms[3] = new Sphere(new Vector(-1, 0, -1), 0.5, new Dielectric(1.5)); 

      Scene world = new Scene(forms); 

      timeOnTracing += System.nanoTime() - lastTime;
      lastTime = System.nanoTime();

      for (int j = ny - 1; j >= 0; j--) {
        for (int i = 0; i < nx; i++) {
          Vector colour = new Vector(0, 0, 0);

          for (int s = 0; s < ns; s++) {
            double u = (i + Math.random()) / (nx * 1.0); 
            double v = (j + Math.random()) / (ny * 1.0);
            Ray r = camera.getRay(u, v);
            Vector p = r.pointAt(2);
            colour.addEquals(colour(r, world, 0));
          }
          colour.divideEquals(ns);
          colour = new Vector(Math.sqrt(colour.r()), Math.sqrt(colour.g()), Math.sqrt(colour.b()));
          int ir = (int)(255.99 * colour.x()); 
          int ig = (int)(255.99 * colour.y()); 
          int ib = (int)(255.99 * colour.z()); 

          timeOnTracing += System.nanoTime() - lastTime;
          lastTime = System.nanoTime();

          out.write(tos(ir)); 
          out.write(" "); 
          out.write(tos(ig)); 
          out.write(" "); 
          out.write(tos(ib)); 
          out.write("\n"); 

          timeOnWriting += System.nanoTime() - lastTime;
          lastTime = System.nanoTime();
        }
      }
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Finished at " + (System.nanoTime() - startTime) / 1000000000.0);
    System.out.println("Writing took " + timeOnWriting / 1000000000.0);
    System.out.println("Tracing " + timeOnTracing / 1000000000.0);
  }


  private static String tos(int n) {
    return Integer.toString(n);
  }

  private static Vector colour(Ray r, Scene world, int depth) {
    FormHit hit = new FormHit(); 
    if (world.hit(r, 0.001, Double.MAX_VALUE, hit)) {
      Ray scattered = new Ray(); 
      Vector attenuation = new Vector(); 
      if (depth < 50 && hit.mat.scatter(r, hit, attenuation, scattered)) {
        return colour(scattered, world, depth + 1).multiply(attenuation);
      } else {
        return new Vector(0, 0, 0);
      }
    } else {
      Vector unit = r.direction().unitVector(); 
      double t = 0.5 * (unit.y() + 1.0);
      return (new Vector(1, 1, 1).multiply(1 - t)).add(new Vector(0.5, 0.7, 1).multiply(t));
    }
  }
}