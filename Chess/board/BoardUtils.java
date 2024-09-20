package board;

public class BoardUtils {

    public static final boolean[] firstCol=initcol(0);

    public static boolean[] secCol=initcol(1);

    public static boolean[] sevCol=initcol(6);

    public static boolean[] aethCol=initcol(7);//colomb 8

    public static final int nTiles = 64;

    public static final int rowTiles = 8;

    public static final boolean[] secRow= null;

    public static final boolean[] sevRow= null;

    private BoardUtils(){
        throw new RuntimeException("USELESS MANN");
    }

    private static boolean[] initcol(int colNo) {
        final boolean[]col=new boolean[nTiles];
        do{
            col[colNo]=true;

            colNo += rowTiles;
        }while(colNo< nTiles);

        return col;
    }

    public static boolean isValidTileco(int move) {
        return move>=0&& move< nTiles;
    }


}
