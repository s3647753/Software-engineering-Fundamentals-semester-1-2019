package view.model;

import view_interfaces.ViewType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import enums.Colr;
import model.DuplicateNameException;
import model.IllegalMoveException;
import model.PieceNotFoundException;
import model.PlayerNotFoundException;
import model.Point;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import model_Interfaces.Piece;
import view.gui.NameAndPasswordDialog;
import view.gui.NewGameDialog;
import view.gui.OperationCancelledException;
import view_interfaces.View;

/**
 * 
 * @author Bernard O'Meara
 *
 */

public class ViewModel implements View {
   // can use either text or GUI
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


   public void init() {
      ui.initView(this, engine.getBoard());
      engine.setView(this);

//      update(null, gameStarted);
   }


   @Override
   public void registerPlayer() {

      // userInterface.
      ui.setStatus("> Register a new Player");

      try {
         String[] namePassword = ui.registerPlayer();

         ui.setStatus(engine.register(namePassword[0], namePassword[1]));

      } catch (OperationCancelledException e) {
         ui.setStatus("> Registration Cancelled");
      }

   }


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


   // Logout a player out
   @Override
   public void logoutPlayer() {

      try {
         ui.setStatus(engine.logout(ui.logoutPlayer(engine.getLoggedInPlayerNames())));
         
      } catch (OperationCancelledException e) {
         ui.setStatus(e.getMessage());
      }
   }


   @Override
   public void update(Observable observable, Object gameStarted) {
      this.gameStarted = (boolean) gameStarted;

      setPlayerTurn();

      ui.setStatus(engine.getStatus());
      ui.updateBoard(board());
      ui.setMovesRemaining(engine.turnsRemaining());
      ui.setPlayerScores(
            engine.getPlayerScore(Colr.WHITE),
            engine.getPlayerScore(Colr.BLACK));

      // TODO update the other panels
   }


   @Override
   public void split() {
      if (from != null && engine.split(from)) {

         ui.highlight(from, false);
         ui.showLegalMoves(board().getLegalMoves(from), false);
         ui.setMerged(board().getCell(from).isMerged());

         from = null;
         to = null;
      }
   }


   @Override
   public void movePlayer(Point from, Point to) {
      if (engine.movePlayer(from, to) && !gameStarted) {
//         gameStarted = true; // TODO is this buggy
         // TODO can controller call GE direct
      }
   }


   @Override
   public void newGame() {

      List<String> names = engine.getLoggedInPlayerNames();

//      // TODO temp until the register and login works
//      if (names.size() < 4) {
//         names.add("Ben");
//         names.add("Bernie");
//         names.add("Matt");
//         names.add("Shaun");
//      }
//      // TODO end of temp

      String[] preferences;
      try {
         preferences = ui.newGame(names);

         if (gameStarted = engine.newGame(preferences[0],
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
      }
   }


   /**
    * Sets the players turn message in the UI
    */
   private void setPlayerTurn() {
      String turnMsg = (engine.whoseTurn() == Colr.WHITE) ? "Whites Turn" : "Blacks Turn";

      ui.setPlayerTurn(turnMsg);

   }


   @Override
   public void notifyGameOver(String message) {
      // TODO Auto-generated method stub

   }


   @Override
   public void notifyMoveIsDangerous(String message) {
      // TODO Auto-generated method stub

   }


   // there are no checks so far
   @Override
   public void squareClicked(Point point) {

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
         ui.setMerged(board().getCell(from).isMerged());

      }

      // moving to
      else if (from != null && engine.getLegalMoves(from).contains(point)) {
         to = point;
         movePlayer(from, to);

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


   /*
    * gets the current game board
    */
   private Board board() {
      return engine.getBoard();
   }

}
