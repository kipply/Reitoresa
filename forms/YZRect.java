/** 2D rectangle on the YZ Plane
 * @author Carol Chen
*/
import java.lang.Math; 

public class YZRect extends Form {
  private double z0; 
  private double z1; 
  private double y1; 
  private double y0; 
  private double k; 
  private Material material; 

  /**
   * @param  _y0 first y coord
   * @param  _y1 second y coord
   * @param  _z0 first z coord
   * @param  _z1 second z coord
   * @param  _k  the third axis variable
   * @param  mat material
   */
  public YZRect(double _y0, double _y1, double _z0, double _z1, double _k, Material mat){
    z0 = _z0;
    z1 = _z1;
    y0 = _y0;
    y1 = _y1;
    k = _k;
    material = mat;
  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit) {
    double t = (k - r.origin().x()) / r.direction().x();
    if (t < minT || t > maxT) { 
      return false;
    }

    double y = r.origin().y() + t * r.direction().y();
    double z = r.origin().z() + t * r.direction().z();
    if (z < z0 || z > z1 || y < y0 || y > y1) { 
      return false;
    }

    hit.u = (y - y0) / (y1 - y0);
    hit.v = (z - z0) / (z1 - z0);
    hit.t = t;
    hit.mat = material;
    hit.p = r.pointAt(t);
    hit.normal = new Vector(1, 0, 0);
    return true;
  }
  
  /**
   * update AABB form representing the bounding bax
   * @param  t0  initial time
   * @param  t1  end time
   * @param  box box to set to bounding box
   * @return     if valid
   */
  public boolean boundingBox(double t0, double t1, AABB box) {
    box.set(new AABB(new Vector(k - 0.0001, y0, z0), new Vector(k + 0.0001, y1, z1)));
    return true;
  }
}