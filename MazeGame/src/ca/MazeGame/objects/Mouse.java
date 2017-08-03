/**
 * Mouse class is used to create mouse object
 * It stores the position of mouse in Maze by the row and column value
 * Mouse will count how many cheese it collected and check if it is still alive
 * Mouse will explore the cells around it after each move
 */
package ca.MazeGame.objects;

import ca.MazeGame.supporters.*;

public class Mouse {

	private static final int STEP = 1;

	private Position position;
	private boolean isAlive;
	private int numOfCheese;
	private Maze maze;

	public Mouse(Position pos, Maze maze) {
		isAlive = true;
		position = new Position(pos);
		this.maze = maze;
		numOfCheese = 0;
		exploreAround();
	}

	public boolean isDead() {
		return !isAlive;
	}

	public Position getPosition() {
		return position;
	}

	public int getNumOfCheese() {
		return numOfCheese;
	}

	public void collectACheese() {
		numOfCheese++;
	}

	public void caughtByCat() {
		isAlive = false;
	}

	public void move(Direction direction) {
		switch (direction) {
		case UP:
			position = position.shiftUp(STEP);
			break;
		case DOWN:
			position = position.shiftDown(STEP);
			break;
		case LEFT:
			position = position.shiftLeft(STEP);
			break;
		case RIGHT:
			position = position.shiftRight(STEP);
			break;
		default:
			assert false;
		}
		exploreAround();
	}

	private void exploreAround() {
		int row = position.getRow();
		int col = position.getCol();

		exploreCell(row, col);
		exploreCell(row, col + STEP);
		exploreCell(row, col - STEP);
		exploreCell(row + STEP, col);
		exploreCell(row - STEP, col);
		exploreCell(row + STEP, col + STEP);
		exploreCell(row - STEP, col - STEP);
		exploreCell(row + STEP, col - STEP);
		exploreCell(row - STEP, col + STEP);
	}

	private void exploreCell(int row, int col) {
		Position tempPos = new Position(row, col);
		maze.exploreCell(tempPos);
	}
}
