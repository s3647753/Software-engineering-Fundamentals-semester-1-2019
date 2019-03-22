package model_Interfaces;

import java.util.ArrayList;

import enums.Colr;
import enums.Type;
import model.Point;

/**
 * Interface for the Chess Pieces
 * Note: Pieces are immutable so they should be made in the constructor 
 * and contain no setters.
 * 
 * @author Bernard O'Meara + TBA
 *
 */

public interface Piece {

	public Colr getColour();

	public Type getType();
	
	
	

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
	 * TODO
	 * @return
	 */
	@Override
	public String toString();

	/**
	 * TODO
	 * @param obj
	 * @return
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * TODO
	 * @return
	 */
	@Override
	int hashCode();


}
