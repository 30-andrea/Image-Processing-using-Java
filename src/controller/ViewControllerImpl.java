package controller;

import java.io.File;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import model.EnhancedImageProcessingModel;
import model.ImageInfo;
import view.ImageView;

/**
 * This class implements the `ControllerForGUI` interface, providing a mechanism for
 * executing commands from the GUI.
 */
public class ViewControllerImpl extends EnhancedController implements ControllerForGUI {

  ImageView view;
  String imageName;

  boolean imageSaveTracker;


  /**
   * Constructs a controller object with the given io, model, view and user input.
   * @param io the user input.
   * @param model the model.
   * @param args the command line arguments.
   * @param view the view.
   */
  public ViewControllerImpl(UserIO io, EnhancedImageProcessingModel model,
                            String[] args, ImageView view) {
    super(io, model, args);
    this.view = view;
    this.view.setFeatures(this);
    imageName = "";
    imageSaveTracker = true;
  }

  @Override
  public void load() {
    if (!imageSaveTracker) {
      boolean choice = view.showLoadWarningPopup();
      if (!choice) {
        return;
      }
    }
    String fileLocation = view.fileChooser();
    if (Objects.equals(fileLocation, "") || fileLocation == null) {
      return;
    }
    imageName = extractImageName(fileLocation);
    String command = "load " + fileLocation + " " + imageName;
    executeLine(command);
    view.setImage(imageList.get(imageName).convertPixelDataToBufferedImage(),
            histogram(imageName).convertPixelDataToBufferedImage());
    imageSaveTracker = true;
    view.enableButtons();
  }

  @Override
  public void save() {
    String fileLocation = view.selectFolder();
    if (Objects.equals(fileLocation, "") || fileLocation == null) {
      return;
    }
    String command = "save " + fileLocation + " " + imageName;
    executeLine(command);
    if (imageList.get(imageName) == null) {
      return;
    }
    imageSaveTracker = true;
  }

  private void processCommand(String component,
                                Function<String, String> imageNameGenerator) {
    String newImageName = imageNameGenerator.apply(imageName);
    String command = component + " " + imageName + " " + newImageName;
    executeLine(command);
    view.setImage(imageList.get(newImageName).convertPixelDataToBufferedImage(),
            histogram(newImageName).convertPixelDataToBufferedImage());
    imageName = newImageName;
    imageSaveTracker = false;
  }

  @Override
  public void redComponent() {
    processCommand("red-component", (imageName) -> "red" + imageName);
  }

  @Override
  public void greenComponent() {
    processCommand("green-component", (imageName) -> "green" + imageName);
  }

  @Override
  public void blueComponent() {
    processCommand("blue-component", (imageName) -> "blue" + imageName);
  }

  @Override
  public void horizontalFlip() {
    processCommand("horizontal-flip", (imageName)
        -> "horizontalFlip" + imageName);
  }

  @Override
  public void verticalFlip() {
    processCommand("vertical-flip", (imageName)
        -> "verticalFlip" + imageName);
  }

  @Override
  public void compression() {
    int compressionFactor = view.getCompressionFactor();
    String compressedImageName = "compressed" + imageName;
    String command = "compress " + compressionFactor + " " + imageName
            + " " + compressedImageName;
    executeLine(command);
    view.setImage(imageList.get(compressedImageName).convertPixelDataToBufferedImage(),
            histogram(compressedImageName).convertPixelDataToBufferedImage());
    imageName = compressedImageName;
  }

  private void executeOperation(BiFunction<String, Integer,
          String> operation, int defaultFactor) {
    String resultImageName = operation.apply(imageName, defaultFactor);
    int choice = view.showSplitImageOperationMenu(
            imageList.get(resultImageName)
                    .convertPixelDataToBufferedImage(),
            histogram(resultImageName).convertPixelDataToBufferedImage());
    int previousChoice = choice;

    while (choice >= 0 && choice <= 100) {
      resultImageName = operation.apply(imageName, choice);
      choice = view.showSplitImageOperationMenu(
              imageList.get(resultImageName)
                      .convertPixelDataToBufferedImage(),
              histogram(resultImageName).
                      convertPixelDataToBufferedImage());

      if (choice == -2) {
        choice = previousChoice;
      } else {
        previousChoice = choice;
      }
    }
    if (choice > 100) {
      resultImageName = operation.apply(imageName, 100);
      view.setImage(imageList.get(resultImageName).
                      convertPixelDataToBufferedImage(),
              histogram(resultImageName).
                      convertPixelDataToBufferedImage());
      imageName = resultImageName;
    } else {
      view.setImage(imageList.get(imageName).convertPixelDataToBufferedImage(),
              histogram(imageName).convertPixelDataToBufferedImage());
    }
    imageSaveTracker = false;
  }

  private void executeBlur(int blurFactor) {
    executeOperation((name, factor) -> {
      String blurredImageName = "blurred" + name;
      String command = "blur " + name + " " + blurredImageName
              + " " + "split" + " " + factor;
      executeLine(command);
      return blurredImageName;
    }, blurFactor);
  }

  @Override
  public void blur() {
    executeBlur(100);
  }

  private void executeGreyScale(int blurFactor) {
    executeOperation((name, factor) -> {
      String greyScaleImageName = "greyScale" + name;
      String command = "greyscale " + name + " "
              + greyScaleImageName + " " + "split" + " " + factor;
      executeLine(command);
      return greyScaleImageName;
    }, blurFactor);
  }

  @Override
  public void greyScale() {
    executeGreyScale(100);
  }

  private void executeSharpen(int sharpenFactor) {
    executeOperation((name, factor) -> {
      String sharpenedImageName = "sharpen" + name;
      String command = "sharpen " + name + " "
              + sharpenedImageName + " " + "split" + " " + factor;
      executeLine(command);
      return sharpenedImageName;
    }, sharpenFactor);
  }

  @Override
  public void sharpen() {
    executeSharpen(100);
  }

  private void executeSepia(int sepiaFactor) {
    executeOperation((name, factor) -> {
      String sepiaTonedImageName = "sepia" + name;
      String command = "sepia " + name + " "
              + sepiaTonedImageName + " " + "split" + " " + factor;
      executeLine(command);
      return sepiaTonedImageName;
    }, sepiaFactor);
  }

  @Override
  public void sepia() {
    executeSepia(100);
  }

  private void executeColorCorrect(int colorCorrectFactor) {
    executeOperation((name, factor) -> {
      String colorCorrectedImageName = "color-correct" + name;
      String command = "color-correct " + name + " "
              + colorCorrectedImageName + " " + "split" + " " + factor;
      executeLine(command);
      return colorCorrectedImageName;
    }, colorCorrectFactor);
  }

  @Override
  public void colorCorrect() {
    executeColorCorrect(100);
  }

  private static String extractImageName(String filePath) {
    File file = new File(filePath);
    String fileName = file.getName();

    int lastDotIndex = fileName.lastIndexOf(".");
    if (lastDotIndex != -1) {
      fileName = fileName.substring(0, lastDotIndex);
    }
    fileName = fileName.replace(" ", "_");
    return fileName;
  }

  private String executeLevelAdjustment(int shadow, int mid,
                                        int highlight, int blurFactor) {
    String levelAdjustedImageName = "levelAdjusted" + imageName;
    String command = "levels-adjust " + shadow + " " + mid + " " +
            highlight + " " + imageName
            + " " + levelAdjustedImageName + " " + "split" + " " + blurFactor;
    executeLine(command);
    return levelAdjustedImageName;
  }

  @Override
  public void levelAdjustment() {
    int[] levels = view.getLevels();
    if (levels[0] == -1) {
      return;
    }
    String levelAdjustedImageName = executeLevelAdjustment(levels[0], levels[1],
            levels[2], 100);
    int choice = view.showSplitImageOperationMenu(imageList.
                    get(levelAdjustedImageName
            ).convertPixelDataToBufferedImage(),
            histogram(levelAdjustedImageName).convertPixelDataToBufferedImage());
    int previousChoice = choice;
    while (choice >= 0 && choice <= 100) {
      levelAdjustedImageName = executeLevelAdjustment(levels[0], levels[1],
              levels[2], choice);
      choice = view.showSplitImageOperationMenu(imageList.get(levelAdjustedImageName
              ).convertPixelDataToBufferedImage(),
              histogram(levelAdjustedImageName).convertPixelDataToBufferedImage());
      if (choice == -2) {
        choice = previousChoice;
      }
      else {
        previousChoice = choice;
      }
    }
    if (choice > 100) {
      levelAdjustedImageName = executeLevelAdjustment(levels[0], levels[1],
              levels[2], 100);
      view.setImage(imageList.get(levelAdjustedImageName).
                      convertPixelDataToBufferedImage(),
              histogram(levelAdjustedImageName).convertPixelDataToBufferedImage());
      imageName = levelAdjustedImageName;
    }
    else {
      view.setImage(imageList.get(imageName).convertPixelDataToBufferedImage(),
              histogram(imageName).convertPixelDataToBufferedImage());
    }
    imageSaveTracker = false;
  }

  private ImageInfo histogram(String imageName) {
    String histogramName = imageName + "Histogram";
    String command = "histogram " + imageName + " " + histogramName;
    executeLine(command);
    return imageList.get(histogramName);
  }
}
