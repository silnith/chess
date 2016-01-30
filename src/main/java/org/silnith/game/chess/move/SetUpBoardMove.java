package org.silnith.game.chess.move;

import org.silnith.game.chess.Board;
import org.silnith.game.chess.Color;


public class SetUpBoardMove implements ChessMove {
    
    public SetUpBoardMove() {
        super();
    }
    
    @Override
    public Board apply(final Board board) {
        return board.setUpInitialBoard();
    }
    
    @Override
    public Color getColor() {
        throw new UnsupportedOperationException();
    }
    
}
