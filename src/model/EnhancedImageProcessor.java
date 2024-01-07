package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.TreeSet;

/**
 * The EnhancedImageProcessor class extends the basic ImageProcessor class
 * and implements the EnhancedImageProcessingModel interface.
 * It provides additional image processing operations to enhance and modify images.
 */
public class EnhancedImageProcessor extends ImageProcessor implements
        EnhancedImageProcessingModel {
  @Override
  public ImageInfo compressImage(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    return compress(image, percentage);
  }

  @Override
  public ImageInfo produceHistogram(ImageInfo image) throws
          IllegalArgumentException {
    return histogram(image);
  }

  @Override
  public ImageInfo colorCorrectImage(ImageInfo image) throws
          IllegalArgumentException {
    return colorCorrect(image);
  }

  @Override
  public ImageInfo levelAdjustment(ImageInfo image, int shadow,
                                   int mid, int highlight)
          throws IllegalArgumentException {
    return performLevelAdjustment(image, shadow, mid, highlight);
  }

  @Override
  public ImageInfo splitBlur(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    return combineImages(blurImage(splitImage(image, percentage)[0]),
            splitImage(image, percentage)[1], image);
  }

  @Override
  public ImageInfo splitSharpen(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    return combineImages(sharpenImage(splitImage(image, percentage)[0]),
            splitImage(image, percentage)[1], image);
  }

  @Override
  public ImageInfo splitSepia(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    return combineImages(sepiaToneImage(splitImage(image, percentage)[0]),
            splitImage(image, percentage)[1], image);
  }

  @Override
  public ImageInfo splitGreyscale(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    return combineImages(greyscaleImage(splitImage(image, percentage)[0]),
            splitImage(image, percentage)[1], image);
  }

  @Override
  public ImageInfo splitColorCorrect(ImageInfo image, int percentage) throws
          IllegalArgumentException {
    return combineImages(colorCorrectImage(splitImage(image, percentage)[0]),
            splitImage(image, percentage)[1], image);
  }

  @Override
  public ImageInfo splitLevelAdjustment(ImageInfo image, int shadow, int mid,
                                        int highlight, int percentage) throws
          IllegalArgumentException {
    ImageInfo leftImage = splitImage(image, percentage)[0];
    return combineImages(levelAdjustment(leftImage,
                    shadow, mid, highlight),
            splitImage(image, percentage)[1], image);
  }

  private ImageInfo[] splitImage(ImageInfo image, int percentage) {
    int width = image.getWidth();
    int height = image.getHeight();
    int splitPoint = width * percentage / 100;
    int[][][] leftData;
    if ((splitPoint + 3) < width) {
      leftData = new int[height][splitPoint + 3][3];
    } else {
      leftData = new int[height][width][3];
    }
    int[][][] rightData = new int[height][width - splitPoint][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (j < splitPoint) {
          for (int k = 0; k < 3; k++) {
            leftData[i][j][k] = image.getPixelValue(i, j, k);
          }
        } else {
          for (int k = 0; k < 3; k++) {
            rightData[i][j - splitPoint][k] = image.getPixelValue(i, j, k);
          }
        }
      }
    }
    if ((splitPoint + 3) < width) {
      for (int i = 0; i < height; i++) {
        for (int j = splitPoint; j < splitPoint + 3; j++) {
          for (int k = 0; k < 3; k++) {
            leftData[i][j][k] = image.getPixelValue(i, j, k);
          }
        }
      }
    }
    ImageInfo leftImage = new ImageData(leftData);
    ImageInfo rightImage = new ImageData(rightData);
    return new ImageInfo[]{leftImage, rightImage};
  }

  private ImageInfo combineImages(ImageInfo leftImage, ImageInfo rightImage,
                                  ImageInfo originalImage) {
    int leftWidth = leftImage.getWidth();
    int rightWidth = rightImage.getWidth();
    if (leftWidth + rightWidth > originalImage.getWidth()) {
      leftWidth = leftWidth - (leftWidth + rightWidth - originalImage.getWidth());
    }
    int height = Math.min(leftImage.getHeight(), rightImage.getHeight());
    int combinedWidth = leftWidth + rightWidth;
    int[][][] combinedData = new int[height][combinedWidth][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < leftWidth; j++) {
        for (int k = 0; k < 3; k++) {
          combinedData[i][j][k] = leftImage.getPixelValue(i, j, k);
        }
      }
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < rightWidth; j++) {
        for (int k = 0; k < 3; k++) {
          combinedData[i][leftWidth + j][k] = rightImage.getPixelValue(i, j, k);
        }
      }
    }
    return new ImageData(combinedData);
  }

  private ImageInfo performLevelAdjustment(
          ImageInfo image, int shadow, int mid, int highlight
  ) {
    shadow = Math.max(0, Math.min(255, shadow));
    mid = Math.max(0, Math.min(255, mid));
    highlight = Math.max(0, Math.min(255, highlight));
    int width = image.getWidth();
    int height = image.getHeight();
    if (width < 2 || height < 2) {
      return image;
    }
    double first = shadow * shadow * (mid - highlight)
            - shadow * (mid * mid - highlight * highlight)
            + highlight * (mid * mid) - mid * (highlight * highlight);
    double aA = -shadow * (128 - 255) + 128 * highlight - 255 * mid;
    double aB = shadow * shadow * (128 - 255) + 255 * (mid * mid)
            - 128 * (highlight * highlight);
    double aC = shadow * shadow * (255 * mid - 128 * highlight)
            - shadow * (255 * (mid * mid) - 128 * (highlight * highlight));
    double a = aA / first;
    double b = aB / first;
    double c = aC / first;
    BufferedImage correctedImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = image.getPixelValue(i, j, 0);
        int greenValue = image.getPixelValue(i, j, 1);
        int blueValue = image.getPixelValue(i, j, 2);
        redValue = applyLevelsAdjustment(redValue, a, b, c);
        greenValue = applyLevelsAdjustment(greenValue, a, b, c);
        blueValue = applyLevelsAdjustment(blueValue, a, b, c);
        int rgb = (redValue << 16) | (greenValue << 8) | blueValue;
        correctedImage.setRGB(j, i, rgb);
      }
    }
    return new ImageData(correctedImage);
  }

  private static int applyLevelsAdjustment(int value, double a, double b, double c) {
    int adjustedValue = (int) (a * value * value + b * value + c);
    return Math.min(255, Math.max(0, adjustedValue));
  }

  private ImageInfo colorCorrect(ImageInfo image) {
    int width = image.getWidth();
    int height = image.getHeight();
    if (width < 2 || height < 2) {
      return image;
    }

    int[][] histograms = computeHistograms(image);
    int[] redHistogram = histograms[0];
    int[] greenHistogram = histograms[1];
    int[] blueHistogram = histograms[2];

    int redPeak = findMeaningfulPeak(redHistogram);
    int greenPeak = findMeaningfulPeak(greenHistogram);
    int bluePeak = findMeaningfulPeak(blueHistogram);

    int averagePeak = (redPeak + greenPeak + bluePeak) / 3;

    int redOffset = averagePeak - redPeak;
    int greenOffset = averagePeak - greenPeak;
    int blueOffset = averagePeak - bluePeak;

    BufferedImage correctedImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = Math.min(255, Math.max(0,
                image.getPixelValue(i, j, 0) + redOffset));
        int greenValue = Math.min(255, Math.max(0,
                image.getPixelValue(i, j, 1) + greenOffset));
        int blueValue = Math.min(255, Math.max(0,
                image.getPixelValue(i, j, 2) + blueOffset));

        int rgb = (redValue << 16) | (greenValue << 8) | blueValue;
        correctedImage.setRGB(j, i, rgb);
      }
    }

    return new ImageData(correctedImage);

  }

  private static int findMeaningfulPeak(int[] histogram) {
    int peakValue = -1;
    int peakIndex = -1;

    for (int i = 10; i < 245; i++) {
      if (histogram[i] > peakValue) {
        peakValue = histogram[i];
        peakIndex = i;
      }
    }

    return peakIndex;
  }

  private int[][] computeHistograms(ImageInfo image) {
    int[][] histograms = new int[3][256];
    int height = image.getHeight();
    int width = image.getWidth();

    for (int channel = 0; channel < 3; channel++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int pixelValue = Math.min(255, Math.max(0, image.getPixelValue(i, j, channel)));
          histograms[channel][pixelValue]++;
        }
      }
    }
    return histograms;
  }

  private ImageInfo histogram(ImageInfo image) {
    int[][] histograms = computeHistograms(image);
    int[] redHistogram = histograms[0];
    int[] greenHistogram = histograms[1];
    int[] blueHistogram = histograms[2];
    BufferedImage histogram = getBufferedImage();
    int maxFrequency = Math.max(maxFrequency(redHistogram),
            Math.max(maxFrequency(greenHistogram),
                    maxFrequency(blueHistogram)));
    drawLineGraph(histogram, redHistogram,
            maxFrequency,
            256, 256, Color.RED);
    drawLineGraph(histogram, greenHistogram,
            maxFrequency,
            256, 256, Color.GREEN);
    drawLineGraph(histogram, blueHistogram,
            maxFrequency,
            256, 256, Color.BLUE);
    return new ImageData(histogram);
  }

  private static BufferedImage getBufferedImage() {
    int histogramWidth = 256;
    int histogramHeight = 256;
    BufferedImage histogram = new BufferedImage(histogramWidth,
            histogramHeight, BufferedImage.TYPE_INT_RGB);
    Color lightGray = new Color(150, 150, 150);
    for (int i = 0; i < histogramWidth; i++) {
      for (int j = 0; j < histogramHeight; j++) {
        histogram.setRGB(i, j, Color.WHITE.getRGB());
      }
    }
    for (int i = 0; i < histogramWidth; i += 16) {
      for (int j = 0; j < histogramHeight; j++) {
        histogram.setRGB(i, j, lightGray.getRGB());
      }
    }
    for (int i = 0; i < histogramHeight; i += 16) {
      for (int j = 0; j < histogramWidth; j++) {
        histogram.setRGB(j, i, lightGray.getRGB());
      }
    }
    return histogram;
  }

  private static int maxFrequency(int[] histogram) {
    int max = 0;
    for (int value : histogram) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }

  private static void drawLineGraph(BufferedImage image, int[] histogram,
                                    int maxFrequency,
                                    int width, int height, Color color) {
    int prevX = 0;
    int prevY = height;
    for (int i = 0; i < histogram.length; i++) {
      int x = i * width / 256;
      int y = height - (int) (((double) histogram[i] / maxFrequency) * (height - 1));
      drawLine(image, prevX, prevY, x, y, color);
      prevX = x;
      prevY = y;
    }
  }

  private static void drawLine(BufferedImage image, int x1,
                               int y1, int x2, int y2, Color color) {
    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;
    int err = dx - dy;

    while (true) {
      if (x1 >= 0 && x1 < image.getWidth() && y1 >= 0 && y1 < image.getHeight()) {
        image.setRGB(x1, y1, color.getRGB());
      }
      if (x1 == x2 && y1 == y2) {
        break;
      }
      int e2 = 2 * err;
      if (e2 > -dy) {
        err -= dy;
        x1 += sx;
      }
      if (x1 == x2 && y1 == y2) {
        if (x1 >= 0 && x1 < image.getWidth() && y1 >= 0 && y1 < image.getHeight()) {
          image.setRGB(x1, y1, color.getRGB());
        }
        break;
      }
      if (e2 < dx) {
        err += dx;
        y1 += sy;
      }
    }
  }

  private ImageInfo compress(ImageInfo image, int percentage) {
    double[][][] paddedImageArray = padImageToPowerOf2(image);
    int height = paddedImageArray.length;
    int width = paddedImageArray[0].length;
    if (height != width) {
      throw new IllegalArgumentException("Padded image must be square");
    }
    for (int k = 0; k < 3; k++) {
      performHaarWaveletTransform(paddedImageArray, k);
    }
    throwUniqueAbsoluteValues(paddedImageArray, percentage);
    for (int k = 0; k < 3; k++) {
      performInverseHaarWaveletTransform(paddedImageArray, k);
    }
    return removePadding(paddedImageArray, image);
  }

  private void throwUniqueAbsoluteValues(double[][][] paddedImageArray,
                                         int percentage) {
    int height = paddedImageArray.length;
    int width = paddedImageArray[0].length;
    Set<Double> nonZeroValues = new TreeSet<>();
    for (int channel = 0; channel < 3; channel++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          nonZeroValues.add(Math.abs(paddedImageArray[i][j][channel]));
        }
      }
    }
    int thresholdIndex = Math.round((float) nonZeroValues.size() *
            ((float) percentage / 100)) - 1;
    double elementAtThresholdIndex = 0;
    int currentIndex = 0;
    for (double value : nonZeroValues) {
      if (currentIndex == thresholdIndex) {
        elementAtThresholdIndex = value;
        break;
      }
      currentIndex++;
    }
    for (int channel = 0; channel < 3; channel++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          if ((Math.abs(paddedImageArray[i][j][channel]) - elementAtThresholdIndex) <=
                  0.001) {
            paddedImageArray[i][j][channel] = 0.0;
          }
        }
      }
    }
  }


  private ImageInfo removePadding(double[][][] paddedImageArray, ImageInfo image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int[][][] unPaddedImageArray = new int[height][width][channels];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < channels; k++) {
          unPaddedImageArray[i][j][k] = Math.max(0, Math.min(255,
                  (int) Math.round(paddedImageArray[i][j][k])));
        }
      }
    }
    return new ImageData(unPaddedImageArray);
  }

  private double[] getColChannelArray(double[][][] paddedImageArray,
                                      int index, int channel, int size) {
    double[] channelArray = new double[size];
    for (int i = 0; i < size; i++) {
      channelArray[i] = paddedImageArray[index][i][channel];
    }
    return channelArray;
  }


  private void performInverseHaarWaveletTransform(double[][][] paddedImageArray,
                                                  int channel) {
    int size = paddedImageArray.length;
    int c = 2;
    while (c <= size) {
      for (int j = 0; j < c; j++) {
        double[] channelArray = getRowChannelArray(paddedImageArray,
                j, channel, c);
        channelArray = reverseHaarTransform(channelArray);
        for (int i = 0; i < c; i++) {
          paddedImageArray[i][j][channel] = channelArray[i];
        }
      }
      for (int i = 0; i < c; i++) {
        double[] channelArray = getColChannelArray(paddedImageArray,
                i, channel, c);
        channelArray = reverseHaarTransform(channelArray);
        for (int j = 0; j < c; j++) {
          paddedImageArray[i][j][channel] = channelArray[j];
        }
      }
      c *= 2;
    }
  }

  private double[] getRowChannelArray(double[][][] paddedImageArray,
                                      int index, int channel, int size) {
    double[] channelArray = new double[size];
    for (int i = 0; i < size; i++) {
      channelArray[i] = paddedImageArray[i][index][channel];
    }
    return channelArray;
  }

  private void performHaarWaveletTransform(double[][][] paddedImageArray,
                                           int channel) {
    int size = paddedImageArray.length;
    while (size > 1) {
      for (int i = 0; i < size; i++) {
        double[] channelArray = getColChannelArray(paddedImageArray,
                i, channel, size);
        channelArray = calculateAvgDiffPairs(channelArray);
        for (int j = 0; j < size; j++) {
          paddedImageArray[i][j][channel] = channelArray[j];
        }
      }
      for (int j = 0; j < size; j++) {
        double[] channelArray = getRowChannelArray(paddedImageArray,
                j, channel, size);
        channelArray = calculateAvgDiffPairs(channelArray);
        for (int i = 0; i < size; i++) {
          paddedImageArray[i][j][channel] = channelArray[i];
        }
      }
      size /= 2;
    }
  }

  private double[][][] padImageToPowerOf2(ImageInfo image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getChannels();
    int maxSize = Math.max(height, width);
    int size = 1;
    while (size < maxSize) {
      size *= 2;
    }
    double[][][] paddedImageArray = new double[size][size][channels];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < channels; k++) {
          paddedImageArray[i][j][k] = image.getPixelValue(i, j, k);
        }
      }
    }
    return paddedImageArray;
  }

  private double[] calculateAvgDiffPairs(double[] s) {
    int n = s.length;
    int half = n / 2;
    double[] result = new double[n];

    for (int i = 0; i < n - 1; i += 2) {
      double a = s[i];
      double b = s[i + 1];
      double av = (a + b) / Math.sqrt(2.0);
      double di = (a - b) / Math.sqrt(2.0);
      result[i / 2 + half] = di;
      result[i / 2] = av;
    }
    return result;
  }

  private double[] reverseHaarTransform(double[] s) {
    int n = s.length;
    int half = n / 2;
    int index = n - 1;
    double[] result = new double[n];
    for (int i = n - 1; i >= n / 2; i -= 1) {
      double av = s[i - half];
      double di = s[i];

      double a = (av + di) / Math.sqrt(2.0);
      double b = (av - di) / Math.sqrt(2.0);

      result[index - 1] = a;
      result[index] = b;
      index -= 2;
    }
    return result;
  }
}
