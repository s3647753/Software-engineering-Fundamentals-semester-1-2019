package tests.unitTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before; 
import org.junit.Test;

import enums.Colr;
import model.BoardImpl;
import model.GameEngineImpl;
import model.Point;
import model_Interfaces.Board;
import model_Interfaces.GameEngine;
import model_Interfaces.Piece; 

public class GameEngineTest {
	GameEngine engine;
	Board board;
	@Before
	public void setUp() {
		board = new BoardImpl();
		engine = new GameEngineImpl(board);
		engine.register("PlayerWhite", "PlayerWhite");
		engine.register("PlayerBlack", "PlayerBlack");
		engine.login("PlayerWhite", "PlayerWhite");
		engine.login("PlayerBlack", "PlayerBlack");
		engine.newGame("PlayerWhite", "PlayerBlack", 2, 2);
	}
	@Test
	public void testTurnSwap() {
		assertEquals(Colr.WHITE, engine.whoseTurn());
		Point from = new Point(5,0);
		Point to = new Point(4,0);
		engine.movePlayer(from, to);
		assertEquals(Colr.BLACK, engine.whoseTurn());
	}
	@Test
	public void testMoveSingle() {
		Point from = new Point(5,0);
		Point to = new Point(4,0);
		List<Piece> pieceFromList = engine.getBoard().getPiecesAt(from);
		List<Piece> pieceFrom = new LinkedList<Piece>();
		for(Piece piece: pieceFromList) {
			pieceFrom.add(piece);
		}
		
		assertTrue(engine.movePlayer(from, to));	
		
		List<Piece> pieceToList = engine.getBoard().getPiecesAt(to);
		List<Piece> pieceTo = new LinkedList<Piece>();
		for(Piece piece: pieceToList) {
			pieceTo.add(piece);
		}
		
		assertEquals(pieceFrom, pieceTo);
	}
	@Test
	public void testMerge() {
		List<Piece> pieceFrom = new LinkedList<Piece>();
		for(Piece piece: engine.getBoard().getPiecesAt(new Point(5,0))) {
			pieceFrom.add(piece);
		}
		for(Piece piece: engine.getBoard().getPiecesAt(new Point(5,1))) {
			pieceFrom.add(piece);
		}
		
		assertTrue(engine.movePlayer(new Point(5,0), new Point(4,0)));
		assertTrue(engine.movePlayer(new Point(0,0), new Point(1,0)));
		assertTrue(engine.movePlayer(new Point(5,1), new Point(4,0)));
		
		List<Piece> pieceToList = engine.getBoard().getPiecesAt(new Point(4,0));
		List<Piece> pieceTo = new LinkedList<Piece>();
		for(Piece piece: pieceToList) {
			pieceTo.add(piece);
		}
		
		assertEquals(pieceFrom, pieceTo);
	}
	@Test
	public void testSplit() {
		List<Piece> pieceFrom = new LinkedList<Piece>();
		for(Piece piece: engine.getBoard().getPiecesAt(new Point(5,0))) {
			pieceFrom.add(piece);
		}
		for(Piece piece: engine.getBoard().getPiecesAt(new Point(5,1))) {
			pieceFrom.add(piece);
		}
		Point piece1From = new Point(5,0);
		Point piece2From = new Point(5,1);
		Point to = new Point(4,0);
		assertTrue(engine.movePlayer(piece1From, to));
		assertTrue(engine.movePlayer(new Point(0,0), new Point(1,0)));
		assertTrue(engine.movePlayer(piece2From, to));
		assertTrue(engine.movePlayer(new Point(1,0), new Point(0,0)));
		
		assertTrue(engine.split(to));
	}
	@Test
	public void testTakePiece() {
		Point fromWhite = new Point(5,0);
		Point toWhite = new Point(3,0);
		List<Piece> pieceFromList = engine.getBoard().getPiecesAt(fromWhite);
		List<Piece> piecePreTake = new LinkedList<Piece>();
		for(Piece piece: pieceFromList) {
			piecePreTake.add(piece);
		}
		assertTrue(engine.movePlayer(fromWhite, toWhite));	
		
		Point fromBlack = new Point(0,0);
		Point toBlack = new Point(2,0);
		assertTrue(engine.movePlayer(fromBlack, toBlack));
		
		assertTrue(engine.movePlayer(toWhite, toBlack));
		
		List<Piece> pieceToList = engine.getBoard().getPiecesAt(toBlack);
		List<Piece> piecePostTake = new LinkedList<Piece>();
		for(Piece piece: pieceToList) {
			piecePostTake.add(piece);
		}
		
		assertEquals(piecePreTake, piecePostTake);
	}
	@Test
	public void testScoreIncreaseSingle() {
		Point fromWhite = new Point(5,0);
		Point toWhite = new Point(3,0);
		assertTrue(engine.movePlayer(fromWhite, toWhite));	
		
		Point fromBlack = new Point(0,0);
		Point toBlack = new Point(2,0);
		assertTrue(engine.movePlayer(fromBlack, toBlack));
		
		assertTrue(engine.movePlayer(toWhite, toBlack));
		
		assertEquals(5, engine.getPlayerScore(Colr.WHITE));
	}
	public void testScoreIncreaseMerged() {		
		assertTrue(engine.movePlayer(new Point(5,0), new Point(3,0)));
		assertTrue(engine.movePlayer(new Point(0,0), new Point(2,0)));
		assertTrue(engine.movePlayer(new Point(5,1), new Point(3,0)));
		assertTrue(engine.movePlayer(new Point(2,0), new Point(3,0)));

		assertEquals(10, engine.getPlayerScore(Colr.BLACK));
	}
	
	//@After
}
