package view.gui;

/**
 * Exception to indicate operation cancelled by user
 * 
 * @author Bernard O'Meara
 *
 */
@SuppressWarnings("serial")
public class OperationCancelledException extends Exception {

	public OperationCancelledException() {
		this("User input cancelled");
	}

	public OperationCancelledException(String message) {
		super(message);
	}
}
