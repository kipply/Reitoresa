import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.Math; 

public class RayTracer {
  public static void main(String args[]) {
    int nx = 200; 
    int ny = 100; 
    try {
      File imageFile = new File("test.ppm");
      FileOutputStream outStream = new FileOutputStream(imageFile);
      OutputStreamWriter outWriter = new OutputStreamWriter(outStream);    
      Writer out = new BufferedWriter(outWriter);
      out.write("P3\n"); 
      out.write(tos(nx)); 
      out.write(" "); 
      out.write(tos(ny)); 
      out.write("\n225\n"); 

      Vector origin = new Vector(0, 0, 0); 
      Vector vertical = new Vector(0, 2, 0); 
      Vector horizontal = new Vector(4, 0, 0);
      Vector lowerLeft = new Vector(-2, -1, -1);

      Form[] forms = new Form[2]; 
      forms[0] = new Sphere(new Vector(0, 0, -1), 0.5); 
      forms[1] = new Sphere(new Vector(0, -100.5, -1), 100); 

      Scene world = new Scene(forms); 

      for (int j = ny - 1; j >= 0; j--) {
        for (int i = 0; i < nx; i++) {
          double u = i / (nx * 1.0); 
          double v = j / (ny * 1.0);
          Ray r = new Ray(origin, lowerLeft.add(horizontal.multiply(u)).add(vertical.multiply(v)));

          Vector p = r.pointAt(2);
          Vector colour = colour(r, world);
          int ir = (int)(255.99 * colour.x()); 
          int ig = (int)(255.99 * colour.y()); 
          int ib = (int)(255.99 * colour.z()); 

          out.write(tos(ir)); 
          out.write(" "); 
          out.write(tos(ig)); 
          out.write(" "); 
          out.write(tos(ib)); 
          out.write("\n"); 
        }
      }
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private static String tos(int n) {
    return Integer.toString(n);
  }

  private static Vector colour(Ray r, Scene world) {
    FormHit hit = new FormHit(); 
    if (world.hit(r, 0.0, Integer.MAX_VALUE, hit)) {
      return (new Vector(hit.normal.x() + 1, hit.normal.y() + 1, hit.normal.z() + 1)).multiply(0.5);
    } else {
      Vector unit = r.direction().unitVector(); 
      double t = 0.5 * (unit.y() + 1.0);
      return (new Vector(1, 1, 1).multiply(1 - t)).add(new Vector(0.5, 0.7, 1).multiply(t));
    }
  }
}