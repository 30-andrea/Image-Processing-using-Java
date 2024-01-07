package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import view.ViewInterface;

/**
 * The `ConsoleUserIO` class is an implementation of the `UserIO` interface for
 * handling user input through the console. It provides methods for reading
 * lines of text entered by the user via the console.
 */
public class ConsoleUserIO implements UserIO {
  private final BufferedReader reader;
  private final ViewInterface view;

  /**
   * Constructs a new ConsoleUserIO instance with the specified ViewInterface.
   *
   * @param view The ViewInterface used for displaying messages.
   */
  public ConsoleUserIO(ViewInterface view) {
    reader = new BufferedReader(new InputStreamReader(System.in));
    this.view = view;
  }

  @Override
  public String readLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      System.out.println("Unable to read the line");
      return null;
    }
  }

  /**
   * Returns the lines of text, used only for testing.
   * @return the lines of text.
   */
  @Override
  public List<String> getLines() {
    return null;
  }


  /**
   * Returns the output lines, used only for testing.
   * @return the output lines.
   */
  @Override
  public List<String> getOutputLines() {
    return null;
  }

  @Override
  public void print(String message) {
    view.display(message);
  }
}
