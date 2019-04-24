package application;

import view_interfaces.ViewType;
import view_interfaces.View;

import model.BoardImpl;
import model.GameEngineImpl;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import view.gui.GuiView;
import view.model.ViewModel;
import view.text.TextView;

/**
 * launches the Chess-Like-Game.
 * 
 * For now I an not running the view in a separate thread as
 * I would prefer no user inputs while the gameEngine is thinking.
 * 
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
		ViewType viewType;
		
		// select one viewType for use in the game play only;
//		viewType = new TextView();
		viewType = new GuiView();
		
		ChessLikeGame game = new ChessLikeGame();
		game.startGame(viewType);

		
	}
	
	/**
	 * Starts the ChessLikeGame
	 * 
	 * @param viewType Type of user interface, text for development, GUI for release.
	 */
	private void startGame(ViewType viewType) {
	   
		Board board = new BoardImpl();
		GameEngine engine = new GameEngineImpl(board);
		
		View viewModel = new ViewModel(engine, viewType);
		viewModel.init();

	}

}
