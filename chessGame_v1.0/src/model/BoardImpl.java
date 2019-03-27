package model;

import java.util.ArrayList;

import enums.Colr;
import enums.Type;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * Object representation of the game board
 * 
 * @author TBA
 *
 */
public class BoardImpl implements Board{
	
	// TODO example only (the magic numbers have to go value comes from the constructor)
	private Cell[][] cells = new Cell[HEIGHT][WIDTH];
	
	
	public BoardImpl() {
		// TODO
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ArrayList<Point> getLegalMoves(Point position) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean setPiece(Piece piece, Point position) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Piece> getPiecesAt(Point point) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void moveSinglePiece(Piece piece, Point from, Point to) throws IllegalMoveException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void moveMergedPiece(Point from, Point to) throws IllegalMoveException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public String toString() {
		// TODO
		return null;
	}


	
}
