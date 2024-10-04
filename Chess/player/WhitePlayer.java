package player;

import board.Board;
import board.Colour;
import board.Move;
import piece.Piece;

import java.util.Collection;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board,whiteLegalMoves,blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhiteP();
    }

    @Override
    public Colour getColour(){
        return Colour.White;
    }

    @Override
    public Player getOpp(){
        return this.board.blackPlayer();
    }
}
