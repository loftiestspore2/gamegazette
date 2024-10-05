package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {

    private final JFrame gameFrame;
    private static Dimension mainFrame = new Dimension(600,600);

    public Table(){
        this.gameFrame=new JFrame("Gamegazette Chess");
        final JMenuBar tMenuBar = new JMenuBar();
        menuMeth(tMenuBar);
        this.gameFrame.setSize(mainFrame);
        this.gameFrame.setVisible(true);
    }

    private void menuMeth(final JMenuBar tMenuBar){
        tMenuBar.add(createFileMenu());
    }

    private JMenu createFileMenu(){ // to load Moves
        final JMenu fMenu = new JMenu("File");

        final JMenuItem openPGN = new JMenuItem("Load PGN File");

        openPGN.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                System.out.println("open pgn file");
            }
        });
        fMenu.add(openPGN);
        return fMenu;
    }

}
