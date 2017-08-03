/**
 * Maze is used to create a maze object
 * It can generate a random maze by given width and height
 * It can check whether a cell in it is a wall or explored already by given row and column
 */

package ca.MazeGame.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ca.MazeGame.supporters.Position;

public class Maze {

	private static final int STEP_NEED_TO_CHECK = 2;
	private static final Position DIGGER_STARTS_AT = new Position(1, 1);;

	private int width;
	private int height;
	private Cell[][] maze;

	public Maze(int width, int height) {

		this.width = width;
		this.height = height;
		maze = new Cell[height][width];
		generateMaze();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isWallCell(Position pos) {
		return maze[pos.getRow()][pos.getCol()].isWall();
	}

	public boolean isVisibleCell(Position pos) {
		return maze[pos.getRow()][pos.getCol()].isVisible();
	}

	public void exploreCell(Position pos) {
		maze[pos.getRow()][pos.getCol()].exploreCell();
	}

	private void generateMaze() {
		// set up wall and space on the maze
		fillMazeWithAllWallCells();
		digThePath();
		randomRemoveSomeWalls();
		fixEdges();
		improveRightCol();
	}

	private void fillMazeWithAllWallCells() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				maze[row][col] = new Cell();
			}
		}
	}

	private void digThePath() {
		// set a previousPosition for initial purpose only
		Position previousPosition = new Position(0, 1);
		// make the first call of the recursive algorithm
		removeWallsByDFS(DIGGER_STARTS_AT, previousPosition);
	}

	private void removeWallsByDFS(Position currentPosition,
			Position previousPosition) {
		// remove walls recursively using modified DFS search algorithm
		// reference: http://en.wikipedia.org/wiki/Maze_generation_algorithm
		// some modifications on the base case are done on the original
		// algorithm to allow each two positions to be connected in more than
		// one way
		removeWallAt(currentPosition);

		ArrayList<Position> possibleNeighbours = getPossibleNeighbours(
				currentPosition, previousPosition);

		Collections.shuffle(possibleNeighbours);

		boolean hasAWallAround = false;
		for (Position pos : possibleNeighbours) {
			if (isWallCell(pos)) {
				hasAWallAround = true;
				removeWallAt(currentPosition.midPosBetween(pos));
				removeWallsByDFS(pos, currentPosition);
			}
		}

		if (!hasAWallAround) {
			Collections.shuffle(possibleNeighbours);
			removeWallAt(currentPosition.midPosBetween(possibleNeighbours
					.get(0)));
		}
	}

	private ArrayList<Position> getPossibleNeighbours(Position currentPosition,
			Position previousPosition) {
		// find a neighbor of the current position that the digger can move to
		// in the process of wall breaking, a neighbor of need to check two
		// steps
		// away in four directions
		ArrayList<Position> possibleNeighbours = new ArrayList<Position>();
		ArrayList<Position> twoStepsAway = new ArrayList<Position>();

		// add position two steps away in four directions
		twoStepsAway.add(currentPosition.shiftUp(STEP_NEED_TO_CHECK));
		twoStepsAway.add(currentPosition.shiftDown(STEP_NEED_TO_CHECK));
		twoStepsAway.add(currentPosition.shiftRight(STEP_NEED_TO_CHECK));
		twoStepsAway.add(currentPosition.shiftLeft(STEP_NEED_TO_CHECK));

		for (Position pos : twoStepsAway) {
			Position neighbour = pos;
			if (!(isOutOfRange(neighbour) || neighbour
					.isSameWith(previousPosition))) {
				possibleNeighbours.add(neighbour);
			}
		}

		return possibleNeighbours;
	}

	private boolean isOutOfRange(Position position) {
		// return true iff position on or out of the boundary
		int row = position.getRow();
		int col = position.getCol();
		return (row < 0 || row > height - 1 || col < 0 || col > width - 1);
	}

	private void removeWallAt(Position position) {
		maze[position.getRow()][position.getCol()].removeWall();
	}

	private void randomRemoveSomeWalls() {
		// To make the game little bit easier
		Random rand = new Random();

		for (int row = 0; row < height; row++) {
			Position tempPos = new Position(row, rand.nextInt(width));
			removeWallAt(tempPos);
		}

		for (int col = 0; col < width; col++) {
			Position tempPos = new Position(rand.nextInt(height), col);
			removeWallAt(tempPos);
		}
	}

	private void fixEdges() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (isOnEdge(row, col)) {
					maze[row][col].addWall();
					maze[row][col].exploreCell();
				}
			}
		}
	}

	private boolean isOnEdge(int row, int col) {
		boolean onEdge = row == 0 || col == 0 || row == height - 1
				|| col == width - 1;
		return onEdge;
	}

	private void improveRightCol() {
		// the algorithm employed may produce dead end on the right column, this
		// function will help eliminate those dead ends
		int col = width - 2;
		for (int row = 1; row < height - 1; row++) {
			if (maze[row][col].isWall() && maze[row][col - 1].isWall()) {
				maze[row][col - 1].removeWall();
			}
		}
	}
}