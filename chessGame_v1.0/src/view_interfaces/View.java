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
	 * Requests the name and password from the user
	 * and starts the registration process.
	 * 
	 * @return 
	 */
	public String registerPlayer();
	
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
	
//	/**
//	 * Updates the chess board to match the supplied Board.
//	 * 
//	 * @param gameBoard The Board that shuld be displayed.
//	 * @return true if successful;
//	 */
//	public void updateBoard(Board gameBoard);
	
//	/**
//	 * Depreciated: Will be removed once the Observer Pattern is finished
//	 * 
//	 * Requests the user interface to update all user views
//	 * to match the fields from the default GameEngine.
//	 * 
//	 * @return true if successful;
//	 * @deprecated As of the integration of the Observer Pattern
//	 */
//	@Deprecated 
//	public void update(Board gameBoard);

//	/**
//	 * Requests that the status message be displayed to the user.
//	 * 
//	 * @param message The message to be displayed
//	 */
//	public void setStatus(String message);
	
	
	/**
	 * TODO
	 */
	public void split();	
	
	
//	/** 
//	 * Asks the player if they wish to split the merged pieces
//	 * 
//	 * @return true to split else false to move both pieces
//	 */
//	public boolean splitPieces();

	/**
	 * Returns a list of the Pieces at a position on the board
	 * For an empty square, an empty list will be returned.
	 * For a single piece in the square, a list of size one will be returned
	 * For a merged piece in the square, a list of size two will be returned.
	 *  
	 * @param row TODO
	 * @param column TODO
	 * @return A list of Pieces on on the board position.
	 */
	public List<Piece> getPieceList(int row, int column);
	
	/**
	 * Moves a piece from one point to another
	 * TODO
	 * @param from
	 * @param to
	 */
	public void movePlayer(Point from, Point to);
	
	/**
	 * Resets everything for a new game
	 */
	public void newGame();
	
	
	public void updateScore(int player1, int player2);
	
	public void setPlayerName(int playerNum, String name);
	
	public void setPlayerColor(int playerNum, Colr color);
	
//	public void setPlayerTurn(int playerNum);
	
	public void notifyGameOver(String message);
	
	public void notifyMoveIsDangerous(String message);
	
	// I already have an updateBoard
	// public void update(Board gameBoard);
	
	public boolean askIfPlayerWantsToSplit(String message);

   public void init();

   public void squareClicked(Point point);

   	

}
