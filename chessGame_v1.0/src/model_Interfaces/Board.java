package model_Interfaces;

import java.util.ArrayList;

import enums.Colr;
import enums.Type;
import model.IllegalMoveException;
import model.Point;

/**
 * Interface for the Chess Board
 * 
 * @author Bernard O'Meaa + TBA
 *
 */

public interface Board {

	/**
	 * Returns a list of the Pieces that are in the cell.
	 * Empty list if the cell is empty.
	 * 
	 * @param position
	 * @return The pieces in the cell
	 */
	public ArrayList<Piece> getPieces(Point position);
	

	/**
	 * Sets a piece to the required position. 
	 * If there is an opponents piece in the cell, the opponents piece will be removed.
	 * If there is a players piece in the cell and a merge is legal the pieces will be merged.
	 * If the piece cannot be set to the position an exception is thrown.
	 * 
	 * @param piece
	 * @param position
	 * @return true if the operation was successful, else false
	 * 
	 */
	public boolean setPiece(Piece piece, Point position) throws IllegalMoveException;
	
	

	/**
	 * Moves a single piece from one point to another point.
	 * If it is a merged pair only the nominated piece will be moved.
	 * If there is an opponents piece in the cell, the opponents piece will be removed.
	 * If there is a players piece on the to-point and a merge is legal they will be merged
	 * If the move is illegal an IllegalMoveException is thrown
	 * 
	 * @param piece The piece to be moved
	 * @param from The point the piece is moved from
	 * @param to The point the piece is moved to
	 * @throws IllegalMoveException
	 * 
	 */
	public void moveSinglePiece(Piece piece, Point from, Point to) throws IllegalMoveException;
	
	
	/**
	 * Moves a pair of merged pieces from one point to another point.
	 * If there is an opponents piece in the cell, the opponents piece will be removed.
	 * If the move is illegal an IllegalMoveException is thrown
	 * 
	 * @param from The point the merged pair are moved from
	 * @param to The point the merged pair are moved to
	 */
	public void moveMergedPiece(Point from, Point to) throws IllegalMoveException;
	

	/**
	 * Returns all of the legal moves for the piece at the nominated position
	 * 
	 * @param position
	 *            The position of the piece
	 * @return All legal moves for the piece at the position
	 */
	public ArrayList<Point> getLegalMoves(Point position);
	
	
	/**
	 * Returns a 2D array of the pieces on the board
	 * @return The board pieces.
	 */
	public Piece[][] getPieces();
	
	
	/**
	 * Returns the background color of the board cell/position.
	 * Position(0, 0) should be WHITE.
	 *  
	 * @return The cell color
	 */
	public Colr getCellColor(Point position);
	

	/**
	 * Overrides the Object.toString() method.
	 */
	@Override
	public abstract String toString();

}
