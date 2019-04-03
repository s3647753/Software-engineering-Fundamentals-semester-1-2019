package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import enums.Colr;
import enums.Type;
import model_Interfaces.Piece;

public class Cell {

   // the pieces in the cell (2 if merged)
   private ArrayList<Piece> pieces;

   public Cell() {
      pieces = new ArrayList<>();
   }

   /**
    * Returns the pieces in the cell
    * 
    * @return The pieces in the cell
    */
   public List<Piece> getPieces() {
      return Collections.unmodifiableList(pieces);
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

      // filter moves
      switch (pieces.size()) {
         case 1:
            // opponents piece in the cell (take it)
            if (!piece.getColor().equals(pieces.get(0).getColor())) {
               pieces.clear();
            } else {
               // players piece of same type in the cell
               if (piece.getType().equals(pieces.get(0).getType())) {
                  throw new IllegalMoveException("Cannot merge like pieces");
               }
            }
            break;
         case 2:
            // opponents piece is merged (take it)
            if (!piece.getColor().equals(pieces.get(0).getColor())) {
               pieces.clear();
            } else {
               // current player already has a merged piece in cell
               throw new IllegalMoveException("Piece already merged.");
            }
      }

      // all checks passed, add the piece
      pieces.add(piece);
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

      return pieces.remove(pieces.indexOf(piece));
   }

   //Overrides the Object.toString() method.
   @Override
   public String toString() {
      StringJoiner sj = new StringJoiner(", ", "Cell[", "]");
      for (Piece p : pieces) {
         sj.add(p.toString());
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
   
   
   // TODO remove before release
   public static void main(String[] args) throws IllegalMoveException {
      Cell c = new Cell();
      System.out.println(c);
      c.addPiece(new Rook(Colr.BLACK));
      System.out.println(c);
      c.addPiece(new Rook(Colr.BLACK));
      System.out.println(c);
   }

}
