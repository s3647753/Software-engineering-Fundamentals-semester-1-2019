package view.gui;

/**
 * Exception to indicate players are not logged in
 * 
 * @author Bernard O'Meara
 *
 */
@SuppressWarnings("serial")
public class PlayersNotLoggedInException extends Exception {
   
   public PlayersNotLoggedInException() {
      this("Two player are not logged in");
   }

   public PlayersNotLoggedInException(String message) {
      super(message);
   }

}
