public class DiffuseLight extends Material{
  private Texture emit; 

  DiffuseLight(Texture a) {
    emit = a;
  }

  public boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered) {
    return false; 
  }

  public Vector emitted(double u, double v, Vector p){
    return emit.value(u, v, p);
  }
}