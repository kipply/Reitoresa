/** Single coloured texture
 * @author Carol Chen
*/

public class SolidTexture extends Texture  {

  private Vector colour; 

  /**
   * @param  c colour of solid texture (vector)
   */
  public SolidTexture(Vector c) {
    colour = c;
  }

  /**
   * @param  u "x" value
   * @param  v "y" value
   * @param  p vector hitting the texture
   */
  public Vector value(double u, double v, Vector p) {
    double sines = Math.sin(10 * p.x()) * Math.sin(10 * p.y()) * Math.sin(10 * p.z());
    return colour; 
  }

}
