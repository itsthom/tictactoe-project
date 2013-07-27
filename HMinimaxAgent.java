/**
 * This class uses a heuristically-informed limited-depth Minimax search
 * to decide which square to play int Tic Tac Toe.
 * @author Tom Gleghorn
 *
 */
public class HMinimaxAgent implements TTTAgent{
	// TODO Write an agent that uses H-Minimax Adversarial Search
	
	private int player; // Identifies if player 1 or 2
	private int opponent; // Identifies if opponent is player 1 or 2
	private static final int MAX_DEPTH = 4; // Adjust value to set depth of search
	private int depth; // Keeps track of the current search depth
	
	/**
	 * Constructor.
	 * @param player tells Agent which player it is.
	 */
	public HMinimaxAgent(int player) {
		this.player = player;
		if (player == 1) this.opponent = 2;
		else this.opponent = 1;
	}
	
	/* (non-Javadoc)
	 * @see TTTAgent#getMove(TTTBoard)
	 * This is the equivalent of MINIMAX-DECISION(state) pseudocode
	 * in our textbook p.166
	 */
	public int getMove(TTTBoard game) {
		// Reset depth indicator
		this.depth = 0;
		
		int[] utilityValues = new int[9];
		int resultAction = Integer.MIN_VALUE, resultValue = Integer.MIN_VALUE;
		for (int i = 0; i < 9; i++) utilityValues[i] = Integer.MIN_VALUE;
		
		// Find mins resulting from each action & choose the max
		int[] actions = game.getActions();
		for (int action : actions) {
			// What would happen if I played this action?
			TTTBoard whatIf = new TTTBoard(game);
			int value = minValue(whatIf.play(this.player, action));
			utilityValues[action] = value;
			
			// Keep track of a greatest value
			if (value > resultValue) {
				resultAction = action;
				resultValue = value;
			}
		}
		
		// Output and return minimax results
		printValues(utilityValues);
		System.out.println("Chosen action: " + resultAction);
		return resultAction;
	}
	
	/**
	 * Finds the maximum utility value from a given state.
	 * @param state A possible state for the game
	 * @return A utility value for the state
	 */
	private int maxValue(TTTBoard state) {
		this.depth++;
		
		// If depth is reached, determine a value
		if ((this.depth >= MAX_DEPTH) || state.gameOver) {
			this.depth--;
			return heuristic(state);
		}
		
		else { // Otherwise progress down the tree
			int value = Integer.MIN_VALUE;
			for (int action : state.getActions()) {
				TTTBoard whatIf = new TTTBoard(state);
				value = Math.max(value, minValue(whatIf.play(this.player, action)));
			}
			this.depth--;
			return value;
		}
	}
	
	/**
	 * Finds the minimum utility value from a given state.
	 * @param state A possible state for the game
	 * @return A utility value for the state
	 */
	private int minValue(TTTBoard state) {
		this.depth++;
		
		// If depth is reached, determine a value
		if ((this.depth >= MAX_DEPTH) || state.gameOver) {
			this.depth--;
			return heuristic(state);
		}
		
		else { // Otherwise, progress down the tree
			int value = Integer.MAX_VALUE;
						
			int[] actions = state.getActions();
			for (int action : actions) {
				TTTBoard whatIf = new TTTBoard(state);
				value = Math.min(value, maxValue(whatIf.play(this.opponent, action)));
			}
			this.depth--;
			return value;
		}
	}
	
	/**
	 * A heuristic for approximating the value of a game state. The function
	 * awards 3 points for every row, column, or diagonal with two of the player's
	 * squares, 1 point for every row, column, or diagonal with one square, and subtracts
	 * the equivalent number of points if it finds the opponent's squares.
	 * @param state A game state
	 * @return A utility value given the game state
	 */
	private int heuristic(TTTBoard state) {
		// If it's a terminal state, can just use the value
		if (state.gameOver) return getValue(state);
		
		else { // Have to approximate
			int value = 0, target = -1;
			if (this.player == 2) target = 1;
			
			// Count X's and O's in every victory direction
			value += heuristicHelper(state, 0, 1, 2, target); // Row 1
			value += heuristicHelper(state, 3, 4, 5, target); // Row 2
			value += heuristicHelper(state, 6, 7, 8, target); // Row 3
			
			value += heuristicHelper(state, 0, 3, 6, target); // Column 1
			value += heuristicHelper(state, 1, 4, 7, target); // Column 2
			value += heuristicHelper(state, 2, 5, 8, target); // Column 3
			
			value += heuristicHelper(state, 0, 4, 8, target); // Diagonal 1
			value += heuristicHelper(state, 6, 4, 2, target); // Diagonal 2
			
			return value;
		}
	}
	
	/**
	 * Helper function for the calculation of the heuristic function
	 * @param state A game state
	 * @param a Square 1
	 * @param b Square 2
	 * @param c Square 3
	 * @param target The value of player's mark in the board representation
	 * @return The adjustment that needs to be made to the heuristic value
	 */
	private int heuristicHelper(TTTBoard state, int a, int b, int c, int target) {
		// Count X's and O's
		int good = 0, bad = 0;
		if (state.board[a] == target) good++;
		else if (state.board[a] != 0) bad++;
		
		if (state.board[b] == target) good++;
		else if (state.board[b] != 0) bad++;
		
		if (state.board[c] == target) good++;
		else if (state.board[c] != 0) bad++;
		
		// Determine adjustment to value
		int adjust = 0;
		if (good == 1) adjust += 1;
		else if (good == 2) adjust += 3;
		
		if (bad == 1) adjust -= 1;
		else if (bad == 2) adjust -= 3;
		
		return adjust;
	}
	
	/**
	 * Gets the value of a terminal state (Used by heuristic, recycled from minimax)
	 */
	private int getValue(TTTBoard state) {
		int value = state.winner;
		if (value == this.player) value = 1;
		else if (value == this.opponent) value = -1;
		else value = 0;
		return value;
	}
	
	/**
	 * Displays the minimax values that the agent has calculated for each square.
	 */
	private void printValues(int[] utilityValues) {
		System.out.println("After searching, player " + this.player + " concludes "
				+ "the following minimax values:");
		int s = 0;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if (utilityValues[s] == Integer.MIN_VALUE) System.out.print("  .");
				else if (utilityValues[s] < 0) System.out.print(" " + utilityValues[s]);
				else System.out.print("  " + utilityValues[s]);
				s++;
			}
			System.out.println();
		}
	}
}
