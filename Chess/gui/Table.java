package gui;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import piece.Piece;
import player.MoveTransition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private Board chessBoard;

    private Tile nowTile;
    private Tile destTile;
    private Piece movedPiece;

    private static Dimension mainFrame = new Dimension(600,600);
    private static Dimension boardPanelDim=new Dimension(400,350);
    private static Dimension tilePanDim=new Dimension(10,10);
    private final static String piecePath = "Chess/pictures/pieces/";

    private final Color darkTileColour = Color.decode("#FFFACD");
    private final Color lightTileColour = Color.decode("#593E1A");

    public Table(){
        this.gameFrame=new JFrame("Gamegazette Chess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tMenuBar = createMTable();

        this.gameFrame.setJMenuBar(tMenuBar);
        this.gameFrame.setSize(mainFrame);
        this.chessBoard =Board.createBoard();
        this.boardPanel= new BoardPanel();
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
                System.out.println("for now this does nothing and this will never print anything meaning :)");
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

        public void drawBoard(Board board){
            removeAll();
            for(final TilePanel tP:boardTiles){
                tP.drawTile(board);
                add(tP);
            }
            validate();
            repaint();
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
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if(isRightMouseButton(e)){
                        nowTile=null;
                        destTile=null;
                        movedPiece=null;
                    }else if(isLeftMouseButton(e)){
                        if(nowTile==null){
                            nowTile=chessBoard.getTile(tileId);
                            movedPiece= nowTile.getPiece();
                            if(movedPiece==null){
                                nowTile=null;
                            }
                        }else{
                            destTile=chessBoard.getTile(tileId);
                            Move move =Move.MoveFactory.createMove(chessBoard,nowTile.getTileco(),destTile.getTileco());

                            final MoveTransition transition=chessBoard.currentPlayer().makeMove(move);
                            if(transition.getMoveStatus().isDone()){
                                chessBoard = transition.getTransitionBoard();
                                //TODO add move to movelog

                            }
                        }
                        nowTile=null;
                        destTile=null;
                        movedPiece=null;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            boardPanel.drawBoard(chessBoard);
                        }
                    });

                }

                @Override
                public void mousePressed(final MouseEvent e) {

                }

                @Override
                public void mouseReleased(final MouseEvent e) {

                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(final MouseEvent e) {

                }
            });
            validate();
        }
        public void drawTile(Board board){
            setTilecolour();
            setPieceIconOnTile(board);
            validate();
            repaint();
        }
        private void setPieceIconOnTile(final Board board){
            this.removeAll();

            if(board.getTile(this.tileId).isTileBusy()){

                try {
                    /*so this would look like this
                    * WhiteBishop --> WB
                    * --> WB.gif this is due to the using BufferedImage
                    **/
                    System.out.println("Loading image from: " +
                            piecePath + board.getTile(this.tileId).getPiece().getpColour().toString().substring(0,1) +
                            board.getTile(this.tileId).getPiece().toString() + ".gif/");//TODO rember to download better looking Pieces
//TODO move this into a method at the bottom
                    final BufferedImage image = ImageIO.read(new File(piecePath +board
                            .getTile(this.tileId).getPiece().getpColour().toString().substring(0,1)+
                            board.getTile(this.tileId).getPiece().toString()+".gif/"));

                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void setTilecolour(){
            if(BoardUtils.aethRow[this.tileId]||BoardUtils.sithRow[this.tileId]||
                    BoardUtils.frthRow[this.tileId]||BoardUtils.secRow[this.tileId]){
                //////////////////////////////////////////////////////////
                setBackground(this.tileId%2==0? lightTileColour :darkTileColour);// No forloop needed used iterator check at callsite
            }else if(BoardUtils.sevRow[this.tileId]||BoardUtils.fithRow[this.tileId]||
                    BoardUtils.thrdRow[this.tileId]||BoardUtils.frstRow[this.tileId]){
                //////////////////////////////////////////////////////////
                setBackground(this.tileId%2!=0? lightTileColour :darkTileColour);
            }
        }
    }

}
