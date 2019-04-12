package view.model;

import view_interfaces.ViewType;

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
	// can be either text or GUI
	private ViewType userInterface;
	private GameEngine engine;

	public ViewModel(GameEngine engine, ViewType viewType) {
		this.engine = engine;
		userInterface = viewType;
		
	}

	@Override
	public String registerPlayer() {
		int nameIdx = 0, passwordIdx = 1;
		String msg = null;
		
//		userInterface.
		setStatus("> Register a new Player");

		try {
			String[] namePassword = userInterface.registerPlayer();

			System.out.println(namePassword[nameIdx] + " : " + namePassword[passwordIdx]); // TODO remove before release

			msg = engine.register(namePassword[nameIdx], namePassword[passwordIdx]);

			if (msg != null) {
				setStatus(msg);
			}

		} catch (OperationCancelledException e) {
			setStatus("> Registration Cancelled");
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
		
		setStatus("> Login a Player");

		try {
			String[] namePassword = userInterface.loginPlayer();

			System.out.println(namePassword[nameIdx] + " : " + namePassword[passwordIdx]); // TODO remove before release

			String msg = engine.login(namePassword[nameIdx], namePassword[passwordIdx]);

			if (msg != null) {
				setStatus(msg);
			}

		} catch (OperationCancelledException e) {
			setStatus("> Login Cancelled");
		} 
	}

	// Log a player out
	@Override
	public void logoutPlayer() {
		String msg = null;
		
		setStatus("> Log Out a Player");

		try {
			msg = engine.logout(userInterface.logoutPlayer());

		} catch (OperationCancelledException e) {
			msg = ("> LogOut Cancelled");
		}

		if (msg != null && msg.length() > 0) {
			setStatus(msg);
		}
	}

	@Override // TODO get rid of this method (redundant)
	public void updateBoard(Board gameBoard) {
		userInterface.updateBoard(gameBoard);
	}

	@Override // TODO get rid of this method (redundant)
	public void update(Board gameBoard) {
	   userInterface.updateBoard(gameBoard);
	   
		// TODO must update all view, e.g. score etc

	}
	
	@Override
   public void update(Observable arg0, Object arg1) {
      // TODO Auto-generated method stub
	   userInterface.updateBoard(engine.getBoard());
      
   }

	@Override
	public void setStatus(String message) {
		userInterface.setStatus(message);
	}

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
   public boolean movePlayer(Point from, Point to) {

         return engine.movePlayer(from, to);
   }

   @Override
   public void newGame() {
      // TODO this method needs lots of work to complete
      
      System.out.println("new game not fully implemented for milestone 1");
      userInterface.initView(this);
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
      // TODO Auto-generated method stub
      
   }

   @Override
   public void setPlayerTurn(int playerNum) {
      // TODO Auto-generated method stub
      
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

   

}
