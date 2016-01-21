package org.silnith.game.chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.silnith.game.Game;
import org.silnith.game.GameState;
import org.silnith.game.chess.move.CaptureMove;
import org.silnith.game.chess.move.ChessMove;
import org.silnith.game.chess.move.PawnPromotionCaptureMove;
import org.silnith.game.chess.move.PawnPromotionMove;
import org.silnith.game.chess.move.PieceMove;

public class Chess implements Game<ChessMove, Board> {

	public Chess() {
	}

	@Override
	public boolean isWin(final Board board) {
		return false;
	}

	@Override
	public Collection<ChessMove> findAllMoves(final GameState<ChessMove, Board> state) {
		final List<ChessMove> moves = new ArrayList<>();
		
		final Board board = state.getBoards().getFirst();
		final Color currentTurn = board.getCurrentTurn();
		
		for (int rank = 0; rank < board.getLength(); rank++) {
			for (int file = 0; file < board.getLength(); file++) {
				final Piece piece = board.getPiece(rank, file);
				if (piece == null) {
					continue;
				}
				if ( !piece.getColor().equals(currentTurn)) {
					continue;
				}
				
				switch (piece.getType()) {
				case PAWN: {
					moves.addAll(findPawnMoves(rank, file, piece, board));
				} break;
				case ROOK: {
					moves.addAll(findHorizontalMoves(rank, file, piece, board));
				} break;
				case KNIGHT: continue;
				case BISHOP: {
					moves.addAll(findDiagonalMoves(rank, file, piece, board));
				} break;
				case QUEEN: {
					moves.addAll(findHorizontalMoves(rank, file, piece, board));
					moves.addAll(findDiagonalMoves(rank, file, piece, board));
				} break;
				case KING: continue;
				default: throw new IllegalArgumentException();
				}
			}
		}
		
		return moves;
	}

	private Collection<ChessMove> findHorizontalMoves(final int rank, final int file, final Piece piece, final Board board) {
		final List<ChessMove> moves = new ArrayList<>();
		
		for (int i = rank - 1; i >= 0; i--) {
			final Piece destPiece = board.getPiece(i, file);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, i, file, piece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, i, file, piece, destPiece));
				}
				break;
			}
		}
		for (int i = rank + 1; i < board.getLength(); i++) {
			final Piece destPiece = board.getPiece(i, file);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, i, file, piece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, i, file, piece, destPiece));
				}
				break;
			}
		}
		for (int i = file - 1; i >= 0; i--) {
			final Piece destPiece = board.getPiece(rank, i);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, rank, i, piece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, rank, i, piece, destPiece));
				}
				break;
			}
		}
		for (int i = file + 1; i < board.getLength(); i++) {
			final Piece destPiece = board.getPiece(rank, i);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, rank, i, piece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, rank, i, piece, destPiece));
				}
				break;
			}
		}
		
		return moves;
	}

	private Collection<ChessMove> findDiagonalMoves(final int rank, final int file, final Piece piece, final Board board) {
		final List<ChessMove> moves = new ArrayList<>();
		
		for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
			final Piece destPiece = board.getPiece(i, j);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, i, j, destPiece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, i, j, piece, destPiece));
				}
				break;
			}
		}
		for (int i = rank + 1, j = file - 1; i < board.getLength() && j >= 0; i++, j--) {
			final Piece destPiece = board.getPiece(i, j);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, i, j, destPiece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, i, j, piece, destPiece));
				}
				break;
			}
		}
		for (int i = rank - 1, j = file + 1; i >= 0 && j < board.getLength(); i--, j++) {
			final Piece destPiece = board.getPiece(i, j);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, i, j, destPiece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, i, j, piece, destPiece));
				}
				break;
			}
		}
		for (int i = rank + 1, j = file + 1; i < board.getLength() && j < board.getLength(); i++, j++) {
			final Piece destPiece = board.getPiece(i, j);
			if (destPiece == null) {
				moves.add(new PieceMove(rank, file, i, j, destPiece));
			} else {
				// destination square is not null
				if ( !piece.getColor().equals(destPiece.getColor())) {
					moves.add(new CaptureMove(rank, file, i, j, piece, destPiece));
				}
				break;
			}
		}
		
		return moves;
	}

	private Collection<ChessMove> findPawnMoves(final int rank, final int file, final Piece piece, final Board board) {
		final List<ChessMove> moves = new ArrayList<>();
		
		final Color color = piece.getColor();
		
		final int increment;
		final int startingRank;
		final int lastRank;
		switch (color) {
		case BLACK: {
			increment = -1;
			startingRank = 6;
			lastRank = 0;
		} break;
		case WHITE: {
			increment = 1;
			startingRank = 1;
			lastRank = 7;
		} break;
		default: throw new IllegalArgumentException();
		}
		
		final int newRank = rank + increment;
		if (newRank == lastRank) {
			// pawn promotion
			if (board.getPiece(newRank, file) == null) {
				// move straight forward to promotion
				moves.add(new PawnPromotionMove(rank, file, newRank, piece, Type.ROOK));
				moves.add(new PawnPromotionMove(rank, file, newRank, piece, Type.KNIGHT));
				moves.add(new PawnPromotionMove(rank, file, newRank, piece, Type.BISHOP));
				moves.add(new PawnPromotionMove(rank, file, newRank, piece, Type.QUEEN));
			}
			
			final int leftFile = file - 1;
			if (leftFile >= 0) {
				final Piece destPiece = board.getPiece(newRank, leftFile);
				if (destPiece != null) {
					if (destPiece.getColor() != color) {
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, leftFile, piece, destPiece, Type.ROOK));
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, leftFile, piece, destPiece, Type.KNIGHT));
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, leftFile, piece, destPiece, Type.BISHOP));
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, leftFile, piece, destPiece, Type.QUEEN));
					}
				}
			}
			final int rightFile = file + 1;
			if (rightFile < board.getLength()) {
				final Piece destPiece = board.getPiece(newRank, rightFile);
				if (destPiece != null) {
					if (destPiece.getColor() != color) {
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, rightFile, piece, destPiece, Type.ROOK));
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, rightFile, piece, destPiece, Type.KNIGHT));
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, rightFile, piece, destPiece, Type.BISHOP));
						moves.add(new PawnPromotionCaptureMove(rank, file, newRank, rightFile, piece, destPiece, Type.QUEEN));
					}
				}
			}
		} else {
			if (board.getPiece(newRank, file) == null) {
				moves.add(new PieceMove(rank, file, newRank, file, piece));
				if (rank == startingRank) {
					final int jumpRank = newRank + increment;
					if (board.getPiece(jumpRank, file) == null) {
						moves.add(new PieceMove(rank, file, jumpRank, file, piece));
					}
				}
			}
			
			final int leftFile = file - 1;
			if (leftFile >= 0) {
				final Piece destPiece = board.getPiece(newRank, leftFile);
				if (destPiece != null) {
					if (destPiece.getColor() != color) {
						moves.add(new CaptureMove(rank, file, newRank, leftFile, piece, destPiece));
					}
				}
			}
			final int rightFile = file + 1;
			if (rightFile < board.getLength()) {
				final Piece destPiece = board.getPiece(newRank, rightFile);
				if (destPiece != null) {
					if (destPiece.getColor() != color) {
						moves.add(new CaptureMove(rank, file, newRank, rightFile, piece, destPiece));
					}
				}
			}
			
			// check for en passant
		}
		
		return moves;
	}

	@Override
	public GameState<ChessMove, Board> pruneGameState(
			final GameState<ChessMove, Board> state) {
		// if en passant, check that the pawn jump was the previous move
		
		if (state.getMoves().size() < 20) {
			return state;
		} else {
			return null;
		}
	}

}
