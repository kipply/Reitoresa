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
}