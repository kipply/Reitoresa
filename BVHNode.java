import java.util.Arrays;

/** Bounding Volume heirarchy node for faster tracing
 * @author Carol Chen
*/
public class BVHNode extends Scene {
  private AABB box;
  private Form left, right;
  private int lower, upper, axis;
  private int bmode = 0;

  public BVHNode() {}

  /** 
   * @param  l     list of forms
   * @param  n     number of forms
   * @param  time0 start time
   * @param  time1 end time
   */
  public BVHNode(Form[] l, int n, double time0, double time1) {
    int axis = (int)(3.0 * Math.random()); 
    if (axis == 0) {
      Arrays.sort(l, 0, n, new SortBoxX());
    } else if(axis == 1) {
      Arrays.sort(l, 0, n, new SortBoxY());
    } else {
      Arrays.sort(l, 0, n, new SortBoxZ());
    }

    if (n == 1) {
      left = l[0]; 
      right = l[0]; 
    } else if (n == 2) {
      left = l[0]; 
      right = l[1]; 
    } else {
      left = new BVHNode(l, n / 2, time0, time1); 
      right = new BVHNode(Arrays.copyOfRange(l, n / 2, n), n - n / 2, time0, time1);
    }

    AABB boxLeft = new AABB(); 
    AABB boxRight = new AABB(); 

    if(!left.boundingBox(time0, time1, boxLeft) || !right.boundingBox(time0, time1, boxRight)){
      throw new java.lang.RuntimeException("no bounding box");
    }
    box = Utils.surroundingBox(boxLeft, boxRight);

  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT, FormHit hit){
    if (box.hit(r, minT, maxT)) { 
      FormHit leftHit = new FormHit();
      FormHit rightHit = new FormHit();
      boolean hitLeft = left.hit(r, minT, maxT, leftHit);
      boolean hitRight = right.hit(r, minT, maxT, rightHit);
      if (hitLeft && hitRight) {
        if (leftHit.t < rightHit.t) {
          hit.set(leftHit);
        } else {
          hit.set(rightHit);
        }
        return true;
      } else if (hitLeft) {
        hit.set(leftHit);
        return true;
      } else if(hitRight){
        hit.set(rightHit);
        return true;
      } else {
        return false;
      }
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
  public boolean boundingBox(double t0, double t1, AABB b){
    b.set(box);
    return true;
  }
}
