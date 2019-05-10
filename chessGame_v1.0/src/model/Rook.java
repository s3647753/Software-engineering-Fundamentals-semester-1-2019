package model;

import java.util.ArrayList;
import java.util.List;

import enums.Colr;
import enums.Type;

/**
 * Rook subclass for Chess Like Game April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */

public class Rook extends AbstractPiece {

   public Rook(Colr color) {
      super(Type.ROOK, color, vectors());
   }


   /**
    * Initializes the vector of movements that the piece could make if not
    * obstructed.
    * 
    * @return The vectors that apply to this piece type
    */
   private static List<Point> vectors() {
      List<Point> moveVectors = new ArrayList<>();

      moveVectors.add(new Point(1, 0));
      moveVectors.add(new Point(2, 0));
      moveVectors.add(new Point(-1, 0));
      moveVectors.add(new Point(-2, 0));
      moveVectors.add(new Point(0, 1));
      moveVectors.add(new Point(0, 2));
      moveVectors.add(new Point(0, -1));
      moveVectors.add(new Point(0, -2));

      return moveVectors;
   }

}
