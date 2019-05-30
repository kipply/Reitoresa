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
      if (formList[i].hit(r, minT, currClosest, tempHit)) {
        hitSuccess = true; 
        currClosest = tempHit.t; 
        hit.set(tempHit); 
      }
    }

    return hitSuccess;
  }
}