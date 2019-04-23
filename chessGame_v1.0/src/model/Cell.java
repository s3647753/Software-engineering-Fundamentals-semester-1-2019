package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import enums.Colr;
import enums.Type;
import model_Interfaces.Piece;

/**
 * Cell class one of each lives in each square of the chess board. The cell
 * class holds pieces, <br>
 * Zero if there is no piece in that board location, One if there is one piece
 * in the board location and Two if there is a merged piece in the location.
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public class Cell {
   private Colr background;
   private boolean merged;

   // the pieces in the cell (size 2 if merged)
   private List<Piece> pieces;


   public Cell(Colr background) {
      this.background = background;
      merged = false;
      pieces = new ArrayList<>();
   }


   /**
    * Returns the enum Colr value of the background of this cell
    * 
    * @return
    */
   public Colr getBackground() {
      return background;
   }


   /**
    * Returns an unmodifiableList of the pieces in the cell
    * 
    * @return The pieces in the cell
    */
   public List<Piece> getPieces() {
      return Collections.unmodifiableList(pieces);
   }


   /**
    * Returns true if there are two pieces in the cell and their state is merged,
    * else false.
    * 
    * @return True if the cell contains a merged piece
    */
   public boolean isMerged() {
      return merged;
   }


   /**
    * Adds a single piece to the cell. if there is a players piece in the cell and
    * a merge is legal then the pair are merged. If there is an opponents piece in
    * the cell it will be removed and the piece inserted. If the piece cannot be
    * placed an IllegalMoveException is thrown. NB: The definition of legal move
    * for this class only refers to merging and taking of pieces. There is no
    * guarantee or test that the pieces movement domain includes this cell.
    * 
    * @param piece
    *           The piece to add to the cell.
    * @throws IllegalMoveException
    */
   public void addPiece(Piece piece) throws IllegalMoveException {
      // the move is illegal
      if (!isLegal(piece)) {
         throw new IllegalMoveException("Illegal Move");
      }

      // there is an opposite color piece in the cell
      if (pieces.size() > 0 && !piece.getColor().equals(pieces.get(0).getColor())) {
         pieces.clear();
      }

      pieces.add(piece);

      // merged piece logic
      if (pieces.size() == 2) {
         merged = true;
      } else {
         merged = false;
      }
   }


   /**
    * Removes the nominated piece from the cell and returns a reference to the
    * removed piece. If the piece is not merged the cell will be empty after this
    * operation. If the cell is merged the other piece will remain in the cell.
    * 
    * @param piece
    *           The piece to be removed.
    * @return A reference to the removed piece;
    * @throws PieceNotFoundException
    */
   public Piece removePiece(Piece piece) throws PieceNotFoundException {
      // requested piece is not in the cell
      if (!pieces.contains(piece)) {
         throw new PieceNotFoundException();
      }

      merged = false;

      return pieces.remove(pieces.indexOf(piece));
   }


   /**
    * Returns the potential moves the piece/s in this cell could make if
    * unobstructed. If the cell is empty an empty list will be returned. Else the
    * combined moves of any pieces in the cell will be returned.
    * 
    * @return A new list of potential moves.
    */
   public List<Point> getPotentialMoves() {
      List<Point> moves = new ArrayList<>();

      for (Piece piece : pieces) {
         moves.addAll(piece.getPotentialMoves());
      }

      return moves;
   }


   /**
    * Determines if the supplied piece can be legally put into this Cell. This
    * method does not move, delete or change any properties. <br>
    * Returns false if: There is a piece, same color and same type in the cell or
    * there is a merged piece of same color in the cell
    * 
    * @param piece
    *           The piece that is asking if it can be put into this cell
    * @return True if the move would be legal, else false
    */
   public boolean isLegal(Piece piece) {
      boolean isLegal = true;

      // illegal if there is already a piece of same type and color in the cell
      // or if there is a merged piece of the same color in the cell
      switch (pieces.size()) {
         // same type and color
         case 1:
            if (piece.equals(pieces.get(0))) {
               isLegal = false;
            }
            break;
         case 2:
            // merged Piece of same color
            if (piece.getColor().equals(pieces.get(0).getColor())) {
               isLegal = false;
            }
      }

      return isLegal;
   }


   /**
    * Splits a piece if it was a merged piece. If not a merged piece then nill
    * effect. Returns true if the piece was merged and is now split. All other
    * cases returns false.
    * 
    * @return True if a merged piece was split.
    */
   public boolean split() {      
      if (!merged) {
         return merged;
      }

      return !(merged = false);
   }


   /**
    * Returns the code for the pieces in the cell sorted in
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
    * @return The code for this cell.
    */
   public String getCode() {
      StringJoiner code = new StringJoiner("");

      // always return merged pieces in the same order
      Collections.sort(pieces);

      // add the piece codes
      for (Piece piece : pieces) {
         code.add(piece.getCode());
      }

      // add the background
      code.add(background.toString().substring(0, 1));

      return code.toString();
   }


   // Overrides the Object.toString() method.
   @Override
   public String toString() {
      StringJoiner sj = new StringJoiner(", ", "Cell[", "]");
      for (Piece piece : pieces) {
         sj.add(piece.toString());
      }

      return sj.toString();
   }


   // Overrides the Object.equals method
   @Override
   public boolean equals(Object that) {
      if (this.getClass() != that.getClass()) {
         return false;
      }

      return this.toString().equals(((Cell) that).toString());
   }


   // Overrides the Object.hashCode method
   @Override
   public int hashCode() {
      return toString().hashCode();
   }

}
