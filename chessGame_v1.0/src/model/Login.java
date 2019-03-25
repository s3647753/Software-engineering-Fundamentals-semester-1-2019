package model;

import java.util.ArrayList;
import model.PlayerNotFoundException;

/**
 * this class takes player name and password,
 * and checks that:
 * 1. the player name exists in the players file
 * 2. the given password's hash matches the corresponding one in the file
 * 
 * this class stores the list of currently logged in players.
 * 
 * @author Shaun Davis
 * 
 **/

public class Login {
	
	private ArrayList<String> playerList;
	
	public Login() {
		playerList = new ArrayList<String>();
	}
	
	/**
	 * hashes the given String with the SHA256 algorithm, because MD5 is for chumps.
	 * @param inputString
	 * @return the hashed String.
	 */
	private String stringToSHA256(String inputString) {
		String hashString = null;
		// i have no idea how hashes work yet
		return hashString;
	}
	
	/**
	 * checks if the player ID is present in the players file.
	 * 
	 * @return true if the ID was found, false if it was not.
	 * @author Shaun Davis
	 */
	private boolean playerExists(String username) throws PlayerNotFoundException {
		// open the players file
		// search for the username
		// return true if it's there!
		return false;
	}
	
	protected boolean loginPlayer(String username, String password) throws PlayerNotFoundException {
		
		if (playerExists(username)) {
			// verify their password hash matches the one in the players file
			// if so, append the player username to playerList
		}

		return false;
	}
	
	protected ArrayList<String> getPlayerList() {
		return playerList;
	}
	
}
