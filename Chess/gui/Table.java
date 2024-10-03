package gui;

import javax.swing.*;
import java.awt.*;

public class Table {

    private final JFrame gameFrame;
    private static Dimension mainFrame = new Dimension(600,600);

    public Table(){
        this.gameFrame=new JFrame("gamegazette_Chess");

        this.gameFrame.setSize(mainFrame);
        this.gameFrame.setVisible(true);
    }

}
