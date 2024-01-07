package model;

/**
 * The ModelUtil class provides utility methods for obtaining various image filters.
 */
class ModelUtil {

  /**
   * Gets a blur filter matrix.
   *
   * @return A 3x3 matrix representing the blur filter.
   */
  static double[][] getBlurFilter() {
    return new double[][]{
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
    };
  }

  /**
   * Gets a sharpen filter matrix.
   *
   * @return A 5x5 matrix representing the sharpen filter.
   */
  static double[][] getSharpenFilter() {
    return new double[][]{
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };
  }

  /**
   * Gets a grayscale filter matrix.
   *
   * @return A 3x3 matrix representing the grayscale filter.
   */
  static double[][] getGreyScaleFilter() {
    return new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
  }

  /**
   * Gets a sepia tone filter matrix.
   *
   * @return A 3x3 matrix representing the sepia tone filter.
   */

  static double[][] getSepiaToneFilter() {
    return new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
  }
}
