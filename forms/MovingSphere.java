import java.lang.Math; 

/** Sphere with motion blur
 * @author Carol Chen
*/
public class MovingSphere extends Form {
  private Vector center0; 
  private Vector center1; 
  private double time0; 
  private double time1; 
  private double radius; 
  private Material material; 

  /**
   * @param  c0 first center
   * @param  c1 second center
   * @param  t0 start time
   * @param  t1 end time
   * @param  r  radius
   * @param  m  matecial
   */
  public MovingSphere(Vector c0, Vector c1, double t0, double t1, double r, Material m) {
    center0 = c0; 
    center1 = c1; 
    time0 = t0; 
    time1 = t1; 
    radius = r; 
    material = m; 
  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit) {
    Vector oc = r.origin().subtract(center(r.time()));
    double a = r.direction().dot(r.direction());     
    double b = oc.dot(r.direction());
    double c = oc.dot(oc) - (radius * radius); 
    double discriminant = b * b - a * c; 
    if (discriminant > 0) {
      double temp = (-b - Math.sqrt(discriminant)) / a;
      if (temp < maxT && temp > minT) {
        hit.t = temp; 
        hit.mat = material; 
        hit.p = r.pointAt(temp); 
        hit.normal = (hit.p.subtract(center(r.time()))).divide(radius); 
        return true; 
      }
      temp = (-b + Math.sqrt(discriminant)) / a;
      if (temp < maxT && temp > minT) {
        hit.t = temp; 
        hit.p = r.pointAt(temp); 
        hit.mat = material; 
        hit.normal = (hit.p.subtract(center(r.time()))).divide(radius); 
        return true; 
      }
    }
    return false; 
  }

  /**
   * update AABB form representing the bounding bax
   * @param  t0  initial time
   * @param  t1  end time
   * @param  box box to set to bounding box
   * @return     if valid
   */
  public boolean boundingBox(double t0, double t1, AABB box){
    AABB b1 = new AABB(center(t0).subtract(new Vector(radius, radius, radius)), center(t0).add(new Vector(radius, radius, radius))); 
    AABB b2 = new AABB(center(t1).subtract(new Vector(radius, radius, radius)), center(t1).add(new Vector(radius, radius, radius))); 
    box.set(Utils.surroundingBox(b1, b2));
    return true;
  }

  private Vector center(double t) {
    return center0.add((center1.subtract(center0)).multiply((t - time0) / (time1 - time0)));
  }
}