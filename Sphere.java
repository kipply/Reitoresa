import java.lang.Math; 

public class Sphere extends Form {
  Vector center; 
  double radius; 
  Material material;

  Sphere(Vector c, double r, Material m) {
    center = c; 
    radius = r; 
    material = m; 
  }

  public boolean hit(Ray r, double minT, double maxT, FormHit hit) {
    Vector oc = r.origin().subtract(center);
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
        hit.normal = (hit.p.subtract(center)).divide(radius); 
        getSphereUv(hit.normal, hit);
        return true; 
      }
      temp = (-b + Math.sqrt(discriminant)) / a;
      if (temp < maxT && temp > minT) {
        hit.t = temp; 
        hit.p = r.pointAt(temp); 
        hit.mat = material; 
        hit.normal = (hit.p.subtract(center)).divide(radius); 
        getSphereUv(hit.normal, hit);
        return true; 
      }
    }
    return false; 
  }

  public boolean boundingBox(double t0, double t1, AABB box) {
    box.set(new AABB(center.subtract(new Vector(radius, radius, radius)), center.add(new Vector(radius, radius, radius))));
    return true;
  }

  private void getSphereUv(Vector p, FormHit hit){
    double phi = Math.atan2(p.z(), p.x());
    double theta = Math.asin(p.y());
    hit.u = 1 - (phi + Math.PI) / (2 * Math.PI);
    hit.v = (theta + Math.PI / 2) / Math.PI;
  }
}