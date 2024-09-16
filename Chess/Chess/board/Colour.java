package Chess.board;

public enum Colour {//this is
    White {
        @Override
        public int getDirection() {
            return -1;
        }
    },
    Black {
        @Override
        public int getDirection() {
            return 1;
        }
    };

    public abstract int getDirection();

}
