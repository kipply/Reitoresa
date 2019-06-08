public class Lambertian extends Material{
  private Texture albedo; 

  Lambertian(Texture a) {
    albedo = a; 
  }
  public boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered) {
    Vector target = hit.p.add((hit.normal).add(Utils.randomSpherePoint()));
    scattered.set(new Ray(hit.p, target.subtract(hit.p), rayIn.time()));
    attenuation.set(albedo.value(hit.u, hit.v, hit.p)); 
    return true;
  }
}