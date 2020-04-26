/** 2D rectangle on the XY Plane
 * @author Carol Chen
*/

import java.lang.Math; 

public class XYRect extends Form {
  private double x0; 
  private double x1; 
  private double y1; 
  private double y0; 
  private double k; 
  private Material material; 

  /**
   * @param  _x0 first x coord
   * @param  _x1 second x coord
   * @param  _y0 first y coord
   * @param  _y1 second y coord
   * @param  _k  the third axis variable
   * @param  mat material
   */
  public XYRect(double _x0, double _x1, double _y0, double _y1, double _k, Material mat){
    x0 = _x0;
    x1 = _x1;
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
    double t = (k - r.origin().z()) / r.direction().z();
    if (t < minT || t > maxT) { 
      return false;
    }

    double x = r.origin().x() + t * r.direction().x();
    double y = r.origin().y() + t * r.direction().y();
    if (x < x0 || x > x1 || y < y0 || y > y1) { 
      return false;
    }

    hit.u = (x - x0) / (x1 - x0);
    hit.v = (y - y0) / (y1 - y0);
    hit.t = t;
    hit.mat = material;
    hit.p = r.pointAt(t);
    hit.normal = new Vector(0, 0, 1);
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
    box.set(new AABB(new Vector(x0, y0, k - 0.0001), new Vector(x1, y1, k + 0.0001)));
    return true;
  }
}