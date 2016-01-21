package org.silnith.game.chess;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final int length;

	private final List<List<Piece>> board;

	private final Color currentTurn;

	public Board(final int length) {
		super();
		if (length < 1) {
			throw new IllegalArgumentException();
		}
		this.length = length;
		this.board = new ArrayList<>(this.length);
		for (int i = 0; i < this.length; i++) {
			final ArrayList<Piece> e = new ArrayList<Piece>(this.length);
			for (int j = 0; j < this.length; j++) {
				e.add(null);
			}
			this.board.add(e);
		}
		this.currentTurn = Color.WHITE;
	}

	protected Board(final Board original) {
		super();
		this.length = original.length;
		this.board = new ArrayList<>(original.board);
		switch (original.currentTurn) {
		case BLACK: this.currentTurn = Color.WHITE; break;
		case WHITE: this.currentTurn = Color.BLACK; break;
		default: throw new IllegalArgumentException();
		}
	}

	public int getLength() {
		return length;
	}

	/**
	 * @return the color of the player who will move next
	 */
	public Color getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @param rank the row
	 * @param file the column
	 * @return the piece on the board square
	 */
	public Piece getPiece(final int rank, final int file) {
		return board.get(rank).get(file);
	}

	/**
	 * @param rank the row
	 * @param file the column
	 * @param piece the piece to place
	 */
	private void putPiece(final int rank, final int file, final Piece piece) {
		board.get(rank).set(file, piece);
	}

	public Board setUpInitialBoard() {
		final Board newBoard = new Board(8);
		final Piece whitePawn = new Piece(Type.PAWN, Color.WHITE);
		for (int i = 0; i < 8; i++) {
			newBoard.putPiece(1, i, whitePawn);
		}
		final Piece whiteRook = new Piece(Type.ROOK, Color.WHITE);
		newBoard.putPiece(0, 0, whiteRook);
		newBoard.putPiece(0, 7, whiteRook);
		final Piece whiteKnight = new Piece(Type.KNIGHT, Color.WHITE);
		newBoard.putPiece(0, 1, whiteKnight);
		newBoard.putPiece(0, 6, whiteKnight);
		final Piece whiteBishop = new Piece(Type.BISHOP, Color.WHITE);
		newBoard.putPiece(0, 2, whiteBishop);
		newBoard.putPiece(0, 5, whiteBishop);
		final Piece whiteQueen = new Piece(Type.QUEEN, Color.WHITE);
		newBoard.putPiece(0, 3, whiteQueen);
		final Piece whiteKing = new Piece(Type.KING, Color.WHITE);
		newBoard.putPiece(0, 4, whiteKing);
		final Piece blackPawn = new Piece(Type.PAWN, Color.BLACK);
		for (int i = 0; i < 8; i++) {
			newBoard.putPiece(6, i, blackPawn);
		}
		final Piece blackRook = new Piece(Type.ROOK, Color.BLACK);
		newBoard.putPiece(7, 0, blackRook);
		newBoard.putPiece(7, 7, blackRook);
		final Piece blackKnight = new Piece(Type.KNIGHT, Color.BLACK);
		newBoard.putPiece(7, 1, blackKnight);
		newBoard.putPiece(7, 6, blackKnight);
		final Piece blackBishop = new Piece(Type.BISHOP, Color.BLACK);
		newBoard.putPiece(7, 2, blackBishop);
		newBoard.putPiece(7, 5, blackBishop);
		final Piece blackQueen = new Piece(Type.QUEEN, Color.BLACK);
		newBoard.putPiece(7, 3, blackQueen);
		final Piece blackKing = new Piece(Type.KING, Color.BLACK);
		newBoard.putPiece(7, 4, blackKing);
		return newBoard;
	}

	public Board movePiece(final int sourceRank, final int sourceFile, final int destinationRank, final int destinationFile) {
		return promotePawn(sourceRank, sourceFile, destinationRank, destinationFile, getPiece(sourceRank, sourceFile));
//		final Board newBoard = new Board(this);
//		
//		final List<Piece> sourceRankList = newBoard.board.get(sourceRank);
//		final List<Piece> newSourceRankList = new ArrayList<>(sourceRankList);
//		newSourceRankList.set(sourceFile, null);
//		newBoard.board.set(sourceRank, newSourceRankList);
//		
//		final List<Piece> destinationRankList = newBoard.board.get(destinationRank);
//		final List<Piece> newDestinationRankList = new ArrayList<>(destinationRankList);
//		newDestinationRankList.set(destinationFile, getPiece(sourceRank, sourceFile));
//		newBoard.board.set(destinationRank, newDestinationRankList);
//		
//		return newBoard;
	}

	public Board promotePawn(final int sourceRank, final int sourceFile, final int destinationRank, final int destinationFile, final Piece promotedPiece) {
		final Board newBoard = new Board(this);
		
		final List<Piece> sourceRankList = newBoard.board.get(sourceRank);
		final List<Piece> newSourceRankList = new ArrayList<>(sourceRankList);
		newSourceRankList.set(sourceFile, null);
		newBoard.board.set(sourceRank, newSourceRankList);
		
		final List<Piece> destinationRankList = newBoard.board.get(destinationRank);
		final List<Piece> newDestinationRankList = new ArrayList<>(destinationRankList);
		newDestinationRankList.set(destinationFile, promotedPiece);
		newBoard.board.set(destinationRank, newDestinationRankList);
		
		return newBoard;
	}

}
