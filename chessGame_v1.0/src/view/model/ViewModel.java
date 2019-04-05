package view.model;

import view_interfaces.ViewType;

import java.util.List;

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
	public void registerPlayer() {
		int nameIdx = 0, passwordIdx = 1;
		
		userInterface.setStatus("> Register a new Player");

		try {
			String[] namePassword = userInterface.registerPlayer();

			System.out.println(namePassword[nameIdx] + " : " + namePassword[passwordIdx]); // TODO remove before release

			String msg = engine.register(namePassword[nameIdx], namePassword[passwordIdx]);

			if (msg != null) {
				userInterface.setStatus(msg);
			}

		} catch (OperationCancelledException e) {
			System.out.println("> Registration Cancelled"); // TODO do I keep this for release
			userInterface.setStatus("> Registration Cancelled");
		} catch (DuplicateNameException e) {
			userInterface.setStatus("> Name Already Exists, Registration Failed");
		}

	}

	// @Override
	// public void deRegisterPlayer() {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void loginPlayer() {
		int nameIdx = 0, passwordIdx = 1;
		
		userInterface.setStatus("> Login a Player");

		try {
			String[] namePassword = userInterface.loginPlayer();

			System.out.println(namePassword[nameIdx] + " : " + namePassword[passwordIdx]); // TODO remove before release

			String msg = engine.login(namePassword[nameIdx], namePassword[passwordIdx]);

			if (msg != null) {
				userInterface.setStatus(msg);
			}

		} catch (OperationCancelledException e) {
			System.out.println("> Login Cancelled"); // TODO delete before final release
			userInterface.setStatus("> Login Cancelled");
		} catch (PlayerNotFoundException e) {
			userInterface.setStatus("> Player not Found, Login Failed");
		}

	}

	// Log a player out
	@Override
	public void logoutPlayer() {
		String msg = null;
		
		userInterface.setStatus("> Log Out a Player");

		try {
			msg = engine.logout(userInterface.logoutPlayer());

		} catch (OperationCancelledException e) {
			msg = ("> LogOut Cancelled");
		}

		if (msg != null && msg.length() > 0) {
			userInterface.setStatus(msg);
		}
	}

	@Override
	public void updateBoard(Board gameBoard) {
		// TODO temp for testing should use the passes in board
	   

	}

	@Override
	public void update(Board gameBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(String message) {
		// TODO Auto-generated method stub

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
      try {
         return engine.movePlayer(from, to);
      } catch (IllegalMoveException | PieceNotFoundException e) {
         setStatus(e.getMessage());
      } catch (Exception e) {
         System.out.println("> Error: Illegal Move");
      }
      
      return false;
   }

   @Override
   public void newGame() {
      // TODO
      System.out.println("new game not implemented for milestone 1");
      
   }

}
