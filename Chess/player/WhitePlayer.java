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

public class WhitePlayer extends Player {
    public WhitePlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board,whiteLegalMoves,blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhiteP();
    }

    @Override
    public Colour getColour(){
        return Colour.White;
    }

    @Override
    public Player getOpp(){
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> workKingCastling(Collection<Move> playerLegal, Collection<Move> oppLegal) {
        final List<Move>kC = new ArrayList<>();
        //white casteling
        if(this.plKing.isFirst()&&!this.isInCheck()){
            if(!this.board.getTile(61).isTileBusy()&&!this.board.getTile(62).isTileBusy()){
                final Tile rTile = this.board.getTile(63);
                if(rTile.isTileBusy()&&rTile.getPiece().isFirst()){
                    if(Player.workAttacksOnTile(61,oppLegal).isEmpty()&&
                            Player.workAttacksOnTile(62,oppLegal).isEmpty()&&
                    rTile.getPiece().getPieceType().isRook()){

                        kC.add(new Move.KingCastleMove(this.board,plKing,62,(Rook)rTile.getPiece(),
                                rTile.getTileco(),61));
                    }

                }
            }
            if(!this.board.getTile(59).isTileBusy()&&!this.board.getTile(58).isTileBusy()&&
                    !this.board.getTile(57).isTileBusy()){

                final Tile rTile = this.board.getTile(56);
                if(rTile.isTileBusy()&&rTile.getPiece().isFirst()){

                    kC.add(new Move.QueenCastleMove(this.board,plKing,58,(Rook)rTile.getPiece(),
                            rTile.getTileco(),59));
                }
            }
        }
        return Collections.unmodifiableList(List.copyOf(kC));
    }
}
