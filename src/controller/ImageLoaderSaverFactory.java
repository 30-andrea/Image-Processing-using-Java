package controller;


/**
 * This class is a factory for creating ImageLoaderSaver objects. Returns the suitable
 * ImageLoaderSaver object based on the file extension.
 */
class ImageLoaderSaverFactory {

  /**
   * This static class creates an ImageLoaderSaver object based on the given file extension.
   *
   * @param fileExtension The file extension used to determine the type of ImageLoaderSaver.
   * @return An ImageLoaderSaver object suitable for the specified file extension.
   */
  static ImageLoaderSaver createImageLoaderSaver(String fileExtension) {
    if (fileExtension.equals("ppm")) {
      return new PPMImageLoaderSaver();
    } else {
      return new OtherImageLoaderSaver();
    }
  }
}
