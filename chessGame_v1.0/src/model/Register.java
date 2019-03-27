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

public class Register {
	
	/**
	 * adds a player to the registered players file with the given username and password. this is intended to be called without
	 * instantiating, i.e. just call Register.registerPlayer(username, password).
	 * @param username the propositioned username
	 * @param password the password to use, in plaintext. it's converted to a hash before being saved to the registered players file.
	 * @return true if the player was successfully registered, false if not (for reasons other than a DuplicateNameException)
	 * @throws DuplicateNameException when the propositioned username is already present in the registered players file
	 */
	public static boolean registerPlayer(String username, String password) throws DuplicateNameException {
		
		return false;
	}
	
}
