/** Checkered texture with two textures
 * @author Carol Chen
*/
public class CheckerTexture extends Texture {
  private Texture odd; 
  private Texture even; 
  private Vector colour; 

  /**
   * @param  t0 first texture
   * @param  t1 second texture
   */
  public CheckerTexture(Texture t0, Texture t1){
    even = t0;
    odd = t1;
  }

  /**
   * @param  u "x" value
   * @param  v "y" value
   * @param  p vector hitting the texture
   */
  public Vector value(double u, double v, Vector p) {
    double sines = Math.sin(10 * p.x()) * Math.sin(10 * p.y()) * Math.sin(10 * p.z());
    if (sines < 0) {
      return odd.value(u, v, p); 
    } else {
      return even.value(u, v, p);
    }
  }
}
