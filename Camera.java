import java.lang.Math; 

public class Camera {
  Vector lowerLeft; 
  Vector horizontal; 
  Vector vertical; 
  Vector origin; 
  Vector u; 
  Vector w; 
  Vector v;
  double lensRadius;
  double time0; 
  double time1; 

  Camera(Vector lookFrom, Vector lookAt, Vector vup, double vfov, double aspect, double aperture, double focusDist, double t0, double t1) {
    lensRadius = aperture / 2; 
    time0 = t0; 
    time1 = t1; 

    double theta = vfov * Math.PI / 180;
    double halfHeight = Math.tan(theta/2);
    double halfWidth = halfHeight * aspect;

    origin = lookFrom; 
    w = (lookFrom.subtract(lookAt)).unitVector(); 
    u = (vup.cross(w)).unitVector();
    v = w.cross(u);

    lowerLeft = origin.subtract(u.multiply(halfWidth).multiply(focusDist)).subtract(v.multiply(halfHeight).multiply(focusDist)).subtract(w.multiply(focusDist));
    horizontal = u.multiply(2 * halfWidth).multiply(focusDist);
    vertical = v.multiply(2 * halfHeight).multiply(focusDist); 
  }

  public Ray getRay(double s, double t) {
    Vector rd = randomDiskUnit().multiply(lensRadius);
    Vector offset = u.multiply(rd.x()).add(v.multiply(rd.y()));
    double time = time0 + Math.random() * (time1 - time0);
    return new Ray(origin.add(offset), lowerLeft.add(horizontal.multiply(s)).add(vertical.multiply(t)).subtract(origin).subtract(offset), time);
  }

  public static Vector randomDiskUnit() {
    Vector p; 
    do {
      p = new Vector(Math.random(), Math.random(), 0).multiply(2).subtract(new Vector(1, 1, 0));
    } while(p.dot(p) >= 1.0);
    return p;
  }
}