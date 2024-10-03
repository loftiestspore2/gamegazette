package piece;

import board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class King extends Piece{//one square in any direction
    private final static int[] canMove ={-9,-8,-7,-1,1,7,8,9};
    public King(int pPos, Colour pColour) {
        super(pPos,pColour,PieceType.King);
    }

    @Override
    public Collection<Move> workLegalMoves(Board board) {

        final List<Move>legalMoves=new ArrayList<>();

        for(int move:canMove){
            int canGoTo = this.pPos+move;//in

            if(isCol1exc(this.pPos,move)||isCol8exc(this.pPos,move)){
                continue;
            }

            if(BoardUtils.isValidTileco(canGoTo)){
                final Tile canGoToTile = board.getTile(canGoTo);
                if(!canGoToTile.isTileBusy()){
                    legalMoves.add(new Move.MajorMove(board,this,canGoTo));
                }else {
                    final Piece who = canGoToTile.getPiece();
                    final Colour whoColour = who.getpColour();

                    if (this.pColour != whoColour) {//kill him

                        legalMoves.add(new Move.killHim(board, this, canGoTo, who));
                    }
                }
            }
        }


        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public King movePiece(Move move) {
        return new King(move.getDestinationCo(),move.getmPiece().getpColour());
    }

    private static boolean isCol1exc(int cPos,int move){//edge case
        return BoardUtils.firstCol[cPos] &&(move==-9)||(move==-1)||(move==7);
    }

    private static boolean isCol8exc(int cPos,int move){//edge case
        return BoardUtils.aethCol[cPos] && (move==-7)||(move==1)||(move==9);
    }

    @Override
    public String toString(){
        return PieceType.King.toString();
    }

}
