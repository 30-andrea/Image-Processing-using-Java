package model;

import java.awt.image.BufferedImage;

/**
 * This interface represents information about an image, providing methods
 * to retrieve details such as height, width, and pixel data.
 */
public interface ImageInfo {

  /**
   * Gets the height of the image.
   *
   * @return The height of the image.
   */
  int getHeight();

  /**
   * Gets the width of the image.
   *
   * @return The width of the image.
   */
  int getWidth();

  /**
   * Converts the image's pixel data to a BufferedImage.
   *
   * @return A BufferedImage representation of the image.
   */
  BufferedImage convertPixelDataToBufferedImage();

  /**
   * Get the number of color channels in the image data.
   *
   * @return The number of color channels, which is 3 for color images (R, G, B).
   */
  int getChannels();

  /**
   * Gets the pixel value at the specified coordinates and channel.
   *
   * @param x The X-coordinate of the pixel.
   * @param y The Y-coordinate of the pixel.
   * @param k The color channel index (0 for Red, 1 for Green, 2 for Blue).
   * @return The pixel value for the given coordinates and channel.
   */
  int getPixelValue(int x, int y, int k);
}
