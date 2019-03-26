package model;

/**
 * Class to encapsulate the coordinate locations on the game board.
 * The point class is immutable, hence it has no setters.
 * 
 * @author Bernard O'Meara
 *
 */

public class Point {
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	
	/**
	 * Returns the X value of this point.
	 * @return The X value.
	 */
	public int getX() {
		return x;
	}

	
	/**
	 * Returns the Y value of this point.
	 * @return The Y value.
	 */
	public int getY() {
		return y;
	}


	/**
	 * Calculates the sum of like fields and returns a new Point of the result. 
	 * Negative values are permitted. <br>
	 * e.g., Point(2, 5) + Point(2, -1) returns Point(4, 4)
	 * 
	 * @param point1 The first operand.
	 * @param point2 The second operand.
	 * @return The vector sum of the two operands.
	 */
	public Point add(Point that) {
		
		return new Point(this.getX() + that.getX(), this.getY() + that.getY());
	}
	

	/*
	 * Returns a human friendly string representation of the Point.
	 */
	@Override
	public String toString() {
		return String.format("Point(%d, %d)", getX(), getY());
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

		if(getX() == point2.getX() && getY() == point2.getY()) {
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
