package board;

import piece.Piece;

public abstract class Move {

    final board.Board board;
    final Piece mPiece;//the piece that was moved
    final int DestinationCo;//where the piece moved to

    private Move(final board.Board board, final Piece mPiece, final int pPlaced){
        this.board=board;
        this.mPiece=mPiece;
        this.DestinationCo =pPlaced;
    }

    public int getDestinationCo(){
        return this.DestinationCo;
    }

    public Piece getmPiece() {
        return this.mPiece;
    }

    public Board execute(){

        final Board.Builder builder = new Board.Builder();

        for(Piece p:this.board.currentPlayer().getActivePieces()){
            //TODO hascode in Piece(check it out)
            if (!this.mPiece.equals(p)){
                builder.setPiece(p);
            }

        }

        for(Piece p :this.board.currentPlayer().getOpp().getActivePieces()){
            builder.setPiece(p);
        }
        builder.setPiece(this.mPiece.movePiece(this));
        builder.setTurn(this.board.currentPlayer().getOpp().getColour());
        return builder.build();
    }

    public static final class MajorMove extends Move {

        public MajorMove(final Board board, final Piece mPiece, final int pPlaced) {
            super(board, mPiece, pPlaced);
        }

    }

    public static final class killHim extends Move {

        final Piece killedPiece; //attacked piece

        public killHim(final Board board, final Piece mPiece, final int pPlaced, final Piece killedPiece) {
            super(board, mPiece, pPlaced);
            this.killedPiece=killedPiece;
        }

        @Override
        public Board execute(){//bite
            return null;
        }
    }
}
