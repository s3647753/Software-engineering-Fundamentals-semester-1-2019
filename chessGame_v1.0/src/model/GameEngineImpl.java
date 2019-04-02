package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import enums.Colr;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import model_Interfaces.Piece;
import view_interfaces.View;

/**
 * Application of the Game Engine Interface
 * This class deals with most of the game logic
 * @author Matt Eletva
 *
 */

public class GameEngineImpl implements GameEngine {
	private Board board;
	private Map<String, Integer> players = new HashMap<String, Integer>();
	private Collection<View> views = new HashSet<>();
	private int currentMove;
	private Login login;
	private Colr currentTurn;
	
	public GameEngineImpl(Board board) {
		this.board = board;
		currentTurn = Colr.WHITE;
	}


	@Override
	public void setView(View view) {
		views.add(view);
	}

	@Override
	public void updateScore(String Username, int score) {
		if(players.containsKey(Username)) {
			players.put(Username, score);
		}
	}

	@Override
	public void addBoard(Board board) {
		this.board = board;
	}

	@Override
	public void setMaxMoves(int player1Moves, int player2Moves) {
		currentMove = Math.round((player1Moves/player2Moves)*2)*2;
	}

	@Override
	public boolean movePlayer(Point from, Point to) throws IllegalMoveException {
		/*Currently Implemented: 
		 	Moving piece
		 	Does not check what the piece is moving onto ye*/
		ArrayList<Piece> pieces = board.getPiecesAt(from);
		boolean moveSuccessful = false;
		if(pieces.size()>1) {
			board.moveMergedPiece(from, to);
			moveSuccessful = true;
			reduceMoves();
			swapTurn();
		}else if(pieces.size() == 1) {
			board.moveSinglePiece(pieces.get(0), from, to);
			moveSuccessful = true;
			reduceMoves();
			swapTurn();
		}
		//think about how to implement this more
		if(currentMove == 0) {
			endGame();
		}
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
	public String register(String username, String password) throws DuplicateNameException {
		Boolean registerSuccess = Register.registerPlayer(username, password);
		String message;
		if(registerSuccess) {
			message = "Username " + username + " has successfully registered";
		}else {
			message = "Username " + username + " has not successfully registered";
		}
		return message;
	}

	@Override
	public String login(String username, String password) throws PlayerNotFoundException {
		login.loginPlayer(username, password);
		return username + " has successfully logged in";
	}

	@Override
	public String logout(String username) {
		
		return null;
	}

	@Override
	public ArrayList<String> getRegisteredPlayerNames() {
		return login.getPlayerList();
	}

	@Override
	public ArrayList<String> getLoggedInPlayerNames() {
		ArrayList<String> playerList = new ArrayList<String>();
		playerList.addAll((players.keySet()));
		return playerList;
	}

	@Override
	public ArrayList<Point> getLegalMoves(Point position) {
		return board.getLegalMoves(position);
	}
	
	public void reduceMoves() {
		currentMove--;
	}

	public void endGame() {
		
	}
}
