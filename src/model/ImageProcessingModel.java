package model;


/**
 * This interface defines a set of operations for image manipulation and enhancement
 * in an image processing application.
 */
public interface ImageProcessingModel {

  /**
   * Creates an image with only the red component from the given image.
   *
   * @param imageData The original image data.
   * @return An image containing only the red component.
   * @throws IllegalArgumentException If the operation cannot be performed or an
   *                                  invalid image is passed.
   */
  ImageInfo createRedComponentOfImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Creates an image with only the green component from the given image.
   *
   * @param imageData The original image data.
   * @return An image containing only the green component.
   * @throws IllegalArgumentException If the operation cannot be performed or an
   *                                  invalid image is passed.
   */
  ImageInfo createGreenComponentOfImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Creates an image with only the blue component from the given image.
   *
   * @param imageData The original image data.
   * @return An image containing only the green component.
   * @throws IllegalArgumentException If the operation cannot be performed or an invalid
   *                                  image is passed.
   */
  ImageInfo createBlueComponentOfImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Creates an image with the value component from the given image.
   *
   * @param imageData The original image data.
   * @return An image containing only the value component.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo createValueComponentOfImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Creates an image with the luma component from the given image.
   *
   * @param imageData The original image data.
   * @return An image containing only the luma component.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo createLumaComponentOfImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Creates an image with the intensity component from the given image.
   *
   * @param imageData The original image data.
   * @return An image containing only the intensity component.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo createIntensityComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException;

  /**
   * Flips the given image horizontally.
   *
   * @param imageData The original image data.
   * @return A horizontally flipped version of the image.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo flipImageHorizontally(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Flips the given image vertically.
   *
   * @param imageData The original image data.
   * @return A vertically flipped version of the image.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo flipImageVertically(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Adjusts the brightness of the image.
   *
   * @param imageData  The original image data.
   * @param brightness The brightness adjustment value.
   * @return An image with adjusted brightness.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null or if brightness value is < 0.
   */
  ImageInfo adjustBrightness(ImageInfo imageData, int brightness) throws
          IllegalArgumentException;


  /**
   * Splits the given image into its color components (RGB).
   *
   * @param imageData The original image data.
   * @return An array of image data, each containing one of the color components (red, green, blue).
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo[] splitImageIntoColorComponents(ImageInfo imageData) throws
          IllegalArgumentException;

  /**
   * Combines individual color components into a single image.
   *
   * @param redComponent   The red color component image data.
   * @param greenComponent The green color component image data.
   * @param blueComponent  The blue color component image data.
   * @return An image formed by combining the specified color components.
   * @throws IllegalArgumentException If the operation cannot be performed due to invalid images
   *                                  or if any of the image data is null.
   */
  ImageInfo combineColorComponents(ImageInfo redComponent, ImageInfo greenComponent,
                                   ImageInfo blueComponent) throws IllegalArgumentException;

  /**
   * Applies a blur effect to the given image.
   *
   * @param imageData The original image data.
   * @return An image with the blur effect applied.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo blurImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Applies a sharpening effect to the given image.
   *
   * @param imageData The original image data.
   * @return An image with the sharpening effect applied.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo sharpenImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Applies a sepia tone effect to the given image.
   *
   * @param imageData The original image data.
   * @return An image with the sepia tone effect applied.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo sepiaToneImage(ImageInfo imageData) throws IllegalArgumentException;

  /**
   * Converts the given image to grayscale.
   *
   * @param imageData The original image data.
   * @return A grayscale version of the image.
   * @throws IllegalArgumentException If the operation cannot be performed due to an invalid image
   *                                  or if the image data is null.
   */
  ImageInfo greyscaleImage(ImageInfo imageData) throws IllegalArgumentException;
}
