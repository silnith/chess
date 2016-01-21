package org.silnith.game.chess.move;

import org.silnith.game.chess.Board;
import org.silnith.game.chess.Color;
import org.silnith.game.move.Move;

public interface ChessMove extends Move<Board> {

	/**
	 * The color of the side making the move.
	 * 
	 * @return the moving side&#x2019;s color
	 */
	Color getColor();

}
