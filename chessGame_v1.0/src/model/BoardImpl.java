package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import enums.Colr;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * Object representation of the game board
 * 
 * @author TBA
 *
 */
public class BoardImpl implements Board{
	private Cell[][] cells = new Cell[HEIGHT][WIDTH];

	public BoardImpl() {
		resetBoard();
	}
	
	@Override
	public boolean resetBoard() {
	   
	   // fill the board with empty cells
	   for (int row = 0; row < HEIGHT; row++) {
         for (int col = 0; col < WIDTH; col++) {
            // a white square
            if ((row + col) % 2 == 0) {
               cells[row][col] = new Cell(Colr.WHITE);
            }
            
            // a black square
            else {
               cells[row][col] = new Cell(Colr.BLACK);
            }
         }
      }
	   
	   try {
	      // add blacks pieces
         cells[0][0].addPiece(new Rook(Colr.BLACK));
         cells[0][1].addPiece(new Bishop(Colr.BLACK));
         cells[0][2].addPiece(new Knight(Colr.BLACK));
         cells[0][3].addPiece(new Knight(Colr.BLACK));
         cells[0][4].addPiece(new Bishop(Colr.BLACK));
         cells[0][5].addPiece(new Rook(Colr.BLACK));
         
         // add whites pieces
         cells[5][0].addPiece(new Rook(Colr.WHITE));
         cells[5][1].addPiece(new Bishop(Colr.WHITE));
         cells[5][2].addPiece(new Knight(Colr.WHITE));
         cells[5][3].addPiece(new Knight(Colr.WHITE));
         cells[5][4].addPiece(new Bishop(Colr.WHITE));
         cells[5][5].addPiece(new Rook(Colr.WHITE));
      } catch (IllegalMoveException e) {
         return false;
      }

	   return true;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT;
	}


	@Override
	public int getWidth() {
		return WIDTH;
	}


	@Override
	public ArrayList<Point> getLegalMoves(Point from) {
	   // the piece/merged piece that wishes to move
	   List<Piece> pieceList = cells[from.getRow()][from.getCol()].getPieces();
	   // the potential moves
	   List<Point> moves = cells[from.getRow()][from.getCol()].getPotentialMoves();
	   

	   Iterator<Point> iter = moves.iterator();
	   Point to;
	   boolean legal = true;
	   
	   while(iter.hasNext()) {
	      legal = true;
	      
	      // the point the piece wishes to move to
	      to = iter.next();
	      
	      // outside of the board
	      if(to.getCol() < 0 || to.getCol() >= WIDTH)
	         legal = false;
	      else if(to.getRow() < 0 || to.getRow() >= HEIGHT)
            legal = false;
	      
	      // case of single piece moving
	      else if(pieceList.size() == 1 &&
	         !cells[to.getRow()][to.getCol()].isLegal(pieceList.get(0))) {
	         legal = false;
	      }
	      
	      // case of merged piece moving
	      else if(pieceList.size() == 2 && !canMoveMergedPiece(from, to)) {
	         legal = false;
	      }
	      
	      // remove the illegal move
	      if(!legal) {
	         iter.remove();
	      }
	    
	   }

      return null;
	}


	@Override
	public void setPiece(Piece piece, Point point) throws IllegalMoveException {
		cells[point.getRow()][point.getCol()].addPiece(piece);
	}


	@Override
	public List<Piece> getPiecesAt(Point point) {
	   return cells[point.getRow()][point.getCol()].getPieces();
	}


	@Override
	public void moveSinglePiece(Piece piece, Point from, Point to) 
	      throws IllegalMoveException, PieceNotFoundException {
	   
	   cells[from.getRow()][from.getCol()].removePiece(piece);
	   cells[to.getRow()][to.getCol()].addPiece(piece);
	}
	

	@Override
	public void moveMergedPiece(Point from, Point to) 
	      throws IllegalMoveException, PieceNotFoundException {
	   
	   // the move is illegal
	   if(!canMoveMergedPiece(from, to)) {
	      throw new IllegalMoveException();
	   }
	   
		for(Piece piece: cells[to.getRow()][to.getCol()].getPieces()) {
		   moveSinglePiece(piece, from, to);
		}
	}
	
	@Override
	public boolean canMoveMergedPiece(Point from, Point to) {
	   // lists of pieces in the 'from' and 'to' points
      List<Piece> fromList = cells[from.getRow()][from.getCol()].getPieces();
      List<Piece> toList = cells[to.getRow()][to.getCol()].getPieces();
      
      // case where the move is legal
      if(toList.size() == 0 || toList.get(0).getColor() != fromList.get(0).getColor()) {
         return true;
      }
      
      return false;
	}
	
	@Override
	public String getCode(Point cell) {
	   return cells[cell.getRow()][cell.getCol()].getCode();
	}
	

	@Override
	public String toString() {
	   StringJoiner boardStr = new StringJoiner("", "", "\n");

	   
	   for(int row = 0; row < HEIGHT; row++) {
	      for(int col = 0; col < WIDTH; col++) {
	         boardStr.add(String.format("%6S", cells[row][col].getCode()));
	      }
	      boardStr.add("\n");
	   }

		return boardStr.toString();
	}

}
