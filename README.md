# Assignment 4+5+6


This repository houses an image processing application with a text-based interface. It is designed to provide a range of operations for 
image manipulation. Below is an overview of the available functionalities:
1. Load Image
2. Save Image
3. Color Components (Red, Green, Blue)
4. Brighten/Darken image
5. Value, Luma, and Intensity components
6. Flip Image (Horizontally or Vertically)
7. Split & Combine - Split the image into its red, green, blue, and greyscale components and recombine them.
8. Blur Image
9. Sharpen Image
10. Sepia Tone
11. Greyscale


The application also allows you to load and execute custom image processing scripts from a script file. This script file should be a .txt file.

The image processing functionality is divided into the following components:

## Model
1. ImageProcessingModel (Interface)
The ImageProcessingModel interface serves as a blueprint for the operations supported by the image processing application. These methods define
the various image processing operations available, ensuring a consistent and standardized interface for processing images.

2. ImageProcessor (Class)
The ImageProcessor class is the central component responsible for executing image processing operations. It implements the ImageProcessingModel interface.
This class serves as the entry point for interacting with the image processing application. The controller can call its methods to perform various image operations.

3. ImageData (Class)
The ImageData class encapsulates the properties of an image and provides low-level image manipulation functionality. It is responsible for performing
operations such as addition, multiplication, component splitting, etc. on images. It provides the necessary tools for pixel-level manipulations.

4. ModelUtil (Class)
This serves as a utility class responsible for providing predefined image filters used in various image processing operations. It offers methods to
obtain specific filters such as blur, sharpen, grayscale, and sepia tone.

5. Color (Enum)
Enumeration which represents the 3 colors of an image (Red, Green, Blue).

6. TriFunction (Functional Interface)
This is employed as a high-level function within the image processing application, facilitating the calculation and extraction of value, intensity,
and luma components from images by accepting three input parameters and returning a result.


## Controller
1. ImageProcessingController (Interface)
This interface defines the controller responsible for user interaction and command execution. It is responsible for executing image processing commands based on 
user input. It also serves as the entry point for processing user requests, facilitating the transformation of images according to the specified operations.

2. Controller (Class)
This class implements the ImageProcessingController interface. It serves as the user interface and command execution hub. This essential component interacts
with the user, manages the execution of diverse image processing operations, and ensures the smooth communication between the user, the image processing model,
and the view. All the operations inside the model (from the image processor) are called by this controller.

3. ImageLoaderSaver (Interface)
We are treating load and save as I/O operations, thus they are a part of the controller. This interface defines the load and save operations for images.
Classes implementing this interface are responsible for handling these image file operations.

4. PPMImageLoaderSaver (Class)
The PPMImageLoaderSaver class is one of the implementations of the ImageLoaderSaver interface, tailored specifically for Portable Pixmap (PPM) image files. This
class specializes in loading and saving PPM images.

5. OtherImageLoaderSaver (Class)
This class serves as another implementation of the ImageLoaderSaver interface, designed to handle various image formats, including JPEG and PNG. 

6. ImageLoaderSaverFactory (Class)
This class is responsible for dynamically creating instances of classes that implement the ImageLoaderSaver interface based on the provided file extension.

7. UserIO (Interface)
This interface defines methods that facilitate interactions with user input and output. Implementations of this interface are utilized to read user input,
display messages to the user, and collect lines of text from user interactions.

8. ConsoleUserIO (Class)
IO class to interact with the user on the console.

9. MockUserIO (Class)
Mock IO class for logging purposes.


## View
For now, our View interface only has one function which prints the error/result back to the console for the user to view.
The interface is named ViewInterface and the class implementing it is View. 


## High Level Design
The Main.java file hands over the control to the controller class after initialising the view, model and controller components.
Our controller is in charge of the I/O operations (user input, loading and saving images). Since loading and saving are treated as I/O operations, they reside within the controller. All other image processing operations are encapsulated within the model (ImageProcessingModel).

The controller handles user input and invokes the corresponding functions within the model, and the processed image is returned to the controller. Additionally, the controller maintains the list of images. Therefore, it must have knowledge of the ImageData class for loading and saving images.

The view component, as of now, includes a single function designed to print errors and results to the console.


## Acknowledgements
The image used for testing in this project is from [Sample Videos](https://sample-videos.com/download-sample-png-image.php). This website has a lot of small png images.
The image we've used is of dimensions 272x170 and of size 104 KB. It is named as random.png in our res folder.



The res folder also has all the processed versions of the sample image along with the class diagram of our implementation.

## Assignment 5

For Assignment 5, we have added the following functionalities:
1. Compression - compress an image
2. Histogram - produce the histogram of an image
3. Color correction of an image
4. Level adjustment of an image using shadow, mid and highlight values
5. Split operations on an image - supported for sharpen, blur, sepia, greyscale, color correct and level adjustment.

## Fixes
- Added javadocs for all the missing class/interfaces and public functions.
- Removed the error message for brightness values in the controller.
- Changed the test cases for brightness accordingly in test/ImageProcessorTest.java.
- Introduced a new interface, ImageInfo, for managing image data within the model. Substituted the class name (ImageData) with the interface name in the controller to promote consistent usage of the interface name.
- Moved the MockUserIO class to the test folder.

## New Features
- Added a new interface: EnhancedImageProcessingModel in our model for new functionalities. This extends the old interface: ImageProcessingModel so the old methods also keep running (backward compatibility).
- Implemented a new class: EnhancedImageProcessor to provide the implementation for the added methods in EnhancedImageProcessingModel. This class extends the existing class, allowing the reuse of methods for the original functionalities.
- No changes required in the existing model classes.
- Also created a new controller class: EnhancedController which supports the new operations - compress, histogram, color correct, levels adjustment and running a script file through command line arguments. This class implements the existing interface (ImageProcessingController) and extends the old Controller class.
- No changes made to the old controller to support the new operations.

## Changes
- We made changes in the existing controller class to support the split functionality for the old operations - sharpen, blur, greyscale, sepia.
Their existing private helper methods have been modified to make the new changes. We did not incorporate these changes in the new 
controller class because we would have to change the visibility of the old methods (private to package private) to support the new split functionality.
Thus, we felt it would be better to make these changes in the existing class.
- Fields io, model and imageList have been made protected, because they need to be accessed by the new Controller class.
- We have used a new image for testing these functionalities. The existing functionalities remain unchanged, and their corresponding images have been updated as well. Details of the new image have been updated in the Acknowledgements section  of this README.
- We are initialising the model and controller using the new classes in the Main.java file.
- The mock model(MockImageProcessingModel) has been updated. It extends the new model class (EnhancedImageProcessingModel). All new methods have been implemented.
- We have added test cases for the new functionalities in new test files for both the controller(EnhancedControllerTest) and model(EnhancedImageProcessorTest).
- Test cases for the split operations have been added in the existing test file because the change has been made in the old controller's file.
- Made isInteger(), executeLine(), runScriptFile() functions in the existing controller class package private. The visibility of these functions had to be changed
so we can reuse them in the EnhancedController class.
- The script file has been updated (res/script.txt). It has all the commands (old and new).
- The Main.java has also been changed. The model is being initialised using the new interface and class names - EnhancedImageProcessingModel and EnhancedImageProcessor. For the controller, the interface remains the same but the class being used is EnhancedController. The EnhancedController class includes an additional argument (args from the main method). This is done to support the option of accepting a script file as a command-line input.

## Assignment 6
## Fixes
* We can now load file paths which have spaces in them. This can be done using the GUI or the text commands. We changed the existing controller to fix this issue.
* Added the missing documentation for methods in the ConsoleUserIO class.
* Made getImage(String imageName) in the controller class private instead of package-private.

No other changes made in the existing classes.

## New Features
Added a new interface (ImageView) and class (ImageViewImpl) for the new GUI operations.
The GUI supports the following operations from the UI:
1. Load
2. Save
3. Red, green, blue component
4. Horizontal, vertical flip
5. Compress
6. Blur, Greyscale, Sharpen, Sepia, Color Correct, Level Adjustment (Preview provided with split functionality before operation is applied)

All the command callbacks have been implemented in the controller. We have created a new controller interface called ControllerForGUI which represents the features that the GUI can perform. This interface is implemented by ViewControllerImpl class which implements all these operations. This class extends the existing EnhancedController class to be able to reuse the existing functions and maintain backward compatibility.

The ImageViewImpl class in the view manages UI components, and the controller handles callbacks. This makes sure that the controller doesn't reveal implementation details to the view.

Screenshot of the GUI with a preloaded image is provided in the res folder (GUIScreenshot).








