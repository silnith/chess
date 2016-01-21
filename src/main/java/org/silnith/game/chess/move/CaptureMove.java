package org.silnith.game.chess.move;

import org.silnith.game.chess.Board;
import org.silnith.game.chess.Color;
import org.silnith.game.chess.Piece;

public class CaptureMove implements ChessMove {

	private final int sourceRank;

	private final int sourceFile;

	private final int destinationRank;

	private final int destinationFile;

	private final Piece capturingPiece;

	private final Piece capturedPiece;

	public CaptureMove(final int sourceRank, final int sourceFile, final int destinationRank, final int destinationFile, final Piece capturingPiece, final Piece capturedPiece) {
		super();
		this.sourceRank = sourceRank;
		this.sourceFile = sourceFile;
		this.destinationRank = destinationRank;
		this.destinationFile = destinationFile;
		this.capturingPiece = capturingPiece;
		this.capturedPiece = capturedPiece;
	}

	@Override
	public Board apply(final Board board) {
		return board.movePiece(sourceRank, sourceFile, destinationRank, destinationFile);
	}

	@Override
	public Color getColor() {
		return capturingPiece.getColor();
	}

}
