package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	//TODO CREATE PLAYER CLASS
	private Map<Colr, String> playerColr = new HashMap<Colr, String>();
	private View view;
	private int currentMove;
	private Login login;
	private Colr currentTurn;
	
	public GameEngineImpl(Board board) {
		this.board = board;
		currentTurn = Colr.WHITE;
	}


	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void updateScore(String Username, int score) {
		players.replace(Username, score);
		view.update(board);
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
	public boolean movePlayer(Point from, Point to) {
		/*Currently Implemented: 
		 	Moving piece
		 	Does not check what the piece is moving onto yet*/
		List<Piece> pieces = board.getPiecesAt(from);
		boolean moveSuccessful = false;
		int piecesTaken = 0;
		try {
			if(pieces.size()>1) {
				piecesTaken = board.moveMergedPiece(from, to);
				moveSuccessful = true;
				reduceMoves();
				swapTurn();
			}else if(pieces.size() == 1) {
				piecesTaken = board.moveSinglePiece(pieces.get(0), from, to);
				moveSuccessful = true;
				reduceMoves();
				swapTurn();
			}
		}catch(IllegalMoveException e){
			moveSuccessful = false;
		}catch(PieceNotFoundException e) {
			moveSuccessful = false;
		}
		increaseScore(piecesTaken);
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
	public String register(String username, String password) {
		Boolean registerSuccess = null;
		String message;
		try {
			registerSuccess = Register.registerPlayer(username, password);
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
		try{
			login.loginPlayer(username, password);
			players.put(username, 0);
		}catch(PlayerNotFoundException e) {
			message = "Player with username " + username + "does not exist";
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
		return login.getPlayerList();
	}

	@Override
	public ArrayList<String> getLoggedInPlayerNames() {
		ArrayList<String> playerList = new ArrayList<String>();
		playerList.addAll((players.keySet()));
		return playerList;
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
		//TODO maybe implement an exception here if the player doesnt exists in the game?
		if(players.containsKey(username)) {
			playerColr.put(colour, username);
		}	
	}
	
	private void reduceMoves() {
		currentMove--;
	}

	private void endGame() {
		
	}
	
	private void increaseScore(int piecesTaken) {
		String user = playerColr.get(currentTurn);
		int currentScore = players.get(user);
		if(piecesTaken==1) {
			players.put(user, currentScore+5);
		}else if(piecesTaken==2) {
			players.put(user, currentScore+10);
		}
		//TODO
		//view.updateScore();
	}


}
