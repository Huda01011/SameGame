import java.awt.event.*;

/**
 * KeyboardInput - Keyboard based input strategy.
 *
 * The player uses arrow keys to move a cursor and Enter to select a tile.
 * Arrow keys move the cursor, Enter clicks the selected tile.
 */
public class KeyboardInput implements InputStrategy
{
   // Current cursor position
   private int cursorRow = 0;
   private int cursorCol = 0;
   private KeyListener listener;

   @Override
   public void connect(GameModel model, GraphicalView view)
   {
      listener = new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent e)
         {
            switch (e.getKeyCode())
            {
               case KeyEvent.VK_UP:
                  cursorRow = Math.max(0, cursorRow - 1);
                  break;
               case KeyEvent.VK_DOWN:
                  cursorRow = Math.min(GameModel.ROWS - 1, cursorRow + 1);
                  break;
               case KeyEvent.VK_LEFT:
                  cursorCol = Math.max(0, cursorCol - 1);
                  break;
               case KeyEvent.VK_RIGHT:
                  cursorCol = Math.min(GameModel.COLS - 1, cursorCol + 1);
                  break;
               case KeyEvent.VK_ENTER:
                  model.click(cursorRow, cursorCol);
                  break;
            }
            view.setCursor(cursorRow, cursorCol);
         }
      };
      view.addKeyListener(listener);
      view.setFocusable(true);
      view.requestFocus();
      System.out.println("Keyboard input activated! Use arrow keys and Enter.");
   }

   @Override
   public void disconnect(GraphicalView view)
   {
      view.removeKeyListener(listener);
      System.out.println("Keyboard input deactivated!");
   }
}