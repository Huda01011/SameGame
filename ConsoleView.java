/**
 * ConsoleView - A text based view of the SameGame.
 * 
 * Prints the game grid to the console using numbers to represent colors.
 * Useful for debugging.
 */
public class ConsoleView implements GameView
{
   @Override
   public void update(GameModel model)
   {
      System.out.println("\n=== SameGame ===");
      System.out.println("Score: " + model.getScore());
      System.out.println("Name: " + model.getName());
      System.out.println();

      for (int r = 0; r < GameModel.ROWS; r++)
      {
         for (int c = 0; c < GameModel.COLS; c++)
         {
            int color = model.getColor(r, c);
            if (color == 0)
               System.out.print(". ");
            else
               System.out.print(color + " ");
         }
         System.out.println();
      }



      if (model.isWon()) {
         System.out.println("\nYOU WIN! " +model.getName());
      // Get the score and save
         Highscores hs = new Highscores();
         hs.load();
         //hs.printScores();
         hs.addScore(model.getName(), model.getScore());
         hs.printScores();
         hs.saveScores();
      } else if (model.isLost()) {
         System.out.println("\nGAME OVER "+model.getName()+"- No more moves!");
      }
   }
}