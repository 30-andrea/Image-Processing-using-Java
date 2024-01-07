package controller;

import java.io.IOException;

import model.EnhancedImageProcessingModel;
import model.ImageInfo;

/**
 * The EnhancedController class adds additional functionality to the Controller class.
 * It adds the following commands:
 * - compress
 * - histogram
 * - color-correct
 * - levels-adjust
 * - running a script file through command line arguments.
 */
public class EnhancedController extends Controller {

  /**
   * Constructor for the enhanced controller with input/output, model and args.
   *
   * @param io    The user input/output interface for communication.
   * @param model The ImageProcessingModel responsible for image processing operations.
   * @param args  The command line arguments.
   */
  public EnhancedController(UserIO io, EnhancedImageProcessingModel model,
                            String[] args) {
    super(io, model);
    if (args.length > 1) {
      processArgs(args);
    }
  }

  private static String combineArgs(String[] scriptFileArgs) {
    if (scriptFileArgs.length < 2) {
      return "";
    }
    StringBuilder combinedString = new StringBuilder(escapeSpaces(scriptFileArgs[1]));
    for (int i = 2; i < scriptFileArgs.length; i++) {
      combinedString.append(" ").append(escapeSpaces(scriptFileArgs[i]));
    }
    return combinedString.toString();
  }

  private void processArgs(String[] scriptFileArgs) {
    runScriptFile(combineArgs(scriptFileArgs));
    executeLine("exit");
  }

  @Override
  void executeLine(String line) {
    if (line.trim().startsWith("#") || line.trim().isEmpty()) {
      return;
    }
    String[] words = line.split(" ");
    if (words.length > 0) {
      switch (words[0].toLowerCase()) {
        case "compress":
          handleCompression(words);
          break;
        case "histogram":
          createHistogram(words);
          break;
        case "color-correct":
          createColorCorrectedImage(words);
          break;
        case "levels-adjust":
          createLevelAdjustedImage(words);
          break;
        default:
          super.executeLine(line);
      }
    }
  }

  private void handleCompression(String[] words) {
    if (words.length >= 4) {
      ImageInfo image = imageList.get(words[2]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      if (!isInteger(words[1])) {
        io.print("Compression percentage value should be an integer.");
        return;
      }
      int compressionPercentage = Integer.parseInt(words[1]);
      if (compressionPercentage < 0 || compressionPercentage > 100) {
        io.print("Compression percentage should be between 0 and 100.");
        return;
      }
      ImageInfo compressedImage;
      try {
        compressedImage = model.compressImage(image,
                compressionPercentage);
      } catch (IllegalArgumentException e) {
        io.print(e.getMessage());
        return;
      }
      imageList.put(words[3], compressedImage);
    } else {
      io.print("Invalid 'compress' command. Should have 4 arguments.");
    }
  }

  private void createHistogram(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo histogram;
      try {
        histogram = model.produceHistogram(image);
      } catch (IllegalArgumentException e) {
        io.print("Unable to create histogram of image.");
        return;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      imageList.put(words[2], histogram);
    } else {
      io.print("Invalid 'histogram' command. Should have 3 arguments.");
    }
  }

  private void createColorCorrectedImage(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo colorCorrectedImage;
      int percentage;
      try {
        if (words.length > 3 && "split".equals(words[3])) {
          percentage = validatePercentage(words[4]);
          if (percentage == -1) {
            return;
          }
          colorCorrectedImage = model.splitColorCorrect(image, percentage);
        } else {
          colorCorrectedImage = model.colorCorrectImage(image);
        }
      } catch (IllegalArgumentException e) {
        io.print("Unable to perform sharpening on the image.");
        return;
      }
      imageList.put(words[2], colorCorrectedImage);
    } else {
      io.print("Invalid 'color-correct' command. Should have 3 arguments.");
    }
  }

  private void createLevelAdjustedImage(String[] words) {
    if (words.length >= 6) {
      ImageInfo image = imageList.get(words[4]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      if (!isInteger(words[1]) || !isInteger(words[2]) || !isInteger(words[3])) {
        io.print("Shadow, mid and highlight values should be integers.");
        return;
      }
      int shadow = Integer.parseInt(words[1]);
      int mid = Integer.parseInt(words[2]);
      int highlight = Integer.parseInt(words[3]);
      if (shadow < 0 || shadow > 255 || mid < 0 || mid > 255
              || highlight < 0 || highlight > 255) {
        io.print("Shadow, mid and highlight values should be between 0 and 255.");
        return;
      }
      if (shadow > mid || mid > highlight) {
        io.print("Shadow value should be less than mid value and mid value should" +
                " be less than highlight value.");
        return;
      }
      ImageInfo levelAdjustedImage;
      int percentage;
      try {
        if (words.length > 6 && "split".equals(words[6])) {
          percentage = validatePercentage(words[7]);
          if (percentage == -1) {
            return;
          }
          levelAdjustedImage = model.splitLevelAdjustment(image,
                  shadow, mid, highlight, percentage);
        } else {
          levelAdjustedImage = model.levelAdjustment(image, shadow, mid, highlight);
        }
      } catch (IllegalArgumentException e) {
        io.print("Unable to perform level adjustment on the image.");
        return;
      }
      imageList.put(words[5], levelAdjustedImage);
    } else {
      io.print("Invalid 'levels-adjust' command. Should have 6 arguments.");
    }
  }
}
