import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class ImageTexture extends Texture{
  private BufferedImage img = null;
  private int height, width;

  /*
   * Empty Constructor
   */
  public ImageTexture() {}

  /**
   * @param  path location to file of image
   */
  public ImageTexture(String path){
    try {
      String basePath = new File("").getAbsolutePath();
      img = ImageIO.read(new File(basePath + "/" + path));
      height = img.getHeight();
      width = img.getWidth();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param  u "x" value
   * @param  v "y" value
   * @param  p vector hitting the texture
   */
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
