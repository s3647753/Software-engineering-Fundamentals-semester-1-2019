package model_Interfaces;

import java.util.ArrayList;

import enums.Colr;
import model.IllegalMoveException;
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

	void setView(View view);
	
	
	public void movePiece(Point positionPrevious, Point positionNew);
	
	//calls a method on player that will add the int given to the players score\
	//or maybe just updates a map with player/playerid as key
	public void updateScore(String Username, int score);
	
	//add a board to the game engine
	public void addBoard(Board board);
	
	//passes both the users move amount in to the engine to average out and decide max moves
	public void setMaxMoves(int player1Moves, int player2Moves);
	
	/*reduces moves left by 1
	maybe reduce by 2, while storing max moves as double what it actually is so 
	it can be reduced after each players turn
	sounds kinda messy though*/
	//maybe remove from interface
	void reduceMoves();
	
	//maybe remove from interface
	void endGame();
	
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
	public String register(String name, String password);
	
	/**
	 * Requests the login of a player
	 * Returns a message informing the player if login was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @param password The players password
	 * @return The message to be displayed to the player
	 */
	public String login(String name, String password);
	
	
	/**
	 * Requests the logout of a player
	 * Returns a message informing the player if logout was successful,
	 * if not successful, then the reason why.
	 * 
	 * @param name The players name
	 * @return The message to be displayed to the player
	 */
	public String logout(String name);
	
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
	public ArrayList<Point> getLegalMoves(Point position);
}
