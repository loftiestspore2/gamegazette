package Brick_Breaker;

import javax.swing.*;

public class BrickBreaker{

    // Constructor to set up the game
    public BrickBreaker() {
        setupGame();
    }

    // Main method
    public static void main(String[] args) {//add Icon
        // Use Swing's Event Dispatch Thread to ensure thread safety
        SwingUtilities.invokeLater(() -> new Main());
    }

    // Method to set up the game window
    private void setupGame() {
        JFrame frame = new JFrame();
        GamePlay gamePlay = new GamePlay();
        frame.setBounds(10, 10, 700, 700);
        frame.setTitle("Breakout Ball");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(gamePlay);
        frame.setVisible(true);

    }
}


