import java.lang.Math; 

/** Textures control the colours and patterns on the surface of a form
 * @author Carol Chen
*/
public abstract class Texture {
  /**
   * @param  u "x" value
   * @param  v "y" value
   * @param  p vector hitting the texture
   */
  public abstract Vector value(double u, double v, Vector p); 
}
