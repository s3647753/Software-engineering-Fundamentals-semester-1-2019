package model;

/**
 * Exception for when a piece cannot be found
 * 
 * @author Bernard O'Meara
 *
 */

@SuppressWarnings("serial")
public class PieceNotFoundException extends Exception {

	public PieceNotFoundException() {
		this("Piece not found.");
	}

	public PieceNotFoundException(String msg) {
		super(msg);
	}


}
