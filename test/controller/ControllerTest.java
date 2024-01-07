package controller;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


/**
 * This class tests all the public functions of the controller.
 */
public class ControllerTest {

  @Test
  public void testImageDimensions() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 100;
    List<String> list = Arrays.asList("load res/random.png png",
            "save res/pngNew.png png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " save res/pngNew.png png," +
            " exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Enter a command: "
            + ", > save res/pngNew.png png, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testEmptyLine() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 101;
    List<String> list = Arrays.asList("", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[, exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , Empty input. Please enter a valid command., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testRunWithEmptyScript1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 102;
    List<String> list = Arrays.asList("run", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[run, exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > run, Missing script file for "
            + "'run' command., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testRunWithEmptyScript2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 103;
    List<String> list = Arrays.asList("run  ", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[run  , exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > run  , Missing script file for " +
            "'run' command., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testRunWithInvalidScriptFile() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 104;
    List<String> list = Arrays.asList("run invalidScript.txt", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[run invalidScript.txt, exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > run invalidScript.txt, Script " +
            "file not found at provided location., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testExitWithoutOtherCommands() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 105;
    List<String> list = Arrays.asList("exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }


  @Test
  public void testInvalidSavePng() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 108;
    List<String> list = Arrays.asList("load res/random.png png",
            "save res/images/pngNew.png png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png png, save res/images/pngNew.png png, exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> save res/images/pngNew.png png, Destination file/folder path not " +
            "found., Enter a command: , "
            + "> exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testInvalidSaveJpg() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 109;
    List<String> list = Arrays.asList("load res/random.png jpeg",
            "save res/images/pngNew.jpeg jpeg", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png jpeg, save " +
            "res/images/pngNew.jpeg jpeg, exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > load res/random.png jpeg, " +
            "Enter a command: , "
            + "> save res/images/pngNew.jpeg jpeg, Destination file/folder path not " +
            "found., Enter a command: , "
            + "> exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testInvalidSavePpm() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 110;
    List<String> list = Arrays.asList("load res/random.png ppm",
            "save res/images/pngNew.ppm ppm", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png ppm, save res/images/pngNew.ppm ppm, exit]";
    assertEquals(expectedInputLines, inputLines);
    String expectedOutputLines = "[Enter a command: , > load res/random.png ppm, Enter " +
            "a command: , "
            + "> save res/images/pngNew.ppm ppm, Destination file/folder path not found." +
            ", Enter a command: , "
            + "> exit, Exiting the program.]";
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testLoadSave() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 111;
    List<String> list = Arrays.asList("load res/random.png png",
            "save res/pngNew.png png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " save res/pngNew.png png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Enter a " +
            "command: "
            + ", > save res/pngNew.png png, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testLoadSaveFormat1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 112;
    List<String> list = Arrays.asList("load res/random.png jpeg",
            "save res/pngNew.jpeg jpeg", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png jpeg," +
            " save res/pngNew.jpeg jpeg," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png jpeg, Enter" +
            " a command: "
            + ", > save res/pngNew.jpeg jpeg, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testInvalidLoad() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 113;
    List<String> list = Arrays.asList("load res/randomImageNotPresent.jpeg ppm", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/randomImageNotPresent.jpeg ppm, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/randomImageNotPresent" +
            ".jpeg ppm, File not found at the "
            + "given path., Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testLoadSaveFormat2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 114;
    List<String> list = Arrays.asList("load res/random.jpeg ppm",
            "save res/jpegNew.ppm ppm", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.jpeg ppm," +
            " save res/jpegNew.ppm ppm," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.jpeg ppm, " +
            "Enter a command: "
            + ", > save res/jpegNew.ppm ppm, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, outputLines);
  }


  @Test
  public void testLoadSaveFormat3() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 115;
    List<String> list = Arrays.asList("load res/random.ppm png",
            "save res/ppmNew.png png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.ppm png," +
            " save res/ppmNew.png png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.ppm png, " +
            "Enter a command: "
            + ", > save res/ppmNew.png png, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testCommentsLoadSave() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 116;
    List<String> list = Arrays.asList("load res/random.png png", "#This was load",
            "save res/pngNew.png png", "#This was save", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String outputLines = io.getOutputLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " #This was load," +
            " save res/pngNew.png png," +
            " #This was save," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , Enter a command: ,"
            + " > save res/pngNew.png png, Enter a command: , Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, outputLines);
  }

  @Test
  public void testLoadInvalidArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 117;
    List<String> list = Arrays.asList("load png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load png, exit]";
    String expectedOutputLines = "[Enter a command: , > load png, Invalid 'load' command. "
            + "Should have 3 arguments., Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }


  @Test
  public void testSaveInvalidArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 119;
    List<String> list = Arrays.asList("load res/random.png png",
            "save res/pngNew.png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, save res/pngNew.png, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> save res/pngNew.png, Invalid 'save' command. Should have 3 " +
            "arguments., Enter a "
            + "command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }


  @Test
  public void testBrightenCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 121;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten 20 png pngBrighter", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: adjustBrightness (" + uniqueCode + ")"
            + "\nBrightness Value: 20" + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png, brighten 20 png " +
            "pngBrighter, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> brighten 20 png pngBrighter, Enter a command: , > exit, Exiting " +
            "the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testBrightenInvalidArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 122;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten 20", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, brighten 20, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png" +
            ", Enter a command: , " +
            "> brighten 20, Invalid 'brighten' command. Should have 4 arguments., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBrightenInvalidArguments2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 123;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, brighten png, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , " +
            "> brighten png, Invalid 'brighten' command. Should have 4 arguments., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBrightenInvalidArguments3() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 124;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten-image 30% png pngBrighten",
            "save res/pngBrightened.png pngBrighten", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " brighten-image 30% png pngBrighten," +
            " save res/pngBrightened.png pngBrighten," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , "
            + "> brighten-image 30% png pngBrighten, Invalid command, please " +
            "try something else., "
            + "Enter a command: , > save res/pngBrightened.png pngBrighten, File " +
            "not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, io.getLines().toString());
  }

  @Test
  public void testBrightenExtraArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 125;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten 20 png pngBrighter extraArgument", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: adjustBrightness ("
            + uniqueCode + ")" + "\nBrightness Value: 20" + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png, brighten 20 png " +
            "pngBrighter extraArgument, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png" +
            ", Enter a command: , > brighten 20 png "
            + "pngBrighter extraArgument, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testBrightenInvalidOrder() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 126;
    List<String> list = Arrays.asList("load res/random.png png",
            "save res/pngBrightened.png pngBrighten",
            "brighten-image 30 png pngBrighten",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " save res/pngBrightened.png pngBrighten," +
            " brighten-image 30 png pngBrighten," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> save res/pngBrightened.png pngBrighten, File not found for saving, "
            + "Enter a command: , > brighten-image 30 png pngBrighten, Invalid" +
            " command, please try something else., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testDarkenCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 128;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten -20 png pngBrighter", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: adjustBrightness (" + uniqueCode + ")" + "\n" +
            "Brightness Value: -20" + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png, brighten -20 png pngBrighter," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Enter " +
            "a command: , > brighten -20 png "
            + "pngBrighter, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testDarkenInvalidArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 129;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten -20", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, brighten -20, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , " +
            "> brighten -20, Invalid 'brighten' command. Should have 4 arguments., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testDarkenInvalidArguments2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 130;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, brighten png, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , " +
            "> brighten png, Invalid 'brighten' command. Should have 4 arguments., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testDarkenExtraArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 131;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten -20 png pngBrighter extraArgument", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: adjustBrightness (" + uniqueCode + ")" + "\n" +
            "Brightness Value: -20" + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png, brighten -20 png pngBrighter " +
            "extraArgument, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Ent" +
            "er a command: , > brighten -20 png "
            + "pngBrighter extraArgument, Enter a command: , > exit, Exiting the " +
            "program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testDarkenInvalidOrder() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 132;
    List<String> list = Arrays.asList("load res/random.png png",
            "brighten -30 png pngDarken",
            "exit", "save res/pngDarkened.png pngDarken");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, brighten -30 png " +
            "pngDarken, exit, save res/pngDarkened.png "
            + "pngDarken]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, "
            + "Enter a command: , > brighten -30 png pngDarken, Enter a command: , > exit, "
            + "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testHorizontalFlipCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 133;
    List<String> list = Arrays.asList("load res/random.png png",
            "horizontal-flip png pngHorizontal",
            "save res/pngHorizontal.png pngHorizontal", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: flipImageHorizontally (" + uniqueCode + ")"
            + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " horizontal-flip png pngHorizontal," +
            " save res/pngHorizontal.png pngHorizontal," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , "
            + "> horizontal-flip png pngHorizontal, Enter a command: , > save" +
            " res/pngHorizontal.png pngHorizontal, File not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testHorizontalFlipInvalidArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 134;
    List<String> list = Arrays.asList("load res/random.png png",
            "horizontal-flip png",
            "save res/png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, horizontal-flip png, "
            + "save res/png, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , " +
            "> horizontal-flip png, Invalid 'horizontal-flip' command. " +
            "Should have 3 arguments., "
            + "Enter a command: , > save res/png, Invalid 'save' command. " +
            "Should have 3 arguments., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testHorizontalFlipInvalidArguments2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 135;
    List<String> list = Arrays.asList("load res/random.png png",
            "flip-horizontally png pngHorizontal",
            "save res/pngHorizontal.png pngHorizontal", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, flip-horizontally " +
            "png pngHorizontal, save res/"
            + "pngHorizontal.png pngHorizontal, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , "
            + "> flip-horizontally png pngHorizontal, Invalid command, please" +
            " try something else., "
            + "Enter a command: , > save res/pngHorizontal.png pngHorizontal, File" +
            " not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testHorizontalFlipExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 136;
    List<String> list = Arrays.asList("load res/random.png png",
            "horizontal-flip png pngHorizontal extraArgument",
            "save res/pngHorizontal.png pngHorizontal", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: flipImageHorizontally (" + uniqueCode + ")"
            + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png, horizontal-flip " +
            "png pngHorizontal extraArgument, save res/"
            + "pngHorizontal.png pngHorizontal, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , "
            + "> horizontal-flip png pngHorizontal extraArgument, "
            + "Enter a command: , > save res/pngHorizontal.png pngHorizontal, File" +
            " not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testVerticalFlipCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 137;
    List<String> list = Arrays.asList("load res/random.png png",
            "vertical-flip png pngVertical",
            "save res/pngVertical.png pngVertical", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: flipImageVertically (" + uniqueCode + ")"
            + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " vertical-flip png pngVertical," +
            " save res/pngVertical.png pngVertical," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > " +
            "load res/random.png png, Enter a command: , "
            + "> vertical-flip png pngVertical, Enter a command: , > save" +
            " res/pngVertical.png pngVertical, File not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testVerticalFlipInvalidArguments1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 138;
    List<String> list = Arrays.asList("load res/random.png png",
            "vertical-flip png",
            "save res/png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, vertical-flip png, "
            + "save res/png, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , " +
            "> vertical-flip png, Invalid 'vertical-flip' command. " +
            "Should have 3 arguments., "
            + "Enter a command: , > save res/png, Invalid 'save' command. " +
            "Should have 3 arguments., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testVerticalFlipInvalidArguments2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 139;
    List<String> list = Arrays.asList("load res/random.png png",
            "flip-vertically png pngVertical",
            "save res/pngVertical.png pngVertical", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, flip-vertically " +
            "png pngVertical, save res/"
            + "pngVertical.png pngVertical, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , "
            + "> flip-vertically png pngVertical, Invalid command, please" +
            " try something else., "
            + "Enter a command: , > save res/pngVertical.png pngVertical, File" +
            " not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testVerticalFlipExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 140;
    List<String> list = Arrays.asList("load res/random.png png",
            "vertical-flip png pngVertical extraArgument",
            "save res/pngVertical.png pngVertical", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: flipImageVertically (" + uniqueCode + ")"
            + "\nImageData Dimensions: "
            + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png, vertical-flip " +
            "png pngVertical extraArgument, save res/"
            + "pngVertical.png pngVertical, exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , "
            + "> vertical-flip png pngVertical extraArgument, "
            + "Enter a command: , > save res/pngVertical.png pngVertical, File" +
            " not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testValueComponentValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 141;
    int dim1 = 272;
    int dim2 = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "value-component png pngValueComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " value-component png pngValueComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > value-component png pngValueComponent, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
    String expectedModelLog = "Method: createValueComponentOfImage (" + uniqueCode
            + ")" + "\nImageData Dimensions: "
            + dim1 + "x" + dim2 + "\n";
    assertEquals(expectedModelLog, convertedModelLog);
  }

  @Test
  public void testValueComponentExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 143;
    int dim1 = 272;
    int dim2 = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "value-component png pngValueComponent extraArgument", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " value-component png pngValueComponent extraArgument," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > value-component png pngValueComponent extraArgument, Enter" +
            " a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
    String expectedModelLog = "Method: createValueComponentOfImage (" + uniqueCode
            + ")" + "\nImageData Dimensions: "
            + dim1 + "x" + dim2 + "\n";
    assertEquals(expectedModelLog, convertedModelLog);
  }

  @Test
  public void testValueComponentInvalid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 144;
    List<String> list = Arrays.asList("load res/random.png png",
            "value-component png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " value-component png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , > value-component png, "
            + "Invalid 'value-component' command. Should have 3 arguments., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
  }

  @Test
  public void testIntensityComponentInvalid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 144;
    List<String> list = Arrays.asList("load res/random.png png",
            "intensity-component png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " intensity-component png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , > intensity-component png, "
            + "Invalid 'intensity-component' command. Should have 3 arguments., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
  }

  @Test
  public void testIntensityComponentValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 141;
    int dim1 = 272;
    int dim2 = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "intensity-component png pngIntensityComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " intensity-component png pngIntensityComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > intensity-component png pngIntensityComponent, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
    String expectedModelLog = "Method: createIntensityComponentOfImage (" + uniqueCode
            + ")" + "\nImageData Dimensions: "
            + dim1 + "x" + dim2 + "\n";
    assertEquals(expectedModelLog, convertedModelLog);
  }


  @Test
  public void testIntensityComponentExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 143;
    int dim1 = 272;
    int dim2 = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "intensity-component png pngIntensityComponent extraArgument", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " intensity-component png pngIntensityComponent extraArgument," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > intensity-component png pngIntensityComponent extraArgument, Enter" +
            " a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
    String expectedModelLog = "Method: createIntensityComponentOfImage (" + uniqueCode
            + ")" + "\nImageData Dimensions: "
            + dim1 + "x" + dim2 + "\n";
    assertEquals(expectedModelLog, convertedModelLog);
  }

  @Test
  public void testLumaComponentValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 145;
    int dim1 = 272;
    int dim2 = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "luma-component png pngLumaComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " luma-component png pngLumaComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > luma-component png pngLumaComponent, Enter a command: , > " +
            "exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
    String expectedModelLog = "Method: createLumaComponentOfImage (" + uniqueCode
            + ")" + "\nImageData Dimensions: "
            + dim1 + "x" + dim2 + "\n";
    assertEquals(expectedModelLog, convertedModelLog);
  }

  @Test
  public void testLumaComponentExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 145;
    int dim1 = 272;
    int dim2 = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "luma-component png pngLumaComponent extraArgument", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " luma-component png pngLumaComponent extraArgument," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: "
            + ", > luma-component png pngLumaComponent extraArgument, Enter a " +
            "command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
    String expectedModelLog = "Method: createLumaComponentOfImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: "
            + dim1 + "x" + dim2 + "\n";
    assertEquals(expectedModelLog, convertedModelLog);
  }

  @Test
  public void testLumaComponentInvalid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 146;
    List<String> list = Arrays.asList("load res/random.png png",
            "luma-component png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " luma-component png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > luma-component png, "
            + "Invalid 'luma-component' command. Should have 3 arguments., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedInputLines, inputLines);
  }

  @Test
  public void testRedComponentCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 147;
    List<String> list = Arrays.asList("load res/random.png png",
            "red-component png pngRedComponent",
            "save res/pngRedComponent.png pngRedComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: createRedComponentOfImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " red-component png pngRedComponent," +
            " save res/pngRedComponent.png pngRedComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Enter a command: "
            + ", > red-component png pngRedComponent, Enter a command: , > save" +
            " res/pngRedComponent.png "
            + "pngRedComponent, File not found for saving, Enter a command: , > " +
            "exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testRedComponentInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 148;
    List<String> list = Arrays.asList("load res/random.png png",
            "red-component png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " red-component png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , > red-component png, "
            + "Invalid 'red-component' command. Should have 3 arguments., Enter " +
            "a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testRedComponentInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 149;
    List<String> list = Arrays.asList("load res/random.png png",
            "red png pngRedComponent",
            "save res/pngRedComponent.png pngRedComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " red png pngRedComponent," +
            " save res/pngRedComponent.png pngRedComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > red png pngRedComponent, Invalid command, please try " +
            "something else., Enter a command: , > save res/pngRedComponent.png "
            + "pngRedComponent, File not found for saving, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testRedComponentExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 150;
    List<String> list = Arrays.asList("load res/random.png png",
            "red png pngRedComponent extraArgument",
            "save res/pngRedComponent.png pngRedComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " red png pngRedComponent extraArgument," +
            " save res/pngRedComponent.png pngRedComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > red png pngRedComponent extraArgument, Invalid command, please try " +
            "something else., Enter a command: , > save res/pngRedComponent.png "
            + "pngRedComponent, File not found for saving, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testGreenComponentCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 151;
    List<String> list = Arrays.asList("load res/random.png png",
            "green-component png pngGreenComponent",
            "save res/pngGreenComponent.png pngGreenComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: createGreenComponentOfImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " green-component png pngGreenComponent," +
            " save res/pngGreenComponent.png pngGreenComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Enter a command: "
            + ", > green-component png pngGreenComponent, Enter a command: , > save" +
            " res/pngGreenComponent.png "
            + "pngGreenComponent, File not found for saving, Enter a command: , > " +
            "exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testGreenComponentInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 152;
    List<String> list = Arrays.asList("load res/random.png png",
            "green-component png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " green-component png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , > green-component png, "
            + "Invalid 'green-component' command. Should have 3 arguments., Enter " +
            "a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testGreenComponentInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 153;
    List<String> list = Arrays.asList("load res/random.png png",
            "makeGreen png pngGreenComponent",
            "save res/pngGreenComponent.png pngGreenComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " makeGreen png pngGreenComponent," +
            " save res/pngGreenComponent.png pngGreenComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > makeGreen png pngGreenComponent, Invalid command, please try " +
            "something else., Enter a command: , > save res/pngGreenComponent.png "
            + "pngGreenComponent, File not found for saving, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testGreenComponentExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 154;
    List<String> list = Arrays.asList("load res/random.png png",
            "green png pngGreenComponent extraArgument",
            "save res/pngGreenComponent.png pngGreenComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " green png pngGreenComponent extraArgument," +
            " save res/pngGreenComponent.png pngGreenComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > green png pngGreenComponent extraArgument, Invalid command, please try " +
            "something else., Enter a command: , > save res/pngGreenComponent.png "
            + "pngGreenComponent, File not found for saving, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBlueComponentCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 155;
    List<String> list = Arrays.asList("load res/random.png png",
            "blue-component png pngBlueComponent",
            "save res/pngBlueComponent.png pngBlueComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: createBlueComponentOfImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " blue-component png pngBlueComponent," +
            " save res/pngBlueComponent.png pngBlueComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, Enter a command: "
            + ", > blue-component png pngBlueComponent, Enter a command: , > save"
            + " res/pngBlueComponent.png "
            + "pngBlueComponent, File not found for saving, Enter a command: , > "
            + "exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testBlueComponentInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 156;
    List<String> list = Arrays.asList("load res/random.png png",
            "blue-component png", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blue-component png," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , > blue-component png, "
            + "Invalid 'blue-component' command. Should have 3 arguments., Enter " +
            "a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBlueComponentInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 157;
    List<String> list = Arrays.asList("load res/random.png png",
            "blue-tint png pngBlueComponent",
            "save res/pngBlueComponent.png pngBlueComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blue-tint png pngBlueComponent," +
            " save res/pngBlueComponent.png pngBlueComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > blue-tint png pngBlueComponent, Invalid command, please try " +
            "something else., Enter a command: , > save res/pngBlueComponent.png "
            + "pngBlueComponent, File not found for saving, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBlueComponentExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 158;
    List<String> list = Arrays.asList("load res/random.png png",
            "blue png pngBlueComponent extraArgument",
            "save res/pngBlueComponent.png pngBlueComponent", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blue png pngBlueComponent extraArgument," +
            " save res/pngBlueComponent.png pngBlueComponent," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: "
            + ", > blue png pngBlueComponent extraArgument, Invalid command, please try " +
            "something else., Enter a command: , > save res/pngBlueComponent.png "
            + "pngBlueComponent, File not found for saving, Enter a command: , " +
            "> exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testRGBSplitValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 159;
    List<String> list = Arrays.asList("load res/random.png png",
            "rgb-split png pngRed pngGreen pngBlue",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: splitImageIntoColorComponents ("
            + uniqueCode + ")" + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " rgb-split png pngRed pngGreen pngBlue," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: ," +
            " > rgb-split png pngRed pngGreen pngBlue, Enter a command: , > " +
            "exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testRGBSplitInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 160;
    List<String> list = Arrays.asList("load res/random.png png",
            "split pngRed pngGreen pngBlue",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " split pngRed pngGreen pngBlue," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png," +
            " Enter a command: , "
            + "> split pngRed pngGreen pngBlue, Invalid command, please " +
            "try something else., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testRGBSplitInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 161;
    List<String> list = Arrays.asList("load res/random.png png",
            "rgb-split png pngRed pngGreen",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " rgb-split png pngRed pngGreen," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> rgb-split png pngRed pngGreen, Invalid 'rgb-split' command. "
            + "Should have 5 arguments., Enter a command: , > exit, Exiting " +
            "the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testRGBSplitExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 162;
    List<String> list = Arrays.asList("load res/random.png png",
            "rgb-split png pngRed pngGreen pngBlue extraArgument",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: splitImageIntoColorComponents ("
            + uniqueCode + ")" + "\nImageData Dimensions: " + "272x170" + "\n";
    String convertedModelLog = modelLog.toString();
    String inputLines = io.getLines().toString();
    String expectedInputLines = "[load res/random.png png," +
            " rgb-split png pngRed pngGreen pngBlue extraArgument," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: ," +
            " > rgb-split png pngRed pngGreen pngBlue extraArgument, Enter a command: , > " +
            "exit, Exiting the program.]";
    assertEquals(expectedInputLines, inputLines);
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, convertedModelLog);
  }


  @Test
  public void testRGBCombineValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 163;
    List<String> list = Arrays.asList("load res/random.png png",
            "rgb-split png pngRed pngGreen pngBlue",
            "rgb-combine pngNew pngRed pngGreen pngBlue",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, rgb-split png pngRed " +
            "pngGreen pngBlue, " +
            "rgb-combine pngNew pngRed pngGreen pngBlue, exit]";
    assertEquals(expectedInputLines, io.getLines().toString());
  }

  @Test
  public void testRGBCombineInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 164;
    List<String> list = Arrays.asList("load res/random.png png",
            "split png pngRed pngGreen pngBlue",
            "combine pngNew pngRed pngGreen pngBlue",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, split png " +
            "pngRed pngGreen pngBlue, "
            + "combine pngNew pngRed pngGreen pngBlue, exit]";
    assertEquals(expectedInputLines, io.getLines().toString());
  }

  @Test
  public void testRGBCombineInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 165;
    List<String> list = Arrays.asList("load res/random.png png",
            "rgb-split png pngRed pngBlue",
            "rgb-combine pngNew pngGreen pngBlue",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, rgb-split png pngRed pngBlue, " +
            "rgb-combine pngNew pngGreen pngBlue, exit]";
    assertEquals(expectedInputLines, io.getLines().toString());
  }

  @Test
  public void testRGBCombineExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 166;
    List<String> list = Arrays.asList("load res/random.png png",
            "rgb-split png pngRed pngGreen pngBlue",
            "rgb-combine pngNew pngRed pngGreen pngBlue",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png, rgb-split " +
            "png pngRed pngGreen pngBlue, " +
            "rgb-combine pngNew pngRed pngGreen pngBlue, exit]";
    assertEquals(expectedInputLines, io.getLines().toString());
  }

  @Test
  public void testBlurCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 167;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur png pngBlur",
            "save res/pngBlurred.png pngBlur", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: blurImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " blur png pngBlur," +
            " save res/pngBlurred.png pngBlur," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , > blur png "
            + "pngBlur, Enter a command: , > save res/pngBlurred.png pngBlur, File " +
            "not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testBlurInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 168;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur-image 30% png pngBlur",
            "save res/pngBlurred.png pngBlur", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blur-image 30% png pngBlur," +
            " save res/pngBlurred.png pngBlur," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > blur-image 30% png "
            + "pngBlur, Invalid command, please try something else., " +
            "Enter a command: , > save res/pngBlurred.png pngBlur, File not found " +
            "for saving, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBlurInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 169;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur x5 png pngBlur",
            "save res/pngBlurred.png pngBlur", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blur x5 png pngBlur," +
            " save res/pngBlurred.png pngBlur," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > blur x5 png "
            + "pngBlur, Wrong image name provided., " +
            "Enter a command: , > save res/pngBlurred.png pngBlur, File not found " +
            "for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testBlurExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 170;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur png pngBlur extraArgument",
            "save res/pngBlurred.png pngBlur", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blur png pngBlur extraArgument," +
            " save res/pngBlurred.png pngBlur," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > blur png "
            + "pngBlur extraArgument, " +
            "Enter a command: , > save res/pngBlurred.png pngBlur, File not found " +
            "for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSharpenCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 171;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen png pngSharpen",
            "save res/pngSharpened.png pngSharpen", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: sharpenImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " sharpen png pngSharpen," +
            " save res/pngSharpened.png pngSharpen," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , > sharpen png "
            + "pngSharpen, Enter a command: , > save res/pngSharpened.png pngSharpen, File " +
            "not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSharpenInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 172;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen-image 50dpi png pngSharpen",
            "save res/pngSharpened.png pngSharpen", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sharpen-image 50dpi png pngSharpen," +
            " save res/pngSharpened.png pngSharpen," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > sharpen-image 50dpi png "
            + "pngSharpen, Invalid command, please try something else., " +
            "Enter a command: , > save res/pngSharpened.png pngSharpen, File not found " +
            "for saving, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSharpenInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 173;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen x5 png pngSharpen",
            "save res/pngSharpened.png pngSharpen", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sharpen x5 png pngSharpen," +
            " save res/pngSharpened.png pngSharpen," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > sharpen x5 png "
            + "pngSharpen, Wrong image name provided., " +
            "Enter a command: , > save res/pngSharpened.png pngSharpen, " +
            "File not found " +
            "for saving, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSharpenExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 174;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen png pngSharpen extraArgument",
            "save res/pngSharpened.png pngSharpen", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: sharpenImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " sharpen png pngSharpen extraArgument," +
            " save res/pngSharpened.png pngSharpen," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , > sharpen png pngSharpen extraArgument, "
            + "Enter a command: , > save res/pngSharpened.png pngSharpen, File " +
            "not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSepiaValid() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 175;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia png pngSepia",
            "save res/pngSepia.png pngSepia", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: sepiaToneImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " sepia png pngSepia," +
            " save res/pngSepia.png pngSepia," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> sepia png pngSepia, Enter a command: , > save res/pngSepia.png " +
            "pngSepia, File not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSepiaInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 176;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia-toned pngSepia",
            "save res/pngSepia.png pngSepia", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sepia-toned pngSepia," +
            " save res/pngSepia.png pngSepia," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> sepia-toned pngSepia, Invalid command, please try something " +
            "else., "
            + "Enter a command: , > save res/pngSepia.png pngSepia, File not " +
            "found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSepiaInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 177;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepiaEffect png pngSepia",
            "save res/pngSepia.png pngSepia", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sepiaEffect png pngSepia," +
            " save res/pngSepia.png pngSepia," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png " +
            "png, Enter a command: , > sepiaEffect png "
            + "pngSepia, Invalid command, please try something else., " +
            "Enter a command: , > save res/pngSepia.png pngSepia, " +
            "File not found " +
            "for saving, Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSepiaExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 178;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia png pngSepia extraArgument",
            "save res/pngSepia.png pngSepia", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: sepiaToneImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " sepia png pngSepia extraArgument," +
            " save res/pngSepia.png pngSepia," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, " +
            "Enter a command: , "
            + "> sepia png pngSepia extraArgument, Enter a command: , > save res/pngSepia.png " +
            "pngSepia, File not found for saving, "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testGreyscaleCorrectly() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 179;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyscale png pngGreyscale",
            "save pngGreyscale.png pngGreyscale", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: greyscaleImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " greyscale png pngGreyscale," +
            " save pngGreyscale.png pngGreyscale," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , "
            + "> greyscale png pngGreyscale, Enter a command: , > save " +
            "pngGreyscale.png pngGreyscale, "
            + "File not found for saving, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testGreyscaleInvalid1() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 180;
    List<String> list = Arrays.asList("load res/random.png",
            "grey png pngGreyscale",
            "save pngGreyscale.png pngGreyscale", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png," +
            " grey png pngGreyscale," +
            " save pngGreyscale.png pngGreyscale," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png," +
            " Invalid 'load' command. "
            + "Should have 3 arguments., Enter a command: , "
            + "> grey png pngGreyscale, Invalid command, please try something " +
            "else., "
            + "Enter a command: , > save pngGreyscale.png pngGreyscale, "
            + "File not found for saving, Enter a command: , > exit, Exiting " +
            "the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testGreyscaleInvalid2() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 181;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyScale png pngGreyscale",
            "save pngGreyscale.png pngGreyscale", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " greyScale png pngGreyscale," +
            " save pngGreyscale.png pngGreyscale," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png png, "
            + "Enter a command: , "
            + "> greyScale png pngGreyscale, "
            + "Enter a command: , > save pngGreyscale.png pngGreyscale, "
            + "File not found for saving, Enter a command: , > exit, Exiting " +
            "the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testGreyscaleExtraArguments() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 182;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyscale png pngGreyscale extraArgument",
            "save pngGreyscale.png pngGreyscale", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: greyscaleImage (" + uniqueCode + ")"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " greyscale png pngGreyscale extraArgument," +
            " save pngGreyscale.png pngGreyscale," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , "
            + "> greyscale png pngGreyscale extraArgument, Enter a command: , > save " +
            "pngGreyscale.png pngGreyscale, "
            + "File not found for saving, Enter a command: , > exit, " +
            "Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitBlur() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 183;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur png pngSplitBlur split 40",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: splitBlur (" + uniqueCode + ")"
            + "\nPercentage: 40"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " blur png pngSplitBlur split 40," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> blur png pngSplitBlur split 40, " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitBlurNegativePercent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 184;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur png pngSplitBlur split -40",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blur png pngSplitBlur split -40," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> blur png pngSplitBlur split -40, " +
            "Percentage of image width should be between 0 and 100., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitBlurAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 185;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur png pngSplitBlur split 110",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blur png pngSplitBlur split 110," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> blur png pngSplitBlur split 110, " +
            "Percentage of image width should be between 0 and 100., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitBlurVariable() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 186;
    List<String> list = Arrays.asList("load res/random.png png",
            "blur png pngSplitBlur split p",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " blur png pngSplitBlur split p," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> blur png pngSplitBlur split p, Percentage of " +
            "image width should be an integer., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitSharpen() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 187;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen png pngSplitSharpen split 40",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: splitSharpen (" + uniqueCode + ")"
            + "\nPercentage: 40"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " sharpen png pngSplitSharpen split 40," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sharpen png pngSplitSharpen split 40, " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitSharpenNegativePercent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 188;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen png pngSplitSharpen split -40",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sharpen png pngSplitSharpen split -40," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sharpen png pngSplitSharpen split -40, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitSharpenAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 189;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen png pngSplitSharpen split 110",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sharpen png pngSplitSharpen split 110," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sharpen png pngSplitSharpen split 110, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitSharpenVariable() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 190;
    List<String> list = Arrays.asList("load res/random.png png",
            "sharpen png pngSplitSharpen split p",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sharpen png pngSplitSharpen split p," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sharpen png pngSplitSharpen split p, Percentage" +
            " of image width should be an integer., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitSepia() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 191;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia png pngSplitSepia split 50",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: splitSepia (" + uniqueCode + ")"
            + "\nPercentage: 50"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " sepia png pngSplitSepia split 50," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sepia png pngSplitSepia split 50, " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitSepiaNegativePercent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 192;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia png pngSplitSepia split -50",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sepia png pngSplitSepia split -50," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sepia png pngSplitSepia split -50, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitSepiaAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 193;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia png pngSplitSepia split 110",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sepia png pngSplitSepia split 110," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sepia png pngSplitSepia split 110, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitSepiaVariable() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 194;
    List<String> list = Arrays.asList("load res/random.png png",
            "sepia png pngSplitSepia split p",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " sepia png pngSplitSepia split p," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> sepia png pngSplitSepia split p, Percentage " +
            "of image width should be an integer., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitGreyscale() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 195;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyscale png pngSplitGreyscale split 50",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: splitGreyscale (" + uniqueCode + ")"
            + "\nPercentage: 50"
            + "\nImageData Dimensions: " + "272x170" + "\n";
    String expectedInputLines = "[load res/random.png png," +
            " greyscale png pngSplitGreyscale split 50," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> greyscale png pngSplitGreyscale split 50, " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

  @Test
  public void testSplitGreyscaleNegativePercent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 196;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyscale png pngSplitGreyscale split -50",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " greyscale png pngSplitGreyscale split -50," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> greyscale png pngSplitGreyscale split -50, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitGreyscaleAbove100Percent() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 197;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyscale png pngSplitGreyscale split 110",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " greyscale png pngSplitGreyscale split 110," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , " +
            "> greyscale png pngSplitGreyscale split 110, " +
            "Percentage of image width should be between 0 and 100.," +
            " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testSplitGreyscaleVariable() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 198;
    List<String> list = Arrays.asList("load res/random.png png",
            "greyscale png pngSplitGreyscale split p",
            "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[load res/random.png png," +
            " greyscale png pngSplitGreyscale split p," +
            " exit]";
    String expectedOutputLines = "[Enter a command: , > load res/random.png" +
            " png, Enter a command: , > greyscale png pngSplitGreyscale split p, " +
            "Percentage of image width should be an integer., " +
            "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testEmptyScript() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 199;
    List<String> list = Arrays.asList("run res/EmptyScript.txt", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[run res/EmptyScript.txt, exit]";
    String expectedOutputLines = "[Enter a command: , > run res/EmptyScript.txt, "
            + "Running script file., "
            + "Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testInvalidScript() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 200;
    List<String> list = Arrays.asList("run res/ErrorScript", "exit");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedInputLines = "[run res/ErrorScript, exit]";
    String expectedOutputLines = "[Enter a command: , > run res/ErrorScript, "
            + "Invalid script file extension. Should be .txt,"
            + " Enter a command: , > exit, Exiting the program.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
  }

  @Test
  public void testValidScript() {
    StringBuilder modelLog = new StringBuilder();
    int uniqueCode = 299;
    List<String> list = Arrays.asList("run res/testScript.txt");
    UserIO io = new MockUserIO(list);
    ImageProcessingController controller = new Controller(io,
            new MockImageProcessingModel(modelLog, uniqueCode));
    controller.execute();
    String expectedModelLog = "Method: sharpenImage (299)\n" +
            "ImageData Dimensions: 272x170\n";
    String expectedInputLines = "[run res/testScript.txt]";
    String expectedOutputLines = "[Enter a command: , > run res/testScript.txt" +
            ", Running script file.]";
    assertEquals(expectedInputLines, io.getLines().toString());
    assertEquals(expectedOutputLines, io.getOutputLines().toString());
    assertEquals(expectedModelLog, modelLog.toString());
  }

}

