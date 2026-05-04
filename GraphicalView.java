import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GraphicalView - A Swing based graphical view of the SameGame.
 * 
 * Shows the game grid as colored rectangles.
 * Handles mouse clicks and passes them to the GameModel.
 */
public class GraphicalView extends JFrame implements GameView
{
   // Size of each tile in pixels
   private static final int TILE_SIZE = 50;

   // Colors for the tiles (index 1-5)
   private static final Color[] COLORS = {
      Color.BLACK,       // 0 = empty (not used)
      Color.RED,         // 1
      Color.BLUE,        // 2
      Color.GREEN,       // 3
      Color.YELLOW,      // 4
      Color.MAGENTA      // 5
   };

   private GameModel model;
   private JPanel gamePanel;
   private JLabel scoreLabel;

   public GraphicalView(GameModel model)
   {
      this.model = model;

      setTitle("SameGame");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());

      // Score label at the top
      scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
      scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
      add(scoreLabel, BorderLayout.NORTH);

      // Game panel in the center
      gamePanel = new JPanel()
      {
         @Override
         protected void paintComponent(Graphics g)
         {
            super.paintComponent(g);
            drawGrid(g);
         }
      };

      gamePanel.setPreferredSize(new Dimension(
         GameModel.COLS * TILE_SIZE,
         GameModel.ROWS * TILE_SIZE
      ));

      // Mouse listener for clicking tiles
      gamePanel.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mousePressed(MouseEvent e)
         {
            int col = e.getX() / TILE_SIZE;
            int row = e.getY() / TILE_SIZE;
            model.click(row, col);
         }
      });

      add(gamePanel, BorderLayout.CENTER);

      // New game button at the bottom
      JButton newGameButton = new JButton("New Game");
      newGameButton.addActionListener(e -> model.newGame());
      add(newGameButton, BorderLayout.SOUTH);

      pack();
      setVisible(true);
   }

   /**
    * Draws the game grid
    */
   private void drawGrid(Graphics g)
   {
      for (int r = 0; r < GameModel.ROWS; r++)
      {
         for (int c = 0; c < GameModel.COLS; c++)
         {
            int color = model.getColor(r, c);
            if (color == 0)
            {
               g.setColor(Color.LIGHT_GRAY);
            }
            else
            {
               g.setColor(COLORS[color]);
            }
            g.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE - 2, TILE_SIZE - 2);
         }
      }
   }

   @Override
   public void update(GameModel model)
   {
      scoreLabel.setText("Score: " + model.getScore());
      gamePanel.repaint();

      if (model.isWon())
         JOptionPane.showMessageDialog(this, "You Win! Score: " + model.getScore());
      else if (model.isLost())
         JOptionPane.showMessageDialog(this, "Game Over! Score: " + model.getScore());
   }
}