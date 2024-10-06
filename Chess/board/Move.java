package board;

import piece.*;

public abstract class Move {
    /// pgn format
    final board.Board board;
    final Piece mPiece;//the piece that was moved
    final int DestinationCo;//where the piece moved to

    public static final Move nullMove = new NullMove();

    private Move(final board.Board board, final Piece mPiece, final int pPlaced){
        this.board=board;
        this.mPiece=mPiece;
        this.DestinationCo =pPlaced;
    }

    public int getCurrentCo(){
        return this.getmPiece().getpPos();
    }

    public int getDestinationCo(){
        return this.DestinationCo;
    }

    @Override
    public int hashCode() {
        final int x=31;
        int x1=1;
        x1 = x*x1+this.DestinationCo;
        x1=x*x1+this.mPiece.hashCode();
        return x1;
    }

    @Override
    public boolean equals(final Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof Move)){
            return false;
        }
        final Move o1=(Move) o;
        return getDestinationCo()== o1.getDestinationCo()&&
                getmPiece().equals(o1.getmPiece());
    }

    public Piece getmPiece() {//moved piece
        return this.mPiece;
    }

    public boolean isAttack(){
        return false;
    }

    public boolean isCastling(){
        return false;
    }

    public Piece getKilledPiece(){
        return null;
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

    public static class KillHim extends Move {

        final Piece killedPiece; //attacked piece

        public KillHim(final Board board, final Piece mPiece, final int pPlaced, final Piece killedPiece) {
            super(board, mPiece, pPlaced);
            this.killedPiece=killedPiece;
        }

        @Override
        public int hashCode(){
            return this.killedPiece.hashCode() +super.hashCode();
        }
        @Override
        public boolean equals(final Object o){
            if(this==o){
                return true;
            }
            if(!(o instanceof KillHim)){
                return false;
            }
            final KillHim o1=(KillHim) o;
            return super.equals(o1)&&getKilledPiece().equals(o1.getKilledPiece());

        }

        @Override
        public Board execute(){//bite
            return null;
        }
        @Override
        public boolean isAttack(){
            return true;
        }
        @Override
        public Piece getKilledPiece(){
            return this.killedPiece;
        }
    }

    public static class PawnMove extends Move {
        public PawnMove(final Board board, final Piece mPiece, final int pPlaced) {
            super(board, mPiece, pPlaced);
        }
    }

    public static class PawnEAttackMove extends KillHim {
        public PawnEAttackMove(final Board board, final Piece mPiece, final int pPlaced,
                                  final Piece killedPiece) {
            super(board, mPiece, pPlaced,killedPiece);
        }
    }

    public static final class PawnAttackMove extends KillHim {
        public PawnAttackMove(final Board board, final Piece mPiece, final int pPlaced,
                              final Piece killedPiece) {
            super(board, mPiece, pPlaced,killedPiece);
        }
    }

    public static class PawnJump extends Move {
        public PawnJump(final Board board, final Piece mPiece, final int pPlaced) {
            super(board, mPiece, pPlaced);
        }
        @Override
        public Board execute(){
            final Board.Builder builder=new Board.Builder();
            for(final Piece p:this.board.currentPlayer().getActivePieces()){
                if(!(this.mPiece.equals(p))){
                    builder.setPiece(p);
                }
            }
            for(final Piece p:this.board.currentPlayer().getOpp().getActivePieces()){
                    builder.setPiece(p);
            }
            final Pawn mPawn =(Pawn)this.mPiece.movePiece(this);
            builder.setPiece(mPawn);
            builder.setEPawn(mPawn);
            builder.setTurn(this.board.currentPlayer().getOpp().getColour());
            return builder.build();
        }
    }

    public static abstract class CastleMove extends Move {
        protected final Rook cRook;
        protected final int cRookStart;
        protected final int cRookEnd;
        public CastleMove(final Board board, final Piece mPiece, final int pPlaced,final Rook cRook,
                          final int cRookStart, final int cRookEnd) {
            super(board, mPiece, pPlaced);

            this.cRook = cRook;
            this.cRookStart = cRookStart;
            this.cRookEnd = cRookEnd;

        }

        public Rook getcRook() {
            return this.cRook;
        }
        public int getcRookEnd() {
            return this.cRookEnd;
        }
        public int getcRookStart() {
            return this.cRookStart;
        }

        @Override
        public boolean isCastling(){
            return true;
        }
        @Override
        public Board execute(){
            final Board.Builder builder=new Board.Builder();
            for(final Piece p:this.board.currentPlayer().getActivePieces()){
                if(!this.mPiece.equals(p)&&!this.cRook.equals(p)){
                    builder.setPiece(p);
                }
            }
            for(final Piece p:this.board.currentPlayer().getOpp().getActivePieces()){
                builder.setPiece(p);
            }
            builder.setPiece(this.mPiece.movePiece(this));
            builder.setPiece(new Rook(this.cRookEnd,this.cRook.getpColour()));
            builder.setTurn(this.board.currentPlayer().getOpp().getColour());
            return builder.build();
        }
    }

    public static class KingCastleMove extends CastleMove {
        public KingCastleMove(final Board board, final Piece mPiece, final int pPlaced,
                              final Rook cRook, final int cRookStart, final int cRookEnd) {
            super(board, mPiece, pPlaced,
                    cRook, cRookStart, cRookEnd);
        }
        @Override
        public String toString(){
            return "O-O";
        }
    }

    public static class QueenCastleMove extends CastleMove {
        public QueenCastleMove(final Board board, final Piece mPiece, final int pPlaced,
                               final Rook cRook, final int cRookStart, final int cRookEnd) {
            super(board, mPiece, pPlaced,cRook,
            cRookStart,cRookEnd);
        }
        @Override
        public String toString(){
            return "O-O-O";
        }
    }

    public static class NullMove extends Move {
        public NullMove() {
            super(null,null,-1);
        }
        @Override
        public Board execute(){
            throw new RuntimeException("Move could not be created(null move)");
        }
    }

    public static class MoveFactory{
        private MoveFactory(){
            throw new RuntimeException("Not comeOn man Static class");
        }

        public static Move createMove(Board board,int currentCo,int destinationCo){
           for(Move m:board.getAllLegalMoves()){
               if(m.getCurrentCo()==currentCo&&m.getDestinationCo()==destinationCo){
                   return m;
               }
           }
           return nullMove;
        }
    }

}
