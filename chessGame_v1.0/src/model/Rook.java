package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.Colr;
import enums.Type;
import model_Interfaces.Piece;

/**
 * Rook subclass for Chess Like Game
 * April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */

public class Rook extends AbstractPiece {
	
	private List<Point> moveVectors = null;


	public Rook(Colr color) {
		super(Type.ROOK, color);
		initMoveVector();
	}
	
	/**
	 * Initializes the vector of movements that the piece
	 * could make if unobstructed.
	 */
	private void initMoveVector() {
		moveVectors = new ArrayList<>();
		
		moveVectors.add(new Point(1, 0));
		moveVectors.add(new Point(2, 0));
		moveVectors.add(new Point(-1, 0));
		moveVectors.add(new Point(-2, 0));
		moveVectors.add(new Point(0, 1));
		moveVectors.add(new Point(0, 2));
		moveVectors.add(new Point(0, -1));
		moveVectors.add(new Point(0, -2));
	}

	@Override
	public List<Point> getPotentialMoves() {
		return Collections.unmodifiableList(moveVectors);
	}

}
