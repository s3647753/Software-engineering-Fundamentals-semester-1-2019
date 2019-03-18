package model;

import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import view_interfaces.View;

public class GameEngineImpl implements GameEngine {
	// examples only
	private BoardImpl board;
	private Players players = new Players();
	private View view;
	
	
	public GameEngineImpl(Board board) {
		// TODO Auto-generated constructor stub
	}


	// either this or use the observer pattern
	@Override
	public void setView(View view) {
		this.view = view;
	}
	
	

}
