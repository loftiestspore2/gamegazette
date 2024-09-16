//union of the bishop and rook

package ChessB.piece;

import ChessB.board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Queen extends ChessB.piece.Piece {

    private final static int[] vectorCanMove = {-9,-7,7,9};


    Queen(int pPos, ChessB.board.Colour pColour) {
        super(pPos, pColour);
    }

    @Override
    public Collection<ChessB.board.Moves> workLegalMoves(ChessB.board.Board board) {

        final List<ChessB.board.Moves>legalMoves=new ArrayList<>();
        for(int move:vectorCanMove){

            int canGoTo = this.pPos;

            while(ChessB.board.BoardUtils.isValidTileco(canGoTo)){

                if(isCol1exc(canGoTo,move)||isCol8exc(canGoTo,move)){
                    break;
                }

                canGoTo+=move;

                if(ChessB.board.BoardUtils.isValidTileco(canGoTo)){
                    final ChessB.board.Tile canGoToTile = board.getTile(canGoTo);

                    if(!canGoToTile.isTileBusy()){
                        legalMoves.add(new ChessB.board.Moves.MajorMove(board,this,canGoTo));

                    }else{

                        final ChessB.piece.Piece who =canGoToTile.getPiece();
                        final ChessB.board.Colour whoColour = who.getpColour();

                        if(this.pColour!=whoColour){//kill him
                            legalMoves.add(new ChessB.board.Moves.killHim(board,this,canGoTo,who));
                        }
                        break;
                    }
                }
            }
        }
        List<ChessB.board.Moves> lmoves = Collections.unmodifiableList(legalMoves);
        return lmoves;
    }
    private static boolean isCol1exc(int cPos,int move){//edge cases
        return ChessB.board.BoardUtils.firstCol[cPos] &&(move==-9||move==7);
    }
    private static boolean isCol8exc(int cPos,int move){//edge cases
        return ChessB.board.BoardUtils.firstCol[cPos] &&(move==-7||move==9);
    }}
