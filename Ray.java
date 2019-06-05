public class Ray {
  Vector A; 
  Vector B; 
  double time; 

  Ray () { }

  Ray (Vector a, Vector b) {
    A = a; 
    B = b; 
  }

  Ray (Vector a, Vector b, double t) {
    A = a; 
    B = b; 
    time = t; 
  }

  public Vector origin() {
    return A; 
  } 

  public Vector direction() {
    return B; 
  }

  public double time() {
    return time; 
  }

  public Vector pointAt(double t) {
    return A.add(B.multiply(t)); 
  }

  public void set(Ray r) {
    A = r.A; 
    B = r.B;
    time = r.time();
  }
}