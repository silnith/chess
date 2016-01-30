package org.silnith.game.chess.move;

import org.silnith.game.chess.Board;
import org.silnith.game.chess.Piece;
import org.silnith.game.chess.Type;


public class PawnPromotionCaptureMove extends PawnPromotionMove {
    
    private final int destinationFile;
    
    private final Piece capturedPiece;
    
    public PawnPromotionCaptureMove(final int sourceRank, final int sourceFile, final int destinationRank,
            final int destinationFile, final Piece capturingPiece, final Piece capturedPiece, final Type type) {
        super(sourceRank, sourceFile, destinationRank, capturingPiece, type);
        this.destinationFile = destinationFile;
        this.capturedPiece = capturedPiece;
    }
    
    public int getDestinationFile() {
        return destinationFile;
    }
    
    public Piece getCapturedPiece() {
        return capturedPiece;
    }
    
    @Override
    public Board apply(final Board board) {
        return board.promotePawn(getSourceRank(), getSourceFile(), getDestinationRank(), getDestinationFile(),
                new Piece(getType(), getColor()));
    }
    
}
