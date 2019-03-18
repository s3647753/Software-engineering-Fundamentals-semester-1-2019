package model;

/**
 * Class to encapsulate the coordinate locations on the game board.
 * 
 * TODO complete this class and change it from an abstract class to a concrete class
 * 
 * @author TBA
 *
 */

public abstract class Point {
	private int x;
	private int y;
	
	public abstract int getX();
	
	public abstract int setX(int x);
	
	public abstract int getY();
	
	public abstract void setY(int y);
	
	
	/**
	 * Overrides the Object.toString() method.
	 */
	@Override
	public abstract String toString();

}
