package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import model.ImageData;
import model.ImageInfo;

/**
 * This class is an implementation of the ImageLoaderSaver interface for loading and saving
 * images using the standard Java ImageIO library.
 */
class OtherImageLoaderSaver implements ImageLoaderSaver {
  @Override
  public ImageInfo loadImage(String imagePath, UserIO io) {
    try {
      File file = new File(imagePath);
      if (!file.exists()) {
        io.print("File not found at the given path.");
        return null;
      }
      BufferedImage image = ImageIO.read(file);
      return new ImageData(image);
    } catch (FileNotFoundException e) {
      io.print("File not found at the given path");
      return null;
    } catch (IOException e) {
      io.print("Error loading the image: " + e.getMessage());
      return null;
    }
  }


  @Override
  public void saveImage(String destinationFilePath, ImageInfo imageData, UserIO io) {
    String fileExtension = destinationFilePath.substring(
            destinationFilePath.lastIndexOf('.') + 1).toLowerCase();
    BufferedImage image;
    if (imageData != null) {
      image = imageData.convertPixelDataToBufferedImage();
    } else {
      return;
    }
    try (OutputStream os = new FileOutputStream(destinationFilePath)) {
      ImageIO.write(image, fileExtension, os);
    } catch (FileNotFoundException e) {
      io.print("Destination file/folder path not found.");
    } catch (IOException e) {
      io.print("Error saving the image.");
    }
  }
}
