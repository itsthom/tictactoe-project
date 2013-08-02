import javax.swing.*;


/**
 * Main Game Window
 * @author Tom Gleghorn
 */
public class PlayFrame extends JFrame {

	// Window Components/Attributes
	private final int WINDOW_WIDTH = 400;
	private final int WINDOW_HEIGHT = 300;
	private JPanel mainPanel;
	private JPanel viewPanel1;
	private JPanel viewPanel2;
	private JLabel turnLabel;
	private JButton nextButton;
	
	// Game Attributes
	private int turn;
	private int p1Type;
	private int p2Type;
	
	public PlayFrame() {
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		initialize();
		add(mainPanel);
		// Visibility set by calling method after setting player types
	}
	
	private void initialize() {
		turnLabel = new JLabel("Turn");
		nextButton = new JButton("Next Turn");
		
		mainPanel = new JPanel();
		mainPanel.add(turnLabel);
		mainPanel.add(nextButton);
		
	}
	
	// Setter for player type
	public void setPlayerType(int player, int type) {
		
	}

}
