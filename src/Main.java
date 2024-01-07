
import controller.ConsoleUserIO;
import controller.EnhancedController;
import controller.ViewControllerImpl;
import controller.ImageProcessingController;
import controller.UserIO;
import model.EnhancedImageProcessingModel;
import model.EnhancedImageProcessor;
import view.ImageView;
import view.ImageViewImpl;
import view.View;
import view.ViewInterface;

/**
 * The main class for the Image Processing application.
 * It initializes the key components of the application and starts the execution.
 */
public class Main {

  /**
   * Main method, transfers the control to the controller after
   * initializing the model and view.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    EnhancedImageProcessingModel model = new EnhancedImageProcessor();
    if (args.length > 0) {
      ViewInterface view = new View();
      UserIO io = new ConsoleUserIO(view);
      if (!args[0].equals("-file") && !args[0].equals("-text")) {
        io.print("Invalid arguments provided");
        return;
      }
      ImageProcessingController controller = new EnhancedController(io, model, args);
      controller.execute();
    }
    else  {
      ImageView view = new ImageViewImpl();
      UserIO io = new ConsoleUserIO(view);
      new ViewControllerImpl(io, model,
              new String[1], view);
    }
  }
}