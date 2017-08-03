/**
 *  MazeGameForGUI is the upgraded class for the MazeGame
 *  Mainly to support observers to watch for changes in the game state
 *  Also added some helper method for the GUI
 */

package ca.MazeGame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.MazeGame.supporters.Direction;

public class MazeGameForGUI extends MazeGame {

	private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

	public MazeGameForGUI() {
		super();
	}

	@Override
	public void move(Direction direction) {
		super.move(direction);
		notifyListeners();
	}

	public char getSymbolOf(int row, int col) {
		return getMazeCondition(false)[row][col];
	}

	public int getMazeRowsNum() {
		return getMazeCondition(false).length;
	}

	public int getMazeColsNum() {
		return getMazeCondition(false)[0].length;
	}

	/*
	 * Observer Functions provided by Dr. Brain Fraser
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	private void notifyListeners() {
		// Guard against having called notifyListeners() before the class is
		// fully constructed.
		// Can happen if base class calls an overridden method before derived
		// class is constructed.
		if (listeners == null) {
			return;
		}
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(event);
		}
	}
}
