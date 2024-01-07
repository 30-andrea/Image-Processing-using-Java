package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.EnhancedImageProcessingModel;
import model.ImageInfo;


/**
 * The controller class is responsible for handling user input,
 * executing image processing commands - interacting
 * with the model and view class.
 */
public class Controller implements ImageProcessingController {
  protected final UserIO io;
  protected final EnhancedImageProcessingModel model;
  protected final Map<String, ImageInfo> imageList;
  private boolean shouldExit = false;

  /**
   * Constructs a new Controller with the provided ImageProcessingModel and UserIO.
   *
   * @param io    The user input/output interface for communication.
   * @param model The ImageProcessingModel responsible for image processing operations.
   */
  public Controller(UserIO io, EnhancedImageProcessingModel model) {
    this.io = io;
    this.model = model;
    this.imageList = new HashMap<>();
  }

  @Override
  public void execute() {
    String line;

    while (!shouldExit) {
      io.print("Enter a command: ");
      try {
        line = io.readLine();
      } catch (IOException e) {
        io.print("Error reading input: " + e.getMessage());
        return;
      }

      if (line == null || line.trim().isEmpty()) {
        io.print("Empty input. Please enter a valid command.");
        continue;
      }
      if (line.trim().startsWith("#")) {
        continue;
      }
      io.print("> " + line);

      String[] words = line.split(" ");
      String command = words[0].toLowerCase();

      switch (command) {
        case "run":
          if (words.length >= 2) {
            String scriptFile = words[1];
            runScriptFile(scriptFile);
          } else {
            io.print("Missing script file for 'run' command.");
          }
          break;
        case "exit":
          io.print("Exiting the program.");
          shouldExit = true;
          break;
        default:
          executeLine(line);
      }
    }
  }

  void runScriptFile(String scriptFile) {
    try {
      String fileExtension = scriptFile.substring(
              scriptFile.lastIndexOf('.') + 1).toLowerCase();
      if (!fileExtension.equals("txt")) {
        io.print("Invalid script file extension. Should be .txt");
        return;
      }
      FileReader fileReader = new FileReader(scriptFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      io.print("Running script file.");
      String line;

      while (!shouldExit && (line = bufferedReader.readLine()) != null) {
        executeLine(line);
      }

      bufferedReader.close();
    } catch (FileNotFoundException e) {
      io.print("Script file not found at provided location.");
    } catch (IOException e) {
      io.print("Error reading the script file: " + e.getMessage());
    }
  }

  void executeLine(String line) {
    if (line.trim().startsWith("#") || line.trim().isEmpty()) {
      return;
    }
    String[] words = line.split(" ");
    if (words.length > 0) {
      switch (words[0].toLowerCase()) {
        case "load":
          handleLoadCommand(words);
          break;
        case "save":
          handleSaveCommand(words);
          break;
        case "brighten":
          handleBrightnessCommand(words);
          break;
        case "red-component":
          handleRedComponentCommand(words);
          break;
        case "green-component":
          handleGreenComponentCommand(words);
          break;
        case "blue-component":
          handleBlueComponentCommand(words);
          break;
        case "value-component":
          handleValueComponent(words);
          break;
        case "luma-component":
          handleLumaComponent(words);
          break;
        case "intensity-component":
          handleIntensityComponent(words);
          break;
        case "horizontal-flip":
          handleFlipHorizontally(words);
          break;
        case "vertical-flip":
          handleFlipVertically(words);
          break;
        case "rgb-split":
          handleRGBSplit(words);
          break;
        case "rgb-combine":
          handleRGBCombine(words);
          break;
        case "blur":
          handleBlur(words);
          break;
        case "sharpen":
          handleSharpen(words);
          break;
        case "sepia":
          handleSepiaConversion(words);
          break;
        case "greyscale":
          handleGreyscaleConversion(words);
          break;
        case "exit":
          shouldExit = true;
          break;
        default:
          io.print("Invalid command, please try something else.");
      }
    }
  }

  protected static String escapeSpaces(String arg) {
    if (arg.endsWith("\\")) {
      arg = arg.substring(0, arg.length() - 1);
    }
    return arg;
  }

  private String[] escapeSpaces(String[] words) {
    StringBuilder pathBuilder = new StringBuilder(escapeSpaces(words[1]));
    for (int i = 2; i < words.length - 1; i++) {
      pathBuilder.append(" ").append(escapeSpaces(words[i]));
    }
    return new String[]{pathBuilder.toString(), words[words.length - 1]};
  }

  private void handleLoadCommand(String[] words) {
    if (words.length >= 3) {
      String[] pathAndName = escapeSpaces(words);
      String path = pathAndName[0];
      String imageName = pathAndName[1];
      loadImage(path, imageName, io);
    } else {
      io.print("Invalid 'load' command. Should have 3 arguments.");
    }
  }

  private void handleSaveCommand(String[] words) {
    if (words.length >= 3) {
      String[] pathAndName = escapeSpaces(words);
      String path = pathAndName[0];
      String imageName = pathAndName[1];
      saveImage(path, imageName, io);
    } else {
      io.print("Invalid 'save' command. Should have 3 arguments.");
    }
  }

  boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private void handleBrightnessCommand(String[] words) {
    if (words.length >= 4) {
      ImageInfo image = imageList.get(words[2]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      if (!isInteger(words[1])) {
        io.print("Brightness value should be an integer.");
        return;
      }
      int brightness = Integer.parseInt(words[1]);
      ImageInfo brightenedImage;
      try {
        brightenedImage = model.adjustBrightness(image,
                brightness);
      } catch (IllegalArgumentException e) {
        io.print(e.getMessage());
        return;
      }
      imageList.put(words[3], brightenedImage);
    } else {
      io.print("Invalid 'brighten' command. Should have 4 arguments.");
    }
  }

  private void handleRedComponentCommand(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo redImage;
      try {
        redImage = model.createRedComponentOfImage(image);
      } catch (IllegalArgumentException e) {
        io.print(e.getMessage());
        return;
      }
      imageList.put(words[2], redImage);
    } else {
      io.print("Invalid 'red-component' command. Should have 3 arguments.");
    }
  }

  private void handleGreenComponentCommand(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo greenImage;
      try {
        greenImage = model.createGreenComponentOfImage(image);
      } catch (IllegalArgumentException e) {
        io.print(e.getMessage());
        return;
      }
      imageList.put(words[2], greenImage);
    } else {
      io.print("Invalid 'green-component' command. Should have 3 arguments.");
    }
  }

  private void handleBlueComponentCommand(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo blueImage;
      try {
        blueImage = model.createBlueComponentOfImage(image);
      } catch (IllegalArgumentException e) {
        io.print(e.getMessage());
        return;
      }
      imageList.put(words[2], blueImage);
    } else {
      io.print("Invalid 'blue-component' command. Should have 3 arguments.");
    }
  }

  private void handleValueComponent(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo valueImage;
      try {
        valueImage = model.createValueComponentOfImage(image);
      } catch (IllegalArgumentException e) {
        io.print("Cannot visualise the value component of this image.");
        return;
      }

      imageList.put(words[2], valueImage);
    } else {
      io.print("Invalid 'value-component' command. Should have 3 arguments.");
    }
  }

  private void handleLumaComponent(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo lumaImage;
      try {
        lumaImage = model.createLumaComponentOfImage(image);
      } catch (IllegalArgumentException e) {
        io.print("Cannot visualise the luma component of this image.");
        return;
      }
      imageList.put(words[2], lumaImage);
    } else {
      io.print("Invalid 'luma-component' command. Should have 3 arguments.");
    }
  }

  private void handleIntensityComponent(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo intensityImage;
      try {
        intensityImage = model.createIntensityComponentOfImage(image);
      } catch (IllegalArgumentException e) {
        io.print("Cannot visualise the intensity component of this image.");
        return;
      }
      imageList.put(words[2], intensityImage);
    } else {
      io.print("Invalid 'intensity-component' command. Should have 3 arguments.");
    }
  }

  private void handleFlipHorizontally(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo horizontallyFlippedImage;
      try {
        horizontallyFlippedImage = model.flipImageHorizontally(image);
      } catch (IllegalArgumentException e) {
        io.print("Unable to flip the image horizontally.");
        return;
      }
      imageList.put(words[2], horizontallyFlippedImage);
    } else {
      io.print("Invalid 'horizontal-flip' command. Should have 3 arguments.");
    }
  }

  private void handleFlipVertically(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo verticallyFlippedImage;
      try {
        verticallyFlippedImage = model.flipImageVertically(image);
      } catch (IllegalArgumentException e) {
        io.print("Unable to flip the image vertically.");
        return;
      }
      imageList.put(words[2], verticallyFlippedImage);
    } else {
      io.print("Invalid 'vertical-flip' command. Should have 3 arguments.");
    }
  }

  private void handleRGBSplit(String[] words) {
    if (words.length >= 5) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo[] splitImages;
      try {
        splitImages = model.splitImageIntoColorComponents(image);
      } catch (IllegalArgumentException e) {
        io.print("Unable to split the image.");
        return;
      }
      imageList.put(words[2], splitImages[0]);
      imageList.put(words[3], splitImages[1]);
      imageList.put(words[4], splitImages[2]);
    } else {
      io.print("Invalid 'rgb-split' command. Should have 5 arguments.");
    }
  }

  private void handleRGBCombine(String[] words) {
    if (words.length >= 5) {
      ImageInfo redImage = imageList.get(words[2]);
      ImageInfo greenImage = imageList.get(words[3]);
      ImageInfo blueImage = imageList.get(words[4]);
      if (redImage == null || greenImage == null || blueImage == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo combinedImage;
      try {
        combinedImage = model.combineColorComponents(redImage,
                greenImage, blueImage);
      } catch (IllegalArgumentException e) {
        io.print("Unable to combine the images.");
        return;
      }
      imageList.put(words[1], combinedImage);
    } else {
      io.print("Invalid 'rgb-combine' command. Should have 5 arguments.");
    }
  }

  private ImageInfo getImage(String imageName) {
    ImageInfo image = imageList.get(imageName);
    if (image == null) {
      io.print("Wrong image name provided.");
      return null;
    }
    return image;
  }

  private void handleBlur(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = getImage(words[1]);
      if (image == null) {
        return;
      }
      ImageInfo blurredImage;
      if (words.length > 3 && "split".equals(words[3])) {
        int percentage;
        percentage = validatePercentage(words[4]);
        if (percentage == -1) {
          return;
        }
        try {
          blurredImage = model.splitBlur(image, percentage);
        } catch (IllegalArgumentException e) {
          io.print("Unable to perform blurring on the image.");
          return;
        }
        imageList.put(words[2], blurredImage);
      } else {
        try {
          blurredImage = model.blurImage(image);
        } catch (IllegalArgumentException e) {
          io.print("Unable to perform blurring on the image.");
          return;
        }
      }
      imageList.put(words[2], blurredImage);
    } else {
      io.print("Invalid 'blur' command. Should have 3 arguments.");
    }
  }

  int validatePercentage(String percentageStr) {
    if (!isInteger(percentageStr)) {
      io.print("Percentage of image width should be an integer.");
      return -1;
    }

    int percentage = Integer.parseInt(percentageStr);
    if (percentage < 0 || percentage > 100) {
      io.print("Percentage of image width should be between 0 and 100.");
      return -1;
    }

    return percentage;
  }

  private void handleSharpen(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo sharpenedImage;
      int percentage;
      try {
        if (words.length > 3 && "split".equals(words[3])) {
          percentage = validatePercentage(words[4]);
          if (percentage == -1) {
            return;
          }
          sharpenedImage = model.splitSharpen(image, percentage);
        } else {
          sharpenedImage = model.sharpenImage(image);
        }
      } catch (IllegalArgumentException e) {
        io.print("Unable to perform sharpening on the image.");
        return;
      }
      imageList.put(words[2], sharpenedImage);
    } else {
      io.print("Invalid 'sharpen' command. Should have 3 arguments.");
    }
  }

  private void handleSepiaConversion(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo sepiaTonedImage;
      int percentage;
      try {
        if (words.length > 3 && "split".equals(words[3])) {
          percentage = validatePercentage(words[4]);
          if (percentage == -1) {
            return;
          }
          sepiaTonedImage = model.splitSepia(image, percentage);
        } else {
          sepiaTonedImage = model.sepiaToneImage(image);
        }
      } catch (IllegalArgumentException e) {
        io.print("Unable to perform sepia conversion on the image.");
        return;
      }
      imageList.put(words[2], sepiaTonedImage);
    } else {
      io.print("Invalid 'sepia' command. Should have 3 arguments.");
    }
  }

  private void handleGreyscaleConversion(String[] words) {
    if (words.length >= 3) {
      ImageInfo image = imageList.get(words[1]);
      if (image == null) {
        io.print("Wrong image name provided.");
        return;
      }
      ImageInfo greyscaleImage;
      int percentage;
      try {
        if (words.length > 3 && "split".equals(words[3])) {
          percentage = validatePercentage(words[4]);
          if (percentage == -1) {
            return;
          }
          greyscaleImage = model.splitGreyscale(image, percentage);
        } else {
          greyscaleImage = model.greyscaleImage(image);
        }
      } catch (IllegalArgumentException e) {
        io.print("Unable to perform greyscale conversion on the image.");
        return;
      }
      imageList.put(words[2], greyscaleImage);
    } else {
      io.print("Invalid 'greyscale' command. Should have 3 arguments.");
    }
  }

  private void loadImage(String imagePath, String imageName, UserIO io) {
    ImageInfo imageInfo;
    String fileExtension = imagePath.substring(imagePath.lastIndexOf('.')
            + 1).toLowerCase();
    FileType fileType = FileType.fromExtension(fileExtension);
    if (fileType == null) {
      io.print("Unsupported file extension: " + fileExtension);
    }
    ImageLoaderSaver imageLoadersaver = ImageLoaderSaverFactory.createImageLoaderSaver(
            fileExtension);
    imageInfo = imageLoadersaver.loadImage(imagePath, io);
    imageList.put(imageName, imageInfo);
  }

  private void saveImage(String destinationFilePath, String imageName, UserIO io) {
    String fileExtension = destinationFilePath.substring(
            destinationFilePath.lastIndexOf('.') + 1).toLowerCase();
    FileType fileType = FileType.fromExtension(fileExtension);
    if (fileType == null) {
      io.print("Unsupported file extension: " + fileExtension);
    }
    ImageLoaderSaver imageLoadersaver = ImageLoaderSaverFactory.createImageLoaderSaver(
            fileExtension);
    ImageInfo imageInfo = imageList.get(imageName);
    if (imageInfo == null) {
      io.print("File not found for saving");
    }
    imageLoadersaver.saveImage(destinationFilePath, imageInfo, io);
  }
}








