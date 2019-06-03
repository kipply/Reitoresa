import java.lang.Math;

public class Scene {
  private Form[] formList;

  Scene(Form[] f) {
    formList = f; 
  }

  public boolean hit(Ray r, double minT, double maxT, FormHit hit) {
    FormHit tempHit = new FormHit(); 
    boolean hitSuccess = false; 
    double currClosest = maxT; 
    for (int i = 0; i < formList.length; i++) {
      if (formList[i] == null) { break; }
      if (formList[i].hit(r, minT, currClosest, tempHit)) {
        hitSuccess = true; 
        currClosest = tempHit.t; 
        hit.set(tempHit); 
      }
    }

    return hitSuccess;
  }

  public static Scene randomScene() {
    int n = 500; 
    Form[] forms = new Form[n + 1]; 
    forms[0] = new Sphere(new Vector(0, -1000, 0), 1000, new Lambertian(new Vector(0.5, 0.5, 0.5)));

    int i = 1; 
    for (int a = -11; a < 11; a++) {
      for (int b = -11; b < 11; b++) {
        double materialChoose = Math.random(); 
        Vector center = new Vector(a + 0.9 * Math.random(), 0.2, b + 0.9 * Math.random()); 

        if (center.subtract(new Vector(4, 0.2, 0)).length() > 0.9) {
          if (materialChoose < 0.8) {
            forms[i++] = new Sphere(center, 0.2, new Lambertian(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random())));
          } else if (materialChoose < 0.95) {
            forms[i++] = new Sphere(center, 0.2, new Metal(new Vector(0.5 * (1 + Math.random()), 0.5 * (1 + Math.random()), 0.5 * Math.random())));
          } else {
            forms[i++] = new Sphere(center, 0.2, new Dielectric(1.5));
          }
        }
      }
    }
    return new Scene(forms);
  }
}