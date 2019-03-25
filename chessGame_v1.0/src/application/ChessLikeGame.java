package application;

import view_interfaces.ViewType;
import view_interfaces.View;

import model.BoardImpl;
import model.GameEngineImpl;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import view.model.ViewModel;
import view.views.GuiView;
import view.views.TextView;

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
	private final int WIDTH = 8;
	private final int HEIGHT = 8;
	
	private GameEngine engine;
	private View view;
	private Board board;
	

	/**
	 * Main entry point for Chess-Like_Game
	 * @param args
	 */
	public static void main(String[] args) {
		ViewType viewType;
		
		// select one viewType, this is for development purposes
		// this could even be a command line thing
		viewType = new TextView();
//		viewType = new GuiView();
		
		ChessLikeGame game = new ChessLikeGame();
		//game.startGame(viewType);
		
		float test = 3/2;
		
	}
	
	/**
	 * Starts the ChessLikeGame
	 * 
	 * @param viewType Type of user interface, text for development, GUI for release.
	 */
	private void startGame(ViewType viewType) {
		board = new BoardImpl(WIDTH, HEIGHT);
		engine = new GameEngineImpl(board);
		view = new ViewModel(engine, viewType);
	}

}
