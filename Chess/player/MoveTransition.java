package player;

import board.*;

public class MoveTransition {
    //TODO the board being an object has taken its first bite :) update at Board at end

    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;///enum

    public MoveTransition(Board transitionBoard, Move move, MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }

    public Board getTransitionBoard() {
        return this.transitionBoard;
    }
}
