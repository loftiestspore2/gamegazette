package Snake;

import javax.swing.JFrame;

public class Snake extends JFrame {
     
    public static final long serialVersionUID = 1L;

    public Snake() {
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


