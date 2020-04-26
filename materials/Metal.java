/** Class to represent a metal
 * @author Carol Chen
*/
public class Metal extends Material {
  private Vector albedo; 

  /**
   * @param  a colour
   */
  public Metal(Vector a) {
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
    Vector reflected = super.reflect(rayIn.direction().unitVector(), hit.normal); 
    scattered.set(new Ray(hit.p, reflected));
    attenuation.set(albedo);
    return scattered.direction().dot(hit.normal) > 0;
  }

}