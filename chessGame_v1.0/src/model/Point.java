package model;

/**
 * Class to encapsulate the coordinate locations on the game board.
 * The point class is immutable, hence it has no setters.
 * 
 * @author Bernard O'Meara
 *
 */

public class Point {
	private final int row;
	private final int col;
	
	public Point(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	
	/**
	 * Returns the X value of this point.
	 * @return The X value.
	 */
	public int getRow() {
		return row;
	}

	
	/**
	 * Returns the Y value of this point.
	 * @return The Y value.
	 */
	public int getCol() {
		return col;
	}


	/**
	 * Calculates the sum of like fields and returns a new Point of the result. 
	 * Negative values are permitted. <br>
	 * e.g., Point(2, 7) + Point(2, -1) returns Point(4, 6)
	 * 
	 * @param that The first operand.
	 * @return The vector sum of the this Point and the passed point.
	 */
	public Point add(Point that) {
		
		return new Point(this.getRow() + that.getRow(), this.getCol() + that.getCol());
	}
	

	/*
	 * Returns a human friendly string representation of the Point.
	 */
	@Override
	public String toString() {
		return String.format("Point(%d, %d)", getRow(), getCol());
	}


	/*
	 * Compares this Point Object with another Point Object for equality,
	 * compares both X values are equal and Y values are equal.
	 * 
	 * @return true if this point and other point are equal, else false
	 */
	@Override
	public boolean equals(Object that) {
		if(this.getClass() != that.getClass()) {
			return false;
		}
		
		Point point2 = (Point)that;

		if(getRow() == point2.getRow() && getCol() == point2.getCol()) {
			return true;
		}
		else {
			return false;
		}
	}


	/*
	 * Returns the hashCode for this Point.
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
