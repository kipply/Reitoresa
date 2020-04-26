/** Translates a form
 * @author Carol Chen
*/

class Translate extends Form{
  private Form ptr;
  private Vector offset;

  /**
   * @param  p            form
   * @param  displacement vector to displace by
   */
  public Translate(Form p, Vector displacement){
    ptr = p;
    offset = displacement;
  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit){
    Ray moved_r = new Ray(r.origin().subtract(offset), r.direction(), r.time()); //translate to origin
    if(ptr.hit(moved_r, minT, maxT, hit)){
      hit.p = hit.p.add(offset); //translate back
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
    if (ptr.boundingBox(t0, t1, box)) {
      box.set(new AABB(box.min().add(offset), box.max().add(offset)));
      return true;
    } else {
      return false;
    }
  }
}

