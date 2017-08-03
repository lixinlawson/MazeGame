/**
 * MazeGameGUI is the main frame of the game GUI.
 */
package ca.MazeGUI;

import java.awt.BorderLayout;

import ca.MazeGUI.panels.*;
import ca.MazeGame.MazeGameForGUI;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MazeGameGUI extends JFrame {

	public MazeGameGUI(MazeGameForGUI game) {
		super("Maze Game");
		setLayout(new BorderLayout());

		PanelForMaze mazePanel = new PanelForMaze(game);
		PanelForStatus statusPanel = new PanelForStatus(game);

		add(new PanelForButtons(this), BorderLayout.NORTH);
		add(mazePanel, BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);
		add(new ControllerPanel(game), BorderLayout.EAST);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
