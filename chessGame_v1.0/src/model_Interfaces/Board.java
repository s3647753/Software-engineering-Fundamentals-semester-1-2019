package model_Interfaces;

import java.util.ArrayList;
import java.util.List;

import enums.Colr;
import enums.Type;
import model.Cell;
import model.IllegalMoveException;
import model.PieceNotFoundException;
import model.Point;

/**
 * Interface for the Chess Board
 * 
 * @author Bernard O'Meaa + TBA
 *
 */

public interface Board {

   static final int WIDTH = 6;
   static final int HEIGHT = 6;


   /**
    * Resets the board to the default for a new game
    * 
    * @return True if the board was successfully reset, else false
    */
   public boolean resetBoard();


   /**
    * Returns the height of the board.
    * 
    * @return The number of cells in each column.
    */
   public int getHeight();


   /**
    * Returns the width of the board.
    * 
    * @return The number of cells in each row.
    */
   public int getWidth();

   /**
    * Returns true if there are two pieces in the cell and their state is merged,
    * else false.
    * 
    * @return True if the cell contains a merged piece
    */
   public boolean isMerged(Point point);

   /**
    * Sets a piece to the required position. If there is an opponents piece in the
    * cell, the opponents piece will be removed. If there is a players piece in the
    * cell and a merge is legal the pieces will be merged. If the piece cannot be
    * set to the position an exception is thrown.
    * 
    * @param piece
    *           TODO
    * @param position
    *           TODO
    * @throws IllegalMoveException
    */
   public void setPiece(Piece piece, Point point) throws IllegalMoveException;


   /**
    * Moves a single piece from one point to another point. If it is a merged pair
    * only the nominated piece will be moved. If there is an opponents piece in the
    * cell, the opponents piece will be removed. If there is a players piece on the
    * to-point and a merge is legal they will be merged. Does not guarantee to
    * check for all illegal moves due to the added movement of a merged piece.
    * 
    * @param piece
    *           The piece to be moved
    * @param from
    *           The point the piece is moved from
    * @param to
    *           The point the piece is moved to
    * @return The number of enemy pieces taken
    * @throws PieceNotFoundException
    * @throws IllegalMoveException
    */
   public int moveSinglePiece(Piece piece, Point from, Point to)
         throws PieceNotFoundException, IllegalMoveException;


   /**
    * Moves a pair of merged pieces from one point to another point. If there is an
    * opponents piece in the cell, the opponents piece will be removed. If the move
    * is illegal an IllegalMoveException is thrown If the piece is not a merged
    * piece a PieceNotFoundException will be thrown
    * 
    * @param from
    *           The point the merged pair are moved from
    * @param to
    *           The point the merged pair are moved to
    * @return The number of enemy pieces taken
    * @throws IllegalMoveException
    * @throws PieceNotFoundException
    */
   public int moveMergedPiece(Point from, Point to)
         throws IllegalMoveException, PieceNotFoundException;


   /**
    * Determines if a merged piece can legally move from one point to another.
    * 
    * @param from
    *           The point the piece is moving from
    * @param to
    *           The point the point is moving to
    * @return True if the move is legal, else false
    */
   public boolean canMoveMergedPiece(Point from, Point to);


   /**
    * Returns all of the legal moves for the piece at the nominated position
    * 
    * @param position
    *           The position of the piece
    * @return All legal moves for the piece at the position
    */
   public List<Point> getLegalMoves(Point position);


   /**
    * Returns a List of the pieces presently located at the nominated point on the
    * board. If the cell is empty an empty List is returned. Note: Pieces and not
    * the cell are returned.
    * 
    * @return A list of the pieces at the board location.
    */
   public List<Piece> getPiecesAt(Point point);

   
   /**
    * Splits a piece if it was a merged piece.
    * If not a merged piece then nill effect.
    * Returns true if the piece was merged and is now split.
    * All other cases returns false.
    * 
    * @return True if a merged piece was split.
    */
   public boolean split(Point cell);

   /**
    * Returns the code for the pieces in the cell. Pieces are sorted in
    * lexicological order.<br>
    * Code is xxyyc, for a merged piece.<br>
    * Code is yyc, for a single piece.<br>
    * Code is c, for an empty cell.<br>
    * Where pieces xx and yy are Piece.type Piece.color.<br>
    * Chars xx only exist if it is a merged piece.<br>
    * Chars yy only exist if there is at least one piece in the cell.<br>
    * Char c is the board color.<br>
    * e.g., Black Rook on White = BRW
    * 
    * @param cell
    *           The point location of the required cell code
    * @return The code for the cell
    */
   public String getCode(Point cell);


   /**
    * Determines if a move by a piece is obstructed by another piece
    * 
    * @param from
    *           The point the piece is moving from.
    * @param to
    *           The point the piece is moving to.
    * @return True if the move is obstructed, else false
    */
   boolean isObstructed(Point from, Point to);


   /**
    * Determines if the Pieces in the two points the same color. Returns true if
    * both cells contain pieces and the pieces are the same color.
    * 
    * @param point1
    *           The first test point.
    * @param point2
    *           The point comparing the first point to.
    * @return True if both cells contain pieces of the same color.
    */
   boolean areSameColor(Point point1, Point point2);


   int movePiece(Point from, Point to)
         throws PieceNotFoundException, IllegalMoveException;


   Cell getCell(Point cell);

}
