/** A class to rotate a form on the Y axis
 * @author Carol Chen
*/
public class RotateY extends Form{
  Form ptr;
  double sinT, cosT;
  boolean hasbox;
  AABB bbox = new AABB();

  public RotateY(Form p, double angle){
    ptr = p;
    double radians = Math.PI * angle / 180;
    sinT = Math.sin(radians);
    cosT = Math.cos(radians);
    hasbox = ptr.boundingBox(0, 1, bbox);
    Vector min = new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    Vector max = new Vector(-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          double x = i * bbox.max().x() + (1 - i) * bbox.min().x();
          double y = j * bbox.max().y() + (1 - j) * bbox.min().y();
          double z = k * bbox.max().z() + (1 - k) * bbox.min().z();
          double newx = cosT * x + sinT * z; 
          double newz = -sinT * x + cosT * z;
          Vector tester = new Vector(newx, y, newz);
          for (int c = 0; c < 3; c++) { 
            if (tester.get(c) > max.get(c)) {
              max.set(c, tester.get(c));
            }
            if(tester.get(c) < min.get(c)){
              min.set(c, tester.get(c));
            }
          }
        }
      }
    }
    bbox.set(new AABB(min, max));
  }


  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit){
    Vector origin = new Vector();
    origin.set(r.origin());
    Vector direction = new Vector();
    direction.set(r.direction());

    origin.set(0, cosT * r.origin().get(0) - sinT * r.origin().get(2)); //rotate
    origin.set(2, sinT * r.origin().get(0) + cosT * r.origin().get(2));
    direction.set(0, cosT * r.direction().get(0) - sinT * r.direction().get(2));
    direction.set(2, sinT * r.direction().get(0) + cosT * r.direction().get(2));
    Ray rotated_r = new Ray(origin, direction, r.time());
    if (ptr.hit(rotated_r, minT, maxT, hit)) {
      Vector p = new Vector();
      p.set(hit.p);
      Vector normal = new Vector();
      normal.set(hit.normal);

      p.set(0, cosT * hit.p.get(0) + sinT * hit.p.get(2)); //unrotate
      p.set(2, -sinT * hit.p.get(0) + cosT * hit.p.get(2));
      normal.set(0, cosT * hit.normal.get(0) + sinT * hit.normal.get(2));
      normal.set(2, -sinT * hit.normal.get(0) + cosT * hit.normal.get(2));
      hit.p = p;
      hit.normal = normal;
      return true; 
    } else {
      return false;
    }
  }

  /**
   * update AABB form representing the bounding bax
   * @param  t0  initial time
   * @param  t1  end time
   * @param  box box to set to bounding box
   * @return     if valid
   */
  public boolean boundingBox(double t0, double t1, AABB box){
    box.set(bbox);
    return hasbox;
  }
}
