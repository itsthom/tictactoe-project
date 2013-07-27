/**
 * This class represents a Tic-Tac-Toe board and provides
 * various useful functions for manipulating it.
 * @author Tom Gleghorn
 */
public class TTTBoard {
	
	public int[] board; // Board representation: each can be -1 (X) +1 (O) or 0
	public boolean gameOver; // Signals a terminal state
	public int winner; // Keeps track of which player wins
	
	/**
	 * Default Constructor (Brand new board)
	 */
	public TTTBoard() {
		this.board = new int[9];
		for (int i = 0; i < 9; i++) this.board[i] = 0;
		this.gameOver = false;
		this.winner = 0;
	}
	
	/**
	 * Copy Constructor
	 */
	public TTTBoard(TTTBoard source) {
		this.board = new int[9];
		for (int i = 0; i < 9; i++) this.board[i] = source.board[i];
		this.gameOver = source.gameOver;
		this.winner = source.winner;
	}
	
	/**
	 * Play a move - alters the representation.
	 * @param player Player which makes the move (1 or 2)
	 * @param square Square which player plays (0-8)
	 */
	public TTTBoard play(int player, int square) {
		int value = -1;
		if (player == 2) value = 1;
		
		this.board[square] = value;
		checkFinished();
		return this;
	}
	
	/**
	 * Check for terminal condition - One of three types of victory
	 * (three in a row, column, or diagonal) or a draw (all squares filled,
	 * no winner).
	 */
	private void checkFinished() {
		int[] sums = new int[8];
		
		// Sum rows, columns and diagonals
		sums[0] = this.board[0] + this.board[1] + this.board[2];
		sums[1] = this.board[3] + this.board[4] + this.board[5];
		sums[2] = this.board[6] + this.board[7] + this.board[8];
		
		sums[3] = this.board[0] + this.board[3] + this.board[6];
		sums[4] = this.board[1] + this.board[4] + this.board[7];
		sums[5] = this.board[2] + this.board[5] + this.board[8];
		
		sums[6] = this.board[0] + this.board[4] + this.board[8];
		sums[7] = this.board[6] + this.board[4] + this.board[2];
		
		// Check for a victory
		int winningSum = 0;
		for (int i : sums) {
			if ((i == 3) || (i == -3)) winningSum = i;
		}
		
		// Declare Victory
		if (winningSum == 3) {
			// Player 2 wins
			this.gameOver = true;
			this.winner = 2;
		}
		else if (winningSum == -3) {
			// Player 1 wins
			this.gameOver = true;
			this.winner = 1;
		}
		else { // Check for tie
			boolean foundBlank = false;
			for (int i : this.board) {
				if (i == 0) foundBlank = true;
			}
			
			if (!foundBlank) {
				// End game but do not set a winner
				this.gameOver = true;
			}
		}
	}
	
	/**
	 * Generates a list of empty squares on the board.
	 * @return A list of all possible actions
	 */
	public int[] getActions() {
		// Determine number of actions possible
		int numActions = 0;
		for (int i = 0; i < 9; i++) {
			if (this.board[i] == 0) numActions++;
		}
		
		// Return a list of all free squares
		int[] actions = new int[numActions];
		int counter = 0;
		for (int i = 0; i < 9; i++) {
			if (this.board[i] == 0) {
				actions[counter] = i;
				counter++;
			}
		}
		return actions;
	}
	
	/**
	 * Display the status of the board
	 */
	public void printBoard() {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				switch (this.board[s]) {
					case -1: System.out.print("  X");
						break;
					case 1: System.out.print("  O");
						break;
					default: System.out.print("  .");
				}
				s++;
			}
			System.out.println();
		}
		System.out.println("\n");
	}
}