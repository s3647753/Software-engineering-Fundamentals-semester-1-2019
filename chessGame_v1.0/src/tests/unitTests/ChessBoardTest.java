package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import enums.Colr;
import enums.Type;
import model.Knight;
import model_Interfaces.Piece;
import view.gui.ChessBoard;
import view_interfaces.View;

import static org.easymock.EasyMock.*;

public class ChessBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetImageFullPath_NoPieces() {
		System.out.println("test start");
		
		View model = createMock(View.class);
		List<Piece> list = new ArrayList<>();
		System.out.println("point 1");
		// add just one piece
//		list.add(new Knight(Type.ROOK, Colr.BLACK));
		
		
		expect(model.getPieceList(0, 0)).andReturn(list);
		// TODO model is returning null
		System.out.println(model.getPieceList(0, 0));
		
		
		System.out.println("point 2");
		ChessBoard cb = new ChessBoard(model);
		System.out.println("point 3");
		System.out.println(cb.getImageFullPath(0, 0));
		System.out.println("hello world");
		
		fail("Not yet implemented");
	}

}
