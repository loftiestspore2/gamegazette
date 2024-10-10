package Snake;

import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

    public static final long serialVersionUID = 1L;

    public Snake() {//icon for Snake
        GamePanelll panel = new GamePanelll();
        //THE background
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        JPanel backgroundPanel = new JPanel() {
            private ImageIcon backgroundImage = new ImageIcon("Gamegazette/images/snakeback.gif");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        ImageIcon xIcon = new ImageIcon("Gamegazette/images/snakeIcon.gif");
        this.setIconImage(xIcon.getImage());

        this.setContentPane(backgroundPanel);
        //this.add(backgroundPanel);

        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}


