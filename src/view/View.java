package view;

/**
 * The `View` class is an implementation of the `ViewInterface` interface,
 * providing a basic mechanism for displaying messages to the console.
 */
public class View implements ViewInterface {

  @Override
  public void display(String message) {
    System.out.println(message);
  }
}
