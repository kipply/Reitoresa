import java.lang.Math;

public class Scene extends Form{
  private Form[] formList;

  Scene() {}
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

  public boolean boundingBox(double t0, double t1, AABB box){
    if(formList.length < 1) return false;
    AABB tempBox = new AABB();
    boolean firstTrue = formList[0].boundingBox(t0, t1, tempBox);
    if (!firstTrue) {
      return false;
    } else {
      box.set(tempBox);
    }
    for (int i = 1; i < formList.length; i++) {
      if (formList[i].boundingBox(t0, t1, tempBox)) {
        box.set(Utils.surroundingBox(box, tempBox));
      } else { 
        return false;
      }
    }
    return true;
  }

  public static Scene randomScene() {
    int n = 500; 
    Form[] forms = new Form[n + 1]; 
    Texture checker = new CheckerTexture(new SolidTexture(new Vector(0.2, 0.2, 0.1)), new SolidTexture(new Vector(0.5, 0.2, 0.1)));

    forms[0] = new Sphere(new Vector(0, -1000, 0), 1000, new Lambertian(checker));

    int i = 1; 
    for (int a = -5; a < 5; a++) {
      for (int b = -5; b < 5; b++) {
        double materialChoose = Math.random(); 
        Vector center = new Vector(a + 0.9 * Math.random(), 0.6 * Math.random(), b + 0.9 * Math.random()); 

        if (center.subtract(new Vector(4, 0.2, 0)).length() > 0.9) {
          if (materialChoose < 0.8) {
            forms[i++] = new MovingSphere(center, center.add(new Vector(0, 0.5 * Math.random(), 0)), 0, 1, 0.2, new Lambertian(new SolidTexture(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random()))));
          } else if (materialChoose < 0.95) {
            forms[i++] = new Sphere(center, center.y(), new Metal(new Vector(0.5 * (1 + Math.random()), 0.5 * (1 + Math.random()), 0.5 * Math.random())));
          } else {
            forms[i++] = new Sphere(center, center.y(), new Dielectric(1.5));
          }
        }
      }
    }
    return new Scene(forms);
  }

  public static BVHNode randomSceneNode() {
    int n = 500; 
    Form[] forms = new Form[n + 1]; 
    Texture checker = new CheckerTexture(new SolidTexture(new Vector(0.2, 0.2, 0.1)), new SolidTexture(new Vector(0.5, 0.2, 0.1)));
    forms[0] = new Sphere(new Vector(0, -1000, 0), 1000, new Lambertian(checker));

    int i = 1; 
    for (int a = -5; a < 5; a++) {
      for (int b = -5; b < 5; b++) {
        double materialChoose = Math.random(); 
        Vector center = new Vector(a + 0.9 * Math.random(), 0.6 * Math.random(), b + 0.9 * Math.random()); 

        if (center.subtract(new Vector(4, 0.2, 0)).length() > 0.9) {
          if (materialChoose < 0.8) {
            forms[i++] = new MovingSphere(center, center.add(new Vector(0, 0.5 * Math.random(), 0)), 0, 1, 0.2, new Lambertian(new SolidTexture(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random()))));
          } else if (materialChoose < 0.95) {
            forms[i++] = new Sphere(center, center.y(), new Metal(new Vector(0.5 * (1 + Math.random()), 0.5 * (1 + Math.random()), 0.5 * Math.random())));
          } else {
            forms[i++] = new Sphere(center, center.y(), new Dielectric(1.5));
          }
        }
      }
    }
    return new BVHNode(forms, i, 0, 1);
  }
}