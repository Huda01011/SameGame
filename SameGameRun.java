/**
 * SameGame - Main class that starts the game.
 * 
 * Creates the model and attaches two views:
 * 1. GraphicalView - Swing window
 * 2. ConsoleView - text output for debugging
 */
public class SameGameRun{
   public static void Run(String[] input){

      GameModel model = new GameModel(3);
      // Create the model with 3 colors (difficulty)
      if(input[0].matches(".*\\d.*")) {
         int num = Integer.parseInt(input[0]);
         // System.out.println(num);
         model = new GameModel(num);
      } 
      
      //GameModel model = new GameModel();
      

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
}