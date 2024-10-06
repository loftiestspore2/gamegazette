package Snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
     
    public static final long serialVersionUID = 1L;

    GameFrame() {
        GamePanelll panel = new GamePanelll();
        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}


