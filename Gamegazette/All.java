import Brick_Breaker.*;
import Snake.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class All {
    //private final JFrame gameFrame;
    //private static Dimension mainFrame = new Dimension(600,600);

    public static void main(String[] args){
        JFrame gameFrame=new JFrame("Gamegazette");
        gameFrame.setSize(new Dimension(600,600));
        gameFrame.setLayout(new BorderLayout());
        final JMenuBar tMenuBar = createMTable();
        gameFrame.setJMenuBar(tMenuBar);
        gameFrame.setVisible(true);
        //games();

    }

    private static void games(){
        final JMenu fMenu = new JMenu("File");

        final JMenuItem exitMenuI = new JMenuItem("Exit");
        exitMenuI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fMenu.add(exitMenuI);
        
    }

    private static JMenuBar createMTable(){
        final JMenuBar tMenuBar = new JMenuBar();
        tMenuBar.add(createFileMenu());
        return tMenuBar;
    }

    private static JMenu createFileMenu(){ // to load Moves
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
