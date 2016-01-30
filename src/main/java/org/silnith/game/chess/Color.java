package org.silnith.game.chess;

public enum Color {
    BLACK,
    WHITE;
    
    @Override
    public String toString() {
        switch (this) {
        case BLACK:
            return "Black";
        case WHITE:
            return "White";
        default:
            return super.toString();
        }
    }
    
}
