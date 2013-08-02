import javax.swing.*;
import java.awt.event.*;

/**
 * Pre-Game window for selecting player types.
 * @author Tom Gleghorn
 */
public class PlayerSelectFrame extends JFrame {

	private final int WINDOW_WIDTH = 200;
	private final int WINDOW_HEIGHT = 130;
	private JLabel p1Label;
	private JLabel p2Label;
	private JComboBox p1BoxSelect;
	private JComboBox p2BoxSelect;
	private JButton playButton;
	private JPanel panel;
	private final String[] playerStrings = { "Human", "Minimax", "H-Minimax"};
	
	/**
	 * Constructor
	 */
	public PlayerSelectFrame () {
		setTitle("Player Select");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		initialize();
		add(panel);
		setVisible(true);
	}
	
	private void initialize() {
		p1Label = new JLabel("Player 1:");
		p2Label = new JLabel("Player 2:");
		p1BoxSelect = new JComboBox(playerStrings);
		p2BoxSelect = new JComboBox(playerStrings);
		playButton = new JButton("Play!");
		playButton.addActionListener(new ButtonListener());
		
		panel = new JPanel();
		panel.add(p1Label);
		panel.add(p1BoxSelect);
		panel.add(p2Label);
		panel.add(p2BoxSelect);
		panel.add(playButton);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get player types
			int p1Type = p1BoxSelect.getSelectedIndex();
			int p2Type = p2BoxSelect.getSelectedIndex();
			
			// Open PlayFrame
			PlayFrame gameWindow = new PlayFrame();
			gameWindow.setPlayerType(1, p1Type);
			gameWindow.setPlayerType(2, p2Type);
			gameWindow.setVisible(true);
			
			// Kill this Frame
			dispose();
		}
		
	}
}
