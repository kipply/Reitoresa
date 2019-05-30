import java.lang.Math; 

public class Vector {
  private double[] vec = new double[3]; 

  Vector (double i, double j, double k) {
    vec[0] = i; 
    vec[1] = j; 
    vec[2] = k; 
  }

  public double x() {
    return vec[0]; 
  }

  public double r() {
    return x(); 
  }

  public double y() {
    return vec[1]; 
  }

  public double g() {
    return y(); 
  }

  public double z() {
    return vec[2]; 
  }

  public double b() {
    return z(); 
  }


  public Vector add(Vector v) {
    return new Vector(vec[0] + v.x(), vec[1] + v.y(), vec[2] + v.z()); 
  }

  public Vector addEquals(Vector v) {
    update(add(v));
    return this; 
  }

  public Vector subtract(Vector v) {
    return new Vector(vec[0] - v.x(), vec[1] - v.y(), vec[2] - v.z()); 
  }

  public Vector subtractEquals(Vector v) {
    update(subtract(v));
    return this; 
  }

  public Vector multiply(Vector v) {
    return new Vector(vec[0] * v.x(), vec[1] * v.y(), vec[2] * v.z()); 
  }

  public Vector multiplyEquals(Vector v) {
    update(multiply(v));
    return this; 
  }

  public Vector multiply(double t) {
    return new Vector(vec[0] * t, vec[1] * t, vec[2] * t); 
  }

  public Vector multiplyEquals(double t) {
    update(multiply(t));
    return this; 
  }

  public Vector divide(Vector v) {
    return new Vector(vec[0] / v.x(), vec[1] / v.y(), vec[2] / v.z()); 
  }

  public Vector divideEquals(Vector v) {
    update(divide(v));
    return this; 
  }

  public Vector divide(double t) {
    return new Vector(vec[0] / t, vec[1] / t, vec[2] / t); 
  }

  public Vector divideEquals(double t) {
    update(divide(t));
    return this; 
  }

  public double dot(Vector v) {
    return vec[0] * v.x() + vec[1] * v.y() + vec[2] * v.z();
  }

  public Vector cross(Vector v) {
    double x = vec[1] * v.z() - vec[2] * v.y();
    double y = vec[0] * v.z() - vec[2] * v.x();
    double z = vec[0] * v.y() - vec[1] * v.x();
    return new Vector(x, y, z);
  }

  public double length() {
    return Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1] + vec[2] * vec[2]);
  }

  public Vector unitVector() {
    double k = 1.0 / length();
    return new Vector(vec[0] * k, vec[1] * k, vec[2] * k); 
  }

  public Vector makeUnitVector() {
    update(unitVector()); 
    return this; 
  }

  public void print() {
    System.out.println("< " + vec[0] + " " + vec[1] + " " + vec[2] + " >");
  }

  private void update(Vector v) {
    vec[0] = v.x(); 
    vec[1] = v.y(); 
    vec[2] = v.z(); 
  }
}