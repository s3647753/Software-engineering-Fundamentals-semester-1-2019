package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.Colr;
import enums.Type;

/**
 * Bishop subclass for Chess Like Game
 * April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public class Bishop extends AbstractPiece {
	
	private static List<Point> moveVectors = null;


	public Bishop(Type type, Colr color) {
		super(type, color);
		initMoveVector();
	}
	
	/**
	 * Initializes the vector of movements that the piece
	 * could make if unobstructed.
	 */
	private void initMoveVector() {
		moveVectors = new ArrayList<>();
		
		moveVectors.add(new Point(1, 1));
		moveVectors.add(new Point(2, 2));
		moveVectors.add(new Point(-1, 1));
		moveVectors.add(new Point(-2, 2));
		moveVectors.add(new Point(1, -1));
		moveVectors.add(new Point(2, -2));
		moveVectors.add(new Point(-1, -1));
		moveVectors.add(new Point(-2, -2));
	}

	@Override
	public List<Point> getPotentialMoves() {
		return Collections.unmodifiableList(moveVectors);
	}

}
