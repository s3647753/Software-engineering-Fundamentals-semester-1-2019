package model;

/**
 * when a player tries to log in when they're already logged in, this exception
 * is thrown.
 * 
 * @author Shaun Davis
 *
 */

public class PlayerLoggedInException extends Exception {

	public PlayerLoggedInException() {
		this("Player already logged in");
	}

	public PlayerLoggedInException(String msg) {
		super(msg);
	}

}
