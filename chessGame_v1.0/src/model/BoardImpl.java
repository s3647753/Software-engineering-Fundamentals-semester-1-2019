package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import enums.Colr;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * Representation of the game board. Handles board objects and related logic.
 * 
 * @author Bernard O'Meara
 *
 */
public class BoardImpl implements Board {
   private Map<Point, Cell> cells = new HashMap<>();


   public BoardImpl() {
      resetBoard();
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#resetBoard()
    */
   @Override
   public boolean resetBoard() {
      Colr color;

      // fill the board with empty cells
      for (int row = 0; row < HEIGHT; row++) {
         for (int col = 0; col < WIDTH; col++) {

            color = ((row + col) % 2 == 0) ? Colr.WHITE : Colr.BLACK;
            cells.put(new Point(row, col), new Cell(color));
         }
      }

      try {

         // add blacks pieces
         getCell(0, 0).addPiece(new Rook(Colr.BLACK));
         getCell(0, 1).addPiece(new Bishop(Colr.BLACK));
         getCell(0, 2).addPiece(new Knight(Colr.BLACK));
         getCell(0, 3).addPiece(new Knight(Colr.BLACK));
         getCell(0, 4).addPiece(new Bishop(Colr.BLACK));
         getCell(0, 5).addPiece(new Rook(Colr.BLACK));

         // add whites pieces
         getCell(5, 0).addPiece(new Rook(Colr.WHITE));
         getCell(5, 1).addPiece(new Bishop(Colr.WHITE));
         getCell(5, 2).addPiece(new Knight(Colr.WHITE));
         getCell(5, 3).addPiece(new Knight(Colr.WHITE));
         getCell(5, 4).addPiece(new Bishop(Colr.WHITE));
         getCell(5, 5).addPiece(new Rook(Colr.WHITE));

      } catch (IllegalMoveException e) {
         return false;
      }

      return true;
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#isMerged(model.Point)
    */
   @Override
   public boolean isMerged(Point point) {
      return getCell(point).isMerged();
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#getLegalMoves(model.Point)
    */
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
               && !getCell(to).isLegal(pieceList.get(0))) {
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


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#getPiecesAt(model.Point)
    */
   @Override
   public List<Piece> getPiecesAt(Point point) {
      return getCell(point).getPieces();

   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#movePiece(model.Point, model.Point)
    */
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
      if (pieces.size() == 1) {
         moveAPiece(pieces.get(0), from, to);
      }

      // merged pieces
      else if (mergedPiece) {
         moveAPiece(pieces.get(1), from, to);
         moveAPiece(pieces.get(0), from, to);
      }

      // split pieces
      else {
         Piece pieceToMove = null;
         for (Piece piece : pieces) {
            if (scalarAddition(piece.getPotentialMoves(), from).contains(to)) {
               pieceToMove = piece;
            }
         }
         moveAPiece(pieceToMove, from, to);
      }

      return numPiecesTaken;
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#allPiecesGone(enums.Colr)
    */
   @Override
   public boolean allPiecesGone(Colr color) {
      List<Piece> pieces;

      for (int row = 0; row < HEIGHT; row++) {
         for (int col = 0; col < WIDTH; col++) {
            pieces = getCell(row, col).getPieces();
            if (pieces.size() > 0 && pieces.get(0).getColor().equals(color)) {
               return false;
            }
         }
      }
      return true;
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#isMergedPiece(model.Point)
    */
   @Override
   public boolean isMergedPiece(Point point) {
      return getCell(point).isMerged();
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#split(model.Point)
    */
   @Override
   public boolean split(Point point) {
      return getCell(point).split();
   }


   /*
    * (non-Javadoc)
    * 
    * @see model_Interfaces.Board#getCode(model.Point)
    */
   @Override
   public String getCode(Point cell) {
      return getCell(cell).getCode();
   }


   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringJoiner boardStr = new StringJoiner("", "", "\n");

      for (int row = 0; row < HEIGHT; row++) {
         for (int col = 0; col < WIDTH; col++) {
            boardStr.add(String.format("%6S", getCell(row, col).getCode()));
         }
         boardStr.add("\n");
      }

      return boardStr.toString();
   }


   /**
    * Helper that moves a single piece from one point to another point.
    * 
    * @param piece
    *           The piece to move.
    * @param from
    *           The cell the piece is being moved from.
    * @param to
    *           The cell the piece is being moved to.
    * @throws IllegalMoveException
    *            Thrown if the move is illegal.
    * @throws PieceNotFoundException
    *            Thrown if the piece is not found.
    */
   private void moveAPiece(Piece piece, Point from, Point to)
         throws IllegalMoveException, PieceNotFoundException {

      getCell(from).removePiece(piece);
      getCell(to).addPiece(piece);
   }


   /**
    * Helper that determines if a moving a merged piece is legal.
    * 
    * @param from
    *           The point the piece is being moved from.
    * @param to
    *           The point the piece is being moved to.
    * @return True if the proposed move is legal, else false.
    */
   private boolean canMoveMergedPiece(Point from, Point to) {
      List<Piece> fromList = getCell(from).getPieces();
      List<Piece> toList = getCell(to).getPieces();
      boolean isLegal = false;

      // case where the move is legal
      if (toList.size() == 0
            || toList.get(0).getColor() != fromList.get(0).getColor()) {
         isLegal = true;
      }

      return isLegal;
   }


   /**
    * Helper that determines if the pieces at the two locations are the same
    * color.
    * 
    * @param point1
    *           The first point.
    * @param point2
    *           The second point.
    * @return True if both points contain piece/s and both are the came color,
    *         else false.
    */
   private boolean areSameColor(Point point1, Point point2) {

      List<Piece> list1 = getPiecesAt(point1);
      List<Piece> list2 = getPiecesAt(point2);

      // check that both cells contain a piece and compare color
      if (list1.size() != 0 && list2.size() != 0
            && list1.get(0).getColor().equals(list2.get(0).getColor())) {
         return true;
      }

      return false;
   }


   /**
    * Helper that determines if a move by a piece is obstructed by another piece
    * 
    * @param from
    *           The point the piece is moving from.
    * @param to
    *           The point the piece is moving to.
    * @return True if the move is obstructed, else false
    */
   private boolean isObstructed(Point from, Point to) {
      boolean obstructed = false;

      int deltaR = Math.abs(from.getRow() - to.getRow());
      int deltaC = Math.abs(from.getCol() - to.getCol());

      if (deltaR != 1 && deltaC != 1) {
         
         // find the mid point
         int midR = (from.getRow() + to.getRow()) / 2;
         int midC = (from.getCol() + to.getCol()) / 2;

         if (getCell(midR, midC).getPieces().size() > 0) {
            obstructed = true;
         }

      }

      return obstructed;

      // int deltaR = Math.abs(from.getRow() - to.getRow());
      // int deltaC = Math.abs(from.getCol() - to.getCol());
      //
      // // case where the move cannot be obstructed
      // if (deltaR == 1 || deltaC == 1) {
      // return false;
      // }
      //
      // // find the mid point
      // int midR = (from.getRow() + to.getRow()) / 2;
      // int midC = (from.getCol() + to.getCol()) / 2;
      //
      // if (getCell(midR, midC).getPieces().size() > 0) {
      // return true;
      // }
      //
      // return false;
   }


   /**
    * Helper that returns the cell located at the given point.
    * 
    * @param point
    *           The point that the cell is requested from.
    * @return The cell at the given point.
    */
   private Cell getCell(Point point) {
      return cells.get(point);
   }


   /**
    * Helper to get a cell from a specified location using coordinates referenced
    * from the top LH corner.
    * 
    * @param row
    *           The cells row.
    * @param col
    *           The cells column
    * @return The cell at the requested location
    */
   private Cell getCell(int row, int col) {
      return getCell(new Point(row, col));
   }


   /**
    * Helper that performs scalar addition of a single point with a list of
    * points.
    * 
    * @param list
    *           The list of points.
    * @param scalar
    *           The single point to be added to each of the points in the list.
    * @return A new list of the scalar addition of the operands.
    */
   private List<Point> scalarAddition(List<Point> list, Point scalar) {
      List<Point> newList = new ArrayList<>();

      for (Point point : list) {
         newList.add(point.add(scalar));
      }
      return newList;
   }

}
