package Chess.piece;

import Chess.board.*;
import Chess.board.Moves.MajorMove;
import Chess.board.Moves.killHim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece{

    private final static int[] vectorCanMove = {-9,-8,-7,-1,1,7,8,9};//bishop U rook

    Bishop(int pPos, Chess.board.Colour pColour) {
        super(pPos, pColour);
    }

    @Override
    public Collection<Chess.board.Moves> workLegalMoves(Chess.board.Board board) {

        final List<Chess.board.Moves>legalMoves=new ArrayList<>();
        for(int move:vectorCanMove){

            int canGoTo = this.pPos;

            while(Chess.board.BoardUtils.isValidTileco(canGoTo)){

                if(isCol1exc(canGoTo,move)||isCol8exc(canGoTo,move)){
                    break;
                }

                canGoTo+=move;

                if(Chess.board.BoardUtils.isValidTileco(canGoTo)){
                    final Chess.board.Tile canGoToTile = board.getTile(canGoTo);

                    if(!canGoToTile.isTileBusy()){
                        legalMoves.add(new MajorMove(board,this,canGoTo));

                    }else{

                        final Piece who =canGoToTile.getPiece();
                        final Chess.board.Colour whoColour = who.getpColour();

                        if(this.pColour!=whoColour){//kill him
                            legalMoves.add(new killHim(board,this,canGoTo,who));
                        }
                        break;
                    }
                }
            }
        }
        List<Chess.board.Moves> lmoves = Collections.unmodifiableList(legalMoves);
        return lmoves;
    }
    private static boolean isCol1exc(int cPos,int move){//edge cases
        return Chess.board.BoardUtils.firstCol[cPos] &&(move==1||move==-9||move==7);
    }
    private static boolean isCol8exc(int cPos,int move){//edge cases
        return Chess.board.BoardUtils.firstCol[cPos] &&(move==-7||move==1||move==9);
    }
}
