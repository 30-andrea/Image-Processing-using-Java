package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the `UserIO` interface designed for testing purposes.
 */
class MockUserIO implements UserIO {
  private final List<String> inputLines;
  private final List<String> outputLines;
  private int inputIndex;

  /**
   * Constructs a mock user IO with the given input lines.
   * @param inputLines the input lines to use.
   */
  public MockUserIO(List<String> inputLines) {
    this.inputLines = new ArrayList<>(inputLines);
    this.outputLines = new ArrayList<>();
    this.inputIndex = 0;
  }

  @Override
  public String readLine() throws IOException {
    if (inputIndex < inputLines.size()) {
      String input = inputLines.get(inputIndex);
      inputIndex++;
      return input;
    } else {
      return null;
    }
  }

  @Override
  public List<String> getLines() {
    return inputLines;
  }

  @Override
  public List<String> getOutputLines() {
    return outputLines;
  }

  @Override
  public void print(String message) {
    outputLines.add(message);
  }
}