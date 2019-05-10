package view_interfaces;

import java.util.List;

import enums.Colr;
import model.Point;
import model_Interfaces.Board;
import view.gui.OperationCancelledException;
import view.gui.PlayersNotLoggedInException;

public interface ViewType {

   /**
    * Initializes the view model and the user interface board.
    * 
    * @param viewModel
    *           A reference to the view model.
    * @param board
    *           A reference to the current game board.
    */
   public void initView(View viewModel, Board board);


   /**
    * Requests the name and password from the user for a new player array[uerName,
    * password]
    * 
    * @return The userName and password of the new player
    * @throws OperationCancelledException
    *            it operation fails or is cancelled
    * 
    */
   public String[] registerPlayer() throws OperationCancelledException;


   /**
    * Requests the name and password from the user for a player wishing to login
    * array[uerName, password]
    * 
    * @return The userName and password of the new player
    * @throws OperationCancelledException
    */
   public String[] loginPlayer() throws OperationCancelledException;


   /**
    * Returns the name of the player wishing to log out.
    * 
    * @param names
    * 
    * @return The players name to log out
    * @throws OperationCancelledException
    *            If operation fails or is cancelled
    */
   public String logoutPlayer(List<String> names)
         throws OperationCancelledException;


   /**
    * Updates the chess board to match the supplied Board.
    * 
    * @param gameBoard
    *           The Board that is to be displayed.
    */
   public void updateBoard(Board gameBoard);


   /**
    * Requests that the supplied message be displayed to the user as a status
    * message.
    * 
    * @param message
    *           The message to be displayed
    */
   public void setStatus(String message);


   /**
    * Highlights a board square
    * 
    * @param point
    * @param set
    */
   void highlight(Point point, boolean set);


   /**
    * Sets a list of board squares to have the markings for legal moves either
    * turned on or off.
    * 
    * @param legalMoves
    *           The squares that the marks are to be modified.
    * @param set
    *           True is the squares are to be marked as legal, else false if any
    *           markings is to be removed.
    */
   public void showLegalMoves(List<Point> legalMoves, boolean set);


   /**
    * Informs the players whose turn it is.
    * 
    * @param colr
    *           The color of the player whose turn it is.
    */
   public void setPlayerTurn(String message);


   /**
    * Handles the logic for the user interface regarding merged pieces.
    * 
    * @param merged
    *           True if the currently selected piece is a merged piece, else false
    *           for all other casses.
    */
   public void setMerged(boolean merged);


   /**
    * Informs the users how many moves are left before the games end due to move
    * count.
    * 
    * @param remaining
    *           The number of moves left before the game ends.
    */
   void setMovesRemaining(int remaining);


   /**
    * Sets the players names in the user interface.
    * 
    * @param whiteName
    *           The white players name.
    * @param blackName
    *           The black players name.
    */
   void setPlayerNames(String whiteName, String blackName);


   /**
    * Sets the players scores in the user interface.
    * 
    * @param whiteName
    *           The white players score.
    * @param blackName
    *           The black players score.
    */
   void setPlayerScores(int whiteScore, int blackScore);


   /**
    * Gets the players preferences for a new game.
    * 
    * @param names
    *           The names of the players for the next game.
    * @return The player preferences.
    * @throws OperationCancelledException
    *            Thrown when the player cancels the new game operation.
    * @throws PlayersNotLoggedInException 
    */
   String[] newGame(List<String> names) throws OperationCancelledException, PlayersNotLoggedInException;

}
