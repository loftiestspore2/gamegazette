package board;

import piece.Piece;


public abstract class Moves {

    final board.Board board;
    final Piece mPiece;//the piece that was moved
    final int pPlaced;//where the piece moved to

    private Moves(final board.Board board, final Piece mPiece, final int pPlaced){
        this.board=board;
        this.mPiece=mPiece;
        this.pPlaced=pPlaced;
    }

    public static final class MajorMove extends Moves{

        public MajorMove(final Board board, final Piece mPiece, final int pPlaced) {
            super(board, mPiece, pPlaced);
        }
    }

    public static final class killHim extends Moves{

        final Piece killedPiece; //attacked piece

        public killHim(final Board board, final Piece mPiece, final int pPlaced, final Piece killedPiece) {
            super(board, mPiece, pPlaced);
            this.killedPiece=killedPiece;
        }
    }
}
