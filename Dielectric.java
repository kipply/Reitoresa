import java.lang.Math; 

public class Dielectric extends Material {
  private double refractiveIndex; 

  Dielectric(double ri) {
    refractiveIndex = ri; 
  }

  public boolean scatter(Ray rayIn, FormHit hit, Vector attenuation, Ray scattered) {
    Vector outwardNormal = new Vector(); 
    Vector reflected = super.reflect(rayIn.direction(), hit.normal); 
    double niOverNt; 
    attenuation.set(new Vector(1, 1, 1));
    Vector refracted = new Vector(); 

    double reflectProb; 
    double cos; 

    if (rayIn.direction().dot(hit.normal) > 0) {
      outwardNormal.set(hit.normal.multiply(-1)); 
      niOverNt = refractiveIndex; 
      cos = refractiveIndex * rayIn.direction().dot(hit.normal) / rayIn.direction().length(); 
      cos = Math.sqrt(1 - refractiveIndex*refractiveIndex*(1 - cos * cos));
    } else {
      outwardNormal.set(hit.normal); 
      niOverNt = 1.0 / refractiveIndex; 
      cos = rayIn.direction().dot(hit.normal) * -1 / rayIn.direction().length();
    }

    if (super.refract(rayIn.direction(), outwardNormal, niOverNt, refracted)) {
      reflectProb = schlick(cos);
    } else {
      reflectProb = 1;
    }

    if (Math.random() < reflectProb) {
      scattered.set(new Ray(hit.p, reflected));
    } else {
      scattered.set(new Ray(hit.p, refracted));
    }
    return true;
  }

  private double schlick(double cos) {
    double r0 = (1.0 - refractiveIndex) / (1.0 + refractiveIndex); 
    r0 = r0 * r0; 
    return r0 + (1.0 - r0) * Math.pow(1 - cos, 5.0);
  }
}