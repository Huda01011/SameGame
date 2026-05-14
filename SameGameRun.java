import java.util.Arrays;

/**
 * SameGameRun - Sets up and launches the SameGame.
 *
 * Creates the GameModel with the given difficulty settings,
 * attaches all views (GraphicalView, ConsoleView, SoundView)
 * using the Observer pattern, and starts the game.
 */
public class SameGameRun
{
   /**
    * Runs the game from command line arguments.
    * The first argument is the player name, followed by difficulty settings.
    * Example: java SameGameRun Alice 3 5
    * @param inputs array where inputs[0] is the player name
    */
   public static void Run(String[] inputs)
   {
      Run(Arrays.copyOfRange(inputs, 1, inputs.length), inputs[0]);
   }

   /**
    * Sets up and starts the game with given settings.
    * @param input array where input[0] = number of colors, input[1] = grid size
    * @param name the player name
    */
   public static void Run(String[] input, String name)
   {
      // Create the model with given number of colors
      GameModel model = new GameModel(3);
      if (input[0].matches(".*\\d.*"))
         model = new GameModel(Integer.parseInt(input[0]));

      model.setName(name);

      // Create the two views (Observer pattern)
      GraphicalView graphicalView = new GraphicalView(model);
      ConsoleView consoleView = new ConsoleView();

      // Set grid size based on difficulty
      if (input[1].matches(".*\\d.*"))
      {
         int num = Integer.parseInt(input[1]);
         int tSize = model.setNumRowsAndCols(num);
         graphicalView.setTileSize(tSize);
      }

      // Attach all observers
      model.addObserver(graphicalView);
      model.addObserver(consoleView);
      model.addObserver(new SoundView());

      // Start the game
      model.newGame();
   }

   /**
    * Main method for running from the command line.
    * @param args command line arguments
    */

   public static void main(String[] args)
   {
      Run(args);
   }
}
