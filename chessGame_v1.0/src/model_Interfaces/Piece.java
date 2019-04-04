package model_Interfaces;

import java.util.List;

import enums.Colr;
import enums.Type;
import model.Point;


/**
 * Interface for the Chess Pieces.
 * Note: Pieces are immutable.
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public interface Piece extends Comparable<Piece> {

	/**
	 * Returns the enum value for the piece color.
	 * 
	 * @return The color of the piece.
	 */
	public Colr getColor();

	/**
	 * Returns the enum value for the type.
	 * 
	 * @return The type of the piece.
	 */
	public Type getType();

	/**
	 * Returns the potential moves the piece could possibly make with no regard to
	 * the bounds of the board. May be negative or out of the board. 
	 * 
	 * @return an UnmodifiableList of potential moves.
	 * 
	 */
	public List<Point> getPotentialMoves();
	
	
	/**
	 * Builds a code for the piece suitable for text display or
	 * building filenames for piece images.
	 * <color, type> e.g. blackRook = "BR", whiteKnight = "WK"
	 * 
	 * @return A two char String of the pieces code.
	 */
	public String getCode();

}
