/**
 * Class for a Human Tic Tac Toe player
 * @author Tom Gleghorn
 *
 */
import java.util.Scanner;

public class HumanAgent implements TTTAgent {
	
	private int player;
	private int opponent;
	private Scanner ins;
	
	/**
	 * Constructor
	 * @param player which player to be
	 */
	public HumanAgent(int player, Scanner sc) {
		this.player = player;
		if (player == 1) this.opponent = 2;
		else this.opponent = 1;
		this.ins = sc;
	}
	
	/**
	 * Lets the player choose a move
	 * @param game current game state
	 * @return the square player chooses to mark
	 */
	public int getMove(TTTBoard game) {
		
		int move = -1;
		
		if (this.player == 1) System.out.println("You are X.");
		else System.out.println("You are O.");
		
		game.printBoard();
		
		while (move < 0 || move > 8) {
			System.out.print("Which square to play (0-8)? ");
			move = this.ins.nextInt();
		}
		
		return move;
	}
}
