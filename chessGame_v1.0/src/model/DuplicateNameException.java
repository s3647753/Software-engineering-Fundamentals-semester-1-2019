package model;

/**
 * when a username is already present in the players file when attempting
 * to register a new player, this exception is thrown.
 * 
 * @author Shaun Davis
 *
 */

@SuppressWarnings("serial")
public class DuplicateNameException extends Exception {

	public DuplicateNameException() {
		this("Player is already registered.");
	}

	public DuplicateNameException(String msg) {
		super(msg);
	}
	
}
