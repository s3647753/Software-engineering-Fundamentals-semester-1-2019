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

	
}
