package piece;

import board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] canMove ={8,16,7,9};

    public Pawn(final int pPos, Colour pColour) {
        super(pPos, pColour);
    }

    @Override
    public Collection<Moves> workLegalMoves(Board board) {

        final List<Moves>legalMoves=new ArrayList<>();
        for(int move:canMove){

            int canGoTo = this.pPos+(this.getpColour().getDirection()*move);

            if(!BoardUtils.isValidTileco(canGoTo)){
                continue;
            }

            final Tile canGoToTile = board.getTile(canGoTo);
            //TODO later on clean up code

            if(move==8 && !canGoToTile.isTileBusy() ){
                //TODO later on (end reached)
                legalMoves.add(new Moves.MajorMove(board,this,canGoTo)); //handels non attacking move
            }else if(move==16 && this.isFirst() &&
                    ((BoardUtils.secRow[this.pPos]&&this.pColour.isBlack())||
                    (BoardUtils.sevRow[this.pPos]&&this.pColour.isWhite()))){

                final int beforeCanGoTo = this.pPos +(this.getpColour().getDirection()*8);

                if(!board.getTile(beforeCanGoTo).isTileBusy()&& !board.getTile(canGoTo).isTileBusy()){
                    legalMoves.add(new Moves.MajorMove(board,this,canGoTo));
                }//handels the jump move
            }else if(move==7
                    && !(BoardUtils.aethCol[this.pPos]&&this.pColour.isWhite()||
                    BoardUtils.firstCol[this.pPos]&&this.pColour.isBlack())){
                if(board.getTile(canGoTo).isTileBusy()){
                    final Piece who = canGoToTile.getPiece();
                    if(this.pColour!=who.getpColour()){
                        //TODO just a stub end reaching
                        legalMoves.add(new Moves.MajorMove(board,this,canGoTo));
                    }
                }

            }else if(move==9
                    && !(BoardUtils.aethCol[this.pPos]&&this.pColour.isBlack()||
                    BoardUtils.firstCol[this.pPos]&&this.pColour.isWhite())){//black at 8 and white at 1

                if(board.getTile(canGoTo).isTileBusy()) {
                    final Piece who = canGoToTile.getPiece();
                    if (this.pColour != who.getpColour()) {
                        //TODO just a stub end reaching (ctrl c) @52
                        legalMoves.add(new Moves.MajorMove(board, this, canGoTo));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}
