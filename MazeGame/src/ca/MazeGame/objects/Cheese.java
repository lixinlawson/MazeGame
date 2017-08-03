/**
 * Cheese class is used to create a cheese object in maze
 * It will randomly place in maze and avoid a position is given
 * It can be placed in to a new place
 */

package ca.MazeGame.objects;

import java.util.Random;

import ca.MazeGame.supporters.Position;

public class Cheese {

	private Position position;
	private Maze maze;

	public Cheese(Maze maze, Position mousePos) {
		this.maze = maze;
		newPlaceForCheese(mousePos);
	}

	public Position getPosition() {
		return position;
	}

	public void newPlaceForCheese(Position mousePos) {
		
		position = getRandomPosition();
		
		while (position.isSameWith(mousePos) || maze.isWallCell(position)) {
			position = getRandomPosition();
		}
	}

	private Position getRandomPosition() {
		Random randIndex = new Random();
		int rowOfCheese = randIndex.nextInt(maze.getHeight());
		int colOfCheese = randIndex.nextInt(maze.getWidth());
		
		Position tempPos = new Position(rowOfCheese, colOfCheese);
		
		return tempPos;
	}

}
