import java.lang.Math; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

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

class ImageTexture extends Texture{
  BufferedImage img = null;
  int height, width;

  public ImageTexture() {}
  public ImageTexture(String path){
    try {
      img = ImageIO.read(new File(path));
      height = img.getHeight();
      width = img.getWidth();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Vector value(double u, double v, Vector p){
    int i = (int)(u * width);
    int j = (int)((1 - v) * height - 0.001);
    if (i < 0) i = 0;
    if (j < 0) j = 0;
    if (i > width - 1) i = width - 1;
    if (j > height - 1) j = height - 1;
    int rgb = img.getRGB(i, j);
    double red = ((rgb & 0x00ff0000) >> 16) / 255.0;
    double green = ((rgb & 0x0000ff00) >> 8) / 255.0;
    double blue = (rgb & 0x000000ff) / 255.0;
    return new Vector(red, green, blue);
  }
}
