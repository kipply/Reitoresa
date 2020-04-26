/** Class to store data involved with a form being hit
 * @author Carol Chen
*/

public class FormHit {
  public double t; 
  public Vector p; 
  public Vector normal; 
  public Material mat; 
  public double u, v;

  /**
   * @param fh Update object
   */
  public void set(FormHit fh){
    t = fh.t;
    p = fh.p;
    normal = fh.normal;
    mat = fh.mat;
    u = fh.u; 
    v = fh.v;
  }

}