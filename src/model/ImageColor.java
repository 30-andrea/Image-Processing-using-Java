package model;

/**
 * Enumeration representing the primary colors for image manipulation.
 */
enum ImageColor {
  RED(0),
  GREEN(1),
  BLUE(2);

  private final int index;

  /**
   * Constructs an ImageColor with the specified index.
   *
   * @param index The index representing the color channel.
   */
  ImageColor(int index) {
    this.index = index;
  }

  /**
   * Gets the index representing the color channel.
   *
   * @return The index of the color channel.
   */
  public int getIndex() {
    return index;
  }
}