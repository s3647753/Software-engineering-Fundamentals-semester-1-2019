package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * A dialog for the user to enter player name and password.
 * 
 * @author Bernard O'Meara, s8358478,
 *
 */
public class NameAndPasswordDialog {

	private static final Font FONT20 = new Font(Font.SERIF, Font.PLAIN, 20);
	private static final Color RED = Color.RED;
	private static final Color WHITE = Color.WHITE;

	/**
	 * Gets user input for name and password using a default message.
	 * 
	 * @return an array[String name, String password]
	 * @throws OperationCancelledException 
	 */
	public String[] getNameAndPassword(String title) throws OperationCancelledException {
		String defaultMessage = "Enter Name and Password";

		return getNameAndPassword(title, defaultMessage);
	}

	/**
	 * Gets user input for name and password. Displays a custom title and message.
	 * 
	 * @param title
	 *            The title to be displayed in the Dialog.
	 * @param message
	 *            The message to be displayed in the Dialog body.
	 * @return an array[String name, String password]
	 * @throws OperationCancelledException
	 *             When cancelled by the user.
	 */
	public String[] getNameAndPassword(String title, String message) throws OperationCancelledException {

		JTextField nameField = new JTextField();
		JPasswordField passwordField = new JPasswordField();

		// JPanel to put into the dialog (enables multiple custom fields)
		JPanel panel = new JPanel(new BorderLayout());

		// add the message and text fields
		addMessage(panel, message);
		addTextFields(panel, nameField, passwordField);

		boolean validInput = false;
		int result;

		// loop until all fields are valid or operation cancelled
		while (!validInput) {
			validInput = true;

			// get the user input
			result = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);

			// operation cancelled
			if (result != JOptionPane.OK_OPTION) {
				throw new OperationCancelledException("> Operation Cancelled");
			}

			// validate the name
			if (!validateTextField(nameField)) {
				validInput = false;
			}

			// validate the password TODO
			if (!validateTextField(passwordField)) {
				validInput = false;
			}

		}

		// return the name and password
		return new String[] { nameField.getText(), String.valueOf(passwordField.getPassword()) };

	}

	/**
	 * Validates the contents of the nameField
	 * 
	 * @param nameField
	 *            The field to be validated
	 * @return True if contains a valid String else false
	 */
	private boolean validateTextField(JTextField nameField) {

		if (nameField.getText().length() == 0) {
			nameField.setBackground(RED);
			return false;
		}

		nameField.setBackground(WHITE);
		return true;
	}

	/**
	 * Helper that adds the message to the dialog
	 * 
	 * @param panel
	 *            The panel that contains the message
	 */
	private void addMessage(JPanel panel, String message) {

		JLabel messageLbl = new JLabel(message);
		messageLbl.setFont(FONT20);
		messageLbl.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

		panel.add(messageLbl, BorderLayout.NORTH);

	}

	/**
	 * Helper that adds two text fields to the Dialog
	 * 
	 * @param panel
	 *            The panel that contains the fields.
	 * @param nameField
	 *            The name field.
	 * @param passwordField
	 *            The password field.
	 */
	private void addTextFields(JPanel panel, JTextField nameField, JTextField passwordField) {

		// add the fields to their own panel for layout purposes
		JPanel fieldPanel = new JPanel(new GridLayout(2, 2, 0, 5));
		fieldPanel.add(new JLabel("Player Name: "));
		fieldPanel.add(nameField);
		fieldPanel.add(new JLabel("Password: "));
		fieldPanel.add(passwordField);

		panel.add(fieldPanel);

	}

	// TODO delete before release
//	public static void main(String[] args) throws OperationCancelledException {
//		String title = "Login Player";
//		String message = "Login Failed, Re-Enter Details";
//
//		String[] namePasword;
//		namePasword = new NameAndPasswordDialog().getNameAndPassword(title);
//		// namePasword = new NameAndPasswordDialog().getNameAndPassword(title, message);
//
//		System.out.println(namePasword[0] + " : " + namePasword[1]);
//	}

}
