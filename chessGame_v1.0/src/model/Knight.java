package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.javafx.collections.UnmodifiableListSet;

import enums.Colr;
import enums.Type;
import model_Interfaces.Piece;

/**
 * Knight subclass for Chess Like Game
 * April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public class Knight extends AbstractPiece {;

	private static List<Point> moveVectors = null;


	public Knight(Colr color) {
		super(Type.KNIGHT, color);
		initMoveVector();
	}
	
	/**
	 * Initializes the vector of movements that the piece
	 * could make if unobstructed.
	 */
	private void initMoveVector() {
		moveVectors = new ArrayList<>();
		
		moveVectors.add(new Point(2, 1));
		moveVectors.add(new Point(2, -1));
		moveVectors.add(new Point(-2, 1));
		moveVectors.add(new Point(-2, -1));
		moveVectors.add(new Point(1, 2));
		moveVectors.add(new Point(1, -2));
		moveVectors.add(new Point(-1, 2));
		moveVectors.add(new Point(-1, -2));
	}

	@Override
	public List<Point> getPotentialMoves() {
		return Collections.unmodifiableList(moveVectors);
	}

}
