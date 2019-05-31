import java.lang.Math; 

public class Camera {
  Vector lowerLeft; 
  Vector horizontal; 
  Vector vertical; 
  Vector origin; 

  Camera(Vector lookFrom, Vector lookAt, Vector vup, double vfov, double aspect) {
    double theta = vfov * Math.PI / 180;
    double halfHeight = Math.tan(theta/2);
    double halfWidth = halfHeight * aspect;

    origin = lookFrom; 
    Vector w = (lookFrom.subtract(lookAt)).unitVector(); 
    Vector u = (vup.cross(w)).unitVector();
    Vector v = w.cross(u);

    lowerLeft = origin.subtract(u.multiply(halfWidth)).subtract(v.multiply(halfHeight)).subtract(w);
    horizontal = u.multiply(2 * halfWidth);
    vertical = v.multiply(2 * halfHeight); 
  }

  public Ray getRay(double s, double t) {
    return new Ray(origin, lowerLeft.add(horizontal.multiply(s)).add(vertical.multiply(t)).subtract(origin));
  }
}