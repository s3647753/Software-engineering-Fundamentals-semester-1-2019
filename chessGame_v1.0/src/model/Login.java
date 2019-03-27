package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import model.PlayerNotFoundException;

/**
 * this class handles all login functions, and stores the list of currently logged in players.
 * 
 * @author Shaun Davis
 * 
 **/

public class Login {
	
	private ArrayList<String> playerList;
	
	/**
	 * the constructor for the Login class simply initalizes the playerList.
	 * @author Shaun Davis
	 */
	public Login() {
		playerList = new ArrayList<String>();
	}
	
	/**
	 * hashes the given String with the SHA256 algorithm, because MD5 is for chumps. this method is static so that
	 * the Register class can also make use of it without instantiating Login.
	 * @param inputString the String to convert to a SHA256 hash
	 * @return the hashed String
	 * @author Shaun Davis
	 */
	protected static String stringToSHA256(String inputString) {
		String hashString = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// update/populate the digest with the bytes of the inputString
			// we specify UTF-8, just in case it tries to use something else.
			md.update(inputString.getBytes(StandardCharsets.UTF_8));
			byte[] hash = md.digest();
			hashString = DatatypeConverter.printHexBinary(hash);
		} catch (NoSuchAlgorithmException e) {
			// this should never happen
		}
		
		return hashString;
	}

	/**
	 * checks that a player exists in the registered players file.
	 * 
	 * @param username the username to search for
	 * @return true if the player exists, false if it doesn't
	 * @author Shaun Davis
	 */
	private boolean playerExists(String username) {
		// open the players file
		// search for the username
		// return true if it's there!
		return false;
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
	protected boolean loginPlayer(String username, String password) throws PlayerNotFoundException {
		
		if (playerExists(username)) {
			// verify their password hash matches the one in the players file
			// if so, append the player username to playerList
			// return true!
		}
		else {
			throw new PlayerNotFoundException();
		}

		return false;
	}
	
	/**
	 * returns the playerList!!!!!!!!!!!!!!!!!!!!!
	 * @return the playerList!!!!!!!!!!!!!!!!!
	 * @author Shaun Daviasdaagfasd
	 */
	protected ArrayList<String> getPlayerList() {
		return playerList;
	}
	
}
