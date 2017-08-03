/**
 * Cat class is used to create a Cat object
 * It stores the position of cat in Maze 
 * The cat can move randomly in maze after it created
 */

package ca.MazeGame.objects;

import ca.MazeGame.supporters.*;

import java.util.ArrayList;
import java.util.Random;

public class Cat {

	private static final int STEP = 1;
	private Position position;
	private Direction backtrack;
	private Maze maze;

	public Cat(Position pos, Maze maze) {
		position = new Position(pos);
		this.maze = maze;
	}

	public Position getPosition() {
		return position;
	}

	public void move() {

		Direction direction = directionForCat();

		switch (direction) {
		case UP:
			position = position.shiftUp(STEP);
			backtrack = Direction.DOWN;
			break;
		case DOWN:
			position = position.shiftDown(STEP);
			backtrack = Direction.UP;
			break;
		case LEFT:
			position = position.shiftLeft(STEP);
			backtrack = Direction.RIGHT;
			break;
		case RIGHT:
			position = position.shiftRight(STEP);
			backtrack = Direction.LEFT;
			break;
		default:
			assert false;
		}

	}

	private Direction directionForCat() {
		Direction direction;

		ArrayList<Direction> prefer = preferDirections();

		if (prefer.isEmpty()) {
			direction = backtrack;
		} else {
			Random ran = new Random();
			int ranIndex = ran.nextInt(prefer.size());
			direction = prefer.get(ranIndex);
		}
		return direction;
	}

	private ArrayList<Direction> preferDirections() {
		ArrayList<Direction> prefer = new ArrayList<Direction>();

		// add all possible directions to preferDirections
		if (!maze.isWallCell(position.shiftUp(STEP))) {
			prefer.add(Direction.UP);
		}
		if (!maze.isWallCell(position.shiftDown(STEP))) {
			prefer.add(Direction.DOWN);
		}
		if (!maze.isWallCell(position.shiftLeft(STEP))) {
			prefer.add(Direction.LEFT);
		}
		if (!maze.isWallCell(position.shiftRight(STEP))) {
			prefer.add(Direction.RIGHT);
		}

		// remove the backtrack from the preferDirections
		// if the cat has backtrack
		if (backtrack != null) {
			int tempIndex = prefer.indexOf(backtrack);
			prefer.remove(tempIndex);
		}

		return prefer;
	}

}
