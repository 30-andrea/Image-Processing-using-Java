package controller;

import java.io.IOException;
import java.util.List;

/**
 * Defines methods for interacting with user input and output.
 * Implementations of this interface are used to read user input, print messages to the user, and
 * retrieve lines of text from user interactions.
 */
public interface UserIO {

  /**
   * Reads a line of text from the user.
   *
   * @return A string representing the user's input line.
   * @throws IOException if there is an error while reading user input.
   */
  String readLine() throws IOException;

  /**
   * Retrieves a list of lines of text from user interactions.
   *
   * @return A list of strings, where each string represents a line of text entered by the user.
   */
  List<String> getLines();

  /**
   * Retrieves a list of output lines to display to the user.
   *
   * @return A list of strings, each representing an output line.
   */
  List<String> getOutputLines();

  /**
   * Displays a message to the user.
   *
   * @param message The message to be displayed.
   */
  void print(String message);
}
