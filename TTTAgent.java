/**
 * A Tic-Tac-Toe playing A.I.
 * @author thom
 *
 */
public interface TTTAgent {
	/**
	 * Determines what move the player will do next.
	 * @return The square that the player will mark.
	 */
	public int getMove(TTTBoard current);
}
