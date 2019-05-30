public abstract class Material {
  public abstract boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered);
}