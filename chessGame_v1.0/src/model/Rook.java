package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.Colr;
import enums.Type;

/**
 * Rook subclass for Chess Like Game
 * April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */

public class Rook extends AbstractPiece {
	
	private static List<Point> moveVectors = null;


	public Rook(Type type, Colr color) {
		super(type, color);
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


//	// TODO get rid of for final release
//	public static void main(String[] args) {
//		Piece p = new Rook(Type.ROOK, Colr.BLACK);
//		System.out.println(p.getType());
//		System.out.println(p.getColor());
//		System.out.println(p);
//		System.out.println(p.getCode());
//		
//		for(Point pt: p.getPotentialMoves())
//			System.out.println(pt);
//	}


}
