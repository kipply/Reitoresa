/** Class to manage the material of a form
 * @author Carol Chen
*/
public abstract class Material {
  /**
   * @param  rayIn       ray hitting the material
   * @param  hit         information about the hit
   * @param  attenuation attenuation of ray
   * @param  scattered   variable to put scattered ray in
   * @return             if scattering occurs
   */
  public abstract boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered);

  /**
   * @param  v         Incoming vector
   * @param  n         normal of surface
   * @param  niOverNt  value of ni over nt
   * @param  refracted refracted vector to update
   * @return           if refraction occurs
   */
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

  /**
   * @param  v vector
   * @param  n normal of surface
   * @return   Vector reflected
   */
  public Vector reflect(Vector v, Vector n) {
    return v.subtract(n.multiply(2 * v.dot(n)));
  }

  /**
   * @param  u coordinate 
   * @param  v other coordinate
   * @param  p angle
   * @return   returns emitted light
   */
  public Vector emitted(double u, double v, Vector p) {
    return new Vector(0, 0, 0);
  }
}