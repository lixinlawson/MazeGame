/**
 * Panel to display the buttons on the main frame.
 */
package ca.MazeGUI.panels;

import ca.MazeGUI.MazeGameGUI;
import ca.MazeGUI.frames.*;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelForButtons extends JPanel {

	private MazeGameGUI currentGame;

	public PanelForButtons(MazeGameGUI currentGame) {
		this.currentGame = currentGame;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(makeNewGameButton());
		add(makeGameHelpButton());
		add(Box.createHorizontalGlue());
		add(makeAboutGameButton());
	}

	private Button makeNewGameButton() {
		Button newGame = new Button("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FrameForNewGame(currentGame);
			}
		});
		newGame.setFocusable(false);
		return newGame;
	}

	private Button makeGameHelpButton() {
		Button gameHelp = new Button("Game Help");
		gameHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FrameForGameHelp();
			}
		});
		gameHelp.setFocusable(false);
		return gameHelp;
	}

	private Button makeAboutGameButton() {
		Button aboutGame = new Button("About Game");
		aboutGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FrameForAboutGame();
			}
		});
		aboutGame.setFocusable(false);
		return aboutGame;
	}
}
