package ChessB.piece;

import ChessB.board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

///this a Piece inherits characteristics of @Piece it also implements methods from @Tile
/// int the board of 64tiles the knight can move an L shape so that would mean that you move 1x 2y or 2x 1y
/// you could + or - hence the can move

public class Knight extends Piece{
    private final static int[] canMove ={-17,-15,-10,-6,6,10,15,17};

    Knight(int pPos, ChessB.board.Colour pColour) {
        super(pPos, pColour);//TODO what does this super thing do check
    }

    @Override
    public Collection<ChessB.board.Moves> workLegalMoves(ChessB.board.Board board) {

        final List<ChessB.board.Moves>legalMoves=new ArrayList<>();

        for(int move:canMove){ // move == currentCanidate or current canidateoffset & canMove==canidate_move_candidates

            int canGoTo=this.pPos+move;//canidate offset

            if(ChessB.board.BoardUtils.isValidTileco(canGoTo)){

                if(isCol1exc(this.pPos,move)||isCol2exc(this.pPos,move)||isCol7exc(this.pPos,move)||isCol8exc(this.pPos,move)){
                    continue;
                }

                final ChessB.board.Tile canGoToTile = board.getTile(canGoTo);
                if(!canGoToTile.isTileBusy()){
                    legalMoves.add(new ChessB.board.Moves.MajorMove(board,this,canGoTo));
                }else{
                    final Piece who =canGoToTile.getPiece();
                    final ChessB.board.Colour whoColour = who.getpColour();

                    if(this.pColour!=whoColour){//kill him

                        // TODO just a place holder cause im quite tired
                        legalMoves.add(new ChessB.board.Moves.MajorMove(board,this,canGoTo));
                    }
                }
            }
        }
        List<ChessB.board.Moves> lmoves = Collections.unmodifiableList(legalMoves);
        return lmoves;
    }

    private static boolean isCol1exc(int cPos,int move){//edge case
        return ChessB.board.BoardUtils.firstCol[cPos] &&(move==-17)||(move==-10)||(move==6)||(move==15);
    }

    private static boolean isCol2exc(int cPos,int move){//edge case
        return ChessB.board.BoardUtils.secCol[cPos] && (move==-10)||(move==6);
    }

    private static boolean isCol7exc(int cPos,int move){//edge case
        return ChessB.board.BoardUtils.sevCol[cPos] && (move==-6)||(move==10);
    }

    private static boolean isCol8exc(int cPos,int move){//edge cases
        return ChessB.board.BoardUtils.aethCol[cPos] && (move==-15)||(move==-6)||(move==10)||(move==17);
    }


}
