package controller;

/**
 * The `ControllerForGUI` interface represents the features that the GUI can
 * perform.
 */
public interface ControllerForGUI extends ImageProcessingController {

  /**
   * Loads an image from the user's file system.
   */
  void load();

  /**
   * Generates and displays the red component of the current image along
   * with its histogram.
   */
  void redComponent();

  /**
   * Generates and displays the green component of the current image along
   * with its histogram.
   */
  void greenComponent();

  /**
   * Generates and displays the blue component of the current image along
   * with its histogram.
   */
  void blueComponent();

  /**
   * Flips the current image horizontally and displays it along
   * with its histogram.
   */
  void horizontalFlip();

  /**
   * Flips the current image vertically and displays it along
   * with its histogram.
   */
  void verticalFlip();

  /**
   * Compresses the current image and displays it along with its histogram.
   */
  void compression();

  /**
   * Blurs the current image and displays it along with its histogram.
   */
  void blur();

  /**
   * Converts the current image to greyscale and displays it along with its
   * histogram.
   */
  void greyScale();

  /**
   * Sharpens the current image and displays it along with its histogram.
   */
  void sharpen();

  /**
   * Color corrects the current image and displays it along with its histogram.
   */
  void colorCorrect();

  /**
   * Applies a sepia tone to the current image and displays it along with its
   * histogram.
   */
  void sepia();

  /**
   * Adjusts the levels of the current image and displays it along with its
   * histogram.
   */
  void levelAdjustment();

  /**
   * Saves the current image to the user's file system.
   */
  void save();
}
