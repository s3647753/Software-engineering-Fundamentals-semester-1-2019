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
 * @author Bernard O'Meara
 * 
 *         TODO get rid of direct references to the array use the getCell()
 *         method
 *
 */
public class BoardImpl implements Board {
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
         setPiece(new Rook(Colr.BLACK), new Point(0, 0));
         setPiece(new Bishop(Colr.BLACK), new Point(0, 1));
         setPiece(new Knight(Colr.BLACK), new Point(0, 2));
         setPiece(new Knight(Colr.BLACK), new Point(0, 3));
         setPiece(new Bishop(Colr.BLACK), new Point(0, 4));
         setPiece(new Rook(Colr.BLACK), new Point(0, 5));

         // add whites pieces
         setPiece(new Rook(Colr.WHITE), new Point(5, 0));
         setPiece(new Bishop(Colr.WHITE), new Point(5, 1));
         setPiece(new Knight(Colr.WHITE), new Point(5, 2));
         setPiece(new Knight(Colr.WHITE), new Point(5, 3));
         setPiece(new Bishop(Colr.WHITE), new Point(5, 4));
         setPiece(new Rook(Colr.WHITE), new Point(5, 5));

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
   public boolean isMerged(Point point) {
      return getCell(point).isMerged();
   }


   @Override
   public List<Point> getLegalMoves(Point from) {
      List<Piece> pieceList = getCell(from).getPieces();

      // the potential moves
      List<Point> rawMoves = getCell(from).getPotentialMoves();
      List<Point> moves = new ArrayList<>();

      // add the potential moves to the present location into a mutable list
      for (Point rawMove : rawMoves) {
         moves.add(rawMove.add(from));
      }

      Iterator<Point> iter = moves.iterator();
      Point to;
      boolean legal = true;

      while (iter.hasNext()) {
         legal = true;

         // the point the piece wishes to move to
         to = iter.next();

         // outside of the board width
         if (to.getCol() < 0 || to.getCol() >= WIDTH)
            legal = false;

         // outside of the board height
         else if (to.getRow() < 0 || to.getRow() >= HEIGHT)
            legal = false;

         // case of single piece moving
         else if (pieceList.size() == 1
               && !cells[to.getRow()][to.getCol()].isLegal(pieceList.get(0))) {
            legal = false;
         }

         // case of merged piece moving
         else if (pieceList.size() == 2 && !canMoveMergedPiece(from, to)) {
            legal = false;
         }

         // case where a move is obstructed by another piece
         else if (isObstructed(from, to)) {
            legal = false;
         }

         // remove the illegal move
         if (!legal) {
            iter.remove();
         }

      }

      return moves;
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
   public int movePiece(Point from, Point to)
         throws PieceNotFoundException, IllegalMoveException {

      int numPiecesTaken = 0;
      boolean mergedPiece = getCell(from).isMerged();
      List<Piece> pieces = getPiecesAt(from);
      
      // only move to legal places
      if (!getLegalMoves(from).contains(to)) {
         throw new IllegalMoveException("Illegal Move");
      }

      // number of pieces taken
      if (!areSameColor(from, to)) {
         numPiecesTaken = getPiecesAt(to).size();
      }
      
      // single piece
      if(pieces.size() == 1) {
         moveAPiece(pieces.get(0), from, to);
      }
      
      // merged pieces
      else if(mergedPiece) {
         moveAPiece(pieces.get(1), from, to);
         moveAPiece(pieces.get(0), from, to);
      }
      
      // split pieces
      else {
         Piece pieceToMove = null;
         for(Piece piece: pieces) {
            if(scalarAddition(piece.getPotentialMoves(), from).contains(to)) {
               pieceToMove = piece;
            }
         }
         moveAPiece(pieceToMove, from, to);
      }

      return numPiecesTaken;
   }
   
   // helper to move a piece
   private void moveAPiece(Piece piece, Point from, Point to) 
         throws IllegalMoveException, PieceNotFoundException {
      
      getCell(from).removePiece(piece);
      getCell(to).addPiece(piece);
   }
   


   @Override // TODO remove this method
   public int moveSinglePiece(Piece piece, Point from, Point to)
         throws PieceNotFoundException, IllegalMoveException {
      
      return movePiece(from, to);

//      // The number of enemy pieces taken this move
//      int numTaken = 0;
//
//      if (!areSameColor(from, to)) {
//         numTaken = getPiecesAt(to).size();
//      }
//
//      // ensure from point contains the piece
//      if (!getPiecesAt(from).contains(piece)) {
//         throw new PieceNotFoundException("Could not find the piece in the from cell");
//      }
//
//      // carry out the move
//      getCell(from).removePiece(piece);
//      getCell(to).addPiece(piece);
//
//      return numTaken;
   }


   @Override // TODO remove this method
   public int moveMergedPiece(Point from, Point to)
         throws IllegalMoveException, PieceNotFoundException {
      
      return movePiece(from, to);

//      List<Piece> pieces = getPiecesAt(from);
//
//      // the piece must be a merged piece
//      if (pieces.size() != 2) {
//         throw new PieceNotFoundException("Piece is not a merged piece");
//      }
//
//      // only move to legal places
//      if (!getLegalMoves(from).contains(to)) {
//         throw new IllegalMoveException();
//      }
//
//      // The number of enemy pieces taken this move
//      int numTaken = 0;
//
//      if (!areSameColor(from, to)) {
//         numTaken = getPiecesAt(to).size();
//      }
//
//      // the move is illegal
//      if (!canMoveMergedPiece(from, to)) {
//         throw new IllegalMoveException();
//      }
//
//      // move the pieces starting from the tail of the list
//      moveSinglePiece(pieces.get(1), from, to);
//      moveSinglePiece(pieces.get(0), from, to);
//
//      return numTaken;
   }


   @Override
   public boolean canMoveMergedPiece(Point from, Point to) {

      // lists of pieces in the 'from' and 'to' points
      List<Piece> fromList = getCell(from).getPieces();
      List<Piece> toList = getCell(to).getPieces();

      // case where the move is legal
      if (toList.size() == 0 || toList.get(0).getColor() != fromList.get(0).getColor()) {
         return true;
      }

      return false;
   }


   @Override
   public boolean areSameColor(Point point1, Point point2) {

      List<Piece> list1 = getPiecesAt(point1);
      List<Piece> list2 = getPiecesAt(point2);

      // check that both cells contain a piece and compare color
      if (list1.size() != 0 && list2.size() != 0
            && list1.get(0).getColor().equals(list2.get(0).getColor())) {
         return true;
      }

      return false;
   }


   @Override
   public boolean split(Point point) {
      return getCell(point).split();
   }


   @Override
   public String getCode(Point cell) {
      return cells[cell.getRow()][cell.getCol()].getCode();
   }


   @Override
   public String toString() {
      StringJoiner boardStr = new StringJoiner("", "", "\n");

      for (int row = 0; row < HEIGHT; row++) {
         for (int col = 0; col < WIDTH; col++) {
            boardStr.add(String.format("%6S", cells[row][col].getCode()));
         }
         boardStr.add("\n");
      }

      return boardStr.toString();
   }


   @Override
   public boolean isObstructed(Point from, Point to) {

      int deltaR = Math.abs(from.getRow() - to.getRow());
      int deltaC = Math.abs(from.getCol() - to.getCol());

      // case where the move cannot be obstructed
      if (deltaR == 1 || deltaC == 1) {
         return false;
      }

      // find the mid point
      int midR = (from.getRow() + to.getRow()) / 2;
      int midC = (from.getCol() + to.getCol()) / 2;

      // determine if the mid point is obstructed
      if (cells[midR][midC].getPieces().size() > 0) {
         return true;
      }

      return false;
   }

   @Override
   public Cell getCell(Point cell) {

      return cells[cell.getRow()][cell.getCol()];
   }
   
   
   /*
    * helper to move
    */
   private List<Point> scalarAddition(List<Point> list, Point scalar) {
      List<Point> newList = new ArrayList<>();
      for(Point point: list) {
         newList.add(point.add(scalar));
      }
      return newList;
   }


   @Override
   public boolean allPiecesGone(Colr color) {
      List<Piece> pieces;
      
      for(int row = 0; row < HEIGHT; row++) {
         for(int col = 0; col < WIDTH; col++) {
            pieces = cells[row][col].getPieces();
            if(pieces.size() > 0 && pieces.get(0).getColor().equals(color)) {
               return false;
            }
         }
      }
      return true;
   }

}
