package controller;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.EnhancedImageProcessingModel;
import model.EnhancedImageProcessor;
import view.ImageView;

import static junit.framework.TestCase.assertEquals;

/**
 * This class tests the public methods of the `ViewControllerImpl` class.
 */
public class ViewControllerImplTest {

  EnhancedImageProcessingModel model;
  String[] args;

  @Before
  public void setUp() {
    model = new EnhancedImageProcessor();
    args = new String[0];
  }

  @Test
  public void testLoad() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 100;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n";
    assertEquals(expectedLog, viewLog.toString());
  }


  @Test
  public void testSave() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 100;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.save();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: selectFolder (" + uniqueCode + ")\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testRedComponent() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 101;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.redComponent();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testGreenComponent() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 102;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.greenComponent();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testBlueComponent() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 103;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.blueComponent();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testHorizontalFlip() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 104;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.horizontalFlip();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testVerticalFlip() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 105;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.verticalFlip();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testCompression() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 106;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.compression();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: getCompressionFactor (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testBlur() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 107;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.blur();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testGreyScale() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 108;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.greyScale();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testSharpen() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 109;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.sharpen();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testSepia() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 110;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.sepia();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testColorCorrect() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 111;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.colorCorrect();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testLevelAdjustment() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 112;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.levelAdjustment();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: getLevels (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testMultipleOperations1() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 113;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.greyScale();
    controller.redComponent();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n";
    assertEquals(expectedLog, viewLog.toString());
  }

  @Test
  public void testMultipleOperations2() {
    StringBuilder viewLog = new StringBuilder();
    int uniqueCode = 113;
    List<String> commands = new ArrayList<>();
    UserIO userIO = new MockUserIO(commands);
    ImageView view = new MockImageViewImpl(viewLog, uniqueCode);
    ControllerForGUI controller = new ViewControllerImpl(userIO, model, args,
            view);
    controller.load();
    controller.greyScale();
    controller.redComponent();
    controller.sepia();
    controller.save();
    String expectedLog = "Method: setFeatures (" + uniqueCode + ")\n"
            + "Method: FileChooser (" + uniqueCode + ")\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: enableButtons (" + uniqueCode + ")\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: showSplitImageOperationMenu (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: setImage (" + uniqueCode + ")\n"
            + "Image Dimensions: 272x170\n"
            + "Image Histogram Dimensions: 256x256\n"
            + "Method: selectFolder (" + uniqueCode + ")\n";
    assertEquals(expectedLog, viewLog.toString());
  }

}