import java.util.Arrays;

public class BVHNode extends Scene {
  public static final int CENTROID = 0;
  public static final int SAH = 1;

  AABB box;
  Form left, right;
  int lower, upper, axis;
  int bmode = 0;

  public BVHNode() {}

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

  public boolean boundingBox(double t0, double t1, AABB b){
    b.set(box);
    return true;
  }
}
