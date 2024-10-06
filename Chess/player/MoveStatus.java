package player;

public enum MoveStatus {
    /// for moves like check,checkMate(like dead or alive in the adventure game)
    DONE {
        @Override
        public boolean isDone() {
            return true;
        }
    },
    IllegalMove {
        @Override
        public boolean isDone() {
            return false;
        }
    }
    ,LeavesPlayerInCheck {
        @Override
        public boolean isDone() {
            return false;
        }
    };
    public abstract boolean isDone();
    }
