package piece;

import board.*;
import java.util.Collection;

public abstract class Piece{
    protected final PieceType pieceType;
    protected final int pPos;
    protected final boolean isFirst;
    protected Colour pColour;//remember to change

    private final int cashedHashCode;

    Piece(final int pPos,final Colour pColour,final PieceType pieceType){
        this.pieceType=pieceType;
        this.pColour = pColour;
        this.pPos = pPos;
        //TODO ~ 20(02 10)
        this.isFirst=false;
        this.cashedHashCode = workcode();
    }

    private int workcode() {
        int x = pieceType.hashCode();
        x=31*x+pColour.hashCode();
        x=31*x+pPos;
        x=31*x+ (isFirst? 1 : 0);
        return x;
    }

    @Override
    public boolean equals(Object other){
        if(this==other){
            return true;
        }
        if(!(other instanceof Piece)){
            return false;
        }
        final Piece otherPiece =(Piece) other;
        return pPos==otherPiece.getpPos() &&pieceType==otherPiece.getPieceType()&&
                pColour==otherPiece.getpColour()&&isFirst== otherPiece.isFirst;
    }

    @Override
    public int hashCode(){
        return this.cashedHashCode;
    }

    public int getpPos(){
        return this.pPos;
    }
    public Colour getpColour() {
        return this.pColour;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public boolean isFirst(){
        return this.isFirst;
    }

    public abstract Collection<Move> workLegalMoves(final Board board);

    public abstract Piece movePiece(Move move);

    public enum PieceType{

        Bishop("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        King("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        },
        Knight("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        Pawn("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        Queen("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        Rook("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        };

        private String pieceName;

        PieceType(String pieceName){
            this.pieceName=pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }

        public abstract boolean isKing();


    }


}
