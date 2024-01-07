package controller;

import java.awt.image.BufferedImage;

import view.ImageView;

/**
 * This is a mock class for the ImageView for testing of the controller.
 */
public class MockImageViewImpl implements ImageView {
  final StringBuilder log;
  final int uniqueCode;

  /**
   * Constructs a mock view with the given log and unique code.
   *
   * @param log        the log to append to.
   * @param uniqueCode the unique code to append to the log.
   */
  public MockImageViewImpl(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String fileChooser() {
    log.append("Method: FileChooser (").append(uniqueCode).append(")\n");
    return "res/random.png";
  }

  @Override
  public void setImage(BufferedImage image, BufferedImage imageHistogram) {
    log.append("Method: setImage (").append(uniqueCode).append(")\n");
    logImageDetails(image, imageHistogram);
  }

  private void logImageDetails(BufferedImage image, BufferedImage imageHistogram) {
    if (image != null) {
      log.append("Image Dimensions: ").append(image.getWidth()).append("x"
      ).append(image.getHeight()).append("\n");
    } else {
      log.append("\nImage is null");
    }
    if (imageHistogram != null) {
      log.append("Image Histogram Dimensions: ").append(imageHistogram.getWidth()
      ).append("x").append(imageHistogram.getHeight()).append("\n");
    } else {
      log.append("\nImage Histogram is null");
    }
  }

  @Override
  public void setFeatures(ControllerForGUI controllerForGUI) {
    log.append("Method: setFeatures (").append(
            uniqueCode).append(")\n");
  }

  @Override
  public int getCompressionFactor() {
    log.append("Method: getCompressionFactor ("
    ).append(uniqueCode).append(")\n");
    return 0;
  }

  @Override
  public int showSplitImageOperationMenu(BufferedImage bufferedImage,
                                         BufferedImage bufferedImageHistogram) {
    log.append("Method: showSplitImageOperationMenu ("
    ).append(uniqueCode).append(")\n");
    logImageDetails(bufferedImage, bufferedImageHistogram);
    return 101;
  }

  @Override
  public int[] getLevels() {
    log.append("Method: getLevels (").append(
            uniqueCode).append(")\n");
    return new int[]{0, 0, 0};
  }

  @Override
  public void display(String message) {
    log.append("Method: display (").append(
            uniqueCode).append(")\n");
    log.append("Message: ").append(message).append("\n");
  }

  @Override
  public String selectFolder() {
    log.append("Method: selectFolder (").append(
            uniqueCode).append(")\n");
    return null;
  }

  @Override
  public boolean showLoadWarningPopup() {
    log.append("Method: showLoadWarningPopup (").append(
            uniqueCode).append(")\n");
    return false;
  }

  @Override
  public void enableButtons() {
    log.append("Method: enableButtons (").append(
            uniqueCode).append(")\n");
  }
}
