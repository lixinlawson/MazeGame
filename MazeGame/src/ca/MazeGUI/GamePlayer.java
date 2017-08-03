/**
 * GamePlayer is the class contains main method to play the game.
 */
package ca.MazeGUI;

import ca.MazeGame.MazeGameForGUI;

public class GamePlayer {

	public static void main(String[] args) {
		new MazeGameGUI(new MazeGameForGUI());
	}
}
