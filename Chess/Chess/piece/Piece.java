package Chess.piece;

import Chess.board.Board;
import Chess.board.Colour;
import Chess.board.Moves;

import java.util.Collection;

public abstract class Piece{
    protected final int pPos;

    protected Colour pColour;//remember to change

    Piece(final int pPos,final Colour pColour){
        this.pColour = pColour;
        this.pPos = pPos;
    }

    public Colour getpColour() {
        return this.pColour;
    }

    public abstract Collection<Moves> workLegalMoves(final Board board);


}
