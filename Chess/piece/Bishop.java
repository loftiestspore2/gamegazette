package Chess.piece;

import Chess.board.*;
import board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece{

    private final static int[] vectorCanMove = {-9,-8,-7,-1,1,7,8,9};//bishop U rook

    public Bishop(int pPos, Colour pColour) {
        super(pPos, pColour);
    }

    @Override
    public Collection<? extends Move> workLegalMoves(Board board) {

        final List<Moves>legalMoves=new ArrayList<>();
        for(int move:vectorCanMove){

            int canGoTo = this.pPos;

            while(BoardUtils.isValidTileco(canGoTo)){

                if(isCol1exc(canGoTo,move)||isCol8exc(canGoTo,move)){
                    break;
                }

                canGoTo+=move;

                if(BoardUtils.isValidTileco(canGoTo)){
                    final Tile canGoToTile = board.getTile(canGoTo);

                    if(!canGoToTile.isTileBusy()){
                        legalMoves.add(new Moves.MajorMove(board,this,canGoTo));

                    }else{

                        final Piece who =canGoToTile.getPiece();
                        final Colour whoColour = who.getpColour();

                        if(this.pColour!=whoColour){//kill him
                            legalMoves.add(new Moves.killHim(board,this,canGoTo,who));
                        }
                        break;
                    }
                }
            }
        }
        List<Moves> lmoves = Collections.unmodifiableList(legalMoves);
        return lmoves;
    }

    @Override
    public String toString(){
        return PieceType.Bishop.toString();
    }

    private static boolean isCol1exc(int cPos,int move){//edge cases
        return BoardUtils.firstCol[cPos] &&(move==1||move==-9||move==7);
    }
    private static boolean isCol8exc(int cPos,int move){//edge cases
        return BoardUtils.firstCol[cPos] &&(move==-7||move==1||move==9);
    }

}
