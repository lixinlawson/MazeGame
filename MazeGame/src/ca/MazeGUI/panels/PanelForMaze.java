/**
 * Panel to display the maze of the game.
 */
package ca.MazeGUI.panels;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.MazeGame.MazeGameForGUI;

@SuppressWarnings("serial")
public class PanelForMaze extends JPanel {

	private static final int ICON_SIZE = 45;
	private static final String ICON_FOG = "icons\\fog.png";
	private static final String ICON_MOUSE = "icons\\mouse.png";
	private static final String ICON_CHEESE = "icons\\cheese.png";
	private static final String ICON_DEAD = "icons\\dead.png";
	private static final String ICON_ROAD = "icons\\road.png";
	private static final String ICON_WALL = "icons\\wall.png";
	private static final String ICON_CAT = "icons\\cat.png";

	private static final char SYMBOL_MOUSE = '@';
	private static final char SYMBOL_CAT = '!';
	private static final char SYMBOL_CHEESE = '$';
	private static final char SYMBOL_DEAD = 'X';
	private static final char SYMBOL_WALL = '#';
	private static final char SYMBOL_ROAD = ' ';

	private MazeGameForGUI game;
	private JLabel[][] iconHolder;

	public PanelForMaze(MazeGameForGUI game) {
		this.game = game;
		addChangeListenerToGame(game);
		setInitialLayout();
	}

	private void addChangeListenerToGame(MazeGameForGUI game) {
		game.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				updatePanel();
			}
		});
	}

	public void updatePanel() {
		for (int row = 0; row < game.getMazeRowsNum(); row++) {
			for (int col = 0; col < game.getMazeColsNum(); col++) {
				char symbol = game.getSymbolOf(row, col);
				remove(iconHolder[row][col]);
				iconHolder[row][col] = getImgIconLabel(symbol);
				add(iconHolder[row][col]);
			}
		}
		revalidate();
		repaint();
	}

	private void setInitialLayout() {
		int numOfRows = game.getMazeRowsNum();
		int numOfCols = game.getMazeColsNum();

		iconHolder = new JLabel[numOfRows][numOfCols];
		setLayout(new GridLayout(numOfRows, numOfCols));

		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				char symbol = game.getSymbolOf(row, col);
				iconHolder[row][col] = getImgIconLabel(symbol);
				add(iconHolder[row][col]);
			}
		}
	}

	private JLabel getImgIconLabel(char symbol) {
		JLabel icon;
		ImageIcon img;
		if (symbol == SYMBOL_CAT) {
			img = new ImageIcon(ICON_CAT);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		} else if (symbol == SYMBOL_WALL) {
			img = new ImageIcon(ICON_WALL);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		} else if (symbol == SYMBOL_ROAD) {
			img = new ImageIcon(ICON_ROAD);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		} else if (symbol == SYMBOL_DEAD) {
			img = new ImageIcon(ICON_DEAD);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		} else if (symbol == SYMBOL_CHEESE) {
			img = new ImageIcon(ICON_CHEESE);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		} else if (symbol == SYMBOL_MOUSE) {
			img = new ImageIcon(ICON_MOUSE);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		} else {
			img = new ImageIcon(ICON_FOG);
			icon = new JLabel(getScaleImageIcon(img, ICON_SIZE, ICON_SIZE));
		}
		return icon;
	}

	/*
	 * This method is provided by Dr. Brain Fraser to resize the image icons
	 */
	private static ImageIcon getScaleImageIcon(ImageIcon icon, int width,
			int height) {
		return new ImageIcon(getScaledImage(icon.getImage(), width, height));
	}

	/*
	 * This method is provided by Dr. Brain Fraser to resize the image icons
	 */
	private static Image getScaledImage(Image srcImg, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();
		return resizedImg;
	}
}
