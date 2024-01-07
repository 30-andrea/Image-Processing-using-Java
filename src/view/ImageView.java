package view;

import java.awt.image.BufferedImage;

import controller.ControllerForGUI;

/**
 * This interface defines the GUI for the image processing program.
 */
public interface ImageView extends ViewInterface {

  /**
   * Changes the image displayed in the GUI to the given image.
   * @return Path of the file chosen.
   */
  String fileChooser();

  /**
   * Sets the image and histogram to the given images.
   * @param image The image to be displayed.
   * @param imageHistogram The histogram of the image.
   */
  void setImage(BufferedImage image, BufferedImage imageHistogram);

  /**
   * Sets the listener for the GUI.
   * @param controllerForGUI The listener for the GUI.
   */
  void setFeatures(ControllerForGUI controllerForGUI);

  /**
   * Fetches the compression factor from the user.
   * @return The compression factor.
   */
  int getCompressionFactor();

  /**
   * Displays a split image operation menu, allowing the user
   * to choose from various options.
   *
   * @param bufferedImage           The main image to be displayed
   *                                in the split image operation menu.
   * @param bufferedImageHistogram The histogram image
   *                              corresponding to the main image.
   * @return An integer representing the user's choice or action:
   *         - 101 for "Apply Operation"
   *         - -1 for "Cancel"
   *         - A valid integer for "Change Split Factor"
   *         - -2 if an invalid split factor is entered
   *         - 0 for default or other cases
   */
  int showSplitImageOperationMenu(BufferedImage bufferedImage, BufferedImage
          bufferedImageHistogram);


  /**
   * Fetches the shadow, mid and highlight values for
   * level adjustment from the user.
   * @return An array of integers representing the shadow, mid and highlight values.
   */
  int[] getLevels();

  /**
   * Displays a popup asking the user to choose a folder for saving the image.
   * @return The path of the folder chosen.
   */
  String selectFolder();

  /**
   * Displays a popup warning the user that the current image will be lost.
   * @return True if the user chooses to continue, false otherwise.
   */
  boolean showLoadWarningPopup();

  /**
   * Enables the buttons in the GUI.
   */
  void enableButtons();
}
