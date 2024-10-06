package player;

import board.Board;
import board.Colour;
import board.Move;
import board.Tile;
import piece.Piece;
import piece.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackPlayer extends Player {
    public BlackPlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board,blackLegalMoves,whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackP();
    }

    @Override
    public Colour getColour() {
        return Colour.Black;
    }

    @Override
    public Player getOpp() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> workKingCastling(Collection<Move> playerLegal, Collection<Move> oppLegal) {
        final List<Move>kC = new ArrayList<>();
        //black casteling
        if(this.plKing.isFirst()&&!this.isInCheck()){
            if(!this.board.getTile(5).isTileBusy()&&!this.board.getTile(6).isTileBusy()){
                final Tile rTile = this.board.getTile(7);
                if(rTile.isTileBusy()&&rTile.getPiece().isFirst()){
                    if(Player.workAttacksOnTile(5,oppLegal).isEmpty()&&
                            Player.workAttacksOnTile(6,oppLegal).isEmpty()&&
                            rTile.getPiece().getPieceType().isRook()){
                        kC.add(new Move.KingCastleMove(this.board,plKing,6,
                                (Rook)rTile.getPiece(), rTile.getTileco(),5));
                    }

                }
            }
            if(!this.board.getTile(1).isTileBusy()&&!this.board.getTile(2).isTileBusy()&&
                    !this.board.getTile(3).isTileBusy()){

                final Tile rTile = this.board.getTile(0);
                if(rTile.isTileBusy()&&rTile.getPiece().isFirst()){

                    kC.add(new Move.QueenCastleMove(this.board,plKing,2,
                            (Rook)rTile.getPiece(), rTile.getTileco(),3));
                }
            }
        }
        return Collections.unmodifiableList(List.copyOf(kC));
    }
}
