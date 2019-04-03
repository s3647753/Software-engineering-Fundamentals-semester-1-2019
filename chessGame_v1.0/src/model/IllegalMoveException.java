package model;

/**
 * Exception for when an illegal move is attempted
 * 
 * @author Bernard O'Meara
 */

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception {

   public IllegalMoveException() {
      super("Illegal move");
   }

   public IllegalMoveException(String msg) {
      super(msg);
   }

}
