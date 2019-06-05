import java.lang.Math; 

public abstract class Texture {
  public abstract Vector value(double u, double v, Vector p); 
}

class SolidTexture extends Texture  {

  Vector colour; 

  SolidTexture(Vector c) {
    colour = c;
  }

  public Vector value(double u, double v, Vector p) {
    double sines = Math.sin(10 * p.x()) * Math.sin(10 * p.y()) * Math.sin(10 * p.z());
    return colour; 
  }

}

class CheckerTexture extends Texture {
  Texture odd; 
  Texture even; 
  Vector colour; 

  public CheckerTexture(Texture t0, Texture t1){
    even = t0;
    odd = t1;
  }

  public Vector value(double u, double v, Vector p) {
    double sines = Math.sin(10 * p.x()) * Math.sin(10 * p.y()) * Math.sin(10 * p.z());
    if (sines < 0) {
      return odd.value(u, v, p); 
    } else {
      return even.value(u, v, p);
    }
  }
}