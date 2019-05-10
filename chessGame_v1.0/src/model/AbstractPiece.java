package model;

import java.util.List;

import enums.Colr;
import enums.Type;
import model_Interfaces.Piece;

/**
 * Abstract Piece class for Chess Like Game April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public abstract class AbstractPiece implements Piece {
	private List<Point> moveVectors = null;
	private Colr color;
	private Type type;

	public AbstractPiece(Type type, Colr color, List<Point> vectors) {
		this.type = type;
		this.color = color;
		moveVectors = vectors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model_Interfaces.Piece#getColor()
	 */
	@Override
	public Colr getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model_Interfaces.Piece#getType()
	 */
	@Override
	public Type getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model_Interfaces.Piece#getPotentialMoves()
	 */
	@Override
	public List<Point> getPotentialMoves() {
		return moveVectors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model_Interfaces.Piece#getCode()
	 */
	@Override
	public String getCode() {
		return String.format("%s%s", color.toString().charAt(0),
				type.toString().charAt(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Piece that) {
		return this.toString().compareTo(that.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object that) {
		if (this.getClass() != that.getClass()) {
			return false;
		}

		return this.toString().equals(((Piece) that).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Piece(%s, %s)", color.toString(), type.toString());
	}
}
