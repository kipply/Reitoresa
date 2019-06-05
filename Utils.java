public class Utils {
  public static Vector randomSpherePoint() {
    Vector point; 
    do {
      point = new Vector(Math.random(), Math.random(), Math.random()).multiply(2).subtract(new Vector(1, 1, 1));
    } while (point.length() * point.length() >= 1);
    return point; 
  }

  public static double sqrt(double n) {
    double half = 0.5d * n;
    long i = Double.doubleToLongBits(n);
    i = 0x5fe6ec85e7de30daL - (i >> 1);
    n = Double.longBitsToDouble(i);
    n *= (1.5d - n * n * n);
    return n;
  }

  public static AABB surroundingBox(AABB box0, AABB box1){
    Vector small = new Vector(Math.min(box0.min().x(), box1.min().x()),
              Math.min(box0.min().y(), box1.min().y()),
              Math.min(box0.min().z(), box1.min().z()));
    Vector big = new Vector(Math.max(box0.max().x(), box1.max().x()),
              Math.max(box0.max().y(), box1.max().y()),
              Math.max(box0.max().z(), box1.max().z()));
    return new AABB(small,big);
  }
}