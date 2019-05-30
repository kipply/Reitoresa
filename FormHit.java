public class FormHit {
  public double t; 
  public Vector p; 
  public Vector normal; 
  public Material mat; 

  public void set(FormHit fh){
    t = fh.t;
    p = fh.p;
    normal = fh.normal;
    mat = fh.mat;
  }

}