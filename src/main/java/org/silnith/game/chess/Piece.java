package org.silnith.game.chess;

public class Piece {
    
    private final Type type;
    
    private final Color color;
    
    public Piece(final Type type, final Color color) {
        super();
        if (type == null) {
            throw new NullPointerException();
        }
        if (color == null) {
            throw new NullPointerException();
        }
        this.type = type;
        this.color = color;
    }
    
    public Type getType() {
        return type;
    }
    
    public Color getColor() {
        return color;
    }
    
    @Override
    public int hashCode() {
        return 0x61082375 ^ color.hashCode() ^ type.hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Piece) {
            final Piece piece = (Piece) obj;
            return color.equals(piece.color) && type.equals(piece.type);
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return color + " " + type;
    }
    
}
