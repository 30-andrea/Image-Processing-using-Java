package model;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


/**
 * This class tests all the public functions of the ColorImageDataTest class.
 */
public class ImageDataTest {

  int[][][] threeDimensionalArray;

  @Before
  public void setup() {
    threeDimensionalArray = new int[][][]{
      {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
      },
      {
        {10, 11, 12},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {19, 20, 21},
        {22, 23, 24},
        {25, 26, 27}
      }
    };
  }

  @Test
  public void testConstructor() {
    ImageData img = new ImageData(threeDimensionalArray);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < img.getChannels(); k++) {
          assertEquals(threeDimensionalArray[i][j][k], img.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidIndices1() {
    ImageData img = new ImageData(threeDimensionalArray);
    img.getPixelValue(-1, 3, 0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidIndices6() {
    ImageData img = new ImageData(threeDimensionalArray);
    img.getPixelValue(3, -1, 0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidIndices2() {
    ImageData img = new ImageData(threeDimensionalArray);
    img.getPixelValue(2, 2, -1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidIndices5() {
    ImageData img = new ImageData(threeDimensionalArray);
    img.getPixelValue(2, 2, 4);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidIndices3() {
    ImageData img = new ImageData(threeDimensionalArray);
    img.getPixelValue(10, 2, 1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidIndices4() {
    ImageData img = new ImageData(threeDimensionalArray);
    img.getPixelValue(2, 10, 1);
  }

  @Test
  public void testGetChannels() {
    ImageData img = new ImageData(threeDimensionalArray);
    assertEquals(3, img.getChannels());
  }

  @Test
  public void testCorrectBufferConversion() {
    int[][][] pixelData = {
            {{255, 0, 0}, {0, 255, 0}},
            {{0, 0, 255}, {255, 255, 255}}
    };
    ImageData imageData = new ImageData(pixelData);
    BufferedImage image = imageData.convertPixelDataToBufferedImage();
    assertEquals(255, (image.getRGB(0, 0) >> 16) & 0xFF);
    assertEquals(0, (image.getRGB(0, 0) >> 8) & 0xFF);
    assertEquals(0, image.getRGB(0, 0) & 0xFF);
    assertEquals(0, (image.getRGB(1, 0) >> 16) & 0xFF);
    assertEquals(255, (image.getRGB(1, 0) >> 8) & 0xFF);
    assertEquals(0, image.getRGB(1, 0) & 0xFF);
    assertEquals(0, (image.getRGB(0, 1) >> 16) & 0xFF);
    assertEquals(0, (image.getRGB(0, 1) >> 8) & 0xFF);
    assertEquals(255, image.getRGB(0, 1) & 0xFF);
    assertEquals(255, (image.getRGB(1, 1) >> 16) & 0xFF);
    assertEquals(255, (image.getRGB(1, 1) >> 8) & 0xFF);
    assertEquals(255, image.getRGB(1, 1) & 0xFF);
  }

  @Test
  public void testAllZeros() {
    int[][][] pixelData = {
      {{0, 0, 0}, {0, 0, 0}},
      {{0, 0, 0}, {0, 0, 0}}
    };
    ImageData imageData = new ImageData(pixelData);
    BufferedImage image = imageData.convertPixelDataToBufferedImage();
    for (int x = 0; x < image.getHeight(); x++) {
      for (int y = 0; y < image.getWidth(); y++) {
        assertEquals(0, (image.getRGB(y, x) >> 16) & 0xFF);
        assertEquals(0, (image.getRGB(y, x) >> 8) & 0xFF);
        assertEquals(0, image.getRGB(y, x) & 0xFF);
      }
    }
  }

  @Test
  public void testConvertPixelDataToBufferedImage() {
    int[][][] data = new int[][][]{
      {{255, 0, 0}, {0, 255, 0}},
      {{0, 0, 255}, {255, 255, 255}}
    };
    ImageData imageData = new ImageData(data);
    BufferedImage bufferedImage = imageData.convertPixelDataToBufferedImage();
    assertNotNull(bufferedImage);
    assertEquals(2, bufferedImage.getWidth());
    assertEquals(2, bufferedImage.getHeight());
    assertEquals(-65536, bufferedImage.getRGB(0, 0)); // Red
    assertEquals(-16711936, bufferedImage.getRGB(1, 0)); // Green
    assertEquals(-16776961, bufferedImage.getRGB(0, 1)); // Blue
    assertEquals(-1, bufferedImage.getRGB(1, 1)); // White
  }

  @Test
  public void testConvertBufferedImageToPixelData() {
    BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
    image.setRGB(0, 0, 0xFF0000);
    image.setRGB(1, 0, 0x00FF00);
    image.setRGB(2, 0, 0x0000FF);
    image.setRGB(0, 1, 0xFFFFFF);
    image.setRGB(1, 1, 0x000000);
    image.setRGB(2, 1, 0x000000);
    image.setRGB(0, 2, 0x000000);
    image.setRGB(1, 2, 0x000000);
    image.setRGB(2, 2, 0x000000);
    int[][][] expected = new int[][][]{
      {
        {255, 0, 0},
        {0, 255, 0},
        {0, 0, 255}
      },
      {
        {255, 255, 255},
        {0, 0, 0},
        {0, 0, 0}
      },
      {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
      }
    };
    ImageData actualImage = new ImageData(image);
    for (int i = 0; i < actualImage.getHeight(); i++) {
      for (int j = 0; j < actualImage.getWidth(); j++) {
        for (int k = 0; k < actualImage.getChannels(); k++) {
          assertEquals(expected[i][j][k], actualImage.getPixelValue(i, j, k));
        }
      }
    }
  }

}