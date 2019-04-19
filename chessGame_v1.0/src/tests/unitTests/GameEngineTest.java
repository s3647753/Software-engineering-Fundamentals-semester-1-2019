package tests.unitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;

import enums.Colr;
import enums.Type;
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
		engine.setMaxMoves(1, 1);
		engine.setPlayingUsers("Steve", "Steve2");
		engine.setColr("Steve", Colr.WHITE);
		engine.setColr("Steve2", Colr.BLACK);
	}
	@Test
	public void testTurnSwap() {
		assertEquals(Colr.WHITE, engine.whoseTurn());
		Point from = new Point(0,0);
		Point to = new Point(0,1);
		engine.movePlayer(from, to);
		assertEquals(Colr.BLACK, engine.whoseTurn());
	}
	@Test
	public void testMoveSingle() {
		Point from = new Point(0,0);
		Point to = new Point(1,0);
		List<Piece> piece1 = engine.getBoard().getPiecesAt(from);
		String code1 = piece1.get(0).getCode();
		engine.movePlayer(from, to);
		List<Piece> piece2 = engine.getBoard().getPiecesAt(to);
		String code2 = piece2.get(0).getCode();
		assertEquals(code1, code2);
	}
	@Test
	public void testGameWinByPoints() {
		engine.movePlayer(new Point(0,0), new Point(4,5));
		engine.movePlayer(new Point(5,5), new Point(4,5));
		//assertEquals("Steve",engine.getWinner());
	}
	//@After
}
