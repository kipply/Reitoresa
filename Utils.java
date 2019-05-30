public class Utils {
  public static Vector randomSpherePoint() {
    Vector point; 
    do {
      point = new Vector(Math.random(), Math.random(), Math.random()).multiply(2).subtract(new Vector(1, 1, 1));
    } while (point.length() * point.length() >= 1);
    return point; 
  }
}