/**
 * Cell class is for the Maze to tell if is a wall cell or not
 * And also record whether the cell is already explored or not
 */

package ca.MazeGame.objects;

public class Cell {

	private boolean isWall;
	private boolean isVisible;

	public Cell() {
		isWall = true;
		isVisible = false;
	}

	public boolean isWall() {
		return isWall;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void removeWall() {
		isWall = false;
	}

	public void addWall() {
		isWall = true;
	}

	public void exploreCell() {
		isVisible = true;
	}
}
