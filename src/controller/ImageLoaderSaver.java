package controller;

import model.ImageInfo;

/**
 * This interface defines methods for loading and saving image data.
 * Classes that implement this interface are responsible for handling
 * image loading and saving operations.
 */
interface ImageLoaderSaver {

  /**
   * Loads image data from a file located at the specified path.
   *
   * @param imagePath The path to the image file to be loaded.
   * @return An `AbstractImageData` object representing the loaded image data.
   */
  ImageInfo loadImage(String imagePath, UserIO io);

  /**
   * Saves image data to a file at the specified destination path.
   *
   * @param destinationFilePath Path where the image should be saved,
   *                            including the desired filename and extension.
   * @param imageData           An `AbstractImageData` object representing image data to be saved.
   */
  void saveImage(String destinationFilePath, ImageInfo imageData, UserIO io);
}
