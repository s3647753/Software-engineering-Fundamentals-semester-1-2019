package view_interfaces;

import java.util.List;

import model.Point;
import model_Interfaces.Board;
import view.gui.OperationCancelledException;

public interface ViewType {
   
   /**
    * TODO docstring
    * 
    * @param viewModel
    * @param board 
    */
	public void initView(View viewModel, Board board);

	/**
	 * Requests the name and password from the user for a new player array[uerName,
	 * password]
	 * 
	 * @return The userName and password of the new player
	 * @throws OperationCancelledException
	 *             it operation fails or is cancelled
	 * 
	 */
	public String[] registerPlayer() throws OperationCancelledException;

//	/**
//	 * Returns the name of the player to de-register.
//	 * 
//	 * @return The name of the player to be de-registered
//	 * @throws UserInputException
//	 *             If operation fails or is cancelled
//	 * 
//	 */
//	public String deRegisterPlayer() throws UserInputException;

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
	 * @return The players name to log out
	 * @throws OperationCancelledException
	 *             If operation fails or is cancelled
	 */
	public String logoutPlayer() throws OperationCancelledException;

	/**
	 * Updates the chess board to match the supplied Board.
	 * 
	 * @param gameBoard
	 *            The Board that shuld be displayed.
	 * @return true if successful;
	 */
	public void updateBoard(Board gameBoard);

	/**
	 * Requests the user interface to update all user views to match the fields from
	 * the default GameEngine.
	 * 
	 * @return true if successful;
	 */
	public boolean update();

	/**
	 * Requests that the status message be displayed to the user.
	 * 
	 * @param message
	 *            The message to be displayed
	 */
	public void setStatus(String message);

	/**
	 * Highlights a board square
	 * @param point
	 * @param set
	 */
   void highlight(Point point, boolean set);

   public void showLegalMoves(List<Point> legalMoves, boolean set);

   // sets the players turn in the status
   public void setPlayerTurn(String string);

   public void updateSplit(boolean split);

	
//	/**
//	 * TODO
//	 * @param height
//	 * @param width
//	 */
//   public void setBoardSize(int height, int width);

}
