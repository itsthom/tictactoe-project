import java.util.Scanner;

/**
 * CSE 3521 Project 2<br>
 * Created 4/5/2013<br>
 * This executable class plays two games of Tic-Tac-Toe played between two
 * computer-controlled agents.<br>The game plays one game where each opponent
 * uses a standard minimax algorithm, and then one game where each opponent uses
 * a heuristically informed minimax with a limited-depth search.
 * @author Tom Gleghorn
 *
 */
public class TicTacToe {

	public static void main(String[] args) {
		// Objects - two game boards, two of each agent type
		TTTBoard game1, game2;
		TTTAgent mP1, mP2, hP1, hP2;
		
		// HUMAN AGENT VS MINIMAX TEST
		TTTBoard game0;
		TTTAgent x1, x2;
		System.out.println("TEST GAME");
		game0 = new TTTBoard();
		Scanner sc = new Scanner(System.in);
		x1 = new HumanAgent(1, sc);
		x2 = new MinimaxAgent(2);
		playGame(game0, x1, x2);
		sc.close();
		
		/* * * * * * * * * * *
		 * GAME 1 - MINIMAX  *
		 * * * * * * * * * * */
		System.out.println("\n*** * * * * * * * * * * ***"
				+ "\n*   GAME 1 :   MINIMAX    *");
		game1 = new TTTBoard();
		mP1 = new MinimaxAgent(1);
		mP2 = new MinimaxAgent(2);
		playGame(game1, mP1, mP2);		
		
		/* * * * * * * * * * * *
		 *  GAME 2 - H-MINIMAX *
		 * * * * * * * * * * * */
		System.out.println("\n*** * * * * * * * * * * ***"
				+ "\n*   GAME 2 :  H-MINIMAX   *");
		game2 = new TTTBoard();
		hP1 = new HMinimaxAgent(1);
		hP2 = new HMinimaxAgent(2);
		playGame(game2, hP1, hP2);		
	}
	
	/**
	 * Actually plays the game.
	 * @param game A TTT board
	 * @param p1 a TTT agent
	 * @param p2 a TTT agent
	 */
	private static void playGame(TTTBoard game, TTTAgent p1, TTTAgent p2) {
		System.out.println("*  Player 1: X            *"
				+ "\n*  Player 2: O            *");
		System.out.println("*** * * * * * * * * * * ***\n");
		
		int turn = 0, whoseTurn = 1, move;
		while (!game.gameOver) {
			turn++;
			System.out.print("*** TURN " + turn);
			// Deliver a copy of the board to agent, get agent's next move
			TTTBoard gameCopy = new TTTBoard(game);
			if (whoseTurn == 1) {
				System.out.println(": PLAYER 1'S TURN. ***");
				move = p1.getMove(gameCopy);
			} else {
				System.out.println(": PLAYER 2'S TURN. ***");
				move = p2.getMove(gameCopy);
			}
			// Play move
			game = game.play(whoseTurn, move);
			
			// Show Current Board
			System.out.println("Player " + whoseTurn + " makes the following move:");
			game.printBoard();
			
			// Switch player
			if (whoseTurn == 1) whoseTurn = 2;
			else whoseTurn = 1;
		}

		// Report results
		System.out.println("Game finished in " + turn + " turns.");
		switch (game.winner) {
			case 1: System.out.println("Player 1 wins.\n");
				break;
			case 2: System.out.println("Player 2 wins.\n");
				break;
			default: System.out.println("The game is a draw.\n");
		}
	}
}
