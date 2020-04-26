/** 2D rectangle on the XZ Plane
 * @author Carol Chen
*/

import java.lang.Math; 

public class XZRect extends Form {
  private double z0; 
  private double z1; 
  private double x1; 
  private double x0; 
  private double k; 
  private  Material material; 

  /**
   * @param  _x0 first x coord
   * @param  _x1 second x coord
   * @param  _z0 first z coord
   * @param  _z1 second z coord
   * @param  _k  the third axis variable
   * @param  mat material
   */
  public XZRect(double _x0, double _x1, double _z0, double _z1, double _k, Material mat){
    z0 = _z0;
    z1 = _z1;
    x0 = _x0;
    x1 = _x1;
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
    double t = (k - r.origin().y()) / r.direction().y();
    if (t < minT || t > maxT) { 
      return false;
    }

    double x = r.origin().x() + t * r.direction().x();
    double z = r.origin().z() + t * r.direction().z();
    if (z < z0 || z > z1 || x < x0 || x > x1) { 
      return false;
    }

    hit.u = (x - x0) / (x1 - x0);
    hit.v = (z - z0) / (z1 - z0);
    hit.t = t;
    hit.mat = material;
    hit.p = r.pointAt(t);
    hit.normal = new Vector(0, 1, 0);
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
    box.set(new AABB(new Vector(x0, k - 0.0001, z0), new Vector(x1, k + 0.0001, z1)));
    return true;
  }
}