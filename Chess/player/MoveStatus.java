package player;

public enum MoveStatus {
    /// for moves like check,checkMate(like dead or alive in the adventure game)
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    },
    IllegalMove {
        @Override
        boolean isDone() {
            return false;
        }
    }
    ,LeavesPlayerInCheck {
        @Override
        boolean isDone() {
            return false;
        }
    };
    abstract boolean isDone();
    }
