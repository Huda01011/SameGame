import java.util.Arrays;

/**
 * SameGame - Main class that starts the game.
 * 
 * Creates the model and attaches two views:
 * 1. GraphicalView - Swing window
 * 2. ConsoleView - text output for debugging
 */
public class SameGameRun{

   // To use in terminal, write ex: java SameGameRun name 2 5
   public static void Run(String[] inputs){
      Run(Arrays.copyOfRange(inputs, 1, inputs.length), inputs[0]);
   }

   public static void Run(String[] input, String name){

      GameModel model = new GameModel(3);
      // Create the model with 3 colors (difficulty)
      if(input[0].matches(".*\\d.*")) {
         int num = Integer.parseInt(input[0]);
         // System.out.println(num);
         model = new GameModel(num);
      } 
      
      //GameModel model = new GameModel();
      model.setName(name);

      // Create and attach two views (Observer pattern)
      GraphicalView graphicalView = new GraphicalView(model);
      ConsoleView consoleView = new ConsoleView();

      //IntroView introView = new IntroView(intro);

      if(input[1].matches(".*\\d.*")) {
         int num = Integer.parseInt(input[1]);
         int tSize = model.setNumRowsAndCols(num);
         // System.out.println(num);
         graphicalView.setTileSize(tSize);
      } 

      //intro.addObserver(introView);

      model.addObserver(graphicalView);
      model.addObserver(consoleView);

      // Add sound Effects
      // ... 
      
      // Start the gamep
      model.newGame();
   }

   public static void main(String[] args) {
      Run(args);
   }
}