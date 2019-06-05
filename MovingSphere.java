import java.lang.Math; 

public class MovingSphere extends Form {
  Vector center0; 
  Vector center1; 
  double time0; 
  double time1; 
  double radius; 
  Material material; 

  MovingSphere(Vector c0, Vector c1, double t0, double t1, double r, Material m) {
    center0 = c0; 
    center1 = c1; 
    time0 = t0; 
    time1 = t1; 
    radius = r; 
    material = m; 
  }

  public boolean hit(Ray r, double minT, double maxT, FormHit hit) {
    Vector oc = r.origin().subtract(center(r.time()));
    double a = r.direction().dot(r.direction());     
    double b = oc.dot(r.direction());
    double c = oc.dot(oc) - (radius * radius); 
    double discriminant = b * b - a * c; 
    if (discriminant > 0) {
      double temp = (-b - Math.sqrt(discriminant)) / a;
      if (temp < maxT && temp > minT) {
        hit.t = temp; 
        hit.mat = material; 
        hit.p = r.pointAt(temp); 
        hit.normal = (hit.p.subtract(center(r.time()))).divide(radius); 
        return true; 
      }
      temp = (-b + Math.sqrt(discriminant)) / a;
      if (temp < maxT && temp > minT) {
        hit.t = temp; 
        hit.p = r.pointAt(temp); 
        hit.mat = material; 
        hit.normal = (hit.p.subtract(center(r.time()))).divide(radius); 
        return true; 
      }
    }
    return false; 
  }

  public boolean boundingBox(double t0, double t1, AABB box){
    AABB b1 = new AABB(center(t0).subtract(new Vector(radius, radius, radius)), center(t0).add(new Vector(radius, radius, radius))); 
    AABB b2 = new AABB(center(t1).subtract(new Vector(radius, radius, radius)), center(t1).add(new Vector(radius, radius, radius))); 
    box.set(Utils.surroundingBox(b1, b2));
    return true;
  }

  private Vector center(double t) {
    return center0.add((center1.subtract(center0)).multiply((t - time0) / (time1 - time0)));
  }
}