public class Camera {
  Vector lowerLeft; 
  Vector horizontal; 
  Vector vertical; 
  Vector origin; 

  Camera() {
    origin = new Vector(0, 0, 0); 
    vertical = new Vector(0, 2, 0); 
    horizontal = new Vector(4, 0, 0);
    lowerLeft = new Vector(-2, -1, -1);
  }

  public Ray getRay(double u, double v) {
    return new Ray(origin, lowerLeft.add(horizontal.multiply(u)).add(vertical.multiply(v)));
  }
}