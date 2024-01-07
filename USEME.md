## Supported script commands


1. Load Image - command should be of the format: load image-path image-name
   
   eg. load res/random.png png


2. Save Image - command should be of the format: save image-path image-name

   eg. save res/pngNew.png png


3. Color Components (Red, Green, Blue) - command should be of the format: red-component image-name dest-image-name. Similar commands
   for green, blue, value, luma, intensity components - blue-component image-name dest-image-name, green-component image-name dest-image-name
   
   eg. red-component png pngRedComponent


4. Brighten/Darken image - command should be of the format: brighten increment image-name dest-image-name

   eg. brighten 20 png pngBrighter / brighten -20 png pngDarker

   Both operations are performed in the same method. Values above 0 will brighten the image, while values below 0 will darken the image.


5. Value, Luma, and Intensity components - command should be of the format: value-component image-name dest-image-name,
   luma-component image-name dest-image-name, intensity-component image-name dest-image-name.
   
   eg. value-component png pngValueComponent


6. Flip Image (Horizontally or Vertically) - command should be of the format: horizontal-flip image-name dest-image-name / vertical-flip image-name dest-image-name

   eg. horizontal-flip png pngHorizontal/ vertical-flip png pngVertical


7. Split & Combine - command should be of the format: 
   
   rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue

   rgb-combine image-name red-image green-image blue-image
   
   eg. rgb-split png pngRed pngGreen pngBlue, rgb-combine pngNew pngRed pngGreen pngBlue

   While performing combine operation, make sure the rgb images to be combined are already present, else a corresponding error message is displayed.


8. Blur Image - command should be of the format: blur image-name dest-image-name
 
   eg. blur png pngBlurred


9. Sharpen Image - command should be of the format: sharpen image-name dest-image-name
   
   eg. sharpen png pngSharpened


10. Sepia Tone - command should be of the format: sepia image-name dest-image-name 

    eg. sepia png pngSepia


11. Greyscale - command should be of the format: greyscale image-name dest-image-name
    
    eg. greyscale png pngGreyscale 

--- For all the above operations, load and save the image before performing the operation, else a corresponding error message is displayed.

12. Run Script File - command should be of the format: run script-file
    
    eg. run res/script.txt

    The script file should be a .txt file, else a corresponding error message is displayed.

Please note that our program does not support file paths containing spaces, and as a result, it will show "file not found" in such cases.
The same restriction applies to the "load" and "save" operations. File/folder paths should not contain spaces.


13. Exit Program - command should be of the format: exit



## For Assignment 5, we have included the following functions:

1. Compression - command should be of the format: compress percentage image-name dest-image-name 

    eg. compress 20 image compressedImage

   Percentage value should be an integer between 0 and 100, if not an error message is displayed. 


2. Histogram - command should be of the format: histogram image-name dest-image-name

    eg. histogram image histogramOfTheImage


3. Color correction - command should be of the format: color-correct image-name dest-image-name

    eg. color-correct image colorCorrectedImage


4. Level adjustment (shadow, mid and highlight) - command should be of the format: levels-adjust b m w image-name dest-image-name

   eg. levels-adjust 30 180 245 png pngLevelAdjusted

   Shadow, mid and highlight values should be between 0 and 255, if not an error message is displayed.


5. Split (sharpen, blur, sepia, greyscale, color correct, level adjustment) - command should be of the format: blur image-name dest-image split p

   eg. blur png pngBlurred split 50

   Value of p should be an integer between 0 and 100, if not an error message is displayed

For all the above operations, load the image before performing the operation, else a corresponding error message is displayed.

Our code also supports the ability to accept a script file as a command-line option. You can directly put the location of the script file as one of the arguments,
do not put any other arguments before or after the location of the script file, example "res/script.txt" will work. The program will run the script and exit. If program is run without any command line arguments, it will allow interactive entry of script commands as before.

There are two script files in the res folder: "script.txt" runs the program normally, while "scriptForJarFile.txt" is for running with the provided jar file in the res folder.  The reason is that for the script.txt to run, load and save needs to be done by providing the path of the image with the res folder, but since the jar file is inside the res folder already we can run the commands without adding res/ in the path.


## For Assignment 6, we have included the following functions:
Used Java Swing to build the GUI. Included scroll functionality to scroll through the image if the image size is greater than the allocated area in the GUI. Included buttons and text fields for all the operations implemented in Assignment 4 and 5 (Load, Save, Red/Green/Blue Component, Blur/Sharpen, Horizontal/Vertical Flip, Compression, Histogram, Level-Adjustment, and Split)
For every function, a histogram is available at all times to the right of the image. At first, before loading an image, none of the buttons will be functional except the Load and exit button. Only after the user selects an image will the buttons be functional.
For compress, level-adjustment and split factor, the user is prompted to enter values in a text box. If the values are out of rang, an error message pops up asking the user to enter a value between 0 and 255 for level-adjustment/ 0 and 100 for other operations.
For blur, sharpen, color-correct, level-adjustment, greyscale, sepia and split, the user is prompted to enter values and can choose to apply the filter after seeing a preview of the operation on the image.
At any given point, clicking the Exit button will exit the window.


1. Load - This button opens a window for the user to choose the image to perform functions on. It only supports jpeg, png and ppm formats. Any attempts to add another file type will result in a dialogue box showing an "Unsupported File Extension Type" error message.


2. Save - This button enables the user to save the image after any function is performed on it. It only supports saving in jpeg, png and ppm formats. Any attempts to add another file type will result in a dialogue box showing an "Unsupported File Extension Type" error message.


3. Color Components (Red, Green, Blue) - This button gives a red/green/blue component of the image respectively. Alongside the image is a corresponding histogram. Trying to get another component of an already red/green/blue image will result in a black image.
   
   eg. A Red Component of an image will not have a Green Component.


4. Flip (Horizontal, Vertical) - This button flips the image horizontally or vertically. The action can be performed any number of times. Alongside the image is a corresponding histogram.


5. Compress - This button opens a text box for the user to enter a compression factor between 0 and 100. If the value entered is out of this range, a dialogue box is displayed prompting the user to enter a valid number. Alongside the image is a corresponding histogram. At any given time, the user can click the 'Cancel' button to cancel the operation.


6. Blur/Sharpen - This button opens a window showing a 100% blurred/sharpened preview image. The user can click the "Change split factor" if they want a lesser blurred/sharpened image. If the value entered is out of the 0 - 100 range, a dialogue box is displayed prompting the user to enter a valid number. The "Apply Operation" button will apply the filter to the image. Alongside the image is a corresponding histogram. At any given time, the user can click the 'Cancel' button to cancel the operation.


7. Greyscale - This button opens a window showing a 100% greyscale preview image. The user can click the "Change split factor" if they want a lesser greyscale image. If the value entered is out of the 0 - 100 range, a dialogue box is displayed prompting the user to enter a valid number. The "Apply Operation" button will apply the filter to the image. Alongside the image is a corresponding histogram. At any given time, the user can click the 'Cancel' button to cancel the operation.


8. Color Correct - This button opens a window showing a 100% color corrected preview image. The user can click the "Change split factor" if they want the image color correction lower. If the value entered is out of the 0 - 100 range, a dialogue box is displayed prompting the user to enter a valid number. The "Apply Operation" button will apply the filter to the image. Alongside the image is a corresponding histogram. At any given time, the user can click the 'Cancel' button to cancel the operation.


9. Sepia - This button opens a window showing a 100% sepia-toned preview image. The user can click the "Change split factor" if they want a lesser sepia-toned image. If the value entered is out of the 0 - 100 range, a dialogue box is displayed prompting the user to enter a valid number. The "Apply Operation" button will apply the filter to the image. Alongside the image is a corresponding histogram. At any given time, the user can click the 'Cancel' button to cancel the operation.


10. Level Adjust - This button opens a text box for the user to enter Shadow, Mid and Highlight values. Each of the values should be between 0 and 255. If the values entered are out of this range, a dialogue box is displayed prompting the user to enter a valid number within the range. The title of the dialogue box also prompts the values for the levels. Once the user enters the values, a preview of the image is visible in a new window where the user can choose to change the split values, apply the filter or cancel the operation. Alongside the image is a corresponding histogram.


11. Exit - This button exits the window completely. It is enabled to be used at all times, be it before/after loading/saving the image.


For running the jar file, open a command-prompt/terminal and navigate to the res folder. Now type:
java -jar Assignment\ 4.jar -file scriptForJarFile.txt
This will run the script and exit.
To run the program using the graphical user interface, type:
java -jar Assignment\ 4.jar. This is what will happen if you simply double-click on the jar file.
To run the jar file in interactive text mode, type java -jar Assignment\ 4.jar -text. This will allow the user to type and execute the commands one at a time.
Any other command-line arguments are invalid: Error message is displayed to the user - "Invalid arguments provided" and the program quits.
Sample image to try out our program remains the same - you can use either of the 3 images - random.png, random.jpeg, random.ppm. These are present in the res folder.
