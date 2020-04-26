import java.lang.Math; 

/** Class to manage view angles and distances and focus blur
 * @author Carol Chen
*/
public class Camera {
  public Vector lowerLeft; 
  public Vector horizontal; 
  public Vector vertical; 
  public Vector origin; 
  public Vector u; 
  public Vector w; 
  public Vector v;
  public double lensRadius;
  public double time0; 
  public double time1; 

  /**
   * @param  lookFrom  vector looking from 
   * @param  lookAt    vector looking at
   * @param  vup       vertical distance up
   * @param  vfov      angle from vertical onto scene
   * @param  aspect    ascept ratio
   * @param  aperture  aperture (contros amount of light)
   * @param  focusDist distance to focus
   * @param  t0        start time
   * @param  t1        end time
   * @return           [description]
   */
  public Camera(Vector lookFrom, Vector lookAt, Vector vup, double vfov, double aspect, double aperture, double focusDist, double t0, double t1) {
    lensRadius = aperture / 2; 
    time0 = t0; 
    time1 = t1; 

    double theta = vfov * Math.PI / 180;
    double halfHeight = Math.tan(theta/2);
    double halfWidth = halfHeight * aspect;

    origin = lookFrom; 
    w = (lookFrom.subtract(lookAt)).unitVector(); 
    u = (vup.cross(w)).unitVector();
    v = w.cross(u);

    lowerLeft = origin.subtract(u.multiply(halfWidth).multiply(focusDist)).subtract(v.multiply(halfHeight).multiply(focusDist)).subtract(w.multiply(focusDist));
    horizontal = u.multiply(2 * halfWidth).multiply(focusDist);
    vertical = v.multiply(2 * halfHeight).multiply(focusDist); 
  }

  /**
   * @param  s point 1
   * @param  t point 2
   * @return   ray at that point
   */
  public Ray getRay(double s, double t) {
    Vector rd = randomDiskUnit().multiply(lensRadius);
    Vector offset = u.multiply(rd.x()).add(v.multiply(rd.y()));
    double time = time0 + Math.random() * (time1 - time0);
    return new Ray(origin.add(offset), lowerLeft.add(horizontal.multiply(s)).add(vertical.multiply(t)).subtract(origin).subtract(offset), time);
  }

  /**
   * @return   Randomized ray vector from disk 
   */
  public static Vector randomDiskUnit() {
    Vector p; 
    do {
      p = new Vector(Math.random(), Math.random(), 0).multiply(2).subtract(new Vector(1, 1, 0));
    } while(p.dot(p) >= 1.0);
    return p;
  }
}