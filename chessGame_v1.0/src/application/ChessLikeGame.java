package application;

import view_interfaces.ViewType;
import view_interfaces.View;

import model.BoardImpl;
import model.GameEngineImpl;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import view.gui.GuiView;
import view.model.ViewModel;

/**
 * launches the Chess-Like-Game, DeskTop GUI version.
 * 
 * @author Bernard O'Meara
 *
 */

public class ChessLikeGame {	

	/**
	 * Main entry point for Chess-Like_Game
	 * @param args
	 */
	public static void main(String[] args) {
		new ChessLikeGame().startGame();

	}
	
	/**
	 * Initializes the major components and Starts the ChessLikeGame
	 * 
	 */
	private void startGame() {
	   ViewType viewType = new GuiView();
		Board board = new BoardImpl();
		GameEngine engine = new GameEngineImpl(board);
		
		View viewModel = new ViewModel(engine, viewType);
		viewModel.init();

	}

}
