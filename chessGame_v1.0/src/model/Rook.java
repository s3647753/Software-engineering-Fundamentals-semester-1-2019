package model;

import java.util.ArrayList;
import java.util.Collections;
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
   private static List<Point> moveVectors = null;


   public Rook(Colr color) {
      super(Type.ROOK, color);
      initMoveVector();
   }


   /**
    * Initializes the vector of movements that the piece could make if not
    * obstructed.
    */
   private void initMoveVector() {
      moveVectors = new ArrayList<>();

      moveVectors.add(new Point(1, 0));
      moveVectors.add(new Point(2, 0));
      moveVectors.add(new Point(-1, 0));
      moveVectors.add(new Point(-2, 0));
      moveVectors.add(new Point(0, 1));
      moveVectors.add(new Point(0, 2));
      moveVectors.add(new Point(0, -1));
      moveVectors.add(new Point(0, -2));
   }


   /*
    * (non-Javadoc)
    * 
    * @see model.AbstractPiece#getPotentialMoves()
    */
   @Override
   public List<Point> getPotentialMoves() {
      return Collections.unmodifiableList(moveVectors);
   }

}
