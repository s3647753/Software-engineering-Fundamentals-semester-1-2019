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

public class ChessBoardTest_GUI {

	@Before
	public void setUp() throws Exception {
	}

	// NB: this test is incomplete
	@Test
	public void testGetImageFullPath_NoPieces() {
		View model = createMock(View.class);
		List<Piece> list = new ArrayList<>();

		// add just one piece
		list.add(new Knight(Colr.BLACK));
		
		
		expect(model.getPieceList(0, 0)).andReturn(list);
		replay(model);
		
		// TODO model is returning null
		System.out.println(model.getPieceList(0, 0));

		
		fail("Not yet implemented");
	}

}
