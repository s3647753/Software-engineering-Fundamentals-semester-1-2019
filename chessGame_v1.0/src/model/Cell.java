package model;

import java.util.ArrayList;

import model_Interfaces.Piece;

public class Cell {
	
	// the pieces in the cell
	private ArrayList<Piece> pieces;
	
	/**
	 * Returns the pieces in the cell
	 * 
	 * @return The pieces in the cell
	 */
	public ArrayList<Piece> getPieces() {
		// TODO
		return null;
	}

	
	/**
	 * Adds a single piece to the cell.
	 * if there is a players piece in the cell and a merge is legal then the pair are merged.
	 * If there is an opponents piece in the cell it will be removed and the piece inserted.
	 * If the piece cannot be placed an IllegalMoveException is thrown.
	 * 
	 * @param piece The piece to add to the cell.
	 * @throws IllegalMoveException
	 */
	public void addPiece(Piece piece) throws IllegalMoveException {
		// TODO
	}
	
	/**
	 * Removes the nominated piece from the cell and returns a reference to the removed piece.
	 * 
	 * @param piece The piece to be removed.
	 * @return A reference to the removed piece;
	 */
	public Piece removePiece(Piece piece) {
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
		// TODO
		return super.equals(arg0);
	}


	/**
	 * TODO
	 */
	@Override
	public int hashCode() {
		// TODO
		return super.hashCode();
	}

	
}
