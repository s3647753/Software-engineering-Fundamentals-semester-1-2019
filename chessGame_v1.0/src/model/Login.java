package src.model;

import model.PlayerNotFoundException;

/**
 * this class takes player name and password,
 * and checks that:
 * 1. the player name exists in the players file
 * 2. the given password's hash matches the corresponding one in the file
 * 
 * @author Shaun Davis
 * 
 **/

public class Login {
	
	private String playerID;
	private String passwordHash;
	
	public Login(String playerID, String password) {
		this.playerID = playerID;
		this.passwordHash = stringToSHA256(password);
		
	}
	
	/**
	 * hashes the given String with the SHA256 algorithm, because MD5 is for chumps.
	 * @param inputString
	 * @return the hashed String.
	 */
	private String stringToSHA256(String inputString) {
		String hashString = null;
		// oh no
		return hashString;
	}
	
	/**
	 * checks if the player ID is present in the players file.
	 * 
	 * @return true if the ID was found, false if it was not.
	 * @author Shaun Davis
	 */
	private boolean playerExists() throws PlayerNotFoundException {
		
		return false;
	}
	
	/**
	 * checks that the password hash matches the one in the Players file.
	 * 
	 * @return true if the password hash matches, false if it doesn't.
	 * @author Shaun Davis
	 */
	private boolean verifyPassword() {
		try {
			if (playerExists()) {
				
			}
		} catch (PlayerNotFoundException e) {
			// print the error or something?
		}
		
		
		
		return false;
	}
	
}
