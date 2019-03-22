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
	 * Returns the height of the board.
	 * 
	 * @return The number of cells in each column.
	 */
	public int getHeight();
	
	
	/**
	 * Returns the width of the board.
	 * @return The number of cells in each row.
	 */
	public int getWidth();
	

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
	 * Returns a List of the pieces presently located at the nominated point on the board.
	 * If the cell is empty an empty List is returned.
	 * Note: Pieces and not the cell are returned.
	 * 
	 * @return A list of the pieces at the board location.
	 */
	public ArrayList<Piece> getPiecesAt(Point point);
	

	/**
	 * Overrides the Object.toString() method.
	 */
	@Override
	public abstract String toString();

}
