package org.silnith.game.chess;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.silnith.game.GameState;
import org.silnith.game.ai.Searcher;
import org.silnith.game.chess.config.SearcherConfiguration;
import org.silnith.game.chess.move.ChessMove;
import org.silnith.game.chess.move.SetUpBoardMove;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Solver {

	public static void main(String[] args) throws InterruptedException {
		try (final AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SearcherConfiguration.class)) {
			final Chess chess = context.getBean(Chess.class);
			
			final ChessMove initialMove = new SetUpBoardMove();
			final Board initialBoard = initialMove.apply(new Board(8));
			final GameState<ChessMove, Board> initialState = new GameState<ChessMove, Board>(initialMove, initialBoard);
			
			final Searcher<ChessMove, Board> searcher = new Searcher<>(chess, initialState);
			
			final int numThreads = 8;
			final Collection<Thread> threads = new ArrayList<>(numThreads);
			for (int i = 0; i < numThreads; i++) {
				final Runnable worker = searcher.getNewWorker();
				final Thread thread = new Thread(worker);
				threads.add(thread);
				thread.start();
			}
			
			final NumberFormat formatter = NumberFormat.getIntegerInstance();
			do {
				Thread.sleep(3000);
				System.out.println();
				System.out.println();
				System.out.println("Number of solutions found: " + formatter.format(searcher.getSolutions().size()));
				System.out.println("Maximum tree depth searched: " + formatter.format(searcher.getMaxDepthSearched()));
				System.out.println("Pending nodes to search: " + formatter.format(searcher.getPendingNodesCount()));
				System.out.println("Nodes searched: " + formatter.format(searcher.getNodesSearched()));
				System.out.println();
			} while ( !searcher.isDone());
			
			for (final Thread thread : threads) {
				thread.interrupt();
			}
			
			System.out.println(searcher.getSolutions());
		}
	}

}
