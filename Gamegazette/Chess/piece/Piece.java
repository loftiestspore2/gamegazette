package Chess.piece;

import Chess.board.Board;
import Chess.board.Colour;
import Chess.board.Move;

import java.util.Collection;

public abstract class Piece{
    protected final int pPos;
    protected final boolean isFirst;


    protected Colour pColour;//remember to change

    Piece(final int pPos,final Colour pColour){
        this.pColour = pColour;
        this.pPos = pPos;
        //TODO
        this.isFirst=false;
    }

    public int getpPos(){
        return this.pPos;
    }
    public Colour getpColour() {
        return this.pColour;
    }

    public boolean isFirst(){
        return this.isFirst;
    }

    public abstract Collection<? extends Move> workLegalMoves(final Board board);

    public enum PieceType{

        Bishop("B"),
        King("K"),
        Knight("N"),
        Pawn("P"),
        Queen("Q"),
        Rook("R");

        private String pieceName;

        PieceType(String pieceName){
            this.pieceName=pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }

    }

}
