
package model;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * This class tests all the public functions of the image processing model.
 */
public class ImageProcessorTest {

  private ImageProcessingModel processor;
  int[][][] threeDimensionalArray;
  int[][][] threeDimensionalArray2;
  int[][][] grayScaleArray;

  @Before
  public void setUp() {
    processor = new ImageProcessor();
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
    threeDimensionalArray2 = new int[][][]{
      {
        {10, 20, 30},
        {40, 50, 60},
        {70, 80, 90}
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
    grayScaleArray = new int[][][]{
      {
        {0, 2, 3},
        {0, 5, 6},
        {0, 8, 9}
      },
      {
        {0, 11, 12},
        {0, 14, 15},
        {0, 17, 18}
      },
      {
        {0, 20, 21},
        {0, 23, 24},
        {0, 26, 27}
      }
    };

  }

  @Test
  public void testCreateImageForIndividualComponentRed() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.createRedComponentOfImage(img);
    int colorIndex = 0;
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < img.getChannels(); k++) {
          if (k == colorIndex) {
            assertEquals(img.getPixelValue(i, j, k), res.getPixelValue(i, j, k));
          } else {
            assertEquals(0, res.getPixelValue(i, j, k));
          }
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateImageForIndividualComponentRedInvalid() {
    processor.createRedComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateImageForIndividualComponentGreenInvalid() {
    processor.createGreenComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateImageForIndividualComponentBlueInvalid() {
    processor.createBlueComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateValueComponentOfImageInvalid() {
    processor.createValueComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateLumaComponentOfImageInvalid() {
    processor.createLumaComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIntensityComponentOfImageInvalid() {
    processor.createIntensityComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontallyInvalid() {
    processor.flipImageHorizontally(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticallyInvalid() {
    processor.flipImageVertically(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightness() {
    processor.adjustBrightness(null, 10);
  }

  @Test
  public void testBrightness2() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    int value = -255;
    ImageInfo res = processor.adjustBrightness(img, -260);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          int expected = Math.max(threeDimensionalArray[i][j][k]
                  + value, 0);
          assertEquals(expected, res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testBrightness3() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    int value = 255;
    ImageInfo res = processor.adjustBrightness(img, 260);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          int expected = Math.min(threeDimensionalArray[i][j][k]
                  + value, 255);
          assertEquals(expected, res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSharpen() {
    processor.sharpenImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlur() {
    processor.blurImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSepia() {
    processor.sepiaToneImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyscale() {
    processor.greyscaleImage(null);
  }

  @Test
  public void testCreateImageForIndividualComponentGreen() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.createGreenComponentOfImage(img);
    int colorIndex = 1;
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < img.getChannels(); k++) {
          if (k == colorIndex) {
            assertEquals(img.getPixelValue(i, j, k), res.getPixelValue(i, j, k));
          } else {
            assertEquals(0, res.getPixelValue(i, j, k));
          }
        }
      }
    }
  }

  @Test
  public void testCreateImageForIndividualComponentBlue() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.createBlueComponentOfImage(img);
    int colorIndex = 2;
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < img.getChannels(); k++) {
          if (k == colorIndex) {
            assertEquals(img.getPixelValue(i, j, k), res.getPixelValue(i, j, k));
          } else {
            assertEquals(0, res.getPixelValue(i, j, k));
          }
        }
      }
    }
  }


  @Test(expected = IllegalArgumentException.class)
  public void testVisualiseImageNullOperation() {

    processor.createLumaComponentOfImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipImageNullOperation() {
    processor.flipImageHorizontally(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCombineChannels() {
    ImageData redImage = new ImageData(threeDimensionalArray);
    ImageData greenImage = new ImageData(threeDimensionalArray);
    processor.combineColorComponents(redImage, greenImage, null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLinearTransformation() {
    processor.sepiaToneImage(null);
  }

  @Test
  public void testImageValueVisualisation() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.createValueComponentOfImage(img);
    int[][] expected = new int[][]{
      {3, 6, 9},
      {12, 15, 18},
      {21, 24, 27}
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testImageIntensityVisualisation() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.createIntensityComponentOfImage(img);
    int[][] expected = new int[][]{
      {2, 5, 8},
      {11, 14, 17},
      {20, 23, 26}
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testImageLumaVisualisation() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.createLumaComponentOfImage(img);
    int[][] expected = new int[][]{
      {1, 4, 7},
      {10, 13, 16},
      {19, 22, 25}
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testImageFlipHorizontally() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.flipImageHorizontally(img);
    int[][][] expected = new int[][][]{
      {
        {7, 8, 9},
        {4, 5, 6},
        {1, 2, 3}
      },
      {
        {16, 17, 18},
        {13, 14, 15},
        {10, 11, 12}
      },
      {
        {25, 26, 27},
        {22, 23, 24},
        {19, 20, 21}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testImageFlipVertically() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.flipImageVertically(img);
    int[][][] expected = new int[][][]{
      {
        {19, 20, 21},
        {22, 23, 24},
        {25, 26, 27}
      },
      {
        {10, 11, 12},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testBrightenImage() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    int value = 2;
    ImageInfo res = processor.adjustBrightness(img, value);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray[i][j][k]
                  + value, res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testAddValueToGrayScaleImage() {
    ImageInfo img = new ImageData(grayScaleArray);
    int value = 2;
    ImageInfo res = processor.adjustBrightness(img, value);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(grayScaleArray[i][j][k] + value, res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testAddValueToImageAbove255() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    int value = 235;
    ImageInfo res = processor.adjustBrightness(img, value);
    int[][][] expected = new int[][][]{
      {
        {236, 237, 238},
        {239, 240, 241},
        {242, 243, 244}
      },
      {
        {245, 246, 247},
        {248, 249, 250},
        {251, 252, 253}
      },
      {
        {254, 255, 255},
        {255, 255, 255},
        {255, 255, 255}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSubValueFromImage() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    int value = -8;
    ImageInfo res = processor.adjustBrightness(img, value);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(Math.max(threeDimensionalArray[i][j][k] + value, 0),
                  res.getPixelValue(i, j, k));

        }
      }
    }
  }

  @Test
  public void testSplitImageChannels() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo[] res = processor.splitImageIntoColorComponents(img);
    int[][] expected = new int[][]{
      {1, 4, 7},
      {10, 13, 16},
      {19, 22, 25}
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j], res[0].getPixelValue(i, j, k));
        }
      }
    }
    int[][] expected2 = new int[][]{
      {2, 5, 8},
      {11, 14, 17},
      {20, 23, 26}
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected2[i][j], res[1].getPixelValue(i, j, k));
        }
      }
    }
    int[][] expected3 = new int[][]{
      {3, 6, 9},
      {12, 15, 18},
      {21, 24, 27}
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected3[i][j], res[2].getPixelValue(i, j, k));
        }
      }
    }
    ImageInfo combine = processor.combineColorComponents(res[0], res[1], res[2]);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray[i][j][k], combine.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testBlur() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.blurImage(img);
    int[][][] expected = new int[][][]{
      {
        {2, 3, 3},
        {5, 6, 6},
        {5, 5, 6}
      },
      {
        {8, 9, 9},
        {13, 14, 15},
        {11, 12, 12}
      },
      {
        {9, 10, 10},
        {14, 15, 15},
        {11, 12, 12}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSharpen() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.sharpenImage(img);
    int[][][] expected = new int[][][]{
      {
        {0, 0, 0},
        {7, 9, 11},
        {5, 6, 7}
      },
      {
        {18, 20, 22},
        {39, 42, 45},
        {30, 31, 33}
      },
      {
        {23, 24, 25},
        {41, 43, 45},
        {32, 33, 34}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSepia() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.sepiaToneImage(img);
    int[][][] expected = new int[][][]{
      {
        {1, 1, 1},
        {5, 5, 3},
        {9, 8, 6}
      },
      {
        {13, 12, 8},
        {17, 15, 11},
        {22, 19, 15}
      },
      {
        {25, 22, 17},
        {29, 26, 20},
        {33, 29, 22}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testGreyscale() {
    ImageInfo img = new ImageData(threeDimensionalArray);
    ImageInfo res = processor.greyscaleImage(img);
    int[][][] expected = new int[][][]{
      {
        {1, 1, 1},
        {3, 3, 3},
        {6, 6, 6}
      },
      {
        {9, 9, 9},
        {13, 13, 13},
        {16, 16, 16}
      },
      {
        {19, 19, 19},
        {21, 21, 21},
        {24, 24, 24}
      }
    };
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }
}
