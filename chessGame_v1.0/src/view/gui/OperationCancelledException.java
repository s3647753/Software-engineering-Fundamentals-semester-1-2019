package view.gui;

public class OperationCancelledException extends Exception {

	public OperationCancelledException() {
		this("User input failed or was cancelled");
	}

	public OperationCancelledException(String message) {
		super(message);
	}


}
