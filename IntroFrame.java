import java.awt.*;
import javax.swing.*;

/**
 * IntroFrame - The introduction/setup screen for SameGame.
 *
 * Shows a window where the player can enter their name and
 * select a difficulty level before starting the game.
 * Difficulty levels control the number of colors and grid size.
 */
public class IntroFrame
{
   /**
    * Main method - launches the intro screen.
    * @param args unused
    */
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(() -> new IntroFrame().createUI());
   }

   /**
    * Creates and displays the intro UI with name input,
    * difficulty selection and a start button.
    */
   private void createUI()
   {
      JFrame frame = new JFrame("Game Setup");
      frame.setSize(400, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);

      JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

      // Title
      JLabel title = new JLabel("Start Game", JLabel.CENTER);
      title.setFont(new Font("Arial", Font.BOLD, 18));

      // Name input
      JTextField nameField = new JTextField();
      JPanel namePanel = new JPanel(new BorderLayout());
      namePanel.add(new JLabel("Enter your name: "), BorderLayout.WEST);
      namePanel.add(nameField, BorderLayout.CENTER);

      // Difficulty selection
      String[] difficulties = {"SuperEasy", "Easy", "Medium", "Hard", "Expert"};
      JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
      JPanel diffPanel = new JPanel(new BorderLayout());
      diffPanel.add(new JLabel("Select difficulty: "), BorderLayout.WEST);
      diffPanel.add(difficultyBox, BorderLayout.CENTER);

      SameGameRun runner = new SameGameRun();

      // Start button
      JButton startButton = new JButton("Start");
      startButton.addActionListener(e -> {
         String name = nameField.getText().trim();
         String difficulty = (String) difficultyBox.getSelectedItem();

         if (name.isEmpty())
         {
            JOptionPane.showMessageDialog(frame, "Please enter your name!");
            return;
         }

         // Map difficulty to number of colors and grid size
         String[] input = new String[]{"2", "3"};
         switch (difficulty)
         {
            case "SuperEasy": input[0] = "1"; input[1] = "2"; break;
            case "Easy":      input[0] = "2"; input[1] = "3"; break;
            case "Medium":    input[0] = "3"; input[1] = "5"; break;
            case "Hard":      input[0] = "4"; input[1] = "7"; break;
            case "Expert":    input[0] = "5"; input[1] = "9"; break;
            default:          input[0] = "3"; input[1] = "5";
         }

         runner.Run(input, name);
         frame.dispose();
      });

      panel.add(title);
      panel.add(namePanel);
      panel.add(diffPanel);
      panel.add(new JLabel());
      panel.add(startButton);

      frame.add(panel);
      frame.setVisible(true);
   }
}
