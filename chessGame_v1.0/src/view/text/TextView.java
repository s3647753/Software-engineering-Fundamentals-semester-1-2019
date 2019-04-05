package view.text;

import java.util.Scanner;

import model.BoardImpl;
import model.Point;
import model_Interfaces.Board;
import view.gui.OperationCancelledException;
import view_interfaces.View;
import view_interfaces.ViewType;

/**
 * 
 * @author Bernard O'Meara
 *
 */
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
		   displayMainMenu();

			System.out.print("> Selection: ");
			String selection = sc.nextLine();
			System.out.println();

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
				viewModel.newGame();
				break;
			case "5":
            movePlayer();
            break;
			case "6":
            System.out.println("6 - temporarily showing default board");
            System.out.println(new BoardImpl()); // TODO
            break;
			case "Q":
				System.out.println("Q");
				quit = true;
				break;
			}
		}
		System.out.println("> Goodbye!");
	}
	
	/**
	 * Displays the main menu to the console
	 */
	private void displayMainMenu() {
	   System.out.println("\nChess Like Game - Main Menu.");
      System.out.println("1) Register Player");
      System.out.println("2) Login Player");
      System.out.println("3) Log Out Player");
      System.out.println("4) Start New Game");
      System.out.println("5) Move Player");
      System.out.println("6) Show Board");
      System.out.println("Q) Quit");
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
	
	/**
	 * gets the from and to coordinates for a player to move a piece
	 * 
	 */
	private void movePlayer() {
	   Point from;
	   Point to;
	   int row, col;
	   boolean validInput = false;
	   
	   while(!validInput) {
	      row = getInt("\n> enter the row of the piece to move, Top row is 0");
	      col = getInt("> enter the column of the piece to move, LH column is 0");
	      from = new Point(row, col);
	      
	      row = getInt("\n> enter the row of the target square, Top row is 0");
         col = getInt("> enter the column of the target, LH column is 0");
         to = new Point(row, col);
         
         System.out.printf("\n> Moving from (Row:%d, Col:%d) ", from.getRow(), from.getCol());
         System.out.printf("To (Row:%d, Col:%d)\n", to.getRow(), to.getCol());
	      
         viewModel.movePlayer(from, to);
         validInput = true;
	   }
	}
	
	private int getInt(String msg) {
	   boolean valid = false;
	   int value = 0;
	   
	   while(!valid) {
	      
	      try {
	         System.out.println(msg);
	         System.out.print("> : ");
            value = sc.nextInt();
            valid = true;
            
            // clear the buffer
            sc.nextLine();
         } catch (Exception e) {
            System.out.println("> Invali input\n");
         }
	      
	   }
	   
	   return value;
	}

}
