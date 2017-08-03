/**
 * Position can create a position based on row and column
 * Can check if is the same position of another one
 * Can return a position which is different from current position in four directions by given step
 */

package ca.MazeGame.supporters;

public class Position {

	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Position(Position pos) {
		this.row = pos.getRow();
		this.col = pos.getCol();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isSameWith(Position pos) {
		return (row == pos.getRow() && col == pos.getCol());
	}

	public Position shiftUp(int step) {
		Position temp = new Position(row - step, col);
		return temp;
	}

	public Position shiftDown(int step) {
		Position temp = new Position(row + step, col);
		return temp;
	}

	public Position shiftLeft(int step) {
		Position temp = new Position(row, col - step);
		return temp;
	}

	public Position shiftRight(int step) {
		Position temp = new Position(row, col + step);
		return temp;
	}

	// find midpoint between current and another position
	public Position midPosBetween(Position pos) {
		int midRow = (row + pos.getRow()) / 2;
		int midCol = (col + pos.getCol()) / 2;
		Position midPos = new Position(midRow, midCol);
		return midPos;
	}

}
