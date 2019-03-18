package model_Interfaces;

import java.util.ArrayList;

import enums.Colr;
import enums.Type;
import model.Point;

/**
 * Interface for the Chess Pieces
 * 
 * @author Bernard O'Meara + TBA
 *
 */

public interface Piece {

	public Colr getColour();

	public Type getType();

	public Point getPosition();

	public Point setPosition(Point Position);
	

	/**
	 * Returns the potential moves the piece could possibly make with no regard to
	 * the bounds of the board. May be negative or out of the board. 
	 * Board will determine if it is a valid move.
	 * 
	 * @return An ArrayList of potential moves.
	 * 
	 */
	public ArrayList<Point> getPotentialMoves();
	
	/**
	 * Overrides the Object.toString() method.
	 * Should be implemented at the subclass level.
	 */
	@Override
	public String toString();


}
