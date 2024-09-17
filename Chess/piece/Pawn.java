package piece;

import board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] canMove ={8};

    Pawn(int pPos, Colour pColour) {
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

            if(move==8&& board.getTile(canGoTo).isTileBusy()){
                //legalMoves.add();
            }
        }
        List<Moves> lmoves = Collections.unmodifiableList(legalMoves);
        return lmoves;
    }
}
