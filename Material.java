public abstract class Material {
  public abstract boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered);

  public boolean refract(Vector v, Vector n, double niOverNt, Vector refracted) {
    Vector unit = v.unitVector(); 
    double dotProduct = unit.dot(n); 
    double discriminant = 1 - niOverNt * niOverNt * (1 - dotProduct * dotProduct); 
    if (discriminant > 0) {
      Vector unitMinusNTimesDot = unit.subtract(n.multiply(dotProduct));
      Vector nTimesRootDiscriminant = n.multiply(Math.sqrt(discriminant));
      refracted.set(unitMinusNTimesDot.multiply(niOverNt).subtract(nTimesRootDiscriminant));
      return true; 
    } else {
      return false; 
    }
  }

  public Vector reflect(Vector v, Vector n) {
    return v.subtract(n.multiply(2 * v.dot(n)));
  }
}