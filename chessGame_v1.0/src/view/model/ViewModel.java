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
   private boolean split = false;

   public ViewModel(GameEngine engine, ViewType viewType) {
      this.engine = engine;
      ui = viewType;
   }

   public void init() {
      ui.initView(this, engine.getBoard());
      engine.setView(this);
      legalMoves = new ArrayList<>();
      update(null, null);
   }

   @Override
   public String registerPlayer() {
      int nameIdx = 0, passwordIdx = 1;
      String msg = null;

      // userInterface.
      ui.setStatus("> Register a new Player");

      try {
         String[] namePassword = ui.registerPlayer();

         System.out.println(namePassword[nameIdx] + " : " + namePassword[passwordIdx]); // TODO
                                                                                        // remove
                                                                                        // before
                                                                                        // release

         msg = engine.register(namePassword[nameIdx], namePassword[passwordIdx]);

         if (msg != null) {
            ui.setStatus(msg);
         }

      } catch (OperationCancelledException e) {
         ui.setStatus("> Registration Cancelled");
      }

      return msg;
   }

   // @Override
   // public void deRegisterPlayer() {
   // // TODO Auto-generated method stub
   //
   // }

   @Override
   public void loginPlayer() {
      int nameIdx = 0, passwordIdx = 1;

      ui.setStatus("> Login a Player");

      try {
         String[] namePassword = ui.loginPlayer();

         System.out.println(namePassword[nameIdx] + " : " + namePassword[passwordIdx]); // TODO
                                                                                        // remove
                                                                                        // before
                                                                                        // release

         String msg = engine.login(namePassword[nameIdx], namePassword[passwordIdx]);

         if (msg != null) {
            ui.setStatus(msg);
         }

      } catch (OperationCancelledException e) {
         ui.setStatus("> Login Cancelled");
      }
   }

   // Log a player out
   @Override
   public void logoutPlayer() {
      String msg = null;

      ui.setStatus("> Log Out a Player");

      try {
         msg = engine.logout(ui.logoutPlayer());

      } catch (OperationCancelledException e) {
         msg = ("> LogOut Cancelled");
      }

      if (msg != null && msg.length() > 0) {
         ui.setStatus(msg);
      }
   }

   // @Override // TODO get rid of this method (redundant)
   // public void updateBoard(Board gameBoard) {
   // userInterface.updateBoard(gameBoard);
   // }

   // @Override // TODO get rid of this method (redundant)
   // public void update(Board gameBoard) {
   // userInterface.updateBoard(gameBoard);
   //
   // // TODO must update all view, e.g. score etc
   //
   // }

   // this is the only method that the GE should call
   @Override
   public void update(Observable arg0, Object arg1) {
      ui.setStatus(engine.getStatus());
      setPlayerTurn();
      ui.updateBoard(engine.getBoard());
      // TODO update the other panels
   }

   // @Override
   // public void setStatus(String message) {
   // // System.out.println("setStatus-viewModel");
   // userInterface.setStatus(message);
   // // System.out.println(engine.getBoard()); // TODO
   // }

   @Override
   public boolean splitPieces() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List<Piece> getPieceList(int row, int column) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void movePlayer(Point from, Point to) {
      
      if(engine.movePlayer(from, to, split) && split) {
         toggleSplit();
      }
      
      // TODO this should not be here it should be called by the GE
      // But it is here until GE adds the line of code to movePlayer
      update(null, null);
   }

   @Override
   public void newGame() {
      // TODO this method needs lots of work to complete

      System.out.println("new game not fully implemented for milestone 1");
      ui.initView(this, engine.getBoard()); // TODO this is not correct
   }

   @Override
   public void updateScore(int player1, int player2) {
      // TODO Auto-generated method stub

   }

   @Override
   public void setPlayerName(int playerNum, String name) {
      // TODO Auto-generated method stub

   }

   @Override
   public void setPlayerColor(int playerNum, Colr color) {
      // TODO

   }

   /**
    * Sets the players turn message in the UI
    */
   private void setPlayerTurn() {
      
      if(engine.whoseTurn() == Colr.WHITE) {
            ui.setPlayerTurn(" Whites Turn ");
         }
      
      else if(engine.whoseTurn() == Colr.BLACK) {
         ui.setPlayerTurn(" Blacks Turn ");
      }

   }
   

   @Override
   public void notifyGameOver(String message) {
      // TODO Auto-generated method stub

   }

   @Override
   public void notifyMoveIsDangerous(String message) {
      // TODO Auto-generated method stub

   }

   @Override
   public boolean askIfPlayerWantsToSplit(String message) {
      // TODO Auto-generated method stub
      return false;
   }

   // there are no checks so far
   @Override
   public void squareClicked(Point point) {

      // moving from
      if (from == null && 
            engine.getBoard().getPiecesAt(point).size() != 0 &&
            engine.getBoard().getPiecesAt(point).get(0).getColor() == 
            engine.whoseTurn()) {
         
         from = point;
         ui.setStatus("Piece Selected"); // TODO temp
         
         // highlight the selected piece
         ui.highlight(point, true);
         
         // mark the legal moves
         legalMoves = engine.getBoard().getLegalMoves(from);
         ui.showLegalMoves(legalMoves, true);
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
      
//      update(null, null);

   }

   @Override
   public void toggleSplit() {
      split = !split;
      ui.updateSplit(split);
   }

}
