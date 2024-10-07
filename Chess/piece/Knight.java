package Chess.piece;

import Chess.board.*;
import board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

///this a Piece inherits characteristics of @Piece it also implements methods from @Tile
/// int the board of 64tiles the knight can move an L shape so that would mean that you move 1x 2y or 2x 1y
/// you could + or - hence the can move

public class Knight extends Piece{
    private final static int[] canMove ={-17,-15,-10,-6,6,10,15,17};

    public Knight(int pPos, Colour pColour) {
        super(pPos, pColour);//TODO what does this super thing do check
    }

    @Override
    public Collection<? extends Move> workLegalMoves(Board board) {

        final List<Moves>legalMoves=new ArrayList<>();

        for(int move:canMove){ // move == currentCanidate or current canidateoffset & canMove==canidate_move_candidates

            int canGoTo=this.pPos+move;//canidate offset

            if(BoardUtils.isValidTileco(canGoTo)){

                if(isCol1exc(this.pPos,move)||isCol2exc(this.pPos,move)||isCol7exc(this.pPos,move)||isCol8exc(this.pPos,move)){
                    continue;
                }

                final Tile canGoToTile = board.getTile(canGoTo);
                if(!canGoToTile.isTileBusy()){
                    legalMoves.add(new Moves.MajorMove(board,this,canGoTo));
                }else{
                    final Piece who =canGoToTile.getPiece();
                    final Colour whoColour = who.getpColour();

                    if(this.pColour!=whoColour){//kill him

                        // TODO just a place holder cause im quite tired Done
                        legalMoves.add(new Moves.killHim(board,this,canGoTo,who));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isCol1exc(int cPos,int move){//edge case
        return BoardUtils.firstCol[cPos] &&(move==-17)||(move==-10)||(move==6)||(move==15);
    }

    private static boolean isCol2exc(int cPos,int move){//edge case
        return BoardUtils.secCol[cPos] && (move==-10)||(move==6);
    }

    private static boolean isCol7exc(int cPos,int move){//edge case
        return BoardUtils.sevCol[cPos] && (move==-6)||(move==10);
    }

    private static boolean isCol8exc(int cPos,int move){//edge cases
        return BoardUtils.aethCol[cPos] && (move==-15)||(move==-6)||(move==10)||(move==17);
    }

    @Override
    public String toString(){
        return PieceType.Knight.toString();
    }
}
