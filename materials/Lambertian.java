/** Represents a matte surface
 * @author Carol Chen
*/
public class Lambertian extends Material{
  private Texture albedo; 

  /**
   * @param  a texture of surface
   */
  public Lambertian(Texture a) {
    albedo = a; 
  }

  /**
   * @param  rayIn       ray hitting the material
   * @param  hit         information about the hit
   * @param  attenuation attenuation of ray
   * @param  scattered   variable to put scattered ray in
   * @return             if scattering occurs
   */
  public boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered) {
    Vector target = hit.p.add((hit.normal).add(Utils.randomSpherePoint()));
    scattered.set(new Ray(hit.p, target.subtract(hit.p), rayIn.time()));
    attenuation.set(albedo.value(hit.u, hit.v, hit.p)); 
    return true;
  }
}