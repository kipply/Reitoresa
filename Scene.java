import java.lang.Math;

/** A scene contains multiple forms
 * @author Carol Chen
*/
public class Scene extends Form{
  private Form[] formList;

  public Scene() {}
  public Scene(Form[] f) {
    formList = f; 
  }

  /**
   * Check if hit
   * @param  r    incoming ray
   * @param  minT max t value (provides wiggle room for angle)
   * @param  maxT min t value
   * @return      true if hit
   */
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

  /**
   * update AABB form representing the bounding bax
   * @param  t0  initial time
   * @param  t1  end time
   * @param  box box to set to bounding box
   * @return     if valid
   */
  public boolean boundingBox(double t0, double t1, AABB box){
    if (formList.length < 1) return false;
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

  /**
   * @return A random scene with varying spheres of different colours and materials
   */
  public static Scene randomSphereScene() {
    int n = 500; 
    Form[] forms = new Form[n + 1]; 
    Texture image1 = new ImageTexture("/images/earth.jpg");
    Texture image2 = new ImageTexture("/images/moon.jpg");
    Texture checker = new CheckerTexture(new SolidTexture(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random())), new SolidTexture(new Vector(1, 1, 1)));
    forms[0] = new Sphere(new Vector(0, -1000, 0), 1000, new Lambertian(checker));

    int i = 1; 
    for (int a = -5; a < 5; a++) {
      for (int b = -5; b < 5; b++) {
        double materialChoose = Math.random(); 
        Vector center = new Vector(a + 0.9 * Math.random(), 0.6 * Math.random(), b + 0.9 * Math.random()); 

        if (center.subtract(new Vector(4, 0.2, 0)).length() > 0.9) {
          if (materialChoose < 0.5) {
            forms[i++] = new Sphere(center, center.y(), new Lambertian(new SolidTexture(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random()))));
          } else if (materialChoose < 0.6) {
            forms[i++] = new MovingSphere(center, center.add(new Vector(0, 0.5 * Math.random(), 0)), 0, 1, 0.2, new Lambertian(new SolidTexture(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random()))));
          } else if (materialChoose < 0.7) {
            forms[i++] = new Sphere(center, center.y(), new Lambertian(image1));
          } else if (materialChoose < 0.8) {
            forms[i++] = new Sphere(center, center.y(), new Lambertian(image2));
          } else if (materialChoose < 0.9) {
            forms[i++] = new Sphere(center, center.y(), new Metal(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random())));
          } else {
            forms[i++] = new Sphere(center, center.y(), new Dielectric(1.5));
          }
        }
      }
    }
    return new Scene(forms);
  }

  /**
   * @return Returns a scene with a sphere and lights
   */
  public static Scene lightBallScene() {
    Form[] forms = new Form[5]; 
    forms[0] = new Sphere(new Vector(0, -1000, 0), 1000, new Lambertian(new SolidTexture(new Vector(0.2, 0.9, 0.9))));
    forms[1] = new Sphere(new Vector(0, 2, 0), 2, new Lambertian(new SolidTexture(new Vector(0.2, 0.2, 0.5))));
    forms[2] = new Sphere(new Vector(0, 7, 0), 2, new DiffuseLight(new SolidTexture(new Vector(3, 3, 3))));
    forms[3] = new XYRect(3, 5, 1, 3, -2, new DiffuseLight(new SolidTexture(new Vector(4, 4, 4))));

    return new Scene(forms);
  }

  /**
   * @return Returns a scene with sun, moon, earth
   */
  public static Scene planetaryScene() {
    int n = 500; 
    Form[] forms = new Form[n + 1]; 
    Texture earth = new ImageTexture("/images/earth.jpg");
    Texture moon = new ImageTexture("/images/moon.jpg");
    Texture mars = new ImageTexture("/images/mars.jpg");
    Texture saturn = new ImageTexture("/images/saturn.jpeg");
    Texture mercury = new ImageTexture("/images/mercury.jpg");
    Texture sun = new ImageTexture("/images/sun.jpeg");
    Texture neptune = new ImageTexture("/images/neptune.jpg");
    Texture checker = new CheckerTexture(new SolidTexture(new Vector(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random())), new SolidTexture(new Vector(1, 1, 1)));
    forms[0] = new Sphere(new Vector(0, -1000, 0), 1000, new Metal(new Vector(0.1, 0.1, 0.2)));

    int i = 1; 

    forms[i++] = new Sphere(new Vector(0, 2.5, -1), 2.5, new Lambertian(earth));
    forms[i++] = new Sphere(new Vector(5, 2, 8.5), 2, new Lambertian(moon));
    forms[i++] = new Sphere(new Vector(2.5, 2, 5), 1.7, new Lambertian(neptune));
    forms[i++] = new Sphere(new Vector(-0.5, 2, 8), 2, new Lambertian(mars));
    forms[i++] = new Sphere(new Vector(-4, 5, 4), 4, new Lambertian(saturn));
    forms[i++] = new Sphere(new Vector(8, 3.5, 3), 3.5, new Lambertian(mercury));
    forms[i++] = new Sphere(new Vector(4, 3, -8), 3, new Lambertian(sun));
    forms[i++] = new Sphere(new Vector(0, 50, 0), 15, new DiffuseLight(new SolidTexture(new Vector(4, 4, 4))));
    forms[i++] = new Sphere(new Vector(0, 20, 40), 20, new DiffuseLight(new SolidTexture(new Vector(1.5, 1.5, 1.5))));
    return new Scene(forms);
  }

  /**
   * @return Returns a classic cornell box
   */
  public static Scene cornellBox(){
    Form[] list = new Form[20];
    int i = 0;
    Material red = new Lambertian(new SolidTexture(new Vector(0.65, 0.05, 0.05)));
    Material shinyRed = new Metal(new Vector(0.65, 0.05, 0.05));
    Material white = new Lambertian(new SolidTexture(new Vector(1, 1, 1)));
    Material shinyWhite = new Metal(new Vector(0.73, 0.73, 0.73));
    Material green = new Lambertian(new SolidTexture(new Vector(0.12, 0.45, 0.15)));
    Material shinyGreen = new Metal(new Vector(0.12, 0.45, 0.15));
    Material light = new DiffuseLight(new SolidTexture(new Vector(1.2, 1.2, 1.2)));
    Material lightBrighter = new DiffuseLight(new SolidTexture(new Vector(5, 5, 5)));
    
    list[i++] = new FlipNormal(new YZRect(0, 300, 0, 555, 555, green));
    list[i++] = new YZRect(0, 300, 0, 555, 0, red);
    list[i++] = new FlipNormal (new XZRect(0, 555, 0, 555, 555, shinyWhite));
    list[i++] = new XZRect(0, 555, 0, 555, 0, shinyWhite);
    list[i++] = new FlipNormal(new XYRect(0, 555, 0, 555, 555, white));
    list[i++] = new XYRect(-200, 800, -200, 1000, -800, light);

    // Form cube = new Translate(new RotateY(new Box(new Vector(0, 0, 0), new Vector(165, 330, 165), shinyWhite), 15), new Vector(265, 0, 295));
    // list[i++] = cube;

    list[i++] = new Sphere(new Vector(400, 60, 300), 60.0, new Dielectric(1.5));
    list[i++] = new Sphere(new Vector(200, 60, 300), 60.0, new Metal(new Vector(0.8, 0.2, 0.2)));
    // list[i++] = new MovingSphere(new Vector(110, 40, 100), new Vector(110, 60, 100), 0, 1, 40, new Lambertian(new SolidTexture(new Vector(0.2, 0.2, 0.8))));
    list[i++] = new Sphere(new Vector(50, 350, 300), 70.0, new Lambertian(new ImageTexture("images/earth.jpg")));
    
    return new Scene(list);
  }

}