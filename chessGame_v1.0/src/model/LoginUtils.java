package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

/**
 * a class containing a bunch of utility functions, intended to be used by the Login and Register classes.
 * i wasn't sure declaring a heap of static methods in the Login class was a good idea, so i've put them in this class.
 * @author Shaun
 *
 */

public final class LoginUtils {
	
	private static final String SHA_256 = "SHA-256";
	private static final String REGISTERED_PLAYERS_FILENAME = "registered_players.txt";

	private LoginUtils() {
	}
	
	/**
	 * hashes the given String with the SHA256 algorithm, because MD5 is for chumps.
	 * 
	 * @param inputString the String to convert to a SHA256 hash
	 * @return the hexadecimal representation of the hash as a String
	 * @author Shaun Davis
	 */
	public static String stringToSHA256(String inputString) {
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
			// this should never happen, but i made the getInstance parameter a constant anyway just to make sure.
		}
		
		return hashString;
	}
	
	/**
	 * checks that a player exists in the registered players file.
	 * the returned String will never be null; if this is the case,
	 * the player is not in the registered players list, so a PlayerNotFoundException is thrown instead of returning null.
	 * 
	 * @param username the username to search for
	 * @return the player's password hash string if they were found, null string if not (but PlayerNotFoundException is always thrown in this case)
	 * @throws PlayerNotFoundException if the player isn't registered.
	 * @author Shaun Davis
	 */
	public static String getPlayerHash(String username) throws PlayerNotFoundException {
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
	 * searches the filename defined by REGISTERED_PLAYERS_FILENAME and returns a HashMap of all
	 * usernames and their corresponding password hashes found in the file.
	 * 
	 * @return a HashMap of the format "username":"passHash"
	 */
	private static HashMap<String, String> getRegisteredPlayers() {
		HashMap<String, String> list = new HashMap<String, String>();
		
		try {
			Scanner in = new Scanner(new File(REGISTERED_PLAYERS_FILENAME));
			
			// entries in the file are in the format
			// username:HASH\n
			in.useDelimiter(":");
			
			while (in.hasNext()) {
				list.put(in.next(), in.next().trim());
			}
			in.close();
		} catch (FileNotFoundException e) {
			// the File is instantiated (created) in the Scanner, this should never be thrown.
		}
		
		return list;
	}
}
