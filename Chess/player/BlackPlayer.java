package player;

import board.Board;
import board.Colour;
import board.Move;
import piece.Piece;

import java.util.Collection;

public class BlackPlayer extends Player {
    public BlackPlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board,blackLegalMoves,whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackP();
    }

    @Override
    public Colour getColour() {
        return Colour.Black;
    }

    @Override
    public Player getOpp() {
        return this.board.whitePlayer();
    }
}
