package view.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import enums.Colr;
import model.Point;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import view_interfaces.ViewType;
import view.gui.OperationCancelledException;
import view.gui.PlayersNotLoggedInException;
import view_interfaces.View;

/**
 * Handles view logic for all types of user interfaces. Can drive any single
 * complying user interface, e.g., text or GUI.
 * 
 * @author Bernard O'Meara
 *
 */
public class ViewModel implements View {
   private ViewType ui;
   private GameEngine engine;
   private Point from = null;
   private Point to = null;
   private List<Point> legalMoves;
   private boolean gameStarted = false;


   public ViewModel(GameEngine engine, ViewType userInterface) {
      this.engine = engine;
      ui = userInterface;

      legalMoves = new ArrayList<>();
   }


   /*
    * (non-Javadoc)
    * @see view_interfaces.View#init()
    */
   @Override
   public void init() {
      ui.initView(this, engine.getBoard());
      engine.setView(this);
   }


   /*
    * (non-Javadoc)
    * @see view_interfaces.View#registerPlayer()
    */
   @Override
   public void registerPlayer() {
      ui.setStatus("Register a new Player");

      try {
         String[] namePassword = ui.registerPlayer();

         ui.setStatus(engine.register(namePassword[0], namePassword[1]));

      } catch (OperationCancelledException e) {
         ui.setStatus("> Registration Cancelled");
      }

   }


   /*
    * (non-Javadoc)
    * @see view_interfaces.View#loginPlayer()
    */
   @Override
   public void loginPlayer() {
      ui.setStatus("Login a Player");

      try {
         String[] namePassword = ui.loginPlayer();

         ui.setStatus(engine.login(namePassword[0], namePassword[1]));

      } catch (OperationCancelledException e) {
         ui.setStatus("> Login Cancelled");
      }
   }


   /*
    * (non-Javadoc)
    * @see view_interfaces.View#logoutPlayer()
    */
   @Override
   public void logoutPlayer() {

      try {
         ui.setStatus(
               engine.logout(ui.logoutPlayer(engine.getLoggedInPlayerNames())));

      } catch (OperationCancelledException e) {
         ui.setStatus(e.getMessage());
      }
   }


   /*
    * (non-Javadoc)
    * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
    */
   @Override
   public void update(Observable observable, Object gameStarted) {
      this.gameStarted = (boolean) gameStarted;

      ui.setPlayerTurn(engine.whoseTurn());
      ui.setStatus(engine.getStatus());
      ui.updateBoard(board());
      ui.setMovesRemaining(engine.turnsRemaining());
      ui.setPlayerScores(
            engine.getPlayerScore(Colr.WHITE),
            engine.getPlayerScore(Colr.BLACK));

   }


   /*
    * (non-Javadoc)
    * @see view_interfaces.View#split()
    */
   @Override
   public void split() {
      if (from != null && engine.split(from)) {

         ui.highlight(from, false);
         ui.showLegalMoves(board().getLegalMoves(from), false);
         ui.setMerged(board().isMergedPiece(from));

         from = null;
         to = null;
      }
   }


   /*
    * (non-Javadoc)
    * @see view_interfaces.View#newGame()
    */
   @Override
   public void newGame() {
      List<String> names = engine.getLoggedInPlayerNames();
      String[] preferences;

      try {
         preferences = ui.newGame(names);

         if (gameStarted = engine.newGame(
               preferences[0],
               preferences[1],
               Integer.valueOf(preferences[2]),
               Integer.valueOf(preferences[3]))) {

            ui.setPlayerNames(preferences[0], preferences[1]);

            ui.setPlayerScores(
                  engine.getPlayerScore(Colr.WHITE),
                  engine.getPlayerScore(Colr.BLACK));

            ui.setStatus("New Game started");
         }

      } catch (OperationCancelledException e) {
         ui.setStatus("New Game cancelled");
         gameStarted = false;
      } catch (PlayersNotLoggedInException e) {
         ui.setStatus(e.getMessage());
         gameStarted = false;
      } 
   }


   /*
    * (non-Javadoc)
    * 
    * @see view_interfaces.View#squareSelected(model.Point)
    */
   @Override
   public void squareSelected(Point point) {

      if (!gameStarted) {
         ui.setStatus("Setup New Game before playing");
         return;
      }

      // moving from
      if (from == null &&
            engine.getBoard().getPiecesAt(point).size() != 0 &&
            engine.getBoard().getPiecesAt(point).get(0).getColor() == engine
                  .whoseTurn()) {

         from = point;
         ui.setStatus("Piece Selected");

         // highlight the selected piece
         ui.highlight(point, true);

         // mark the legal moves
         legalMoves = board().getLegalMoves(from);
         ui.showLegalMoves(legalMoves, true);

         // setting the split button logic
         ui.setMerged(board().isMergedPiece(from));

      }

      // moving to
      else if (from != null && engine.getLegalMoves(from).contains(point)) {
         to = point;
         engine.movePlayer(from, to);
         // movePlayer(from, to);

         // reset for the next move
         ui.highlight(from, false);
         ui.showLegalMoves(legalMoves, false);
         from = null;
         to = null;
      }

      // player made illegal selection
      else {
         ui.setStatus("Illegal selection");
      }

   }


   /**
    * Gets the current game board from the game engine.
    * 
    * @return The current game board.
    */
   private Board board() {
      return engine.getBoard();
   }

}
