package view_interfaces;

import model_Interfaces.Board;

public interface View {
	
	/** 
	 * Requests the name and password from the user
	 * and starts the registration process.
	 */
	public void registerPlayer();
	
//	/**
//	 * De-registers a player specified by the user.
//	 */
//	public void deRegisterPlayer();
	
	/** 
	 * Requests the name and password from the user
	 * and starts the login process.
	 */
	public void loginPlayer();
	
	/**
	 * Logs out a player specified by the user.
	 */
	public void logoutPlayer();
	
	/**
	 * Updates the chess board to match the supplied Board.
	 * 
	 * @param gameBoard The Board that shuld be displayed.
	 * @return true if successful;
	 */
	public boolean updateBoard(Board gameBoard);
	
	/**
	 * Requests the user interface to update all user views
	 * to match the fields from the default GameEngine.
	 * 
	 * @return true if successful;
	 */
	public boolean update();
	
	/**
	 * Requests that the status message be displayed to the user.
	 * 
	 * @param message The message to be displayed
	 */
	public void setStatus(String message);
	
	
	
	
	/** 
	 * Asks the player if they wish to split the merged pieces
	 * 
	 * @return true to split else false to move both pieces
	 */
	public boolean splitPieces();

}
