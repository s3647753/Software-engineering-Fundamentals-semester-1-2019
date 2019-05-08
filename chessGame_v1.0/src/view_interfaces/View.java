package view_interfaces;

import java.util.Observer;
import model.Point;

/**
 * Interface for the view model. Is an observer of the game engine.
 * 
 * @author Bernard O'Meara
 *
 */
public interface View extends Observer {

   /**
    * Adds a reference to this as the view model to the game engine and the user
    * interface.
    */
   public void init();


   /**
    * Requests the name and password from the user and starts the registration
    * process.
    * 
    * @return
    */
   public void registerPlayer();


   /**
    * Requests the name and password from the user and starts the login process.
    */
   public void loginPlayer();


   /**
    * Logs out a player specified by the user.
    */
   public void logoutPlayer();


   /**
    * Splits a merged piece and resets the user interface.
    */
   public void split();


   /**
    * Resets the game board and gets player preferences for a new game. Default
    * preferences for a new game are the preferences from the previous game.
    * Guarantees that player names are unique and that preferred game length is a
    * valid integer.
    * 
    */
   public void newGame();


   /**
    * Handles the logic when a player selects a board square. Handles cases where
    * the move is from, to or an invalid square is selected.
    * 
    * @param point
    *           The location of the selected board square.
    */
   public void squareSelected(Point point);

}
