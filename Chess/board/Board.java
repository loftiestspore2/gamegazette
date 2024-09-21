package board;

import piece.Piece;
import piece.*;

import java.util.*;

public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece>whitePieces;
    private final Collection<Piece>blackPieces;

    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces=workActivePieces(this.gameBoard,Colour.White);
        this.blackPieces=workActivePieces(this.gameBoard,Colour.Black);

        final Collection<Moves> whiteLegalMoves= workLegalmoves(this.whitePieces);
        final Collection<Moves> blackLegalMoves= workLegalmoves(this.blackPieces);

    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();

        for(int i =0;i<BoardUtils.nTiles;i++){
           String tileText = this.gameBoard.get(i).toString();
           builder.append(String.format("%3s",tileText));
           if((i+1)%BoardUtils.rowTiles==0){
               builder.append("\n");
           }
        }
        return builder.toString();
    }

    private Collection<Moves> workLegalmoves(Collection<Piece> pieces){

        final List<Moves>legalMoves=new ArrayList<>();

        for(Piece piece:pieces){
            legalMoves.add((Moves) piece.workLegalMoves(this));//TODO check out later
        }

        return Collections.unmodifiableList(legalMoves);
    }

    private static Collection<Piece> workActivePieces(List<Tile> gameBoard, Colour colour) {

        final List<Piece>activePieces= new ArrayList<>();

        for(Tile tile:gameBoard){
            Piece piece = tile.getPiece();
            if(piece.getpColour()==colour){
                activePieces.add(piece);
            }else{continue;}
        }

        return Collections.unmodifiableList(activePieces);
    }

    public Tile getTile( final int tileco) {
        return gameBoard.get(tileco);
    }

    private static List createGameBoard(Builder builder){
        final Tile[] tiles =new Tile[BoardUtils.nTiles];
        for(int i=0;i<BoardUtils.nTiles;i++){
            tiles[i]=Tile.createTile(i,builder.bConFig.get(i));//bconfig is a Map of the Tiles on the board
        }
        return Collections.unmodifiableList(List.of(tiles));
    }

    //createStandardBoard()
    public static Board createBoard(){/// (starting position)
        final Builder builder = new Builder();
        //top 2 rows layout //TODO queen face queen
        builder.setPiece(new Rook(0,Colour.Black));
        builder.setPiece(new Knight(1,Colour.Black));
        builder.setPiece(new Bishop(2,Colour.Black));
        builder.setPiece(new Queen(3,Colour.Black));
        builder.setPiece(new King(4,Colour.Black));
        builder.setPiece(new Bishop(5,Colour.Black));
        builder.setPiece(new Knight(6,Colour.Black));
        builder.setPiece(new Rook(7,Colour.Black));
        builder.setPiece(new Pawn(8,Colour.Black));
        builder.setPiece(new Pawn(9,Colour.Black));
        builder.setPiece(new Pawn(10,Colour.Black));
        builder.setPiece(new Pawn(11,Colour.Black));
        builder.setPiece(new Pawn(12,Colour.Black));
        builder.setPiece(new Pawn(13,Colour.Black));
        builder.setPiece(new Pawn(14,Colour.Black));
        builder.setPiece(new Pawn(15,Colour.Black));
        //bottom 2 layout White
        builder.setPiece(new Rook(56,Colour.Black));
        builder.setPiece(new Knight(57,Colour.Black));
        builder.setPiece(new Bishop(58,Colour.Black));
        builder.setPiece(new Queen(59,Colour.Black));
        builder.setPiece(new King(60,Colour.Black));
        builder.setPiece(new Bishop(61,Colour.Black));
        builder.setPiece(new Knight(62,Colour.Black));
        builder.setPiece(new Rook(63,Colour.Black));
        builder.setPiece(new Pawn(48,Colour.Black));
        builder.setPiece(new Pawn(49,Colour.Black));
        builder.setPiece(new Pawn(50,Colour.Black));
        builder.setPiece(new Pawn(51,Colour.Black));
        builder.setPiece(new Pawn(52,Colour.Black));
        builder.setPiece(new Pawn(53,Colour.Black));
        builder.setPiece(new Pawn(54,Colour.Black));
        builder.setPiece(new Pawn(55,Colour.Black));
        //first move
        builder.setTurn(Colour.White);

        return builder.build();
    }

    //TODO benefits of a builder
    public static class Builder{

        Map<Integer, Piece> bConFig;

        Colour nextTurn;

        public Builder(){
            this.bConFig=new HashMap<>();
        }

        public Builder setPiece(Piece piece){
            this.bConFig.put(piece.getpPos(),piece);
            return this;
        }

        public Builder setTurn(Colour colour){//turn is the person currently playing
            this.nextTurn=nextTurn;
            return this;
        }

        Board build(){
            return new Board(this);
        }

    }
}
