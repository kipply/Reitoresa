public class Ray {
  Vector A; 
  Vector B; 

  Ray () { }

  Ray (Vector a, Vector b) {
    A = a; 
    B = b; 
  }

  public Vector origin() {
    return A; 
  } 

  public Vector direction() {
    return B; 
  }

  public Vector pointAt(double t) {
    return A.add(B.multiply(t)); 
  }

  public void set(Ray r) {
    A = r.A; 
    B = r.B;
  }
}