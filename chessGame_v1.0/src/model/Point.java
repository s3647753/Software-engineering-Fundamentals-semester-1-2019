package model;

/**
 * Class to encapsulate the coordinate locations on the game board.
 * The point class is immutable, hence it has no settters.
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
	 * TODO
	 * @return
	 */
	public int getX() {
		return x;
	}

	
	/**
	 * TODO
	 * @return
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
	public Point add(Point point1, Point point2) {
		// TODO
		return null;
	}
	

	/**
	 * Overrides the Object.toString() method.
	 */
	@Override
	public String toString() {
		// TODO
		return null;
	}


	/**
	 * TODO
	 */
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}


	/**
	 * TODO
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
