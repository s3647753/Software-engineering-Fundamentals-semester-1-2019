package view.model;

import view_interfaces.ViewType;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import view.gui.NameAndPasswordDialog;
import view.gui.OperationCancelledException;
import view_interfaces.View;

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
		}
		
	}

//	@Override
//	public void deRegisterPlayer() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void loginPlayer() {
		int nameIdx = 0, passwordIdx = 1;
		
		
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
		}
		
	}

	@Override
	public void logoutPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateBoard(Board gameBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
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


	
	
}
