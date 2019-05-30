public class Lambertian extends Material{
  private Vector albedo; 

  Lambertian(Vector a) {
    albedo = a; 
  }
  public boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered) {
    Vector target = hit.p.add((hit.normal).add(Utils.randomSpherePoint()));
    scattered.set(new Ray(hit.p, target.subtract(hit.p)));
    attenuation.set(albedo); 
    return true;
  }
}