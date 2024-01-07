package model;

import java.awt.image.BufferedImage;


/**
 * This class encapsulates the properties of an image and
 * provides ways of operating on it.
 */
public class ImageData implements ImageInfo {
  private final int[][][] data;

  /**
   * Creates an ImageData instance from a 3D array representing color image data.
   *
   * @param data A 3D array where the first index represents height,
   *             the second index represents width,
   *             and the third index represents color channels (R, G, B).
   */
  public ImageData(int[][][] data) {
    this.data = data;
  }

  /**
   * Creates an ImageData instance from a BufferedImage.
   *
   * @param image The BufferedImage from which image data is extracted.
   */
  public ImageData(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] pixelData = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        pixelData[y][x][0] = red;
        pixelData[y][x][1] = green;
        pixelData[y][x][2] = blue;
      }
    }

    this.data = pixelData;
  }

  @Override
  public int getChannels() {
    return 3;
  }

  @Override
  public int getPixelValue(int x, int y, int k) {
    if (x > getHeight() || x < 0) {
      throw new IndexOutOfBoundsException("Row index out of bounds");
    }
    if (y > getWidth() || y < 0) {
      throw new IndexOutOfBoundsException("Column index out of bounds");
    }
    if (k > 2 || k < 0) {
      throw new IndexOutOfBoundsException("Invalid color index passed");
    }
    return data[x][y][k];
  }

  @Override
  public int getHeight() {
    return data.length;
  }

  @Override
  public int getWidth() {
    return data[0].length;
  }

  @Override
  public BufferedImage convertPixelDataToBufferedImage() {
    int height = getHeight();
    int width = getWidth();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int red = data[x][y][0];
        int green = data[x][y][1];
        int blue = data[x][y][2];
        int rgb = (red << 16) | (green << 8) | blue;
        image.setRGB(y, x, rgb);
      }
    }

    return image;
  }
}