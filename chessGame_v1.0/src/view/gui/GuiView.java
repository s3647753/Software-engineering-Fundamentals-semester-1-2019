package view.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

import enums.Colr;
import model.Point;
import model_Interfaces.Board;
import view_interfaces.View;
import view_interfaces.ViewType;

/**
 * A GUI to be used with the Chess Like Game.
 * 
 * @author Bernard O'Meara
 *
 */
@SuppressWarnings("serial")
public class GuiView extends JFrame implements ViewType {

   private GuiChessBoard guiBoard;
   private ToolBar toolbar;
   private StatusBar statusBar;
   private PlayerPanel playerWhite, playerBlack;
   private String[] preferences;


   public GuiView() {
      super();

      setTitle("Chess Like Game");
      setBounds(300, 50, 900, 700);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);

      setVisible(true);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#initView(view_interfaces.View,
    * model_Interfaces.Board)
    */
   @Override
   public void initView(View viewModel, Board board) {
      guiBoard = new GuiChessBoard(viewModel, board);
      toolbar = new ToolBar();
      statusBar = new StatusBar();
      playerWhite = new PlayerPanel(viewModel, Colr.WHITE);
      playerBlack = new PlayerPanel(viewModel, Colr.BLACK);

      setJMenuBar(new MenuBarChess(viewModel));
      add(guiBoard, BorderLayout.CENTER);
      add(toolbar, BorderLayout.NORTH);
      add(statusBar, BorderLayout.SOUTH);
      add(playerWhite, BorderLayout.WEST);
      add(playerBlack, BorderLayout.EAST);

      setVisible(true);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#registerPlayer()
    */
   @Override
   public String[] registerPlayer() throws OperationCancelledException {
      String title = "Player Registration";

      return new NameAndPasswordDialog().getNameAndPassword(title);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#loginPlayer()
    */
   @Override
   public String[] loginPlayer() throws OperationCancelledException {
      String title = "Player Login";

      return new NameAndPasswordDialog().getNameAndPassword(title);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#logoutPlayer(java.util.List)
    */
   @Override
   public String logoutPlayer(List<String> names)
         throws OperationCancelledException {

      String[] namesArray = {};
      namesArray = (String[]) names.toArray(namesArray);

      // get the name of the player to log out
      String name = (String) JOptionPane.showInputDialog(
            null,
            "Select Player to Log Out",
            "Logout Player",
            JOptionPane.PLAIN_MESSAGE,
            null, namesArray, "");

      // logout cancelled
      if (name == null || name.length() == 0) {
         throw new OperationCancelledException("Logout cancelled");
      }

      return name;
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#newGame(java.util.List)
    */
   @Override
   public String[] newGame(List<String> names)
         throws OperationCancelledException, PlayersNotLoggedInException {

      return (preferences = new NewGameDialog().getGamePreferences(names,
            preferences));
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#updateBoard(model_Interfaces.Board)
    */
   @Override
   public void updateBoard(Board gameBoard) {
      guiBoard.update(gameBoard);
      revalidate();
      repaint();
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#setStatus(java.lang.String)
    */
   @Override
   public void setStatus(String message) {
      statusBar.setMessage(message);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#highlight(model.Point, boolean)
    */
   @Override
   public void highlight(Point point, boolean set) {
      guiBoard.highlight(point, set);

   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#showLegalMoves(java.util.List, boolean)
    */
   @Override
   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      guiBoard.showLegalMoves(legalMoves, set);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#setPlayerTurn(enums.Colr)
    */
   @Override
   public void setPlayerTurn(Colr color) {
      toolbar.setTurnColor(color == Colr.WHITE ? "Whites Turn" : "Blacks Turn");
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#setMovesRemaining(int)
    */
   @Override
   public void setMovesRemaining(int remaining) {
      statusBar.setMovesRemaining(remaining);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#setMerged(boolean)
    */
   @Override
   public void setMerged(boolean merged) {
      // Both the split buttons perform the same function for both players
      playerWhite.setMerged(merged);
      playerBlack.setMerged(merged);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#setPlayerNames(java.lang.String,
    * java.lang.String)
    */
   @Override
   public void setPlayerNames(String whiteName, String blackName) {
      playerWhite.setName(whiteName);
      playerBlack.setName(blackName);
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.ViewType#setPlayerScores(int, int)
    */
   @Override
   public void setPlayerScores(int whiteScore, int blackScore) {
      playerWhite.setScore(String.valueOf(whiteScore));
      playerBlack.setScore(String.valueOf(blackScore));
   }

}
