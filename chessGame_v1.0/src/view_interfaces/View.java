package view_interfaces;

import java.util.List;
import java.util.Observer;

import enums.Colr;
import model.Point;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * 
 * @author Bernard O'Meara
 *
 */

public interface View extends Observer {

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
    * TODO
    */
   public void split();


   /**
    * Moves a piece from one point to another TODO
    * 
    * @param from
    * @param to
    */
   public void movePlayer(Point from, Point to);


   /**
    * Resets the game board and gets player preferences for a new game. Default
    * preferences for a new game are the preferences from the previous game.
    * Guarantees that player names are unique and that preferred game length is a
    * valid integer.
    * 
    */
   public void newGame();


   public void notifyGameOver(String message);


   public void notifyMoveIsDangerous(String message);


   public void init();


   public void squareClicked(Point point);

}
