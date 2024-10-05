
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessRulesGUI extends JFrame {

    public ChessRulesGUI() {
        // Set up the frame (main window)
        setTitle("Chess Rules Guide");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        
        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Load the original icon
        ImageIcon originalIcon = new ImageIcon("Chess-icon.png");
        
        // Resize the icon to make it smaller
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        
        // Create a button with the resized icon
        JButton chessButton = new JButton("Click to view Chess Rules", resizedIcon);
        chessButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        chessButton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        // Add button click listener
        chessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChessRules();
            }
        });
        
        // Add the button to the panel
        panel.add(chessButton, BorderLayout.CENTER);
        
        // Add the panel to the frame
        add(panel);
    }
    
    // Method to show the rules of chess
    private void showChessRules() {
        String rules = """
        === Chess Rules ===
        1. **Objective**: Checkmate the opponent's king.
        2. **Movement**:
           - King: One square in any direction.
           - Queen: Any number of squares in any direction.
           - Rook: Any number of squares horizontally or vertically.
           - Bishop: Any number of squares diagonally.
           - Knight: Moves in an "L" shape (two squares in one direction and then one square perpendicular).
           - Pawn: Moves forward one square but captures diagonally.
        3. **Special Moves**:
           - Castling: Move the king two squares towards a rook, then move the rook to the other side of the king.
           - En passant: A pawn capturing move under specific conditions.
           - Pawn Promotion: If a pawn reaches the opposite side of the board, it can be promoted to any piece (except a king).
        4. **Endgame**:
           - Check: The king is in danger of being captured.
           - Checkmate: The king is in check and cannot escape. The game ends.
        """;
        
        // Show the rules in a message dialog
        JOptionPane.showMessageDialog(this, rules, "Chess Rules", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChessRulesGUI gui = new ChessRulesGUI();
                gui.setVisible(true);
            }
        });
    }
}
