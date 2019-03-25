package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import enums.Colr;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import model_Interfaces.Piece;
import view_interfaces.View;

/**
 * Application of the Game Engine Interface
 * 
 * @author Matt Eletva
 *
 */

public class GameEngineImpl implements GameEngine {
	private Board board;
	private ArrayList<String> loggedInPlayers = new ArrayList<String>();
	private Map<String, Integer> players = new HashMap<String, Integer>();
	private View view;
	private int currentMove;
	
	public GameEngineImpl(Board board2) {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setView(View view) {
		this.view = view;
	}

/*	@Override
	public void addPlayer(String Username) {
		players.put(Username, 0);
	}*/

	@Override
	public void movePiece(Point positionPrevious, Point positionNew) {
		// TODO Auto-generated method stub
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
		//might turn out as float so deal with that soon
		currentMove = (player1Moves/player2Moves)*2;
	}

	@Override
	public void reduceMoves() {
		currentMove--;
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean movePlayer(Point from, Point to) throws IllegalMoveException {
		ArrayList<Piece> pieces = board.getPiecesAt(from);
		if(pieces.size()>1) {
			//todo
			return true;
		}else if(pieces.size() == 1) {
			board.moveSinglePiece(pieces.get(0), from, to);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Colr whoseTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logout(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getRegisteredPlayerNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getLoggedInPlayerNames() {
		return loggedInPlayers;
	}

	@Override
	public ArrayList<Point> getLegalMoves(Point position) {
		return board.getLegalMoves(position);
	}
}
