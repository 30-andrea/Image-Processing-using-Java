package controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the EnhancedController class.
 */
public class EnhancedControllerTest {

  @Test
  public void testCompressCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 201;
    List<String> list = Arrays.asList("load res/random.png png",
            "compress 20 png pngCompressed",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedModelLog = "Method: compressImage (" + uniqueCode + ")"
            + "\nPercentage: 20"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " compress 20 png pngCompressed," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , "
            + "> compress 20 png pngCompressed, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testCompressNegativePercentage() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 202;
    List<String> list = Arrays.asList("load res/random.png png",
            "compress -20 png pngCompressed",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " compress -20 png pngCompressed," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png" +
            ", Enter a command: , > compress -20 png pngCompressed" +
            ", Compression percentage should be between 0 and 100." +
            ", Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testCompressAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 203;
    List<String> list = Arrays.asList("load res/random.png png",
            "compress 120 png pngCompressed",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " compress 120 png pngCompressed," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png" +
            ", Enter a command: , > compress 120 png pngCompressed" +
            ", Compression percentage should be between 0 and 100." +
            ", Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testHistogramCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 204;
    List<String> list = Arrays.asList("load res/random.png png",
            "histogram png pngHistogram",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedModelLog = "Method: produceHistogram (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " histogram png pngHistogram," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , "
            + "> histogram png pngHistogram, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testHistogramInvalidCommands() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 205;
    List<String> list = Arrays.asList("load res/random.png png",
            "produceHistogram png pngHistogram",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " produceHistogram png pngHistogram," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> produceHistogram png pngHistogram, Invalid command, please try something else.," +
            " Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testHistogramExtraCommands() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 206;
    List<String> list = Arrays.asList("load res/random.png png",
            "histogram rgb-graph png pngHistogram",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " histogram rgb-graph png pngHistogram," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> histogram rgb-graph png pngHistogram, Wrong image name provided.," +
            " Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testColorCorrect() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 207;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct png pngColorCorrected",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedModelLog = "Method: colorCorrectImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " color-correct png pngColorCorrected," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , "
            + "> color-correct png pngColorCorrected, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testColorCorrectInvalidCommands() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 208;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct-image png pngColorCorrected",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " color-correct-image png pngColorCorrected," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> color-correct-image png pngColorCorrected, " +
            "Invalid command, please try something else., Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testColorCorrectExtraCommands() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 209;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct-red color-correct-green color-correct-blue png pngColorCorrected",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " color-correct-red color-correct-green color-correct-blue png pngColorCorrected," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> color-correct-red color-correct-green color-correct-blue png pngColorCorrected, " +
            "Invalid command, please try something else., Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustment() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 210;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 30 180 245 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedModelLog = "Method: levelAdjustment (" + uniqueCode + ")"
            + "\nShadow: 30"
            + "\nMid: 180"
            + "\nHighlight: 245"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 30 180 245 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 30 180 245 png pngLevelAdjusted, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testLevelAdjustmentVariables() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 211;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust b m w png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust b m w png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust b m w png pngLevelAdjusted, " +
            "Shadow, mid and highlight values should be integers., Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentNegativeLevels() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 212;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust -10 -50 -100 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust -10 -50 -100 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust -10 -50 -100 png pngLevelAdjusted, " +
            "Shadow, mid and highlight values should be between 0 and 255., " +
            "Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentAbove255() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 213;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 20 135 260 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 20 135 260 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 20 135 260 png pngLevelAdjusted, " +
            "Shadow, mid and highlight values should be between 0 and 255., " +
            "Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentWrongOrder() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 212;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 135 20 200 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 135 20 200 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 135 20 200 png pngLevelAdjusted, " +
            "Shadow value should be less than mid value and mid value " +
            "should be less than highlight value., " +
            "Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentWrongOrder2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 212;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 20 200 180 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 20 200 180 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 20 200 180 png pngLevelAdjusted, " +
            "Shadow value should be less than mid value and mid value " +
            "should be less than highlight value., " +
            "Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentInvalidCommands1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 214;
    List<String> list = Arrays.asList("load res/random.png png",
            "adjust black=20 mid=135 white=240 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " adjust black=20 mid=135 white=240 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> adjust black=20 mid=135 white=240 png pngLevelAdjusted, " +
            "Invalid command, please try something else., Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentInvalidCommands2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 215;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 100 200 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 100 200 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 100 200 png pngLevelAdjusted, " +
            "Invalid 'levels-adjust' command. Should have 6 arguments., " +
            "Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testLevelAdjustmentExtraCommands() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 216;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 20 135 240 300 png pngLevelAdjusted",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 20 135 240 300 png pngLevelAdjusted," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 20 135 240 300 png pngLevelAdjusted, " +
            "Wrong image name provided., Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitColorCorrect() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 217;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct png pngSplitColorCorrect split 50",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedModelLog = "Method: splitColorCorrect (" + uniqueCode + ")"
            + "\nPercentage: 50"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " color-correct png pngSplitColorCorrect split 50," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> color-correct png pngSplitColorCorrect split 50, " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitColorCorrectNegativePercent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 218;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct png pngSplitColorCorrect split -50",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " color-correct png pngSplitColorCorrect split -50," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> color-correct png pngSplitColorCorrect split -50, " +
            "Percentage of image width should be between 0 and 100., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitColorCorrectAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 219;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct png pngSplitColorCorrect split 110",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " color-correct png pngSplitColorCorrect split 110," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> color-correct png pngSplitColorCorrect split 110, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitColorCorrectVariable() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 220;
    List<String> list = Arrays.asList("load res/random.png png",
            "color-correct png pngSplitColorCorrect split p",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " color-correct png pngSplitColorCorrect split p," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , > color-correct png pngSplitColorCorrect split p, " +
            "Percentage of image width should be an integer., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitLevelAdjustment() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 221;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 0 128 255 png pngSplitLevelAdjustment split 45",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedModelLog = "Method: splitLevelAdjustment (" + uniqueCode + ")"
            + "\nPercentage: 45"
            + "\nShadow: 0"
            + "\nMid: 128"
            + "\nHighlight: 255"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 0 128 255 png pngSplitLevelAdjustment split 45," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 0 128 255 png pngSplitLevelAdjustment split 45, " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitLevelAdjustmentNegativePercent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 222;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 0 128 255 png pngSplitLevelAdjustment split -45",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 0 128 255 png pngSplitLevelAdjustment split -45," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 0 128 255 png pngSplitLevelAdjustment split -45, " +
            "Percentage of image width should be between 0 and 100., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitLevelAdjustmentAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 223;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 0 128 255 png pngSplitLevelAdjustment split 110",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 0 128 255 png pngSplitLevelAdjustment split 110," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> levels-adjust 0 128 255 png pngSplitLevelAdjustment split 110, " +
            "Percentage of image width should be between 0 and 100., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitLevelAdjustmentVariable() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 224;
    List<String> list = Arrays.asList("load res/random.png png",
            "levels-adjust 0 128 255 png pngSplitLevelAdjustment split p",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), new String[0]);
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " levels-adjust 0 128 255 png pngSplitLevelAdjustment split p," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , > levels-adjust 0 128 255 " +
            "png pngSplitLevelAdjustment split p, " +
            "Percentage of image width should be an integer., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testScriptArgumentsValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 225;
    List<String> list = new ArrayList<>();
    UserIO io = new MockUserIO(list);
    String[] args = new String[]{"-file", "res/EmptyScript.txt"};
    new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), args);
    String expectedOutputLines = "[Running script file.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testScriptArgumentsInValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 227;
    List<String> list = new ArrayList<>();
    UserIO io = new MockUserIO(list);
    String[] args = new String[]{"-file", "res/ErrorScript"};
    new EnhancedController(io,
            new MockImageProcessingModel(modelLog, uniqueCode), args);
    String expectedOutputLines = "[Invalid script file extension. Should be .txt]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }
}