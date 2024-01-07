package model;

import java.io.IOException;

/**
 * The EnhancedImageProcessingModel interface defines a set of
 * additional image processing operations to enhance and modify images.
 */
public interface EnhancedImageProcessingModel extends ImageProcessingModel {

  /**
   * Compresses the provided image to reduce its file size.
   *
   * @param image      The original image to be compressed.
   * @param percentage The compression percentage (0-100),
   *                   where 0 means no compression
   *                   and 100 means maximum compression.
   * @return A new ImageInfo object representing the compressed image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo compressImage(ImageInfo image, int percentage) throws
          IllegalArgumentException;

  /**
   * Produces a histogram for the provided image, showing the distribution
   * of color intensities.
   *
   * @param image The original image for which to create a histogram.
   * @return A new ImageInfo object representing histogram of the given image.
   * @throws IllegalArgumentException If the operation cannot be performed on
   *                                  the provided image.
   */
  ImageInfo produceHistogram(ImageInfo image) throws IllegalArgumentException, IOException;

  /**
   * Applies color correction to the provided image, by aligning the
   * meaningful peaks of its histogram.
   *
   * @param image The original image to be color-corrected.
   * @return A new ImageInfo object representing the color-corrected image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo colorCorrectImage(ImageInfo image) throws IllegalArgumentException;

  /**
   * Adjusts the levels of the provided image by modifying the shadow, mid, and highlight values.
   *
   * @param image     The original image to undergo level adjustment.
   * @param shadow    The shadow adjustment value.
   * @param mid       The mid-tone adjustment value.
   * @param highlight The highlight adjustment value.
   * @return A new ImageInfo object representing the level-adjusted image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo levelAdjustment(ImageInfo image, int shadow, int mid, int highlight)
          throws IllegalArgumentException;

  /**
   * Applies a split blur effect to the provided image based on the specified percentage.
   *
   * @param image      The original image to undergo split blur.
   * @param percentage The percentage of the image to apply the blur effect.
   * @return A new ImageInfo object representing the split-blurred image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo splitBlur(ImageInfo image, int percentage) throws
          IllegalArgumentException;

  /**
   * Applies a split sharpening effect to the provided image based on the specified percentage.
   *
   * @param image      The original image to undergo split sharpening.
   * @param percentage The percentage of the image to apply the sharpening effect.
   * @return A new ImageInfo object representing the split-sharpened image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo splitSharpen(ImageInfo image, int percentage) throws
          IllegalArgumentException;

  /**
   * Applies a split sepia tone effect to the provided image based on the specified percentage.
   *
   * @param image      The original image to undergo split sepia toning.
   * @param percentage The percentage of the image to apply the sepia tone effect.
   * @return A new ImageInfo object representing the split-sepia-toned image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo splitSepia(ImageInfo image, int percentage) throws
          IllegalArgumentException;

  /**
   * Applies a split greyscale effect to the provided image based on the specified percentage.
   *
   * @param image      The original image to undergo split greyscale conversion.
   * @param percentage The percentage of the image to apply the greyscale effect.
   * @return A new ImageInfo object representing the split-greyscale image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo splitGreyscale(ImageInfo image, int percentage) throws
          IllegalArgumentException;


  /**
   * Applies a split color correction effect to the provided image based on
   * the specified percentage.
   *
   * @param image      The original image to undergo split color correction.
   * @param percentage The percentage of the image to apply the color correction effect.
   * @return A new ImageInfo object representing the split-color-corrected image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo splitColorCorrect(ImageInfo image, int percentage) throws
          IllegalArgumentException;

  /**
   * Applies a split-level adjustment effect to the provided image based on
   * the specified parameters.
   *
   * @param image      The original image to undergo split-level adjustment.
   * @param shadow     The shadow adjustment value.
   * @param mid        The mid-tone adjustment value.
   * @param highlight  The highlight adjustment value.
   * @param percentage The percentage of the image to apply the level adjustment effect.
   * @return A new ImageInfo object representing the split-level-adjusted image.
   * @throws IllegalArgumentException If the operation cannot be
   *                                  performed on the provided image.
   */
  ImageInfo splitLevelAdjustment(ImageInfo image, int shadow, int mid,
                                 int highlight, int percentage) throws
          IllegalArgumentException;

}
