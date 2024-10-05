
import java.awt.*;
import javax.swing.*;

public class ChessPiecesDisplay extends JPanel {

    // Array of chess pieces to display
    private String[] chessPieces = {"r", "n", "b", "q", "k", "p", "R", "N", "B", "Q", "K", "P"};

    // Load images for the chess pieces
    private Image loadPieceImage(String piece) {
        String imageName = switch (piece) {
            case "r" -> "black_rook.png";
            case "n" -> "black_knight.png";
            case "b" -> "black_bishop.png";
            case "q" -> "black_queen.png";
            case "k" -> "black_king.png";
            case "p" -> "black_pawn.png";
            case "R" -> "white_rook.png";
            case "N" -> "white_knight.png";
            case "B" -> "white_bishop.png";
            case "Q" -> "white_queen.png";
            case "K" -> "white_king.png";
            case "P" -> "white_pawn.png";
            default -> null;
        };
        return new ImageIcon(imageName).getImage();
    }

    // Override the paintComponent method to draw the chess pieces
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 20;  // Starting X position
        int y = 20;  // Y position (same for all pieces)

        // Loop through the chess pieces and draw them
        for (String piece : chessPieces) {
            Image pieceImage = loadPieceImage(piece);
            if (pieceImage != null) {
                g.drawImage(pieceImage, x, y, 64, 64, this);  // Draw each piece at position (x, y)
                x += 80;  // Move the X position to the right for the next piece
            }
        }
    }

    // Set the preferred size of the JPanel to accommodate all the pieces
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 100);  // Width accommodates all pieces; height fits one row of pieces
    }

    // Main method to run the chess pieces display application
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Pieces Display");
        ChessPiecesDisplay chessPiecesDisplay = new ChessPiecesDisplay();
        frame.add(chessPiecesDisplay);
        frame.pack();  // Adjusts the window size based on component sizes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Centers the window
        frame.setVisible(true);
    }
}
