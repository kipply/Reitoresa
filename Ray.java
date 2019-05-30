public class Ray {
  Vector A; 
  Vector B; 
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
}