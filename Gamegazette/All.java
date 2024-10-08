import Brick_Breaker.*;
import Snake.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class All {

    public static void main(String[] args){
        JFrame gameFrame=new JFrame("Gamegazette");
        gameFrame.setSize(new Dimension(600,600));
        gameFrame.setLayout(new BorderLayout());
        final JMenuBar tMenuBar = createMTable();
        gameFrame.setJMenuBar(tMenuBar);
        //THE background
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        JPanel backgroundPanel = new JPanel() {
            private ImageIcon backgroundImage = new ImageIcon("Gamegazette/images/background.gif");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        ImageIcon xIcon = new ImageIcon("Gamegazette/images/icon.gif");
        gameFrame.setIconImage(xIcon.getImage());

        gameFrame.setContentPane(backgroundPanel);
        gameFrame.setVisible(true);
        //games();

    }

    /*private static void games(){
        final JMenu fMenu = new JMenu("File");

        final JMenuItem exitMenuI = new JMenuItem("Exit");
        exitMenuI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fMenu.add(exitMenuI);
        
    }*/

    private static JMenuBar createMTable(){
        final JMenuBar tMenuBar = new JMenuBar();
        tMenuBar.add(createFileMenu());
        return tMenuBar;
    }

    private static JMenu createFileMenu(){
        final JMenu fMenu = new JMenu("Options");

        final JMenuItem exitMenuI = new JMenuItem("Exit");
        exitMenuI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fMenu.add(exitMenuI);

        final JMenuItem breakerI = new JMenuItem("Play BrickBreaker");
        breakerI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrickBreaker brickBreaker = new BrickBreaker();
            }
        });
        fMenu.add(breakerI);

        final JMenuItem SnakeI = new JMenuItem("Play Snake");
        SnakeI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Snake s = new Snake();
            }
        });
        fMenu.add(SnakeI);
        return fMenu;
    }

    
}
