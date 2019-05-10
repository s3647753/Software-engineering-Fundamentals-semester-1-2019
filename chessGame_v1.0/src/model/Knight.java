package model;

import java.util.ArrayList;
import java.util.List;

import enums.Colr;
import enums.Type;

/**
 * Knight subclass for Chess Like Game April 2019
 * 
 * @author Bernard O'Meara + Ben Hunter
 *
 */
public class Knight extends AbstractPiece {

   public Knight(Colr color) {
      super(Type.KNIGHT, color, vectors());
   }


   /**
    * Initializes the vector of movements that the piece could make if not
    * obstructed.
    * 
    * @return The vectors that apply to this piece type
    */
   private static List<Point> vectors() {
      List<Point> moveVectors = new ArrayList<>();

      moveVectors.add(new Point(2, 1));
      moveVectors.add(new Point(2, -1));
      moveVectors.add(new Point(-2, 1));
      moveVectors.add(new Point(-2, -1));
      moveVectors.add(new Point(1, 2));
      moveVectors.add(new Point(1, -2));
      moveVectors.add(new Point(-1, 2));
      moveVectors.add(new Point(-1, -2));

      return moveVectors;
   }

}
