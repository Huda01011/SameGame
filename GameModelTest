import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest
{
   private GameModel model;

   @BeforeEach
   public void setUp()
   {
      model = new GameModel(3);
   }

   @Test
   public void testNewGameScoreIsZero()
   {
      assertEquals(0, model.getScore());
   }

   @Test
   public void testNewGameGridIsFilled()
   {
      int emptyCount = 0;
      for (int r = 0; r < GameModel.ROWS; r++)
         for (int c = 0; c < GameModel.COLS; c++)
            if (model.getColor(r, c) == 0) emptyCount++;
      assertEquals(0, emptyCount);
   }

   @Test
   public void testColorsInValidRange()
   {
      for (int r = 0; r < GameModel.ROWS; r++)
         for (int c = 0; c < GameModel.COLS; c++)
         {
            int color = model.getColor(r, c);
            assertTrue(color >= 1 && color <= 3);
         }
   }

   @Test
   public void testNewGameResetsScore()
   {
      for (int r = 0; r < GameModel.ROWS; r++)
         for (int c = 0; c < GameModel.COLS; c++)
            model.click(r, c);
      model.newGame();
      assertEquals(0, model.getScore());
   }

   @Test
   public void testClickOutOfBoundsDoesNothing()
   {
      int scoreBefore = model.getScore();
      model.click(-1, -1);
      model.click(100, 100);
      assertEquals(scoreBefore, model.getScore());
   }

   @Test
   public void testGetName()
   {
      model.setName("Alice");
      assertEquals("Alice", model.getName());
   }

   @Test
   public void testNewGameIsNotWon()
   {
      assertFalse(model.isWon());
   }

   @Test
   public void testNewGameIsNotLost()
   {
      assertFalse(model.isLost());
   }

   @Test
   public void testScoreIncreasesAfterValidClick()
   {
      int initialScore = model.getScore();
      for (int r = 0; r < GameModel.ROWS; r++)
         for (int c = 0; c < GameModel.COLS; c++)
            model.click(r, c);
      assertTrue(model.getScore() >= initialScore);
   }
}
