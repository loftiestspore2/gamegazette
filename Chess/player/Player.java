package player;

import board.*;
import piece.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Player {

    protected final Board board;
    protected final King plKing;
    protected final Collection<Move> legalMoves;
    
    private final boolean isInCheck;
    
    Player(final Board board, final Collection <Move>legalMoves, final Collection<Move>opsMoves){
        this.board=board;
        this.plKing=establishKing();//ensures that there
        this.legalMoves=legalMoves;
        this.isInCheck = Player.workAttacksOnTile(this.plKing.getpPos(),opsMoves).isEmpty();
    }

    public King getPlKing() {
        return this.plKing;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    private static Collection<Move> workAttacksOnTile(int i, Collection<Move> moves) {
        ///helper method
        final List<Move> attackMoves=new ArrayList<>();
        for(Move m :moves){
            if(i==m.getDestinationCo()){
                attackMoves.add(m);
            }
        }
        return Collections.unmodifiableList(List.copyOf(attackMoves));//TODO remember to change the rest
    }

    private King establishKing() {//legal state
        for(Piece p:getActivePieces()){
            if(p.getPieceType().isKing()){
                return (King)p;//polymorphism
            }//IF
        }
        throw new RuntimeException("should not reach here!! not a valid board");//for

    }//establishKing

    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }


    public boolean isInCheckMate(){
        return this.isInCheck && hasEscapeMoves();
    }

    //TODO these are just stubs
    public boolean isInStalemate(){
        return !this.isInCheck && hasEscapeMoves();
    }

    public boolean isCastle(){
        return false;
    }

    public MoveTransition makeMove(final Move move){
        ///this is in a way the " cmd " switch of moving
        if(!isMoveLegal(move)){
            return new MoveTransition(this.board,move,MoveStatus.IllegalMove);
        }

        final Board transitionBoard=move.execute();
        final Collection<Move>kingAttacks=Player.workAttacksOnTile(transitionBoard.currentPlayer().getOpp().getPlKing().getpPos(),transitionBoard.currentPlayer().getLegalMoves());
        if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board,move,MoveStatus.LeavesPlayerInCheck);
        }
        return new MoveTransition(transitionBoard,move,MoveStatus.DONE);
    }

    protected boolean hasEscapeMoves(){
        for(final Move m:this.legalMoves){
            final MoveTransition transition=makeMove(m);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public abstract Collection<Piece>getActivePieces();
    public abstract Colour getColour();
    public abstract Player getOpp();
}
