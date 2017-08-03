/**
 * MazeGame is used to create a game object which contains all main logic for the game
 * Handle the changes all the objects make
 * Give the MazeGameGUI class the current condition of the game
 */

package ca.MazeGame;

import ca.MazeGame.objects.*;
import ca.MazeGame.supporters.*;

public class MazeGame {

	private static final int MAZE_WIDTH = 20;
	private static final int MAZE_HEIGHT = 15;

	private static final int SIZE_OF_CELL = 1;
	private static final int NUM_OF_CATS = 6;
	private static final int NUM_OF_CHEESE_TO_WIN = 5;

	private static final Position TOP_LEFT_CORNER_POS = new Position(1, 1);
	private static final Position TOP_RIGHT_CORNER_POS = new Position(1, 18);
	private static final Position BOT_LEFT_CORNER_POS = new Position(13, 1);
	private static final Position BOT_RIGHT_CORNER_POS = new Position(13, 18);

	private static final char SYMBOL_MOUSE = '@';
	private static final char SYMBOL_CAT = '!';
	private static final char SYMBOL_CHEESE = '$';
	private static final char SYMBOL_DEAD = 'X';
	private static final char SYMBOL_FOG = '.';
	private static final char SYMBOL_WALL = '#';
	private static final char SYMBOL_ROAD = ' ';

	private Maze maze;
	private Mouse mouse;
	private Cheese cheese;
	private Cat[] cats;

	public MazeGame() {
		maze = new Maze(MAZE_WIDTH, MAZE_HEIGHT);

		mouse = new Mouse(TOP_LEFT_CORNER_POS, maze);

		cheese = new Cheese(maze, mouse.getPosition());

		cats = new Cat[NUM_OF_CATS];
		getCatsReady();
	}

	// PRE: the direction is a direction mouse can move to
	public void move(Direction direction) {
		mouse.move(direction);
		tryCatchMouse();
		tryCollectCheese();

		if (!isOver()) {
			catsMove();
			tryCatchMouse();
		}
	}

	public boolean canMoveTo(Direction direction) {
		boolean isPossible = true;
		Position tempPos = new Position(mouse.getPosition());
		switch (direction) {
		case UP:
			tempPos = tempPos.shiftUp(SIZE_OF_CELL);
			break;
		case DOWN:
			tempPos = tempPos.shiftDown(SIZE_OF_CELL);
			break;
		case LEFT:
			tempPos = tempPos.shiftLeft(SIZE_OF_CELL);
			break;
		case RIGHT:
			tempPos = tempPos.shiftRight(SIZE_OF_CELL);
			break;
		default:
			assert false;
		}

		if (maze.isWallCell(tempPos)) {
			isPossible = false;
		}
		return isPossible;
	}

	public boolean isOver() {
		return win() || lost();
	}

	public boolean win() {
		return mouse.getNumOfCheese() == NUM_OF_CHEESE_TO_WIN;
	}

	public boolean lost() {
		return mouse.isDead();
	}

	public int getNumOfCheeseCollected() {
		return mouse.getNumOfCheese();
	}

	public int getNumOfCheeseToWin() {
		return NUM_OF_CHEESE_TO_WIN;
	}

	public char[][] getMazeCondition(boolean cheat) {
		char[][] condition = new char[MAZE_HEIGHT][MAZE_WIDTH];

		if (isOver()) {
			cheat = true;
		}

		for (int row = 0; row < maze.getHeight(); row++) {
			for (int col = 0; col < maze.getWidth(); col++) {
				// place the mouse, cats, cheese or cell base on row and column
				Position tempPos = new Position(row, col);

				if (tempPos.isSameWith(mouse.getPosition())) {
					condition[row][col] = SYMBOL_MOUSE;
				} else if (positionContainsACat(tempPos)) {
					condition[row][col] = SYMBOL_CAT;
				} else if (tempPos.isSameWith(cheese.getPosition())) {
					condition[row][col] = SYMBOL_CHEESE;
				} else {
					if (cheat == false) {
						if (maze.isVisibleCell(tempPos)) {
							if (maze.isWallCell(tempPos)) {
								condition[row][col] = SYMBOL_WALL;
							} else {
								condition[row][col] = SYMBOL_ROAD;
							}
						} else {
							// unexplored cell
							condition[row][col] = SYMBOL_FOG;
						}
					} else {
						// in cheat mode all explored
						if (maze.isWallCell(tempPos)) {
							condition[row][col] = SYMBOL_WALL;
						} else {
							condition[row][col] = SYMBOL_ROAD;
						}
					}
				}
			}
		}

		if (mouse.isDead()) {
			int mouseRow = mouse.getPosition().getRow();
			int mouseCol = mouse.getPosition().getCol();
			condition[mouseRow][mouseCol] = SYMBOL_DEAD;
		}

		return condition;
	}

	private void getCatsReady() {
		cats[0] = new Cat(TOP_RIGHT_CORNER_POS, maze);
		cats[1] = new Cat(TOP_RIGHT_CORNER_POS, maze);
		cats[2] = new Cat(BOT_LEFT_CORNER_POS, maze);
		cats[3] = new Cat(BOT_LEFT_CORNER_POS, maze);
		cats[4] = new Cat(BOT_RIGHT_CORNER_POS, maze);
		cats[5] = new Cat(BOT_RIGHT_CORNER_POS, maze);
	}

	private void catsMove() {
		for (Cat cat : cats) {
			cat.move();
		}
	}

	private void tryCollectCheese() {
		if (mouse.getPosition().isSameWith(cheese.getPosition())) {
			mouse.collectACheese();
			cheese.newPlaceForCheese(mouse.getPosition());
		}
	}

	private void tryCatchMouse() {
		if (positionContainsACat(mouse.getPosition())) {
			mouse.caughtByCat();
		}
	}

	private boolean positionContainsACat(Position pos) {
		boolean sameCell = false;
		for (Cat cat : cats) {
			if (pos.isSameWith(cat.getPosition())) {
				sameCell = true;
			}
		}
		return sameCell;
	}

}
