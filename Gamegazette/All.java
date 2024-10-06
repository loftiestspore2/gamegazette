import Brick_Breaker.*;
import Snake.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static sun.security.util.KeyUtil.validate;

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
        //validate();
    }

    private static JMenuBar createMTable(){
        final JMenuBar tMenuBar = new JMenuBar();
        tMenuBar.add(createFileMenu());
        return tMenuBar;
    }

    private static JMenu createFileMenu(){ // to load Moves
        final JMenu fMenu = new JMenu("File");

        final JMenuItem openPGN = new JMenuItem("Load PGN File");

        openPGN.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                System.out.println("for now this does nothin and this will never print anything meaning :)");
            }
        });
        ///very weird syntax not really
        fMenu.add(openPGN);

        final JMenuItem exitMenuI = new JMenuItem("Exit");
        exitMenuI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fMenu.add(exitMenuI);
        return fMenu;
    }
}
