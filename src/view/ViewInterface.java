package view;


/**
 * This interface defines the contract for classes
 * responsible for displaying messages to users or other output mechanisms.
 */
public interface ViewInterface {

  /**
   * Displays a message to the user or an output mechanism.
   *
   * @param message The message to be displayed.
   */
  void display(String message);

}
