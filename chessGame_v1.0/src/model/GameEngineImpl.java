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

	//TODO Depreciated, remove once playerList is implemented
	//private Map<String, Integer> players = new HashMap<String, Integer>();
	//private Map<Colr, String> playerColr = new HashMap<Colr, String>();

	private int currentMove;
	private int maxMove;

	private Login login;
	private Colr currentTurn;
	private String statusMessage = "";

	private boolean gameRunning;

	private Colr winner;


	public GameEngineImpl(Board board) {
		this.board = board;
		login = new Login();
		currentTurn = Colr.WHITE;
		playerList.put(Colr.WHITE, null);
		playerList.put(Colr.BLACK, null);
		gameRunning = false;
		maxMove = 0;
		currentMove = 0;
	}


	@Override
	public void newGame(String playerWhite, String playerBlack, int player1TurnLimit, int player2TurnLimit) {
		if(!gameRunning) {

			if(login.getPlayerList().contains(playerWhite) && login.getPlayerList().contains(playerBlack)) {

				playerList.replace(Colr.WHITE, new PlayerImpl(playerWhite));
				playerList.replace(Colr.BLACK, new PlayerImpl(playerBlack));

				currentTurn = Colr.WHITE;

				maxMove = (player1TurnLimit+player2TurnLimit/2);
				currentMove = maxMove*2;


				gameRunning = true;
			}else {
				notifyAllObservers("Player Does not exist");
			}
		}
	}


	@Override
	public void setView(View view) {
		addObserver(view);
	}


	@Override
	public void updateScore(String Username, int score) {
		//TODO remove
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

		//TODO implement split
		if(pieces.size()!=0) {
			if(pieces.get(0).getColor() == currentTurn) {
				try {
					if(pieces.size()>1) {
						piecesTaken = board.moveMergedPiece(from, to);
						moveSuccessful = true;
						reduceMoves();
						swapTurn();
						message = "Piece moved";
					}else if(pieces.size() == 1) {
						piecesTaken = board.moveSinglePiece(pieces.get(0), from, to);
						moveSuccessful = true;
						reduceMoves();
						swapTurn();
						message = "Merged Piece moved";
					}
				}catch(IllegalMoveException e){
					moveSuccessful = false;
					message = "Move not Legal";
				}catch(PieceNotFoundException e) {
					moveSuccessful = false;
					message = "No piece in location";
				}
			}else {
				moveSuccessful = false;
				message = "It is " + currentMove + "'s turn";
			}
		}else {
			moveSuccessful = false;
			message = "No piece in location";
		}

		increaseScore(piecesTaken);
		if(currentMove == 0) {
			endGame();
		}
		notifyObservers(message);
		return moveSuccessful;
	}


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
		currentMove--;
	}

	
	private void endGame() {
		gameRunning = false;
		currentMove = 0;
		notifyAllObservers("Game complete, " + playerList.get(winner).getName() + "Wins");
	}

	private void increaseScore(int piecesTaken) {
		if(piecesTaken==1) {
			playerList.get(currentTurn).increaseScore(5);
		}else if(piecesTaken==2) {
			playerList.get(currentTurn).increaseScore(10);
		}
	}

	
	//TODO look more into how to implement observer
	//Method taken from Bernie, not final
	private void notifyAllObservers(String message) {
		statusMessage = message;
		setChanged();
		notifyObservers();
	}

	
	@Override
	//TODO remove
	public void setPlayingUsers(String player1, String player2) {
		if(login.getPlayerList().contains(player1) && login.getPlayerList().contains(player2)) {
			playerList.replace(Colr.WHITE, new PlayerImpl(player1));
			playerList.replace(Colr.BLACK, new PlayerImpl(player2));
		}
	}

	
	@Override
	public Player getWinner() {
		return null;
	}

	
	@Override
	public String getStatus() {
		return statusMessage;
	}


	@Override
	public boolean split(Point point) {
		// TODO Auto-generated method stub
		return false;
	}


}
