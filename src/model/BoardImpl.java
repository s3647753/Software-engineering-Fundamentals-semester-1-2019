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
	private Cell[][] cells = new Cell[8][8];
	
	
	public BoardImpl(int width, int height) {
		// TODO
	}


	@Override
	public ArrayList<Point> getLegalMoves(Point position) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Colr getCellColor(Point position) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean setPiece(Piece piece, Point position) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Piece[][] getPieces() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<Piece> getPieces(Point position) {
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