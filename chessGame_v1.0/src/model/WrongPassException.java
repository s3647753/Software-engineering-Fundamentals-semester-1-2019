package model;

/**
 * when a mismatch between password hashes is found, this exception is thrown.
 * 
 * @author Shaun Davis
 *
 */

@SuppressWarnings("serial")
public class WrongPassException extends Exception {

	public WrongPassException() {
		this("Password incorrect");
	}

	public WrongPassException(String msg) {
		super(msg);
	}

}
