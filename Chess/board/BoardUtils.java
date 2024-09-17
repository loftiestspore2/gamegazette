package board;

public class BoardUtils {

    public static final boolean[] firstCol=initcol(0);

    public static boolean[] secCol=initcol(1);

    public static boolean[] sevCol=initcol(6);

    public static boolean[] aethCol=initcol(7);//colomb 8

    public static final int tiles = 64;

    public static final int rowTiles = 8;

    private BoardUtils(){
        throw new RuntimeException("USELESS MANN");
    }

    private static boolean[] initcol(int colNo) {
        final boolean[]col=new boolean[tiles];
        do{
            col[colNo]=true;

            colNo += rowTiles;
        }while(colNo<tiles);

        return col;
    }

    public static boolean isValidTileco(int move) {
        return move>=0&& move<tiles;
    }


}
