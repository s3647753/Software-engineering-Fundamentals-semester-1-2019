package model_Interfaces;

import java.util.ArrayList;
import java.util.List;

import enums.Colr;
import model.IllegalMoveException;
import model.Point;
import view_interfaces.View;

/**
 * Interface for the Game Engine
 * 
 * @author Matt Eletva
 *
 */

public interface GameEngine {

	/**
	 * Initialises the parameters for a new game to begin
	 * @param The White Player's username
	 * @param The Black Player's username
	 * @param One of the players turn limits
	 * @param One of the players turn limits
	 * @return whether a new game can begin or not
	 */
	public boolean newGame(String playerWhite, String playerBlack, int player1TurnLimit, int player2TurnLimit);
	/**
	 * Set the views that will be used by the game engine
	 * In current design this may not end up being used to remove if so
	 * @param The view to be added
	 */
	void setView(View view);
	
	/**
	 * Adds a game board for the game engine to use
	 *  
	 * @param The gameboard to be used by the engine
	 */
	public void addBoard(Board board);
	
	/**
	 * Moves a player from point A to point B
	 * If the from location is a merged piece 
	 * then the player must be asked if they wish to split.
	 *  
	 * @param from The point the piece is being moved from.
	 * @param to The point the piece is being moved to.
	 * @return true is successful else false
	 * @throws IllegalMoveException Thrown when the move is illegal
	 */
	public boolean movePlayer(Point from, Point to);
	
	/**
	 * Returns the Colr value of the player whose turn it is
	 * 
	 * @return 
	 */
	public Colr whoseTurn(); 
	
	/**
	 * Requests the registration of a new player
	 * Returns a message informing the player if registration was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @param password The players password
	 * @return The message to be displayed to the player
	 */
	public boolean register(String username, String password);
	
	/**
	 * Requests the login of a player
	 * Returns a message informing the player if login was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @param password The players password
	 * @return The message to be displayed to the player
	 */
	public boolean login(String username, String password);
	
	
	/**
	 * Requests the logout of a player
	 * Returns a message informing the player if logout was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @return The message to be displayed to the player
	 */
	public boolean logout(String username);
	
	
	/**
	 * Returns a list of the logged in players names
	 * 
	 * @return The logged in players names
	 */
	public ArrayList<String> getLoggedInPlayerNames();
	

	/**
	 * Returns a list of all the valid moves the piece located at
	 * the nominated position can move to
	 * 
	 * @param position The position of the piece that wishes to move
	 * @return The legal moves that the piece can move to
	 */
	public List<Point> getLegalMoves(Point position);
	
	
	/**
	 * Returns the gameboard being used by the game engine
	 * 
	 * @return The game board
	 */
	public Board getBoard();
	
	
	/**
	 * Returns the colour of a winner 
	 * 
	 * @return winner as Colr
	 */
	public Player getWinner();

	
	/**
	 * Returns the current status message of the game engine
	 * 
	 * @return status message as string
	 */
	public String getStatus();
	
	/**
	 * Returns the current info message of the game engine
	 * 
	 * @return info message as string
	 */
	public String getInfoMessage();
	
	
	/**
	 * Splits the pieces at the point given on the board
	 * 
	 * @param location on board as point
	 * @return whether split was successful or not
	 */
	public boolean split(Point point);
	
	
	/**
	 * Returns the amount of turns remaining in the current game
	 * 
	 * @return turns remaining for black as an int
	 */
	public int turnsRemaining();
	
	
	/**
	 * Returns the the player score for the colour given
	 * 
	 * @param colour of player as Colr
	 * @return player score
	 */
	public int getPlayerScore(Colr colour);
	
	/**
	 * Returns whether a game is running or not
	 * 
	 * @return whether game is running as a boolean
	 */
	public boolean gameRunning();
}
