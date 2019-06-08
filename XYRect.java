import java.lang.Math; 

public class XYRect extends Form {
  double x0; 
  double x1; 
  double y1; 
  double y0; 
  double k; 
  Material material; 

  XYRect(double _x0, double _x1, double _y0, double _y1, double _k, Material mat){
    x0 = _x0;
    x1 = _x1;
    y0 = _y0;
    y1 = _y1;
    k = _k;
    material = mat;
  }

  public boolean hit(Ray r, double minT, double maxT, FormHit hit) {
    double t = (k - r.origin().z()) / r.direction().z();
    if (t < minT || t > maxT) { 
      return false;
    }

    double x = r.origin().x() + t * r.direction().x();
    double y = r.origin().y() + t * r.direction().y();
    if (x < x0 || x > x1 || y < y0 || y > y1) { 
      return false;
    }

    hit.u = (x - x0) / (x1 - x0);
    hit.v = (y - y0) / (y1 - y0);
    hit.t = t;
    hit.mat = material;
    hit.p = r.pointAt(t);
    hit.normal = new Vector(0, 0, 1);
    return true;
  }

  public boolean boundingBox(double t0, double t1, AABB box) {
    box.set(new AABB(new Vector(x0, y0, k - 0.0001), new Vector(x1, y1, k + 0.0001)));
    return true;
  }
}