public class Sphere extends Form {
  Vector center; 
  double radius; 

  Sphere(Vector c, double r) {
    center = c; 
    radius = r;
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
        hit.p = r.pointAt(temp); 
        hit.normal = (hit.p.subtract(center)).divide(radius); 
        return true; 
      }
      temp = (-b + Math.sqrt(discriminant)) / a;
      if (temp < maxT && temp > minT) {
        hit.t = temp; 
        hit.p = r.pointAt(temp); 
        hit.normal = (hit.p.subtract(center)).divide(radius); 
        return true; 
      }
    }
    return false; 
  }
}