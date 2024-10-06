package piece;

import board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {

    private final static int[] vectorCanMove = {-8,-1,1,8};

    public Rook(int pPos, Colour pColour) {
        super(pPos,pColour,PieceType.Rook);
    }

    @Override
    public Collection<Move> workLegalMoves(Board board) {

        final List<Move>legalMoves=new ArrayList<>();
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
                        legalMoves.add(new Move.MajorMove(board,this,canGoTo));

                    }else{

                        final Piece who =canGoToTile.getPiece();
                        final Colour whoColour = who.getpColour();

                        if(this.pColour!=whoColour){//kill him
                            legalMoves.add(new Move.KillHim(board,this,canGoTo,who));
                        }
                        break;
                    }
                }
            }
        }
        List<Move> lmoves = Collections.unmodifiableList(legalMoves);
        return lmoves;
    }

    @Override
    public Rook movePiece(Move move) {
        return new Rook(move.getDestinationCo(),move.getmPiece().getpColour());
    }

    private static boolean isCol1exc(int cPos,int move){//edge cases
        return BoardUtils.firstCol[cPos] &&(move==-1);
    }
    private static boolean isCol8exc(int cPos,int move){//edge cases
        return BoardUtils.firstCol[cPos] &&(move==1);
    }

    @Override
    public String toString(){
        return PieceType.Rook.toString();
    }
}
