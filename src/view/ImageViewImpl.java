package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

import controller.ControllerForGUI;

/**
 * This class implements the GUI for the image processing program.
 */
public class ImageViewImpl extends JFrame implements ImageView {

  private final JPanel featurePanel;
  private JButton loadButton;
  private JButton saveButton;
  private JButton redComponentButton;
  private JButton greenComponentButton;
  private JButton blueComponentButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton compressionButton;
  private JButton blurButton;
  private JButton greyScaleButton;
  private JButton sharpenButton;
  private JButton colorCorrectButton;
  private JButton sepiaButton;
  private JButton levelAdjustmentButton;
  private JButton exitButton;

  private JLabel imageLabel;
  private JLabel imageHistogramLabel;

  /**
   * This constructor initializes the GUI.
   */
  public ImageViewImpl() {
    super();
    featurePanel = new JPanel();
    this.setBackground(Color.BLUE);
    this.setTitle("Image View");
    this.setSize(1350, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    buildImageDisplay();
    buildFeaturePanel();
    this.setVisible(true);
  }

  @Override
  public int getCompressionFactor() {
    while (true) {
      String compressionFactor = JOptionPane.showInputDialog("Enter " +
              "Compression Factor");
      if (compressionFactor == null) {
        return 0;
      }

      int factor = checkForIntegerBetween0And100(compressionFactor);
      if (factor == -1) {
        display(ErrorMessages.ERROR_VALID_INTEGER_BETWEEN_0_AND_100.getMessage());
      } else {
        return factor;
      }
    }
  }

  @Override
  public String selectFolder() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileFilter());
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int result = fileChooser.showSaveDialog(null);
    String folderLocation = "";
    if (result == JFileChooser.CANCEL_OPTION) {
      return "";
    }
    else if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFolder = fileChooser.getSelectedFile();
      folderLocation = selectedFolder.getAbsolutePath();
    }
    if (new FileFilter().accept(new File(folderLocation))) {
      return folderLocation;
    }
    else {
      display(ErrorMessages.ERROR_INVALID_FILE.getMessage());
    }
    return "";
  }

  private int checkForIntegerBetween0And100(String input) {
    try {
      int factor = Integer.parseInt(input);
      if (factor >= 0 && factor <= 100) {
        return factor;
      } else {
        return -1;
      }
    } catch (NumberFormatException e) {
      return -1;
    }
  }



  @Override
  public String fileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileFilter());

    int result = fileChooser.showOpenDialog(null);
    String fileLocation = "";
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      fileLocation = selectedFile.getAbsolutePath();
    }
    return fileLocation;
  }


  private void buildImageDisplay() {
    imageLabel = new JLabel();
    imageLabel.setMinimumSize(new Dimension(10, 5));
    imageHistogramLabel = new JLabel();
    imageHistogramLabel.setMinimumSize(new Dimension(10, 5));
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageHistogramLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
    imageHistogramLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
    JPanel imageAndHistogramPanel = new JPanel();
    imageAndHistogramPanel.setLayout(new BoxLayout(imageAndHistogramPanel,
            BoxLayout.Y_AXIS));
    imageAndHistogramPanel.add(imageLabel, BorderLayout.CENTER);
    imageAndHistogramPanel.add(imageHistogramLabel, BorderLayout.CENTER);
    JScrollPane scrollPaneImage = new JScrollPane(imageLabel);
    scrollPaneImage.setPreferredSize(new Dimension(700, 400));
    JScrollPane scrollPaneHistogram = new JScrollPane(imageHistogramLabel);
    scrollPaneHistogram.setPreferredSize(new Dimension(600, 300));
    featurePanel.add(scrollPaneImage, BorderLayout.CENTER);
    featurePanel.add(scrollPaneHistogram, BorderLayout.CENTER);
  }

  @Override
  public void setImage(BufferedImage image, BufferedImage imageHistogram) {
    imageLabel.setIcon(new ImageIcon(image));
    imageHistogramLabel.setIcon(new ImageIcon(imageHistogram));
  }

  @Override
  public void setFeatures(ControllerForGUI controllerForGUI) {
    loadButton.addActionListener(evt -> controllerForGUI.load());
    saveButton.addActionListener(evt -> controllerForGUI.save());
    redComponentButton.addActionListener(evt -> controllerForGUI.redComponent());
    greenComponentButton.addActionListener(evt -> controllerForGUI.greenComponent());
    blueComponentButton.addActionListener(evt -> controllerForGUI.blueComponent());
    horizontalFlipButton.addActionListener(evt -> controllerForGUI.horizontalFlip());
    verticalFlipButton.addActionListener(evt -> controllerForGUI.verticalFlip());
    compressionButton.addActionListener(evt -> controllerForGUI.compression());
    blurButton.addActionListener(evt -> controllerForGUI.blur());
    greyScaleButton.addActionListener(evt -> controllerForGUI.greyScale());
    sharpenButton.addActionListener(evt -> controllerForGUI.sharpen());
    colorCorrectButton.addActionListener(evt -> controllerForGUI.colorCorrect());
    sepiaButton.addActionListener(evt -> controllerForGUI.sepia());
    levelAdjustmentButton.addActionListener(evt -> controllerForGUI.levelAdjustment());
    exitButton.addActionListener(evt -> System.exit(0));
  }

  @Override
  public int showSplitImageOperationMenu(BufferedImage bufferedImage, BufferedImage
          bufferedImageHistogram) {
    Object[] options = {"Apply Operation", "Cancel", "Change Split Factor"};
    JPanel imagesPanel = new JPanel();
    imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.Y_AXIS));
    imagesPanel.add(new JLabel(new ImageIcon(bufferedImage)));
    imagesPanel.add(new JLabel(new ImageIcon(bufferedImageHistogram)));
    JScrollPane scrollPane = new JScrollPane(imagesPanel);
    scrollPane.setPreferredSize(new Dimension(800, 600));
    int result;
    int choice = JOptionPane.showOptionDialog(null,
            scrollPane,
            "Options",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
    switch (choice) {
      case 0:
        result = 101;
        break;
      case 1:
      case -1:
        result = -1;
        break;
      case 2:
        String input = JOptionPane.showInputDialog(null,
                "Enter the split factor:");
        if (input == null) {
          return -1;
        }
        try {
          result = checkForIntegerBetween0And100(input);
          if (result == -1) {
            display(ErrorMessages.ERROR_VALID_INTEGER_BETWEEN_0_AND_100.getMessage());
            return -2;
          }
        } catch (NumberFormatException e) {
          display(ErrorMessages.ERROR_VALID_INTEGER.getMessage());
          result = 0;
        }
        break;
      default:
        result = 0;
        break;
    }
    return result;
  }

  @Override
  public int[] getLevels() {
    int[] levels = new int[3];
    boolean isValidInput = false;
    while (!isValidInput) {
      try {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        JTextField shadowField = new JTextField();
        JTextField midField = new JTextField();
        JTextField highlightField = new JTextField();
        panel.add(new JLabel("Shadow:"));
        panel.add(shadowField);
        panel.add(new JLabel("Mid:"));
        panel.add(midField);
        panel.add(new JLabel("Highlight:"));
        panel.add(highlightField);
        int option = JOptionPane.showOptionDialog(null, panel,
                "Enter Levels (0-255)", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
          return new int[]{-1, -1, -1};
        }
        levels[0] = validateAndParse(shadowField.getText());
        levels[1] = validateAndParse(midField.getText());
        levels[2] = validateAndParse(highlightField.getText());
        if (isValidLevel(levels[0]) && isValidLevel(levels[1])
                && isValidLevel(levels[2])) {
          isValidInput = true;
        } else {
          display(ErrorMessages.ERROR_VALID_INTEGER_BETWEEN_0_AND_255.getMessage());
        }
      } catch (NumberFormatException e) {
        display(ErrorMessages.ERROR_VALID_INTEGER.getMessage());
      }
    }
    return levels;
  }

  @Override
  public void enableButtons() {
    saveButton.setEnabled(true);
    redComponentButton.setEnabled(true);
    greenComponentButton.setEnabled(true);
    blueComponentButton.setEnabled(true);
    horizontalFlipButton.setEnabled(true);
    verticalFlipButton.setEnabled(true);
    compressionButton.setEnabled(true);
    blurButton.setEnabled(true);
    greyScaleButton.setEnabled(true);
    sharpenButton.setEnabled(true);
    colorCorrectButton.setEnabled(true);
    sepiaButton.setEnabled(true);
    levelAdjustmentButton.setEnabled(true);
  }

  @Override
  public void display(String message) {
    JOptionPane.showMessageDialog(null, message, "Error",
            JOptionPane.WARNING_MESSAGE);
  }

  @Override
  public boolean showLoadWarningPopup() {
    int choice = JOptionPane.showOptionDialog(null,
            "The current processed image has not been saved. "
                    + "Are you sure you want to load another image?",
            "Warning",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            new Object[]{"Yes", "No"},
            "No");

    return choice == JOptionPane.YES_OPTION;
  }

  private int validateAndParse(String input) throws NumberFormatException {
    if (input == null) {
      return 0;
    }
    try {
      Integer.parseInt(input);
    }
    catch (NumberFormatException e) {
      throw new NumberFormatException();
    }
    return Integer.parseInt(input);
  }

  private boolean isValidLevel(int level) {
    return level >= 0 && level <= 255;
  }

  private void buildFeaturePanel() {
    loadButton = new JButton("Load");
    featurePanel.add(loadButton);
    saveButton = new JButton("Save");
    saveButton.setEnabled(false);
    featurePanel.add(saveButton);
    redComponentButton = new JButton("Red Component");
    redComponentButton.setEnabled(false);
    featurePanel.add(redComponentButton);
    greenComponentButton = new JButton("Green Component");
    greenComponentButton.setEnabled(false);
    featurePanel.add(greenComponentButton);
    blueComponentButton = new JButton("Blue Component");
    blueComponentButton.setEnabled(false);
    featurePanel.add(blueComponentButton);
    horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setEnabled(false);
    featurePanel.add(horizontalFlipButton);
    verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setEnabled(false);
    featurePanel.add(verticalFlipButton);
    compressionButton = new JButton("Compress");
    compressionButton.setEnabled(false);
    featurePanel.add(compressionButton);
    blurButton = new JButton("Blur");
    blurButton.setEnabled(false);
    featurePanel.add(blurButton);
    greyScaleButton = new JButton("Greyscale");
    greyScaleButton.setEnabled(false);
    featurePanel.add(greyScaleButton);
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setEnabled(false);
    featurePanel.add(sharpenButton);
    colorCorrectButton = new JButton("Color Correct");
    colorCorrectButton.setEnabled(false);
    featurePanel.add(colorCorrectButton);
    sepiaButton = new JButton("Sepia");
    sepiaButton.setEnabled(false);
    featurePanel.add(sepiaButton);
    levelAdjustmentButton = new JButton("Level Adjustment");
    levelAdjustmentButton.setEnabled(false);
    featurePanel.add(levelAdjustmentButton);
    exitButton = new JButton("Exit");
    featurePanel.add(exitButton);
    this.getContentPane().add(featurePanel);
  }
}