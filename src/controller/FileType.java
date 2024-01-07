package controller;

/**
 * Enumeration of supported file formats for image processing.
 */
public enum FileType {
  PPM,
  JPEG,
  PNG;

  /**
   * Returns corresponding enum value for the file extension.
   *
   * @param extension The file extension.
   * @return The corresponding enum value for the file extension.
   */
  public static FileType fromExtension(String extension) {
    if (extension != null) {
      switch (extension.toLowerCase()) {
        case "ppm":
          return PPM;
        case "jpeg":
        case "jpg":
          return JPEG;
        case "png":
          return PNG;
        default:
          return null;
      }
    }
    return null;
  }
}