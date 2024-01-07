package controller;

import model.EnhancedImageProcessingModel;
import model.ImageData;
import model.ImageInfo;

/**
 * This class represents a mock model for testing purposes.
 */
class MockImageProcessingModel implements EnhancedImageProcessingModel {

  final StringBuilder log;
  final int uniqueCode;

  /**
   * Constructs a mock model with the given log and unique code.
   * @param log the log to append to.
   * @param uniqueCode the unique code to append to the log.
   */
  public MockImageProcessingModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }


  @Override
  public ImageInfo createRedComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: createRedComponentOfImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo createGreenComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: createGreenComponentOfImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo createBlueComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: createBlueComponentOfImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo createValueComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: createValueComponentOfImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo createLumaComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: createLumaComponentOfImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo createIntensityComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: createIntensityComponentOfImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo flipImageHorizontally(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: flipImageHorizontally (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo flipImageVertically(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: flipImageVertically (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo adjustBrightness(ImageInfo imageData, int brightness) throws
          IllegalArgumentException {
    log.append("Method: adjustBrightness (").append(uniqueCode).append(")\n");
    log.append("Brightness Value: ").append(brightness).append("\n");
    logImageDataDimensions(imageData);
    return null;
  }


  @Override
  public ImageInfo[] splitImageIntoColorComponents(ImageInfo imageData) throws
          IllegalArgumentException {
    log.append("Method: splitImageIntoColorComponents (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return new ImageData[]{null, null, null};
  }

  @Override
  public ImageInfo combineColorComponents(ImageInfo redComponent,
                                          ImageInfo greenComponent,
                                          ImageInfo blueComponent) throws
          IllegalArgumentException {
    log.append("Method: combineColorComponents (").append(uniqueCode).append(")\n");
    logImageDataDimensions(redComponent);
    logImageDataDimensions(greenComponent);
    logImageDataDimensions(blueComponent);
    return null;
  }

  @Override
  public ImageInfo blurImage(ImageInfo imageData) throws IllegalArgumentException {
    log.append("Method: blurImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo sharpenImage(ImageInfo imageData) throws IllegalArgumentException {
    log.append("Method: sharpenImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo sepiaToneImage(ImageInfo imageData) throws IllegalArgumentException {
    log.append("Method: sepiaToneImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo greyscaleImage(ImageInfo imageData) throws IllegalArgumentException {
    log.append("Method: greyscaleImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(imageData);
    return null;
  }

  @Override
  public ImageInfo compressImage(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    log.append("Method: compressImage (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo produceHistogram(ImageInfo image) throws
          IllegalArgumentException {
    log.append("Method: produceHistogram (").append(uniqueCode).append(")\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo colorCorrectImage(ImageInfo image) throws
          IllegalArgumentException {
    log.append("Method: colorCorrectImage (").append(uniqueCode).append(")\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo levelAdjustment(ImageInfo image, int shadow, int mid,
                                   int highlight) throws IllegalArgumentException {
    log.append("Method: levelAdjustment (").append(uniqueCode).append(")\n");
    log.append("Shadow: ").append(shadow).append("\n");
    log.append("Mid: ").append(mid).append("\n");
    log.append("Highlight: ").append(highlight).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo splitBlur(ImageInfo image, int percentage) throws IllegalArgumentException {
    log.append("Method: splitBlur (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo splitSharpen(ImageInfo image, int percentage) throws IllegalArgumentException {
    log.append("Method: splitSharpen (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo splitSepia(ImageInfo image, int percentage) throws IllegalArgumentException {
    log.append("Method: splitSepia (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo splitGreyscale(ImageInfo image, int percentage) throws IllegalArgumentException {
    log.append("Method: splitGreyscale (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    logImageDataDimensions(image);
    return null;
  }


  @Override
  public ImageInfo splitColorCorrect(ImageInfo image, int percentage)
          throws IllegalArgumentException {
    log.append("Method: splitColorCorrect (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  @Override
  public ImageInfo splitLevelAdjustment(ImageInfo image, int shadow, int mid,
                                        int highlight, int percentage) throws
          IllegalArgumentException {
    log.append("Method: splitLevelAdjustment (").append(uniqueCode).append(")\n");
    log.append("Percentage: ").append(percentage).append("\n");
    log.append("Shadow: ").append(shadow).append("\n");
    log.append("Mid: ").append(mid).append("\n");
    log.append("Highlight: ").append(highlight).append("\n");
    logImageDataDimensions(image);
    return null;
  }

  protected void logImageDataDimensions(ImageInfo imageData) {
    log.append("ImageData Dimensions: ").append(imageData.getWidth()).append(
            "x").append(imageData.getHeight()).append("\n");
  }
}
