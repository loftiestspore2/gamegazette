import board.Board;
import gui.Table;

/**
 * created by Loftiestspore2 on 10/08/2024
 */

public class gChess {
    ///g -> gamegazette (group name)

    public static void main(String[] args){

        Board board = Board.createBoard();
        System.out.println(board);

        Table table = new Table();
    }
}
