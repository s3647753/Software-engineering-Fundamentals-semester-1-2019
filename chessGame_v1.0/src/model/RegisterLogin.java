package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

/**
 * this class handles all login functions, and stores the list of currently
 * logged in players. also contains a bunch of utility functions used by the
 * register and login processes.
 * 
 * @author Shaun
 *
 */

public class RegisterLogin {

	private static final String SHA_256 = "SHA-256";
	private static final String REGISTERED_PLAYERS_FILENAME = "registered_players.txt";
	private static final String FILE_DELIMITER = ":";
	private ArrayList<String> playerList;

	/**
	 * the constructor for the RegLog class simply initializes the playerList.
	 * 
	 * @author Shaun Davis
	 */
	public RegisterLogin() {
		playerList = new ArrayList<String>();
	}

	/**
	 * logs this player into the game by adding their name to the playerList array
	 * of this Login instance.
	 * 
	 * @param username the player's username
	 * @param password the player's password in plaintext
	 * @throws PlayerNotFoundException if the player isn't registered i.e. the
	 *                                 username wasn't found in the registered
	 *                                 players file
	 * @throws WrongPassException      if the entered password didn't match the one
	 *                                 in the registered players file
	 * @author Shaun Davis
	 */
	public void loginPlayer(String username, String password) throws PlayerNotFoundException, WrongPassException {
		String passHash = getPlayerHash(username);

		if (passHash.equals(stringToSHA256(password))) {
			// the password hashes match!
			playerList.add(username);
		} else {
			throw new WrongPassException();

			// TODO: what happens if the player is already logged in? throw another
			// exception maybe?

		}
	}

	/**
	 * logs this player out of the game by removing their username from the
	 * playerList.
	 * 
	 * @param username of player to logout
	 * @return true if the player was logged out successfully, false if the player
	 *         isn't logged in/username isn't in the list.
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

	/**
	 * adds a player to the registered players file with the given username and
	 * password.
	 * 
	 * @param username the propositioned username
	 * @param password the password to use, in plaintext. it's converted to a hash
	 *                 before being saved to the registered players file.
	 * @return true if the player was successfully registered, false if not (for
	 *         reasons other than a DuplicateNameException)
	 * @throws DuplicateNameException when the propositioned username is already
	 *                                present in the registered players file
	 * @author Shaun Davis
	 */
	protected void registerPlayer(String username, String password) throws DuplicateNameException {
		try {
			// this first line will immediately throw a PlayerNotFoundException if there's a
			// matching username.
			getPlayerHash(username);
			// so if it ever passes that first line, the username already exists.
			throw new DuplicateNameException();
		} catch (PlayerNotFoundException e) {
			String passHash = stringToSHA256(password);
			saveToFile(username, passHash);
		}
	}

	/**
	 * saves the username and password hash of a player to the registered players
	 * file.
	 * 
	 * @param username the username to save
	 * @param passHash the corresponding hash of the password
	 * @author Shaun Davis
	 */
	private void saveToFile(String username, String passHash) {
		try {
			FileWriter out = new FileWriter(new File(REGISTERED_PLAYERS_FILENAME), true);

			out.write(username + FILE_DELIMITER + passHash);
			out.write('\n');

			out.close();

		} catch (IOException e) {
			// the File is instantiated (created) in the FileWriter, this should never be
			// thrown.
			e.printStackTrace();
		}
	}

	/**
	 * hashes the given String with the SHA256 algorithm, because MD5 is for chumps.
	 * 
	 * @param inputString the String to convert to a SHA256 hash
	 * @return the hexadecimal representation of the hash as a String
	 * @author Shaun Davis
	 */

	public String stringToSHA256(String inputString) {
		String hashString = null;

		try {
			MessageDigest md = MessageDigest.getInstance(SHA_256);
			// update/populate the digest with the bytes of the inputString
			// we specify UTF-8, just in case it tries to use the "platform default".
			md.update(inputString.getBytes(StandardCharsets.UTF_8));
			byte[] hash = md.digest();
			// convert the bytes of the hash to hex because hex is cool
			hashString = DatatypeConverter.printHexBinary(hash);
		} catch (NoSuchAlgorithmException e) {
			// this should never happen, but i made the getInstance parameter a constant
			// anyway just to make sure.
			e.printStackTrace();
		}

		return hashString;
	}

	/**
	 * checks that a player exists in the registered players file. the returned
	 * String will never be null; if this is the case, the player is not in the
	 * registered players list, so a PlayerNotFoundException is thrown instead of
	 * returning null.
	 * 
	 * @param username the username to search for
	 * @return the player's password hash string if they were found, null string if
	 *         not (but PlayerNotFoundException is always thrown in this case)
	 * @throws PlayerNotFoundException if the player isn't registered.
	 * @author Shaun Davis
	 */
	public String getPlayerHash(String username) throws PlayerNotFoundException {
		String playerHash = null;
		HashMap<String, String> registeredList = getRegisteredPlayers();

		playerHash = registeredList.get(username);
		if (playerHash != null) {
			return playerHash;
		} else {
			throw new PlayerNotFoundException();
		}

	}

	/**
	 * searches the filename defined by REGISTERED_PLAYERS_FILENAME and returns a
	 * HashMap of all usernames and their corresponding password hashes found in the
	 * file.
	 * 
	 * @return a HashMap of the format "username":"passHash"
	 */
	private HashMap<String, String> getRegisteredPlayers() {
		HashMap<String, String> list = new HashMap<String, String>();

		try {
			Scanner in = new Scanner(new File(REGISTERED_PLAYERS_FILENAME));

			while (in.hasNextLine()) {
				// entries in the file are in the format
				// username:HASH\n
				String[] player = in.nextLine().split(FILE_DELIMITER);
				list.put(player[0], player[1].trim());
			}
			in.close();
		} catch (FileNotFoundException e) {
			// the File is instantiated (created) in the Scanner, this should never be
			// thrown.
			e.printStackTrace();
		}

		return list;
	}

}
