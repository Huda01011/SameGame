import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GraphicalView - A Swing based graphical view of the SameGame.
 *
 * Shows the game grid as colored rectangles.
 * Supports swappable input strategies (Strategy pattern).
 */
public class GraphicalView extends JFrame implements GameView
{
   // Size of each tile in pixels - public so input strategies can use it
   public static final int TILE_SIZE = 50;

   // Colors for the tiles (index 1-5)
   private static final Color[] COLORS = {
      Color.BLACK,
      Color.RED,
      Color.BLUE,
      Color.GREEN,
      Color.YELLOW,
      Color.MAGENTA
   };

   private GameModel model;
   private JPanel gamePanel;
   private JLabel scoreLabel;

   // Current input strategy (Strategy pattern)
   private InputStrategy inputStrategy;

   // Cursor position for keyboard input
   private int cursorRow = -1;
   private int cursorCol = -1;

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

      add(gamePanel, BorderLayout.CENTER);

      // Button panel at the bottom
      JPanel buttonPanel = new JPanel();

      JButton newGameButton = new JButton("New Game");
      newGameButton.addActionListener(e -> model.newGame());

      JButton mouseButton = new JButton("Mouse Input");
      mouseButton.addActionListener(e -> setInputStrategy(new MouseInput()));

      JButton keyboardButton = new JButton("Keyboard Input");
      keyboardButton.addActionListener(e -> setInputStrategy(new KeyboardInput()));

      buttonPanel.add(newGameButton);
      buttonPanel.add(mouseButton);
      buttonPanel.add(keyboardButton);
      add(buttonPanel, BorderLayout.SOUTH);

      pack();
      setVisible(true);

      // Default input strategy is mouse
      setInputStrategy(new MouseInput());
   }

   /**
    * Sets the input strategy (Strategy pattern)
    * Disconnects the old one and connects the new one
    */
   public void setInputStrategy(InputStrategy strategy)
   {
      if (inputStrategy != null)
         inputStrategy.disconnect(this);
      inputStrategy = strategy;
      inputStrategy.connect(model, this);
   }

   /**
    * Returns the game panel (used by input strategies)
    */
   public JPanel getGamePanel() { return gamePanel; }

   /**
    * Sets the cursor position (used by keyboard input)
    */
   public void setCursor(int row, int col)
   {
      cursorRow = row;
      cursorCol = col;
      gamePanel.repaint();
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
               g.setColor(Color.LIGHT_GRAY);
            else
               g.setColor(COLORS[color]);

            g.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE - 2, TILE_SIZE - 2);

            // Draw cursor
            if (r == cursorRow && c == cursorCol)
            {
               g.setColor(Color.WHITE);
               g.drawRect(c * TILE_SIZE + 2, r * TILE_SIZE + 2, TILE_SIZE - 6, TILE_SIZE - 6);
            }
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