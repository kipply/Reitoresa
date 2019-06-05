import java.lang.Math;

public class AABB {
  Vector mi; 
  Vector ma; 

  public AABB () {}
  public AABB (Vector a, Vector b) {
    mi = a; 
    ma = b; 
  }

  public Vector min() {
    return mi; 
  }

  public Vector max() {
    return ma; 
  }  

  public void set(AABB box) {
    mi = box.min(); 
    ma = box.max();
  }

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