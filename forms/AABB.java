import java.lang.Math;

/** Represents an axis aligned bounding box
 * @author Carol Chen
*/
public class AABB {
  private Vector mi;
  private Vector ma;

  /**
   * Empty constructor
   */
  public AABB () {}

  /**
   * @param  a First diagonal
   * @param  b second diagonal
   */
  public AABB (Vector a, Vector b) {
    mi = a;
    ma = b;
  }

  /**
   * @return returns minimum Vector in box
   */
  public Vector min() {
    return mi;
  }

  /**
   * @return returns maximum Vector in box
   */
  public Vector max() {
    return ma;
  }

  /**
   * Update the box
   * @param box new box to set
   */
  public void set(AABB box) {
    mi = box.min();
    ma = box.max();
  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
  public boolean hit(Ray r, double minT, double maxT) {
    for (int a = 0; a < 3; a++) {
      double invD = 1 / r.direction().get(a);
      double t0 = mi.get(a) - r.origin().get(a) * invD;
      double t1 = ma.get(a) - r.origin().get(a) * invD;

      if (invD < 0) {
        double temp = t0;
        t0 = t1;
        t1 = temp;
      }


      // double t0 = Math.min((mi.get(a) - r.origin().get(a)) / r.direction().get(a),
      //   (ma.get(a) - r.origin().get(a)) / r.direction().get(a));
      // double t1 = Math.max((mi.get(a) - r.origin().get(a)) / r.direction().get(a),
      //   (ma.get(a) - r.origin().get(a)) / r.direction().get(a));

      minT = Math.max(t0, minT);
      maxT = Math.max(t1, maxT);

      if (maxT <= minT) {
        return false;
      }
    }

    return true;
  }


}
