import java.awt.event.*;

/**
 * MouseInput - Mouse based input strategy.
 *
 * The player clicks on tiles using the mouse.
 * This is the default input method.
 */
public class MouseInput implements InputStrategy
{
   private MouseListener listener;

   @Override
   public void connect(GameModel model, GraphicalView view)
   {
      listener = new MouseAdapter()
      {
         @Override
         public void mousePressed(MouseEvent e)
         {
            int col = e.getX() / GraphicalView.TILE_SIZE;
            int row = e.getY() / GraphicalView.TILE_SIZE;
            model.click(row, col);
         }
      };
      view.getGamePanel().addMouseListener(listener);
      System.out.println("Mouse input activated!");
   }

   @Override
   public void disconnect(GraphicalView view)
   {
      view.getGamePanel().removeMouseListener(listener);
      System.out.println("Mouse input deactivated!");
   }
}