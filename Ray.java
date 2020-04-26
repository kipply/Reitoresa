/** Class to represent a ray
 * @author Carol Chen
*/
public class Ray {
  public Vector A; 
  public Vector B; 
  public double time; 

  /**
   * Empty constructor
   */
  public Ray () { }

  /**
   * @param  a point a
   * @param  b point b
   */
  public Ray (Vector a, Vector b) {
    A = a; 
    B = b; 
  }

  /**
   * @param  a point a
   * @param  b point b
   * @param  t value t (controls "magnitude")
   */
  public Ray (Vector a, Vector b, double t) {
    A = a; 
    B = b; 
    time = t; 
  }

  /**
   * @return origin (point a) of the vector
   */
  public Vector origin() {
    return A; 
  } 

  /**
   * @return direction vector (point b)
   */
  public Vector direction() {
    return B; 
  }

  /**
   * @return get time of ray's existance
   */
  public double time() {
    return time; 
  }

  /**
   * @return get the point at t value
   */
  public Vector pointAt(double t) {
    return A.add(B.multiply(t)); 
  }

  /**
   * @param r Ray to set ray to
   */
  public void set(Ray r) {
    A = r.A; 
    B = r.B;
    time = r.time();
  }
}