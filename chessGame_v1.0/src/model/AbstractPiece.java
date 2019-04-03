package model;

import java.util.List;

import enums.Colr;
import enums.Type;
import model_Interfaces.Piece;

/**
 * Abstract Piece class for Chess Like Game
 * April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public abstract class AbstractPiece implements Piece {
	private Colr color;
	private Type type;

	public AbstractPiece(Type type, Colr color) {
		this.type = type;
		this.color = color;
	}

	@Override
	public Colr getColor() {
		return color;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public abstract List<Point> getPotentialMoves();
	
	@Override
	public String getCode() {
		return String.format("%s%s",
				color.toString().toLowerCase().charAt(0),
				type.toString().toLowerCase().charAt(0));
	}
	
	@Override
	public int compareTo(Piece that) {
		return this.toString().compareTo(that.toString());
	}
	
	@Override
	public boolean equals(Object that) {
		if(this.getClass() != that.getClass()) {
			return false;
		}

		return this.toString().equals(((Piece)that).toString());
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		return String.format("Piece(%s, %s)",
				color.toString(),
				type.toString());
	}
}
