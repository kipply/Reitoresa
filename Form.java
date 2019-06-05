import java.util.Comparator; 

public abstract class Form {
  public abstract boolean hit(Ray r, double minT, double maxT, FormHit hit);

  public abstract boolean boundingBox(double t0, double t1, AABB box);
}

class SortBoxX implements Comparator<Form>{
  public int compare(Form a, Form b){
    AABB boxLeft = new AABB();
    AABB boxRight = new AABB();
    if (!a.boundingBox(0,0, boxLeft) || !b.boundingBox(0,0, boxRight)) {
      throw new java.lang.RuntimeException("no bounding box");
    }
    if (boxLeft.min().x() == boxRight.min().x()) {
      return 0;
    }
    if (boxLeft.min().x() < boxRight.min().x()) {
      return -1;
    } else {
      return 1;
    }
  }
}

class SortBoxY implements Comparator<Form>{
  public int compare(Form a, Form b) {
    AABB boxLeft = new AABB();
    AABB boxRight = new AABB();
    if (!a.boundingBox(0,0, boxLeft) || !b.boundingBox(0,0, boxRight)) {
      throw new java.lang.RuntimeException("no bounding box");
    }
    if (boxLeft.min().y() == boxRight.min().y()) {
      return 0;
    }
    if (boxLeft.min().y() < boxRight.min().y()) {
      return -1;
    } else {
      return 1;
    }
  }
}

class SortBoxZ implements Comparator<Form>{
  public int compare(Form a, Form b) {
    AABB boxLeft = new AABB();
    AABB boxRight = new AABB();
    if (!a.boundingBox(0,0, boxLeft) || !b.boundingBox(0,0, boxRight)){
      throw new java.lang.RuntimeException("no bounding box");
    }
    if (boxLeft.min().z() == boxRight.min().z()) {
      return 0;
    }
    if (boxLeft.min().z() < boxRight.min().z()) {
      return -1;
    } else {
      return 1;
    }
  }
}
