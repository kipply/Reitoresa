import java.lang.Math; 

/** Vector class 
 * @author Carol Chen
*/
public class Vector {
  private double[] vec = new double[3];

  /*
  Empty Constructor
   */
  public Vector () { }

  /**
   * @param  i x val
   * @param  j y val
   * @param  k z val
   */
  public Vector (double i, double j, double k) {
    vec[0] = i; 
    vec[1] = j; 
    vec[2] = k; 
  }

  /**
   * @return value of x
   */
  public double x() {
    return vec[0]; 
  }

  /**
   * @return value of red (0 - 1)
   */
  public double r() {
    return x(); 
  }

  /**
   * @return value of y
   */
  public double y() {
    return vec[1]; 
  }

  /**
   * @return value of green (0 - 1)
   */
  public double g() {
    return y(); 
  }

  /**
   * @return value of z
   */
  public double z() {
    return vec[2]; 
  }

  /**
   * @return value of blue (0 - 1)
   */
  public double b() {
    return z(); 
  }

  /**
   * @param n index of value to get
   * @return return value of the index
   */
  public double get(int n) {
    return vec[n];
  }

  /**
   * @param n index of value to update
   * @param v value to set that index to
   */
  public void set(int n, double v) {
    vec[n] = v;
  }

  /**
   * @param  v Vector to add
   * @return   returns added vector
   */
  public Vector add(Vector v) {
    return new Vector(vec[0] + v.x(), vec[1] + v.y(), vec[2] + v.z()); 
  }

  /**
   * @param  v Vector to add
   */
  public Vector addEquals(Vector v) {
    set(add(v));
    return this; 
  }

  /**
   * @param  v Vector to subtract
   * @return   returns added vector
   */
  public Vector subtract(Vector v) {
    return new Vector(vec[0] - v.x(), vec[1] - v.y(), vec[2] - v.z()); 
  }

  /**
   * @param  v Vector to subrtact
   */
  public Vector subtractEquals(Vector v) {
    set(subtract(v));
    return this; 
  }

  /**
   * @param  v Vector to multiply
   * @return   returns multiplied vector
   */
  public Vector multiply(Vector v) {
    return new Vector(vec[0] * v.x(), vec[1] * v.y(), vec[2] * v.z()); 
  }

  /**
   * @param  v Vector to multiply
   */
  public Vector multiplyEquals(Vector v) {
    set(multiply(v));
    return this; 
  }

  /**
   * @param  t double to multiply
   * @return   returns multpilied vector
   */
  public Vector multiply(double t) {
    return new Vector(vec[0] * t, vec[1] * t, vec[2] * t); 
  }

  /**
   * @param  t double to multiply
   */
  public Vector multiplyEquals(double t) {
    set(multiply(t));
    return this; 
  }

  /**
   * @param v vector to divide
   * @return   returns divided vector
   */
  public Vector divide(Vector v) {
    return new Vector(vec[0] / v.x(), vec[1] / v.y(), vec[2] / v.z()); 
  }

  /**
   * @param v vector to divide
   */
  public Vector divideEquals(Vector v) {
    set(divide(v));
    return this; 
  }

  /**
   * @param v double to divide
   * @return   returns divided vector
   */
  public Vector divide(double t) {
    return new Vector(vec[0] / t, vec[1] / t, vec[2] / t); 
  }

  /**
   * @param v double to divide
   */
  public Vector divideEquals(double t) {
    set(divide(t));
    return this; 
  }

  /**
   * @param v vector to dot product
   * @return   returns dot product
   */
  public double dot(Vector v) {
    return vec[0] * v.x() + vec[1] * v.y() + vec[2] * v.z();
  }

  /**
   * @param v vector to cross product
   * @return   returns cross product
   */
  public Vector cross(Vector v) {
    double x = vec[1] * v.z() - vec[2] * v.y();
    double y = -(vec[0] * v.z() - vec[2] * v.x());
    double z = vec[0] * v.y() - vec[1] * v.x();
    return new Vector(x, y, z);
  }

  /**
   * @return   length of vector 
   */
  public double length() {
    return Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1] + vec[2] * vec[2]);
  }

  /**
   * @return  unit vector of vector
   */
  public Vector unitVector() {
    double k = 1.0 / length();
    return new Vector(vec[0] * k, vec[1] * k, vec[2] * k); 
  }

  /**
    Makes the vector into a unit vector
    @return the vector
   */
  public Vector makeUnitVector() {
    set(unitVector()); 
    return this; 
  }


  /**
    @param v the vector to update it to
   */
  public void set(Vector v) {
    vec[0] = v.x(); 
    vec[1] = v.y(); 
    vec[2] = v.z(); 
  }
}