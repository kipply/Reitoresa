/** Class with general purpose utility functions
 * @author Carol Chen
*/

public class Utils {
  /**
   * @return returns a random point for a sphere
   */
  public static Vector randomSpherePoint() {
    Vector point; 
    do {
      point = new Vector(Math.random(), Math.random(), Math.random()).multiply(2).subtract(new Vector(1, 1, 1));
    } while (point.length() * point.length() >= 1);
    return point; 
  }

  /**
   * @param box0 first box
   * @param box1 second box
   * @return Returns the AABB box of two boxes
   */
  public static AABB surroundingBox(AABB box0, AABB box1){
    Vector small = new Vector(Math.min(box0.min().x(), box1.min().x()),
              Math.min(box0.min().y(), box1.min().y()),
              Math.min(box0.min().z(), box1.min().z()));
    Vector big = new Vector(Math.max(box0.max().x(), box1.max().x()),
              Math.max(box0.max().y(), box1.max().y()),
              Math.max(box0.max().z(), box1.max().z()));
    return new AABB(small, big);
  }
}