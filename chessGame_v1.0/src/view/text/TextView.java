package view.text;

import java.util.Scanner;

import model_Interfaces.Board;
import view.gui.OperationCancelledException;
import view_interfaces.View;
import view_interfaces.ViewType;

public class TextView implements ViewType {
	private View viewModel;
	private Scanner sc = new Scanner(System.in);

	public TextView() {
		super();
		System.out.println("> Game using Textual for development purposes");
	}

	@Override
	public void initView(View viewModel) {
		this.viewModel = viewModel;
		mainMenu();
	}

	/**
	 * Main game menu for the console based game
	 */
	private void mainMenu() {
		boolean quit = false;

		while (!quit) {
			System.out.println("\nChess Like Game - Main Menu.");
			System.out.println("1) Register Player");
			System.out.println("2) Login Player");
			System.out.println("3) Log Out Player");
			System.out.println("4) Start New Game");
			System.out.println("Q) Quit");

			System.out.print("> Selection: ");
			String selection = sc.nextLine();

			switch (selection.toUpperCase()) {
			case "1":
				viewModel.registerPlayer();
				break;
			case "2":
				viewModel.loginPlayer();
				break;
			case "3":
				viewModel.logoutPlayer();
				break;
			case "4":
				System.out.println("4 - Not Implemented");
				break;
			case "Q":
				System.out.println("Q");
				quit = true;
				break;
			}
		}
		System.out.println("> Goodbye!");
	}

	// gets from the user name and password
	@Override
	public String[] registerPlayer() throws OperationCancelledException {
		return getNameAndPassword();
	}

	// @Override
	// public String deRegisterPlayer() throws UserInputException {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public String[] loginPlayer() throws OperationCancelledException {
		return getNameAndPassword();
	}

	@Override
	public String logoutPlayer() throws OperationCancelledException {
		return getString("> Enter Name to Logout");
	}

	@Override
	public boolean updateBoard(Board gameBoard) {
		System.out.println(gameBoard.toString());
		return true; // TODO do I need this return, I think not
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatus(String message) {
		System.out.println(message);
	}

	// gets the user name and password
	private String[] getNameAndPassword() throws OperationCancelledException {
		String[] values = new String[2];

		values[0] = getString("> Enter Your Name");
		values[1] = getString("> Enter Your Password");

		return values;
	}

	// gets a string from the user
	private String getString(String msg) throws OperationCancelledException {
		boolean isValue = false;
		String str = "";

		while (!isValue) {
			System.out.print(msg);
			System.out.println(" <Q to Cancel>");
			System.out.print("> : ");
			str = sc.nextLine();

			// operation cancelled
			if (str.toUpperCase().equals("Q")) {
				throw new OperationCancelledException();
			}

			// empty strings are not accepted
			if (str.length() > 0) {
				isValue = true;
			}
		}
		return str;
	}

}
