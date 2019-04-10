package model;

/**
 * this class handles registering players.
 * takes player name + password;
 * checks player name is not already taken + doesn't include weird characters (no spaces, maybe?)
 * saves player name and password hash to file!
 * 
 * @author Shaun Davis
 *
 */

public class Register extends RegLog {
	
	public Register() {
		
	}
	
	/**
	 * adds a player to the registered players file with the given username and password.
	 * 
	 * @param username the propositioned username
	 * @param password the password to use, in plaintext. it's converted to a hash before being saved to the registered players file.
	 * @return true if the player was successfully registered, false if not (for reasons other than a DuplicateNameException)
	 * @throws DuplicateNameException when the propositioned username is already present in the registered players file
	 * @author Shaun Davis
	 */
	protected boolean registerPlayer(String username, String password) throws DuplicateNameException {
		try {
			// this first line will immediately throw a PlayerNotFoundException if there's a matching username.
			getPlayerHash(username);
			// so if it ever passes that first line, the username already exists.
			throw new DuplicateNameException();
		} catch (PlayerNotFoundException e) {
			String passHash = stringToSHA256(password);
			saveToFile(username, passHash);
		}
		return false;
	}
	
	/**
	 * saves the username and password hash of a player to the registered players file.
	 * 
	 * @param username the username to save
	 * @param passHash the corresponding hash of the password
	 * @author Shaun Davis
	 */
	private static void saveToFile(String username, String passHash) {
		// TODO: make this one do thing
	}
}
