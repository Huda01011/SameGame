import java.awt.*;
import javax.swing.*;


public class IntroFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IntroFrame().createUI());
    }

    private void createUI() {
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

        // Running the game
        SameGameRun runner = new SameGameRun();

        // Start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String difficulty = (String)difficultyBox.getSelectedItem();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name!");
                return;
            } 

            System.out.println(difficulty);

            // The main functionality to run the program
            String[] input = new String[]{"2", "3"};

            switch (difficulty) {
                case "SuperEasy":
                    input[0] = "1";
                    input[1] = "2";
                    break;
                case "Easy":
                    input[0] = "2";
                    input[1] = "3";
                    break;
                case "Medium":
                    input[0] = "3";
                    input[1] = "5";
                    break;
                case "Hard":
                    input[0] = "4";
                    input[1] = "7";
                    break;
                case "Expert":
                    input[0] = "5";
                    input[1] = "9";
                    break;
                default:
                    input[0] = "3";
                    input[1] = "5";
            }

            runner.Run(input);

            frame.dispose();
            //openMainWindow(name, difficulty);
            
        });

        panel.add(title);
        panel.add(namePanel);
        panel.add(diffPanel);
        panel.add(new JLabel()); // spacer
        panel.add(startButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void openMainWindow(String name, String difficulty) {
        JFrame mainFrame = new JFrame("Main App");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        JLabel label = new JLabel(
            "Welcome " + name + " | Difficulty: " + difficulty,
            JLabel.CENTER
        );

        mainFrame.add(label);
        mainFrame.setVisible(true);
    }
}