package view;

import java.io.File;

/**
 * This class implements the FileFilter class to filter out files that are not supported image
 * extensions.
 */
class FileFilter extends javax.swing.filechooser.FileFilter {

  /**
   * Returns true if the given file is a directory or has a supported image extension.
   * @param file the File to test
   * @return true if the given file path or has a supported image extension.
   */
  @Override
  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    }
    String extension = getExtension(file);
    return extension != null && (extension.equals("jpg") || extension.equals("jpeg") ||
            extension.equals("ppm") || extension.equals("png"));
  }

  /**
   * Returns the description of the file filter.
   * @return the description of the file filter.
   */
  @Override
  public String getDescription() {
    return "Image Files (jpg, jpeg, ppm, png)";
  }

  private String getExtension(File file) {
    String name = file.getName();
    int lastIndexOf = name.lastIndexOf(".");
    if (lastIndexOf == -1 || lastIndexOf == 0 || lastIndexOf == name.length() - 1) {
      return null;
    }
    return name.substring(lastIndexOf + 1);
  }

}