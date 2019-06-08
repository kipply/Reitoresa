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
    int ny = 300; 
    int ns = 400; 

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


      Vector lookFrom = new Vector(13,4,3); 
      Vector lookAt = new Vector(0, 0, 0);
      double focusDist = 10;
      double aperture = 0;
      Camera camera = new Camera(lookFrom, lookAt, new Vector(0, 1, 0), 40, Double.valueOf(nx) / ny, aperture, focusDist, 0, 1); 

      // Scene world = Scene.randomSceneNode(); 
      Scene world = Scene.lightBallScene(); 

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
        // System.out.println(1.0*(ny - j) / ny * 100  + " % done");
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
      Vector emitted = hit.mat.emitted(hit.u, hit.v, hit.p);
      if (depth < 50 && hit.mat.scatter(r, hit, attenuation, scattered)) {
        return colour(scattered, world, depth + 1).multiply(attenuation).add(emitted);
      } else {
        return emitted;
      }
    } else {
      return new Vector(0, 0, 0);
    }
  }
}