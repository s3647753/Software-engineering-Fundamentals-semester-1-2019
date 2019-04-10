package model;

import java.util.ArrayList;
import model.PlayerNotFoundException;

/**
 * this class handles all login functions, and stores the list of currently logged in players.
 * 
 * @author Shaun Davis
 * 
 **/

public class Login extends RegLog {
	
	private ArrayList<String> playerList;
	
	/**
	 * the constructor for the Login class simply initalizes the playerList.
	 * 
	 * @author Shaun Davis
	 */
	public Login() {
		playerList = new ArrayList<String>();
	}
	
	/**
	 * logs this player into the game by adding their name to the playerList array of this Login instance.
	 * 
	 * @param username the player's username
	 * @param password the player's password in plaintext
	 * @return true if the login was successful, false if the password was incorrect
	 * @throws PlayerNotFoundException if the player isn't registered i.e. the username wasn't found in the registered players file
	 * @author Shaun Davis
	 */
	public boolean loginPlayer(String username, String password) throws PlayerNotFoundException {
		String passHash = getPlayerHash(username);
		
		if (passHash.equals(stringToSHA256(password))) {
			// the password hashes match!
			playerList.add(username);
			return true;
		}
		else {
			// the password's wrong.
			
			// TODO: what happens if the player is already logged in?
			
			return false;
		}
	}
	
	/**
	 * logs this player out of the game by removing their username from the playerList.
	 * 
	 * @param username of player to logout
	 * @return true if the player was logged out successfully, false if the player isn't logged in/username isn't in the list.
	 * @author Shaun Davis
	 */
	public boolean logoutPlayer(String username) {
		return playerList.remove(username);
	}
	
	/**
	 * returns the playerList!!!!!!!!!!!!!!!!!!!!!
	 * 
	 * @return the playerList!!!!!!!!!!!!!!!!!
	 * @author Shaun Daviasdaagfasd
	 */
	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	
}
