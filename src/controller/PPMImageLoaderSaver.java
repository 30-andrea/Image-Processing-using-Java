package controller;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.ImageData;
import model.ImageInfo;

/**
 * This class is an implementation of the ImageLoaderSaver interface for loading and saving
 * images in the PPM (Portable Pixel Map) format.
 */
class PPMImageLoaderSaver implements ImageLoaderSaver {
  @Override
  public ImageInfo loadImage(String imagePath, UserIO io) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(imagePath));
    } catch (FileNotFoundException e) {
      io.print("File not found at the given path");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine().trim();
      if (!s.isEmpty() && s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      io.print("Invalid PPM file: plain RAW file should begin with P3");
      return null;
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    if (maxValue != 255) {
      io.print("Invalid PPM file: Maximum color value should be 255.");
      return null;
    }

    int[][][] pixelData = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixelData[i][j][0] = r;
        pixelData[i][j][1] = g;
        pixelData[i][j][2] = b;
      }
    }
    return new ImageData(pixelData);
  }

  @Override
  public void saveImage(String destinationFilePath, ImageInfo imageData, UserIO io) {
    if (imageData == null) {
      return;
    }
    int height = imageData.getHeight();
    int width = imageData.getWidth();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n");
      int red;
      int green;
      int blue;
      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) {
          red = imageData.getPixelValue(x, y, 0);
          green = imageData.getPixelValue(x, y, 1);
          blue = imageData.getPixelValue(x, y, 2);

          writer.write(red + " " + green + " " + blue + " ");
        }
        writer.write("\n");
      }
    } catch (FileNotFoundException e) {
      io.print("Destination file/folder path not found.");
    } catch (IOException e) {
      io.print("Error saving the image.");
    }
  }
}
