/** Represents a rectangle prism
 * @author Carol Chen
*/
public class Box extends Form{
  private Vector pmin, pmax;
  private Scene fullBox;

  /**
   * @param  p0 first point
   * @param  p1 second point
   * @param mat material of box
   */
  public Box(Vector p0, Vector p1, Material mat){
    pmin = p0;
    pmax = p1;
    Form[] list = new Form[6];
    list[0] = new XYRect(p0.x(), p1.x(), p0.y(), p1.y(), p1.z(), mat);
    list[1] = new FlipNormal(new XYRect(p0.x(), p1.x(), p0.y(), p1.y(), p0.z(), mat));
    list[2] = new XZRect(p0.x(), p1.x(), p0.z(), p1.z(), p1.y(), mat);
    list[3] = new FlipNormal(new XZRect(p0.x(), p1.x(), p0.z(), p1.z(), p0.y(), mat));
    list[4] = new YZRect(p0.y(), p1.y(), p0.z(), p1.z(), p1.x(), mat);
    list[5] = new FlipNormal(new YZRect(p0.y(), p1.y(), p0.z(), p1.z(), p0.x(), mat));
    fullBox = new Scene(list);
  }


  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit){
    return fullBox.hit(r, minT, maxT, hit);
  }

  /**
   * update AABB form representing the bounding bax
   * @param  t0  initial time
   * @param  t1  end time
   * @param  box box to set to bounding box
   * @return     if valid
   */
  public boolean boundingBox(double t0, double t1, AABB box){
    box.set(new AABB(pmin, pmax));
    return true;
  }
}
