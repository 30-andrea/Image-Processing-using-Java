package model;


import java.util.function.BiFunction;

/**
 * An implementation of the ImageProcessingModel interface for image manipulations
 * and enhancement.
 * This class encapsulates the core functionality of the image processing application,
 * including handling different image formats, performing operations like brightness
 * adjustment and blurring, and facilitating the visualization of
 * image attributes. It ensures that image-related operations are carried
 * out efficiently and reliably.
 */
public class ImageProcessor implements ImageProcessingModel {


  @Override
  public ImageInfo createRedComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return createImageForComponent(imageData, ImageColor.RED.getIndex());
  }

  @Override
  public ImageInfo createGreenComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return createImageForComponent(imageData, ImageColor.GREEN.getIndex());
  }

  @Override
  public ImageInfo createBlueComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return createImageForComponent(imageData, ImageColor.BLUE.getIndex());
  }

  @Override
  public ImageInfo createValueComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return calculate(imageData, (value1, value2, value3
    ) -> Math.max(value1, Math.max(value2, value3)));
  }

  @Override
  public ImageInfo createLumaComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return calculate(imageData, (red, green, blue
    ) -> (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue));
  }

  @Override
  public ImageInfo createIntensityComponentOfImage(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return calculate(imageData, (value1, value2, value3
    ) -> (value1 + value2 + value3) / 3);
  }

  @Override
  public ImageInfo flipImageHorizontally(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return flip(imageData, (coordinate, size
    ) -> coordinate, (coordinate, size) -> size - coordinate - 1);
  }

  @Override
  public ImageInfo flipImageVertically(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return flip(imageData, (coordinate, size
    ) -> size - coordinate - 1, (coordinate, size) -> coordinate);
  }

  @Override
  public ImageInfo adjustBrightness(ImageInfo imageData, int brightness) throws
          IllegalArgumentException {
    checkImage(imageData);
    return add(imageData, brightness);
  }


  @Override
  public ImageInfo[] splitImageIntoColorComponents(ImageInfo imageData) throws
          IllegalArgumentException {
    checkImage(imageData);
    return new ImageInfo[]{createGreyscaleImageForComponent(imageData, ImageColor.RED.getIndex()),
            createGreyscaleImageForComponent(imageData, ImageColor.GREEN.getIndex()),
            createGreyscaleImageForComponent(imageData, ImageColor.BLUE.getIndex())};
  }

  @Override
  public ImageInfo combineColorComponents(ImageInfo redComponent,
                                          ImageInfo greenComponent,
                                          ImageInfo blueComponent) throws
          IllegalArgumentException {
    checkImage(redComponent);
    checkImage(greenComponent);
    checkImage(blueComponent);
    return combine(redComponent, greenComponent, blueComponent);
  }

  @Override
  public ImageInfo blurImage(ImageInfo imageData) throws IllegalArgumentException {
    checkImage(imageData);
    return applyFilter(imageData, ModelUtil.getBlurFilter());
  }

  @Override
  public ImageInfo sharpenImage(ImageInfo imageData) throws IllegalArgumentException {
    checkImage(imageData);
    return applyFilter(imageData, ModelUtil.getSharpenFilter());
  }

  @Override
  public ImageInfo sepiaToneImage(ImageInfo imageData) throws IllegalArgumentException {
    checkImage(imageData);
    return linearTransformation(imageData, ModelUtil.getSepiaToneFilter());
  }

  @Override
  public ImageInfo greyscaleImage(ImageInfo imageData) throws IllegalArgumentException {
    checkImage(imageData);
    return linearTransformation(imageData, ModelUtil.getGreyScaleFilter());
  }

  private void checkImage(ImageInfo imageData) {
    if (imageData == null) {
      throw new IllegalArgumentException("Image data cannot be null.");
    }
  }

  private ImageInfo add(ImageInfo image, int value) {
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int[][][] newPixelData = new int[height][width][channels];

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        for (int k = 0; k < channels; k++) {
          int pixelValue = Math.min(255, Math.max(0, image.getPixelValue(x, y, k)
                  + value));
          newPixelData[x][y][k] = pixelValue;
        }
      }
    }

    return new ImageData(newPixelData);
  }


  private ImageInfo calculate(ImageInfo image,
                              TriFunction<Integer, Integer, Integer, Integer> operation) {
    if (operation == null) {
      throw new IllegalArgumentException("Cannot perform the specified operation.");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    int[][][] result = new int[height][width][3];
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int red = image.getPixelValue(x, y, 0);
        int green = image.getPixelValue(x, y, 1);
        int blue = image.getPixelValue(x, y, 2);
        int newValue = operation.apply(red, green, blue);
        result[x][y][0] = newValue;
        result[x][y][1] = newValue;
        result[x][y][2] = newValue;
      }
    }

    return new ImageData(result);
  }

  private ImageInfo flip(ImageInfo image,
                         BiFunction<Integer, Integer, Integer> flipFunctionX,
                         BiFunction<Integer, Integer, Integer> flipFunctionY) {
    if (flipFunctionX == null || flipFunctionY == null) {
      throw new IllegalArgumentException("Unable to flip image.");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int[][][] flipped = new int[height][width][3];

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int newX = flipFunctionX.apply(x, height);
        int newY = flipFunctionY.apply(y, width);
        for (int k = 0; k < channels; k++) {
          flipped[newX][newY][k] = image.getPixelValue(x, y, k);
        }
      }
    }

    return new ImageData(flipped);
  }


  private ImageInfo applyFilter(ImageInfo image,
                                double[][] kernel) {
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int kernelSize = kernel.length;
    if (kernelSize % 2 == 0) {
      throw new IllegalArgumentException("Kernel size must be an odd number.");
    }
    int[][][] result = new int[height][width][3];

    int kernelOffset = kernelSize / 2;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double[] sum = new double[channels];

        for (int ky = 0; ky < kernelSize; ky++) {
          for (int kx = 0; kx < kernelSize; kx++) {
            int pixelX = x + kx - kernelOffset;
            int pixelY = y + ky - kernelOffset;

            if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
              int[] pixel = new int[channels];
              for (int k = 0; k < channels; k++) {
                pixel[k] = image.getPixelValue(pixelY, pixelX, k);
              }
              double weight = kernel[ky][kx];

              for (int i = 0; i < channels; i++) {
                sum[i] += weight * pixel[i];
              }
            }
          }
        }

        for (int i = 0; i < channels; i++) {
          result[y][x][i] = Math.min(255, Math.max(0, (int) sum[i]));
        }
      }
    }

    return new ImageData(result);
  }


  private ImageInfo linearTransformation(ImageInfo image,
                                         double[][] filter) {
    int width = image.getWidth();
    int height = image.getHeight();
    int channels = image.getChannels();
    int sum;
    int[][][] result = new int[height][width][channels];
    if (filter.length != 3) {
      throw new IllegalArgumentException("Filter matrix must be 3x3.");
    }
    for (double[] doubles : filter) {
      if (doubles.length != 3) {
        throw new IllegalArgumentException("Filter matrix must be 3x3.");
      }
    }
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        for (int i = 0; i < channels; i++) {
          sum = 0;
          for (int j = 0; j < channels; j++) {
            sum += (int) (image.getPixelValue(x, y, j) * filter[i][j]);
          }
          result[x][y][i] = Math.min(255, Math.max(0, sum));
        }

      }
    }
    return new ImageData(result);
  }


  private ImageInfo createImageForComponent(ImageInfo image,
                                            int colorIndex) {
    if (colorIndex > 2 || colorIndex < 0) {
      throw new IllegalArgumentException("Invalid color component provided.");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int[][][] componentPixelData = new int[height][width][3];
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int[] pixel = new int[channels];
        for (int k = 0; k < channels; k++) {
          pixel[k] = image.getPixelValue(x, y, k);
        }
        int componentValue = pixel[colorIndex];

        int[] componentPixel = new int[3];
        componentPixel[colorIndex] = componentValue;

        componentPixelData[x][y] = componentPixel;
      }
    }
    return new ImageData(componentPixelData);
  }

  private ImageInfo createGreyscaleImageForComponent(ImageInfo image,
                                                     int colorIndex) {
    if (colorIndex > 2 || colorIndex < 0) {
      throw new IllegalArgumentException("Invalid color component provided.");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int[][][] grayscalePixelData = new int[height][width][3];
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int[] pixel = new int[channels];
        for (int k = 0; k < channels; k++) {
          pixel[k] = image.getPixelValue(x, y, k);
        }
        int componentValue = pixel[colorIndex];
        int[] grayscalePixel = new int[]{componentValue, componentValue, componentValue};
        grayscalePixelData[x][y] = grayscalePixel;
      }
    }
    return new ImageData(grayscalePixelData);
  }


  private ImageInfo combine(ImageInfo redImage,
                            ImageInfo greenImage,
                            ImageInfo blueImage) {
    if (redImage == null || greenImage == null || blueImage == null) {
      throw new IllegalArgumentException("Cannot combine null images.");
    }
    int height = redImage.getHeight();
    int width = redImage.getWidth();
    int channels = redImage.getChannels();
    int[][][] combinedPixelData = new int[height][width][channels];
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int redValue = redImage.getPixelValue(x, y, 0);
        int greenValue = greenImage.getPixelValue(x, y, 0);
        int blueValue = blueImage.getPixelValue(x, y, 0);
        int[] combinedPixel = new int[]{redValue, greenValue, blueValue};
        combinedPixelData[x][y] = combinedPixel;
      }
    }
    return new ImageData(combinedPixelData);
  }
}