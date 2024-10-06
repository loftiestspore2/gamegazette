package gui;

import board.Board;
import board.BoardUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private final JFrame gameFrame;
    private final Board chessBoard;
    private final BoardPanel boardPanel;

    private static Dimension mainFrame = new Dimension(600,600);
    private static Dimension boardPanelDim=new Dimension(400,350);
    private static Dimension tilePanDim=new Dimension(10,10);
    private final static String piecePath = "gifs/pieces/";

    private final Color darkTileColour = Color.decode("#FFFACD");
    private final Color lightTileColour = Color.decode("#593E1A");

    public Table(){
        this.gameFrame=new JFrame("Gamegazette Chess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tMenuBar = createMTable();

        this.gameFrame.setJMenuBar(tMenuBar);
        this.gameFrame.setSize(mainFrame);

        this.boardPanel= new BoardPanel();
        this.chessBoard =Board.createBoard();
        this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);

        this.gameFrame.setVisible(true);

    }

    private JMenuBar createMTable(){
        final JMenuBar tMenuBar = new JMenuBar();
        tMenuBar.add(createFileMenu());
        return tMenuBar;
    }

    private JMenu createFileMenu(){ // to load Moves
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

    private class BoardPanel extends JPanel{
        final List<TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTiles=new ArrayList<>();
            for(int i=0;i< BoardUtils.nTiles;i++){
                TilePanel tilePanel=new TilePanel(this,i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(boardPanelDim);
            validate();
        }
    }

    private class TilePanel extends JPanel{
        private final int tileId;

        TilePanel(BoardPanel bPanel ,int tileId){
            super(new GridBagLayout());
            this.tileId=tileId;
            setPreferredSize(tilePanDim);
            setTilecolour();//assign tileColour
            setPieceIconOnTile(chessBoard);
            validate();
        }

        private void setPieceIconOnTile(final Board board){
            this.removeAll();

            if(board.getTile(this.tileId).isTileBusy()){

                try {
                    /*so this would look like this
                    * WhiteBishop --> WB
                    * --> WB.gif this is due to the using BufferedImage
                    **/
                    final BufferedImage image = ImageIO.read(new File(piecePath +board
                            .getTile(this.tileId).getPiece().getpColour().toString().substring(0,1)+
                            board.getTile(this.tileId).getPiece().toString()+"gif"));

                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void setTilecolour(){
            if(BoardUtils.frstRow[this.tileId]||BoardUtils.sithRow[this.tileId]||
                    BoardUtils.frthRow[this.tileId]||BoardUtils.secRow[this.tileId]){
                //////////////////////////////////////////////////////////
                setBackground(this.tileId%2==0? lightTileColour :darkTileColour);
            }else if(BoardUtils.secRow[this.tileId]||BoardUtils.fithRow[this.tileId]||
                    BoardUtils.thrdRow[this.tileId]||BoardUtils.frstRow[this.tileId]){
                //////////////////////////////////////////////////////////
                setBackground(this.tileId%2!=0? lightTileColour :darkTileColour);
            }
        }
    }

}
