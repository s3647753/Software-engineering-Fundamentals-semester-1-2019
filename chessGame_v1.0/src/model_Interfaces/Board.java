package model_Interfaces;

import java.util.List;

import enums.Colr;
import model.IllegalMoveException;
import model.PieceNotFoundException;
import model.Point;

/**
 * Interface for the Chess Board
 * 
 * @author Bernard O'Meara
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
    * Returns true if there are two pieces in the nominated cell and their status
    * is merged.
    * 
    * @param point
    *           The position of the cell to be inspected.
    * @return True if the cell contains a merged pair of pieces, else false.
    */
   public boolean isMerged(Point point);


   /**
    * Moves a piece from one point to another point. If it is a merged pair both
    * pieces will be moved. If there is a pair of pieces on the square that have
    * been split, the piece that can move will move and the remaining piece will
    * not be moved. If there is an opponents piece in the cell, the opponents
    * piece will be removed. If there is a players piece on the to-point and a
    * merge is legal they will be merged.
    * 
    * @param from
    *           The point the piece is moved from
    * @param to
    *           The point the piece is moved to
    * @return The number of enemy pieces taken
    * @throws PieceNotFoundException
    * @throws IllegalMoveException
    */
   int movePiece(Point from, Point to)
         throws PieceNotFoundException, IllegalMoveException;


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
    * Splits a piece if it was a merged piece. If not a merged piece then nill
    * effect. Returns true if the piece was merged and is now split. All other
    * cases returns false.
    * 
    * @return True if a merged piece was split.
    */
   public boolean split(Point cell);


   /**
    * Returns the code for the pieces in the cell. Pieces are sorted in
    * lexicological order.<br>
    * Code is XXYYC, for a merged piece.<br>
    * Code is YYC, for a single piece.<br>
    * Code is C, for an empty cell.<br>
    * Where pieces XX and YY are Piece.type Piece.color.<br>
    * Chars XX only exist if it is a merged piece.<br>
    * Chars YY only exist if there is at least one piece in the cell.<br>
    * Char C is the board color.<br>
    * e.g., Black Rook on White = BRW
    * 
    * @param cell
    *           The point location of the required cell code
    * @return The code for the cell
    */
   public String getCode(Point cell);


   /**
    * Returns true if there are no more pieces of the designated color left on the
    * game board.
    * 
    * @param colour
    *           The color of the pieces in question
    * @return True if there are no pieces left of the color, else false
    */
   boolean allPiecesGone(Colr colour);

}
