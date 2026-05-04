/**
 * SameGame - Main class that starts the game.
 * 
 * Creates the model and attaches two views:
 * 1. GraphicalView - Swing window
 * 2. ConsoleView - text output for debugging
 */
public class SameGame
{
   public static void main(String[] args)
   {
      // Create the model with 3 colors (difficulty)
      GameModel model = new GameModel(3);

      // Create and attach two views (Observer pattern)
      GraphicalView graphicalView = new GraphicalView(model);
      ConsoleView consoleView = new ConsoleView();

      model.addObserver(graphicalView);
      model.addObserver(consoleView);

      // Start the game
      model.newGame();
   }
}