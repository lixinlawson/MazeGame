/**
 * Panel to display the game status of the game.
 */
package ca.MazeGUI.panels;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.MazeGame.MazeGameForGUI;

@SuppressWarnings("serial")
public class PanelForStatus extends JPanel {

	private MazeGameForGUI game;
	private JLabel statusLabel;

	public PanelForStatus(MazeGameForGUI game) {
		this.game = game;
		addChangeListenerToGame(game);

		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		statusLabel = makeStatusLabel();
		statusLabel.setFont(new Font("Arial", Font.BOLD, 17));
		add(Box.createHorizontalGlue());
		add(statusLabel);
	}

	private void addChangeListenerToGame(MazeGameForGUI game) {
		game.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				updatePanel();
			}
		});
	}

	private void updatePanel() {
		statusLabel.setText(getTextOnStatusLabel());
	}

	private JLabel makeStatusLabel() {
		JLabel status = new JLabel(getTextOnStatusLabel());
		return status;
	}

	private String getTextOnStatusLabel() {
		int numCollected = game.getNumOfCheeseCollected();
		int numToWin = game.getNumOfCheeseToWin();
		String labeText = String.format("Collected: %d of %d Cheese.",
				numCollected, numToWin);
		return labeText;
	}

}
