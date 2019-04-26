package view.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

   private View viewModel;
   private ChessBoard guiBoard;
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


   @Override
   public void initView(View viewModel, Board board) {
      this.viewModel = viewModel;
      guiBoard = new ChessBoard(viewModel, board);
      toolbar = new ToolBar();
      statusBar = new StatusBar();
      playerWhite = new PlayerPanel(viewModel, Colr.WHITE);
      playerBlack = new PlayerPanel(viewModel, Colr.BLACK);

      setJMenuBar(new ChessMenuBar(viewModel));
      add(guiBoard, BorderLayout.CENTER);
      add(toolbar, BorderLayout.NORTH);
      add(statusBar, BorderLayout.SOUTH);
      add(playerWhite, BorderLayout.WEST);
      add(playerBlack, BorderLayout.EAST);

      setVisible(true);
   }


   @Override
   public String[] registerPlayer() throws OperationCancelledException {
      String title = "Player Registration";

      return new NameAndPasswordDialog().getNameAndPassword(title);
   }


   @Override
   public String[] loginPlayer() throws OperationCancelledException {
      String title = "Player Login";

      return new NameAndPasswordDialog().getNameAndPassword(title);
   }


   @Override
   public String logoutPlayer(List<String> names) throws OperationCancelledException {
      String[] namesArray = {};

      // TODO temp commented out until ge and login are ready
//      namesArray = (String[]) names.toArray(namesArray);

      // TODO temp until ge is ready
      namesArray = new String[] { "Ben", "Bernie", "Matt", "Shaun" };

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


   @Override
   public String[] newGame(List<String> names)
         throws OperationCancelledException {

      return (preferences = new NewGameDialog().getGamePreferences(names, preferences));
   }


   // called by the ViewModel to update the game board
   @Override
   public void updateBoard(Board gameBoard) {
      guiBoard.update(gameBoard); // TODO this is init should be set
      revalidate();
      repaint();
   }


   @Override
   public void setStatus(String message) {
      statusBar.setMessage(message);
   }


   @Override
   public void highlight(Point point, boolean set) {
      guiBoard.highlight(point, set);

   }


   @Override
   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      guiBoard.showLegalMoves(legalMoves, set);
   }


   @Override
   public void setPlayerTurn(String message) {
      toolbar.setTurnColor(message);
   }


   @Override
   public void setMovesRemaining(int remaining) {
      statusBar.setMovesRemaining(remaining);
   }


   @Override
   public void setMerged(boolean merged) {
      // TODO only set the applicable player to true
      playerWhite.setMerged(merged);
      playerBlack.setMerged(merged);
   }


   @Override
   public void setPlayerNames(String whiteName, String blackName) {
      playerWhite.setName(whiteName);
      playerBlack.setName(blackName);
   }


   @Override
   public void setPlayerScores(int whiteScore, int blackScore) {
      playerWhite.setScore(String.valueOf(whiteScore));
      playerBlack.setScore(String.valueOf(blackScore));
   }

}
