
import java.util.List;


public class ChessAI {

    
    private static final int MAX_DEPTH = 3;  
    
    
    public Move getBestMove(Board board, boolean isMaximizingPlayer) {
        List<Move> legalMoves = board.getLegalMoves(isMaximizingPlayer);
        Move bestMove = null;
        int bestValue = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        
        for (Move move : legalMoves) {
            Board boardCopy = board.copy(); 
            boardCopy.makeMove(move); 

            int boardValue = minimax(boardCopy, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, !isMaximizingPlayer);

            
            if (isMaximizingPlayer) {
                if (boardValue > bestValue) {
                    bestValue = boardValue;
                    bestMove = move;
                }
            } 
            
            else {
                if (boardValue < bestValue) {
                    bestValue = boardValue;
                    bestMove = move;
                }
            }
        }
        return bestMove; 
    }

    
    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board); 
        }

        List<Move> legalMoves = board.getLegalMoves(isMaximizingPlayer);

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : legalMoves) {
                Board boardCopy = board.copy();
                boardCopy.makeMove(move);
                int eval = minimax(boardCopy, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : legalMoves) {
                Board boardCopy = board.copy();
                boardCopy.makeMove(move);
                int eval = minimax(boardCopy, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    
    private int evaluateBoard(Board board) {
        int pieceValues = 0;

        
        for (Piece piece : board.getAllPieces()) {
            pieceValues += getPieceValue(piece);
        }

        

        return pieceValues;
    }

    
    private int getPieceValue(Piece piece) {
        switch (piece.getType()) {
            case PAWN:
                return 10;
            case KNIGHT:
            case BISHOP:
                return 30;
            case ROOK:
                return 50;
            case QUEEN:
                return 90;
            case KING:
                return 900;
            default:
                return 0;
        }
    }
}
