package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * a class containing a bunch of utility functions, intended to be used by the Login and Register classes.
 * i wasn't sure declaring a heap of static methods in the Login class was a good idea, so i've put them in this class.
 * @author Shaun
 *
 */

public final class LoginUtils {
	
	private static final String SHA_256 = "SHA-256";

	private LoginUtils() {
	}
	
	/**
	 * hashes the given String with the SHA256 algorithm, because MD5 is for chumps.
	 * 
	 * @param inputString the String to convert to a SHA256 hash
	 * @return the hexadecimal representation of the hash as a String
	 * @author Shaun Davis
	 */
	protected static String stringToSHA256(String inputString) {
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
	 * 
	 * @param username the username to search for
	 * @return the player's password hash string if they were found, null string if not
	 * @throws PlayerNotFoundException if the player isn't registered.
	 * @author Shaun Davis
	 */
	protected static String getPlayerHash(String username) throws PlayerNotFoundException {
		String passHash = null;
		// open the players file
		// search for the username
		// grab their hash if they exist and return it
		return passHash;
	}
}
