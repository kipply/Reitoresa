/** A light emitting material
 * @author Carol Chen
*/
public class DiffuseLight extends Material{
  private Texture emit; 

  /**
   * @param  a texture of light
   */
  public DiffuseLight(Texture a) {
    emit = a;
  }

  /**
   * @param  rayIn       ray hitting the material
   * @param  hit         information about the hit
   * @param  attenuation attenuation of ray
   * @param  scattered   variable to put scattered ray in
   * @return             if scattering occurs
   */
  public boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered) {
    return false; 
  }

  /**
   * @param  u coordinate 
   * @param  v other coordinate
   * @param  p angle
   * @return   returns emitted light
   */
  public Vector emitted(double u, double v, Vector p){
    return emit.value(u, v, p);
  }
}