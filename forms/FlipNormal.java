/** Flip the normal of a surface
 * @author Carol Chen
*/
public class FlipNormal extends Form{
  private Form ptr;

  /**
   * @param  p form to flip normal of
   */
  public FlipNormal(Form p) {
    ptr = p;
  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit){
    if (ptr.hit(r, minT, maxT, hit)) {
      hit.normal = hit.normal.multiply(-1);
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
    return ptr.boundingBox(t0, t1, box);
  }
}