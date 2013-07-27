/**
 * This class uses a Minimax search to decide which square to play
 * in Tic Tac Toe.
 * @author Tom Gleghorn
 *
 */
public class MinimaxAgent implements TTTAgent {	

	private int player; // Identifies if player 1 or 2
	private int opponent; // Identifies if opponent is player 1 or 2
		
	/**
	 * Constructor.
	 * @param player tells Agent which player it is.
	 */
	public MinimaxAgent(int player) {
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
		return resultAction;
	}
	
	/**
	 * Finds the maximum utility value froma  given state.
	 * @param state A possible state for the game
	 * @return A utility value for the state
	 */
	private int maxValue(TTTBoard state) {
		// If game is over, determine winner and return utility value
		if (state.gameOver) return getValue(state);
		else { // Otherwise progress down the tree
			int value = Integer.MIN_VALUE;
			for (int action : state.getActions()) {
				TTTBoard whatIf = new TTTBoard(state);
				value = Math.max(value, minValue(whatIf.play(this.player, action)));
			}
			return value;
		}
	}
	
	/**
	 * Finds the minimum utility value from a given state.
	 * @param state A possible state for the game
	 * @return A utility value for the state
	 */
	private int minValue(TTTBoard state) {		
		// If game is over, determine winner and return utility value
		if (state.gameOver) return getValue(state);
		
		else { // Otherwise, progress down the tree
			int value = Integer.MAX_VALUE;
						
			int[] actions = state.getActions();
			for (int action : actions) {
				TTTBoard whatIf = new TTTBoard(state);
				value = Math.min(value, maxValue(whatIf.play(this.opponent, action)));
			}
			return value;
		}
	}
	
	/**
	 * Gets the value of a terminal state
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
