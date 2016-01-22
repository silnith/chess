package org.silnith.game.chess.move;

import org.silnith.game.chess.Board;
import org.silnith.game.chess.Color;
import org.silnith.game.chess.Piece;
import org.silnith.game.chess.Type;

public class PawnPromotionMove implements ChessMove {
    
    private final int sourceRank;
    
    private final int sourceFile;
    
    private final int destinationRank;
    
    private final Color color;
    
    /**
     * The type of piece to promote the pawn to. This may be any of
     * {@link Type#ROOK}, {@link Type#KNIGHT}, {@link Type#BISHOP}, or
     * {@link Type#QUEEN}.
     */
    private final Type type;
    
    public PawnPromotionMove(final int sourceRank, final int sourceFile,
            final int destinationRank, final Piece piece, final Type type) {
        super();
        this.sourceRank = sourceRank;
        this.sourceFile = sourceFile;
        this.destinationRank = destinationRank;
        this.color = piece.getColor();
        this.type = type;
    }
    
    public int getSourceRank() {
        return sourceRank;
    }
    
    public int getSourceFile() {
        return sourceFile;
    }
    
    public int getDestinationRank() {
        return destinationRank;
    }
    
    public Type getType() {
        return type;
    }
    
    @Override
    public Board apply(final Board board) {
        return board.promotePawn(sourceRank, sourceFile, destinationRank,
                sourceFile, new Piece(type, color));
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
}
