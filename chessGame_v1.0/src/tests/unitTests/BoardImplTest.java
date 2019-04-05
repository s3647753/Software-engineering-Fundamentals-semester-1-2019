package tests.unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.BoardImpl;
import model_Interfaces.Board;

/**
 * Note:
 * See tests.integration.BoardImpImplementationTest.java
 * For the majority of tests applicable to the Board class.
 * 
 * @author Bernard O'Meara
 *
 */
public class BoardImplTest {
   private Board board;

   @Before
   public void setUp() throws Exception {
      board = new BoardImpl();
   }
   
   @Test
   public void testHeightAnsWidth() {
      assertEquals("board.getHeight()", 6, board.getHeight());
      assertEquals("board.getWidth()", 6, board.getWidth());
   }
   

}
