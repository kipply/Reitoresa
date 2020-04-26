import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.Math; 

/** Main class to do ray tracing
 * @author Carol Chen
*/
public class RayTracer {
  private static int nx, ny; 

  public static void main(String args[]) {
    System.out.println("Starting tracer"); 
    long startTime = System.nanoTime();
    long lastTime = startTime; 
    long timeOnWriting = 0; 
    long timeOnTracing = 0;
    nx = 800; 
    ny = 800; 
    int ns = 1200; 

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

      Camera camera = planetarySceneCam();
      Scene world = Scene.planetaryScene(); 

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
          colour = new Vector(Math.min(1, Math.sqrt(colour.r())), Math.min(1, Math.sqrt(colour.g())), Math.min(1, Math.sqrt(colour.b())));
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
        System.out.println(1.0*(ny - j) / ny * 100  + " % done");
      }
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Finished at " + (System.nanoTime() - startTime) / 1000000000.0);
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
      if (depth < 1000 && hit.mat.scatter(r, hit, attenuation, scattered)) {
        return colour(scattered, world, depth + 1).multiply(attenuation).add(emitted);
      } else {
        return emitted;
      }
    } else {
      return new Vector(0, 0, 0);
      // Vector unit = r.direction().unitVector(); 
      // double t = 0.5 * (unit.y() + 1.0);
      // return (new Vector(1, 1, 1).multiply(1 - t)).add(new Vector(0.5, 0.7, 1).multiply(t));
    }
  }

  private static Camera getCornellCam() {
    Vector lookFrom = new Vector(278, 278, -800); 
    Vector lookAt = new Vector(278, 278, 0);
    double focusDist = lookFrom.subtract(lookAt).length();
    double aperture = 0;
    Camera camera = new Camera(lookFrom, lookAt, new Vector(0, 1, 0), 40, Double.valueOf(nx) / ny, aperture, focusDist, 0, 1); 
    return camera; 
  }

  private static Camera getRandomSceneCam() {
    Vector lookFrom = new Vector(13,4,3); 
    Vector lookAt = new Vector(0, 0, 0);
    double focusDist = 10;
    double aperture = 0;
    Camera camera = new Camera(lookFrom, lookAt, new Vector(0, 1, 0), 40, Double.valueOf(nx) / ny, aperture, focusDist, 0, 1); 
    return camera; 
  }

  private static Camera planetarySceneCam() {
    Vector lookFrom = new Vector(10, 14, 20); 
    Vector lookAt = new Vector(0, 0, 0);
    double focusDist = 10;
    double aperture = 0;
    Camera camera = new Camera(lookFrom, lookAt, new Vector(0, 1, 0), 50, Double.valueOf(nx) / ny, aperture, focusDist, 0, 1); 
    return camera; 
  }
}