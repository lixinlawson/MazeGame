/**
 * The ControllerPanel class is mainly used to handle the keyboard actions 
 * and play the sounds when it is necessary
 */
package ca.MazeGUI.panels;

import ca.MazeGame.MazeGameForGUI;
import ca.MazeGame.supporters.Direction;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class ControllerPanel extends JPanel {

	private static final String[] KEYS = { "UP", "DOWN", "LEFT", "RIGHT" };

	private static final File SOUND_WALL = new File("sounds//BOING!.WAV");
	private static final File SOUND_DEAD = new File("sounds//AHOOGA.wav");
	private static final File SOUND_WIN = new File("sounds//IFEELGOOD.wav");

	private MazeGameForGUI game;

	public ControllerPanel(MazeGameForGUI game) {
		this.game = game;
		registerKeyPresses();
	}

	/*
	 * This method is from ArrowKeyDemo.java provided by Dr. Brain Fraser
	 */
	private void registerKeyPresses() {
		for (int i = 0; i < KEYS.length; i++) {
			String key = KEYS[i];
			this.getInputMap().put(KeyStroke.getKeyStroke(key), key);
			this.getActionMap().put(key, getKeyListener(key));
		}
	}

	/*
	 * This method structure is from ArrowKeyDemo.java provided by Dr. Brain
	 * Fraser
	 */
	private AbstractAction getKeyListener(final String move) {
		return new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				Direction direction = getMoveDirection(move);
				if (!game.isOver()) {
					if (game.canMoveTo(direction)) {

						game.move(direction);

						if (game.isOver()) {
							winOrLostSound();
						}

					} else {
						playSound(SOUND_WALL);
					}
				}
			}

		};
	}

	private void winOrLostSound() {
		if (game.win()) {
			playSound(SOUND_WIN);
		} else {
			playSound(SOUND_DEAD);
		}
	}

	private Direction getMoveDirection(String move) {
		Direction direction;
		if (move == "UP") {
			direction = Direction.UP;
		} else if (move == "DOWN") {
			direction = Direction.DOWN;
		} else if (move == "LEFT") {
			direction = Direction.LEFT;
		} else {// direction == "RIGHT"
			direction = Direction.RIGHT;
		}
		return direction;
	}

	/*
	 * This method is provided by Dr. Brain Fraser to play sounds
	 */
	private static void playSound(File sound) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
