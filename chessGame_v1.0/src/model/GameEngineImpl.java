package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import enums.Colr;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import model_Interfaces.Piece;
import model_Interfaces.Player;
import view_interfaces.View;

/**
 * Application of the Game Engine Interface
 * This class deals with most of the game logic
 * @author Matt Eletva
 *
 */

public class GameEngineImpl extends Observable implements GameEngine {
	private Board board;

	private Map<Colr, Player> playerList = new HashMap<Colr, Player>();
	
	//current placeholder, may not be final
	private Map<Colr, Integer> turns = new HashMap<Colr, Integer>();
	
	//TODO Depreciated, remove once playerList is implemented
	//private Map<String, Integer> players = new HashMap<String, Integer>();
	//private Map<Colr, String> playerColr = new HashMap<Colr, String>();

	private int maxMove;

	private Login login;
	private Colr currentTurn;
	private String statusMessage = "";

	private boolean gameRunning;

	private Colr winner;


	public GameEngineImpl(Board board) {
		this.board = board;
		login = new Login();
		playerList.put(Colr.WHITE, null);
		playerList.put(Colr.BLACK, null);
		turns.put(Colr.WHITE, 0);
		turns.put(Colr.BLACK, 0);
		gameRunning = false;
		maxMove = 0;
	}


	@Override
	public boolean newGame(String playerWhite, String playerBlack, int player1TurnLimit, int player2TurnLimit) {
		if(!gameRunning) {
			//temp until login in complete
			//login.getPlayerList().contains(playerWhite) && login.getPlayerList().contains(playerBlack)
			if(true) {

				playerList.replace(Colr.WHITE, new PlayerImpl(playerWhite));
				playerList.replace(Colr.BLACK, new PlayerImpl(playerBlack));

				currentTurn = Colr.WHITE;
				
				maxMove = (player1TurnLimit+player2TurnLimit/2);
				
				turns.replace(Colr.WHITE, maxMove);
				turns.replace(Colr.BLACK, maxMove);

				gameRunning = true;
			}else {
				notifyAllObservers("Player Does not exist");
			}
		}
		return gameRunning;
	}


	@Override
	public void setView(View view) {
		addObserver(view);
	}


	@Override
	public void addBoard(Board board) {
		this.board = board;
	}


	@Override
	public void setMaxMoves(int player1Moves, int player2Moves) {
		//remove
	}


	@Override
	public boolean movePlayer(Point from, Point to) {
		List<Piece> pieces = board.getPiecesAt(from);
		boolean moveSuccessful = false;
		int piecesTaken = 0;
		String message = null;
		//TODO implement game end when all pieces taken
		if(pieces.size()!=0) {
			if(pieces.get(0).getColor() == currentTurn) {
				if(!turnLimitReached()) {
					try {
						piecesTaken = board.movePiece(from, to);
						moveSuccessful = true;
						reduceMoves();
						swapTurn();
						message = "Piece moved";
					}catch(IllegalMoveException e){
						moveSuccessful = false;
						message = "Move not Legal";
					}catch(PieceNotFoundException e) {
						moveSuccessful = false;
						message = "No piece in location";
					}
					
					increaseScore(piecesTaken);
					notifyAllObservers(message);
					
					if(turnLimitReached()) {
						endGame();
					}
				}
			}else {
				moveSuccessful = false;
				message = "It is " + currentTurn + "'s move";
			}
		}else {
			moveSuccessful = false;
			message = "No piece in location";
		}
		return moveSuccessful;
	}
	
	
	public int getPlayerScore(Colr colour) {
		return playerList.get(colour).getPoints();
	}
	
	/*public int[] getAllPlayerScore() {
		int[] scores = new int[2];
		scores[0] = playerList.get(Colr.WHITE).getPoints();
		scores[1] = playerList.get(Colr.BLACK).getPoints();
		return scores;
	}*/

	private void swapTurn() {
		if(currentTurn == Colr.WHITE) {
			currentTurn = Colr.BLACK;
		}else {
			currentTurn = Colr.WHITE;
		}
	}


	@Override
	public Colr whoseTurn() {
		return currentTurn;
	}


	@Override
	public String register(String username, String password) {
		Boolean registerSuccess = null;
		String message;
		Register register = new Register();
		try {
			registerSuccess = register.registerPlayer(username, password);
			if(registerSuccess) {
				message = "Username " + username + " has successfully registered";
			}else {
				message = "Username " + username + " has not successfully registered";
			}
		}catch(DuplicateNameException e) {
			registerSuccess = false;
			message = "Username " + username + " already exists";
		}
		return message;
	}
	

	@Override
	public String login(String username, String password) {
		String message = username + " has successfully logged in";
		boolean passwordCorrect = true;
		try{
			passwordCorrect = login.loginPlayer(username, password);
		}catch(PlayerNotFoundException e) {
			message = "Player with username " + username + "does not exist";
		}
		if(!passwordCorrect) {
			message = "Password incorrect";
		}
		return message;
	}

	
	@Override
	public String logout(String username) {
		boolean logoutSuccessful = login.logoutPlayer(username);
		String message;
		if(logoutSuccessful) {
			message = username + " has successfully logged out.";
		}else {
			message = username + " was not succcessfully logged out";
		}
		return message;
	}

	
	@Override
	public ArrayList<String> getRegisteredPlayerNames() {
		//TODO
		return null;
	}

	
	@Override
	public ArrayList<String> getLoggedInPlayerNames() {
		return login.getPlayerList();
	}

	
	@Override
	public List<Point> getLegalMoves(Point position) {
		return board.getLegalMoves(position);
	}

	
	@Override
	public Board getBoard() {
		return board;
	}


	@Override
	public void setColr(String username, Colr colour) {
		//TODO
	}

	
	private void reduceMoves() {
		int playerTurn = turns.get(currentTurn);
		playerTurn--;
		turns.replace(Colr.WHITE, playerTurn);
	}

	
	private void endGame() {
		if(playerList.get(Colr.WHITE).getPoints() > playerList.get(Colr.BLACK).getPoints()) {
			winner = Colr.WHITE;
		}else if(playerList.get(Colr.WHITE).getPoints() < playerList.get(Colr.BLACK).getPoints()) {
			winner = Colr.BLACK;
		}else {
			winner = null;
		}
		if(winner != null) {
			notifyAllObservers("Game complete, " + playerList.get(winner).getName() + "Wins");
		}else {
			notifyAllObservers("Game complete, tied game");
		}
		
		gameRunning = false;
		maxMove = 0;
		
		playerList.get(Colr.WHITE).resetScore();
		playerList.get(Colr.BLACK).resetScore();
		
		turns.replace(Colr.WHITE, 0);
		turns.replace(Colr.BLACK, 0);
	}

	private void increaseScore(int piecesTaken) {
		if(piecesTaken==1) {
			playerList.get(currentTurn).increaseScore(5);
		}else if(piecesTaken==2) {
			playerList.get(currentTurn).increaseScore(10);
		}
	}
	
	
	private boolean turnLimitReached() {
		boolean gameOver;
		
		if(turns.get(Colr.WHITE) == 0 && turns.get(Colr.BLACK) == 0) {
			gameOver = true;
			
		}else {
			gameOver = false;
		}
		
		return gameOver;
	}

	
	//TODO look more into how to implement observer
	//Method taken from Bernie, not final
	private void notifyAllObservers(String message) {
		statusMessage = message;
		setChanged();
		notifyObservers();
	}

	
	@Override
	public Player getWinner() {
		return playerList.get(winner);
	}

	
	@Override
	public String getStatus() {
		return statusMessage;
	}


	@Override
	public boolean split(Point point) {
		boolean splitSuccess;
		String message;
		//TODO clean up, maybe make a piece colour checking method
		if(board.getPiecesAt(point).get(0).getColor() == currentTurn) {
			if(board.isMerged(point)) {
				board.split(point);
				swapTurn();
		        reduceMoves();
				splitSuccess = true;
				message = "Merged piece split";
			}else {
				splitSuccess = false;
				message = "Piece cannot be split";
			}
		}else {
			splitSuccess = false;
			message = "It is " + currentTurn + "'s move";
		}
		notifyAllObservers(message);
		return splitSuccess;
	}


	@Override
	public int turnsRemaining() {
		int turnsRemaining;
		if(turns.get(Colr.WHITE) >= turns.get(Colr.BLACK)) {
			turnsRemaining = turns.get(Colr.WHITE);
		}else {
			turnsRemaining = turns.get(Colr.BLACK);
		}
		
		return turnsRemaining;
	}


}
