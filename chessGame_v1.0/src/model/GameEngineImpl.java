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
	private Map<Colr, Integer> turns = new HashMap<Colr, Integer>();
	private Colr currentTurnColour;
	
	private RegisterLogin login;
	
	private String statusMessage = "";

	private boolean gameRunning;

	private Colr winner;

	
	public GameEngineImpl(Board board) {
		this.board = board;
		login = new RegisterLogin();
		playerList.put(Colr.WHITE, new PlayerImpl("Player 1"));
		playerList.put(Colr.BLACK, new PlayerImpl("Player 2"));
		turns.put(Colr.WHITE, 0);
		turns.put(Colr.BLACK, 0);
		gameRunning = false;
	}


	@Override
	public boolean newGame(String playerWhite, String playerBlack, int player1TurnLimit, int player2TurnLimit) {
		boolean newGameMade;
		if(login.getPlayerList().contains(playerWhite) && login.getPlayerList().contains(playerBlack)) {
			board.resetBoard();
			
			
			playerList.replace(Colr.WHITE, new PlayerImpl(playerWhite));
			playerList.replace(Colr.BLACK, new PlayerImpl(playerBlack));

			currentTurnColour = Colr.WHITE;
			
			int maxMove = (player1TurnLimit+player2TurnLimit)/2;
				
			turns.replace(Colr.WHITE, maxMove);
			turns.replace(Colr.BLACK, maxMove);

			gameRunning = true;
			newGameMade = true;
			
			board.resetBoard();
			
			notifyAllObservers("New Game");
		}else {
			notifyAllObservers("Player Does not exist");
			newGameMade = false;
		}
		
		return newGameMade;
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
	public boolean movePlayer(Point from, Point to) {
		
		List<Piece> pieces = board.getPiecesAt(from);
		boolean moveSuccessful = false;
		int piecesTaken = 0;
		String message = null;
		boolean moveAllowed = true;
		
		if(turnLimitReached()) {
			moveAllowed = false;
			moveSuccessful = false;
		}else if(pieces.size()==0) {
			moveAllowed = false;
			moveSuccessful = false;
			message = "No piece in location";
		}else if(pieces.get(0).getColor() != currentTurnColour) {
			moveAllowed = false;
			moveSuccessful = false;
			message = "It is " + currentTurnColour + "'s move";
		}
		
		if(moveAllowed) {
			try {
				piecesTaken = board.movePiece(from, to);
				moveSuccessful = true;
				reduceMoves();
				message = "Piece moved";
			}catch(IllegalMoveException e){
				moveSuccessful = false;
				message = "Move not Legal";
			}catch(PieceNotFoundException e) {
				moveSuccessful = false;
				message = "No piece in location";
			}
			
			increaseScore(piecesTaken);
			swapTurn();
			notifyAllObservers(message);
			
			if(turnLimitReached() || allOpposingPieceGone()) {
				endGame();
			}
		}else {
			if(!turnLimitReached()) {
				notifyAllObservers(message);
			}
		}
		
		return moveSuccessful;
		
		/*List<Piece> pieces = board.getPiecesAt(from);
		boolean moveSuccessful = false;
		int piecesTaken = 0;
		String message = null;
		if(pieces.size()!=0) {
			if(pieces.get(0).getColor() == currentTurn) {
				if(!turnLimitReached()) {
					try {
						piecesTaken = board.movePiece(from, to);
						moveSuccessful = true;
						reduceMoves();
						message = "Piece moved";
					}catch(IllegalMoveException e){
						moveSuccessful = false;
						message = "Move not Legal";
					}catch(PieceNotFoundException e) {
						moveSuccessful = false;
						message = "No piece in location";
					}
					
					increaseScore(piecesTaken);
					swapTurn();
					notifyAllObservers(message);
					
					if(turnLimitReached() || allOpposingPieceGone()) {
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
		
		return moveSuccessful;*/
	}
	
	
	@Override
	public int getPlayerScore(Colr colour) {
		return playerList.get(colour).getPoints();
	}


	@Override
	public Colr whoseTurn() {
		return currentTurnColour;
	}

	
	@Override
	public boolean register(String username, String password) {
		String message;
		boolean registerSuccess;
		try {
			login.registerPlayer(username, password);
			message = "Username " + username + " has successfully registered";
			registerSuccess = true;
		}catch(DuplicateNameException e) {
			message = "Username " + username + " already exists";
			registerSuccess = false;
		}
		
		notifyAllObservers(message);
		
		return registerSuccess;
	}
	

	@Override
	public boolean login(String username, String password) {
		String message = username + " has successfully logged in";
		boolean loginSuccess;
		
		//temp until implemented in login
		if(login.getPlayerList().contains(username)) {
			message = username + " is already logged in";
			loginSuccess = false;
		}else {
			try{
				login.loginPlayer(username, password);
				loginSuccess = true;
			}catch(PlayerNotFoundException e) {
				message = "Player with username " + username + " does not exist";
				loginSuccess = false;
			}catch(WrongPassException e) {
				message = "Password incorrect";
				loginSuccess = false;
			}
		}
		
		notifyAllObservers(message);
		
		return loginSuccess;
	}

	
	@Override
	public boolean logout(String username) {
		boolean logoutSuccessful = login.logoutPlayer(username);
		String message;
		if(logoutSuccessful) {
			message = username + " has successfully logged out.";
		}else {
			message = username + " was not succcessfully logged out";
		}
		
		notifyAllObservers(message);
		
		return logoutSuccessful;
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
		if(board.getPiecesAt(point).get(0).getColor() == currentTurnColour) {
			if(board.isMerged(point)) {
				board.split(point);
				reduceMoves();
				swapTurn();
				splitSuccess = true;
				message = "Merged piece split";
			}else {
				splitSuccess = false;
				message = "Piece cannot be split";
			}
		}else {
			splitSuccess = false;
			message = "It is " + currentTurnColour + "'s move";
		}
		notifyAllObservers(message);
		
		return splitSuccess;
	}


	@Override
	public int turnsRemaining() {
		return turns.get(Colr.BLACK);
	}

	
	private void notifyAllObservers(String message) {
		statusMessage = message;
		setChanged();
		notifyObservers(gameRunning);
	}
	
	
	private boolean allOpposingPieceGone() {
		Colr opposingColour;
		
		if(currentTurnColour == Colr.WHITE) {
			opposingColour = Colr.BLACK;
		}else {
			opposingColour = Colr.WHITE;
		}
		
		return board.allPiecesGone(opposingColour);
	}

	
	private void increaseScore(int piecesTaken) {
		if(piecesTaken==1) {
			playerList.get(currentTurnColour).increaseScore(5);
		}else if(piecesTaken==2) {
			playerList.get(currentTurnColour).increaseScore(10);
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
	
	
	private void reduceMoves() {
		int playerTurn = turns.get(currentTurnColour);
		playerTurn--;
		turns.replace(currentTurnColour, playerTurn);
	}

	
	private void endGame() {		
		gameRunning = false;
		
		turns.replace(Colr.WHITE, 0);
		turns.replace(Colr.BLACK, 0);
		
		if(playerList.get(Colr.WHITE).getPoints() > playerList.get(Colr.BLACK).getPoints()) {
			winner = Colr.WHITE;
		}else if(playerList.get(Colr.WHITE).getPoints() < playerList.get(Colr.BLACK).getPoints()) {
			winner = Colr.BLACK;
		}else {
			winner = null;
		}
		
		if(winner != null) {
			notifyAllObservers("Game complete, " + playerList.get(winner).getName() + " Wins");
		}else {
			notifyAllObservers("Game complete, tied game");
		}
	}
	
	
	private void swapTurn() {
		if(currentTurnColour == Colr.WHITE) {
			currentTurnColour = Colr.BLACK;
		}else {
			currentTurnColour = Colr.WHITE;
		}
	}
	
	
}
