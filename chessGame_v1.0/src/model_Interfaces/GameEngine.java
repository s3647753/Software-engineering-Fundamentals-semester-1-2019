package model_Interfaces;

import java.util.ArrayList;
import java.util.List;

import enums.Colr;
import model.DuplicateNameException;
import model.IllegalMoveException;
import model.PlayerNotFoundException;
import model.Players;
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
	 * Set the views that will be used by the game engine
	 * In current design this may not end up being used to remove if so
	 * @param The view to be added
	 */
	void setView(View view);
	
	/**
	 * Updates a player's scores in the engine
	 *  
	 * @param The username of the player
	 * @param how much the player's score has increased
	 */
	public void updateScore(String Username, int scoreIncrease);
	
	/**
	 * Adds a game board for the game engine to use
	 *  
	 * @param The gameboard to be used by the engine
	 */
	public void addBoard(Board board);
	
	/**
	 * Passes both the users chosen turn amounts into the engine to average out and decide max moves.
	 * The max moves is stored as double the average value as it is reduced each time a players does a turn
	 * not once each player has completed a pair of turns
	 * @param The first players preferred moves
	 * @param The Second players preferred moves
	 */
	public void setMaxMoves(int player1Moves, int player2Moves);
	
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
	public boolean movePlayer(Point from, Point to) throws IllegalMoveException;
	
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
	public String register(String username, String password) throws DuplicateNameException;
	
	/**
	 * Requests the login of a player
	 * Returns a message informing the player if login was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @param password The players password
	 * @return The message to be displayed to the player
	 */
	public String login(String username, String password) throws PlayerNotFoundException;
	
	
	/**
	 * Requests the logout of a player
	 * Returns a message informing the player if logout was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @return The message to be displayed to the player
	 */
	public String logout(String username);
	
	/**
	 * Returns a list of the registered players names
	 * 
	 * @return The registered players names
	 */
	public ArrayList<String> getRegisteredPlayerNames();
	
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
}
