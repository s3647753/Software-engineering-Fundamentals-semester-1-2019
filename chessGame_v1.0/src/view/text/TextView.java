package view.text;

import java.util.List;
import java.util.Observable;
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
   public void initView(View viewModel, Board board) { // TODO I probably dont need this
                                                       // parameter
      this.viewModel = viewModel;
      mainMenu();
   }


   /**
    * Main game menu for the console based game
    */
   private void mainMenu() {
      boolean quit = false;

      while (!quit) {
         // display the current board
         viewModel.update(new Observable(), null);

         displayMainMenu();

         System.out.print("\n> Selection: ");
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
               // viewModel.update(new Observable(), null);
               break;
            case "6":
               // viewModel.update(new Observable(), null);
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
   public String logoutPlayer(List<String> names)
         throws OperationCancelledException {
      
      return getString("> Enter Name to Logout");
   }


   @Override
   public void updateBoard(Board gameBoard) {
      System.out.println(gameBoard.toString());
   }


   @Override
   public void setStatus(String message) {
      System.out.println("text status");
      System.out.println(message);
      System.out.println();
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

      while (!validInput) {
         row = getInt("\n> enter the row of the piece to move, (Top row is 0)");
         col = getInt("> enter the column of the piece to move, (LH column is 0");
         from = new Point(row, col);

         row = getInt("\n> enter the row of the target square, Top row is 0");
         col = getInt("> enter the column of the target, LH column is 0");
         to = new Point(row, col);

         System.out.printf("\n> Moving from (Row:%d, Col:%d) ", from.getRow(),
               from.getCol());
         System.out.printf("To (Row:%d, Col:%d)\n", to.getRow(), to.getCol());

         viewModel.movePlayer(from, to);
         validInput = true;
      }
   }


   private int getInt(String msg) {
      boolean valid = false;
      int value = 0;

      while (!valid) {

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


   @Override
   public void highlight(Point point, boolean set) {
      // TODO Auto-generated method stub

   }


   @Override
   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      // TODO Auto-generated method stub

   }


   @Override
   public void setPlayerTurn(String string) {
      // TODO Auto-generated method stub

   }


   // @Override
   // public void updateSplit(boolean split) {
   // // TODO Auto-generated method stub
   //
   // }

   @Override
   public void setMovesRemaining(int remaining) {
      // TODO Auto-generated method stub

   }


   @Override
   public void setMerged(boolean merged) {
      // TODO Auto-generated method stub

   }


   @Override
   public void setPlayerNames(String whiteName, String blackName) {
      // TODO Auto-generated method stub

   }


   @Override
   public void setPlayerScores(int whiteScore, int blackScore) {
      // TODO Auto-generated method stub

   }


   @Override
   public String[] newGame(List<String> names) throws OperationCancelledException {
      // TODO Auto-generated method stub
      return null;
   }

}
