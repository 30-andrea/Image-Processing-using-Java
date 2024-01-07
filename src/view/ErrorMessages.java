package view;

/**
 * This enum class contains all the error messages that can be displayed to the user.
 */
public enum ErrorMessages {
  ERROR_VALID_INTEGER("Invalid input. Please enter a valid integer."),
  ERROR_VALID_INTEGER_BETWEEN_0_AND_100("Please enter a valid integer between 0 and 100."),
  ERROR_VALID_INTEGER_BETWEEN_0_AND_255("Please enter a valid integer between 0 and 255."),
  ERROR_INVALID_FILE("Only support png, jpg, jpeg and ppm extensions. Please try again.");


  private final String message;

  /**
   * Constructs an error message with the given message.
   * @param message the message.
   */
  ErrorMessages(String message) {
    this.message = message;
  }

  /**
   * Returns the message for the error.
   * @return the message.
   */
  public String getMessage() {
    return message;
  }
}
