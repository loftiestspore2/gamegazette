package board;

import piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileco; //this will say if it's there

    private static final Map <Integer,EmptyTile> emptyTiles = createAllPossibileEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibileEmptyTiles() {
        final Map<Integer,EmptyTile> emptyTileMap =new HashMap<>();
        for(int i = 0; i< BoardUtils.nTiles; i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }
        Map<Integer, EmptyTile> emptyTileMap1 = Collections.unmodifiableMap(emptyTileMap);
        return emptyTileMap1;
    }
    public static Tile createTile(final int tileco,final Piece piece){
        return (piece!=null)? new BusyTile(tileco,piece):emptyTiles.get(tileco);
    }

    private Tile(int tileco){
        this.tileco = tileco;
    }
    public abstract boolean isTileBusy ();/*abstract means that meathod is not difined here but will be in a subClass
     this method will allow to check if there is a piece here */

    public abstract Piece getPiece();//it will get the piece

    private static final class EmptyTile extends Tile {//subclass defines"attributes of tile"

        EmptyTile(final int cord) {
            super(cord);
        }

        @Override
        public String toString(){
            return "-";
        }

        @Override
        public boolean isTileBusy() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }
    private static final class BusyTile extends Tile{//subclass defines"attributes of tile
        private final Piece pieceOnTile;

        BusyTile(int tileco,Piece pieceOnTile ){
            super(tileco);
            this.pieceOnTile=pieceOnTile;
        }

        @Override
        public String toString(){
            return (getPiece().getpColour().isBlack())?
                    getPiece().toString().toLowerCase() : getPiece().toString();
        }

        @Override
        public boolean isTileBusy(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }

    }
}
