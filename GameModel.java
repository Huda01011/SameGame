import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GameModel - The brain of the SameGame.
 * 
 * Keeps track of:
 * - The grid of colored tiles
 * - The current score
 * - The difficulty (number of colors)
 * - Win/lose conditions
 * - Notifies observers when the state changes (Observer pattern)
 */
public class GameModel
{
   // Hej
   // Grid dimensions
   public static final int ROWS = 10;
   public static final int COLS = 15;

   // 0 means empty cell
   private int[][] grid;

   // Current score
   private int score;

   // Number of colors (difficulty)
   private int numColors;

   // List of observers (Observer pattern)
   private List<GameView> observers;

   /**
    * Creates a new game with the given number of colors (difficulty)
    * More colors = easier, fewer colors = harder
    */
   public GameModel(int numColors)
   {
      this.numColors = numColors;
      observers = new ArrayList<GameView>();
      newGame();
   }

   /**
    * Starts a new game by filling the grid with random colors
    */
   public void newGame()
   {
      grid = new int[ROWS][COLS];
      score = 0;
      Random rand = new Random();
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++)
            grid[r][c] = rand.nextInt(numColors) + 1; // colors 1 to numColors
      notifyObservers();
   }

   /**
    * Called when the player clicks on a tile at (row, col)
    * Finds all connected tiles of the same color and removes them
    */
   public void click(int row, int col)
   {
      if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return;
      if (grid[row][col] == 0) return; // empty cell

      // Find all connected tiles of the same color
      List<int[]> group = findGroup(row, col);

      // Need at least 2 tiles to remove
      if (group.size() < 2) return;

      // Calculate score - more tiles = more points
      score += group.size() * group.size();

      // Remove the tiles
      for (int[] pos : group)
         grid[pos[0]][pos[1]] = 0;

      // Apply gravity - tiles fall down
      applyGravity();

      // Shift columns left if a column is empty
      shiftColumnsLeft();

      // Notify all observers of the change
      notifyObservers();
   }

   /**
    * Finds all connected tiles of the same color starting from (row, col)
    * Uses flood fill algorithm
    */
   private List<int[]> findGroup(int row, int col)
   {
      List<int[]> group = new ArrayList<int[]>();
      boolean[][] visited = new boolean[ROWS][COLS];
      int color = grid[row][col];
      floodFill(row, col, color, visited, group);
      return group;
   }

   /**
    * Recursive flood fill to find connected tiles of the same color
    */
   private void floodFill(int row, int col, int color, boolean[][] visited, List<int[]> group)
   {
      if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return;
      if (visited[row][col]) return;
      if (grid[row][col] != color) return;

      visited[row][col] = true;
      group.add(new int[]{row, col});

      floodFill(row - 1, col, color, visited, group);
      floodFill(row + 1, col, color, visited, group);
      floodFill(row, col - 1, color, visited, group);
      floodFill(row, col + 1, color, visited, group);
   }

   /**
    * Applies gravity - tiles fall down to fill empty spaces
    */
   private void applyGravity()
   {
      for (int c = 0; c < COLS; c++)
      {
         int emptyRow = ROWS - 1;
         for (int r = ROWS - 1; r >= 0; r--)
         {
            if (grid[r][c] != 0)
            {
               grid[emptyRow][c] = grid[r][c];
               if (emptyRow != r) grid[r][c] = 0;
               emptyRow--;
            }
         }
      }
   }

   /**
    * Shifts columns left to fill empty columns
    */
   private void shiftColumnsLeft()
   {
      int emptyCol = 0;
      for (int c = 0; c < COLS; c++)
      {
         if (grid[ROWS - 1][c] != 0) // column is not empty
         {
            for (int r = 0; r < ROWS; r++)
            {
               grid[r][emptyCol] = grid[r][c];
               if (emptyCol != c) grid[r][c] = 0;
            }
            emptyCol++;
         }
      }
   }

   /**
    * Checks if the game is won (board is empty)
    */
   public boolean isWon()
   {
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++)
            if (grid[r][c] != 0) return false;
      return true;
   }

   /**
    * Checks if the game is lost (no more valid moves)
    */
   public boolean isLost()
   {
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++)
            if (grid[r][c] != 0 && findGroup(r, c).size() >= 2)
               return false;
      return true;
   }

   /**
    * Returns the color at a given position (0 = empty)
    */
   public int getColor(int row, int col)
   {
      return grid[row][col];
   }

   /**
    * Returns the current score
    */
   public int getScore() { return score; }

   // OBSERVER PATTERN methods

   public void addObserver(GameView view) { observers.add(view); }
   public void removeObserver(GameView view) { observers.remove(view); }

   private void notifyObservers()
   {
      for (GameView view : observers)
         view.update(this);
   }
}
