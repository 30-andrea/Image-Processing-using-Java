package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


import static org.junit.Assert.assertEquals;

/**
 * Test class for the EnhancedImageProcessor class.
 */
public class EnhancedImageProcessorTest {

  private EnhancedImageProcessingModel model;
  int[][][] threeDimensionalArray1;
  int[][][] threeDimensionalArray2;

  int[][][] threeDimensionalArray3;

  @Before
  public void setUp() {
    model = new EnhancedImageProcessor();
    threeDimensionalArray1 = new int[][][]{
      {
        {1, 254, 3},
        {10, 255, 6},
        {11, 255, 9}
      },
      {
        {12, 250, 11},
        {14, 251, 15},
        {13, 17, 18}
      },
      {
        {11, 20, 21},
        {12, 23, 24},
        {14, 26, 27}
      }
    };
    threeDimensionalArray2 = new int[][][]{
      {
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}
      },
      {
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}
      },
      {
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}
      }
    };
    threeDimensionalArray3 = new int[][][]{
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
  public void testCompressionZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.compressImage(img, 0);
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < res.getHeight(); i++) {
        for (int j = 0; j < res.getWidth(); j++) {
          assertEquals(threeDimensionalArray1[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testCompressionHundredPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.compressImage(img, 100);
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < res.getHeight(); i++) {
        for (int j = 0; j < res.getWidth(); j++) {
          assertEquals(0, res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testCompressionFiftyPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.compressImage(img, 50);
    int[][][] expected = new int[][][]{
      {
        {6, 252, 4},
        {6, 252, 4},
        {12, 255, 15}
      },
      {
        {6, 252, 13},
        {6, 252, 13},
        {12, 17, 15}
      },
      {
        {12, 21, 20},
        {12, 21, 20},
        {6, 26, 29}
      }
    };
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < res.getHeight(); i++) {
        for (int j = 0; j < res.getWidth(); j++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testLevelAdjustment() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.levelAdjustment(img, 0, 128, 255);
    int[][][] expected = new int[][][]{
      {
        {1, 254, 3},
        {10, 255, 6},
        {11, 255, 9}
      },
      {
        {12, 250, 11},
        {14, 251, 15},
        {13, 17, 18}
      },
      {
        {11, 20, 21},
        {12, 23, 24},
        {14, 26, 27}
      }
    };
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < res.getHeight(); i++) {
        for (int j = 0; j < res.getWidth(); j++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }


  @Test
  public void testHistogram() throws IOException {
    int[][][] threeDimensionalArray4 = new int[][][]{
      {
        {130, 95, 190},
        {130, 95, 190},
        {130, 95, 0}
      },
      {
        {130, 0, 190},
        {0, 95, 190},
        {130, 0, 0}
      },
      {
        {130, 95, 190},
        {0, 95, 0},
        {0, 0, 190}
      }
    };
    ImageInfo img = new ImageData(threeDimensionalArray4);
    ImageInfo res = model.produceHistogram(img);
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        if (i == 130) {
          if (j == 0 || j == 94 || j == 96 || j == 189
                  || j == 191) {
            assertEquals(0, res.getPixelValue(i, j, 0));
          } else if (j % 16 == 0) {
            assertEquals(150, res.getPixelValue(i, j, 0));
          } else {
            assertEquals(255, res.getPixelValue(i, j, 0));
          }
        } else if (i == 95) {
          if (j == 130 || j == 190) {
            assertEquals(0, res.getPixelValue(i, j, 1));
          } else if (j % 16 == 0) {
            assertEquals(150, res.getPixelValue(i, j, 1));
          } else {
            assertEquals(255, res.getPixelValue(i, j, 1));
          }
        } else if (i == 190) {
          if (j == 94 || j == 96 || j == 129
                  || j == 131) {
            assertEquals(0, res.getPixelValue(i, j, 2));
          } else if (j % 16 == 0 && j != 0) {
            assertEquals(150, res.getPixelValue(i, j, 2));
          } else {
            assertEquals(255, res.getPixelValue(i, j, 2));
          }
        }
      }
    }
  }

  @Test
  public void testColorCorrection() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.colorCorrectImage(img);
    int[][][] expected = new int[][][]{
      {
        {3, 250, 5},
        {12, 251, 8},
        {13, 251, 11}
      },
      {
        {14, 246, 13},
        {16, 247, 17},
        {15, 13, 20}
      },
      {
        {13, 16, 23},
        {14, 19, 26},
        {16, 22, 29}
      }
    };
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < res.getHeight(); i++) {
        for (int j = 0; j < res.getWidth(); j++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitBlurFullImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitBlur(img, 100);
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
  public void testSplitBlurHalfImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitBlur(img, 50);
    int[][][] expected = new int[][][]{
      {
        {1, 1, 2},
        {4, 5, 6},
        {7, 8, 9}
      },
      {
        {5, 5, 6},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {6, 6, 6},
        {22, 23, 24},
        {25, 26, 27}
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
  public void testSplitBlurZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitBlur(img, 0);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray3[i][j][k],
                  res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitSharpenFullImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitSharpen(img, 100);
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
  public void testSplitSharpenZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitSharpen(img, 0);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray3[i][j][k],
                  res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitSharpenHalfImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitSharpen(img, 50);
    int[][][] expected = new int[][][]{
      {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
      },
      {
        {15, 16, 18},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {21, 22, 23},
        {22, 23, 24},
        {25, 26, 27}
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
  public void testSplitSepiaFullImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitSepia(img, 100);
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
  public void testSplitSepiaZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitSepia(img, 0);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray3[i][j][k],
                  res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitSepiaHalfImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitSepia(img, 50);
    int[][][] expected = new int[][][]{
      {
        {1, 1, 1},
        {4, 5, 6},
        {7, 8, 9}
      },
      {
        {13, 12, 8},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {25, 22, 17},
        {22, 23, 24},
        {25, 26, 27}
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
  public void testSplitGreyscaleFullImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitGreyscale(img, 100);
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

  @Test
  public void testSplitGreyscaleZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitGreyscale(img, 0);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray3[i][j][k],
                  res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitGreyscaleHalfImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitGreyscale(img, 50);
    int[][][] expected = new int[][][]{
      {
        {1, 1, 1},
        {4, 5, 6},
        {7, 8, 9}
      },
      {
        {9, 9, 9},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {19, 19, 19},
        {22, 23, 24},
        {25, 26, 27}
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
  public void testSplitColorCorrectFullImage() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.splitColorCorrect(img, 100);
    int[][][] expected = new int[][][]{
      {
        {3, 250, 5},
        {12, 251, 8},
        {13, 251, 11}
      },
      {
        {14, 246, 13},
        {16, 247, 17},
        {15, 13, 20}
      },
      {
        {13, 16, 23},
        {14, 19, 26},
        {16, 22, 29}
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
  public void testSplitColorCorrectZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitColorCorrect(img, 0);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray3[i][j][k],
                  res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitColorCorrectHalfImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitColorCorrect(img, 50);
    int[][][] expected = new int[][][]{
      {
        {2, 2, 2},
        {4, 5, 6},
        {7, 8, 9}
      },
      {
        {11, 11, 11},
        {13, 14, 15},
        {16, 17, 18}
      },
      {
        {20, 20, 20},
        {22, 23, 24},
        {25, 26, 27}
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
  public void testSplitLevelAdjustmentFullImage() {
    ImageInfo img = new ImageData(threeDimensionalArray1);
    ImageInfo res = model.splitLevelAdjustment(img, 0, 128,
            255, 100);
    int[][][] expected = new int[][][]{
      {
        {1, 254, 3},
        {10, 255, 6},
        {11, 255, 9}
      },
      {
        {12, 250, 11},
        {14, 251, 15},
        {13, 17, 18}
      },
      {
        {11, 20, 21},
        {12, 23, 24},
        {14, 26, 27}
      }
    };
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < res.getHeight(); i++) {
        for (int j = 0; j < res.getWidth(); j++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitLevelAdjustmentZeroPercent() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitLevelAdjustment(img, 0,
            128, 255, 0);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(threeDimensionalArray3[i][j][k],
                  res.getPixelValue(i, j, k));
        }
      }
    }
  }

  @Test
  public void testSplitLevelAdjustmentHalfImage() {
    ImageInfo img = new ImageData(threeDimensionalArray3);
    ImageInfo res = model.splitLevelAdjustment(img, 0, 128,
            255, 50);
    int[][][] expected = new int[][][]{
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
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], res.getPixelValue(i, j, k));
        }
      }
    }
  }

}