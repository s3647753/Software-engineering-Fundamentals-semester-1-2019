package tests.unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.junit.Before;
import org.junit.Test;

import enums.Colr;
import javafx.scene.paint.Color;
import model.Bishop;
import model.BoardImpl;
import model.IllegalMoveException;
import model.Knight;
import model.PieceNotFoundException;
import model.Point;
import model.Rook;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * Tests for the Board Class
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


   // trivial tests
   @Test
   public void testConstantValues() {
      assertEquals("board height", 6, Board.HEIGHT);
      assertEquals("board width", 6, Board.WIDTH);
   }


   // the toString() method is used by the textual interface to display the board
   @Test
   public void testToString() {
      assertEquals(defaultBoardStr(), board.toString());
   }


   // helper used to tesst the toString method
   private String defaultBoardStr() {
      String[] pieces = { "BRW", "BBB", "BKW", "BKB", "BBW", "BRB", "B", "W", "B",
            "W", "B", "W", "W", "B", "W", "B", "W", "B", "B", "W", "B", "W", "B",
            "W", "W", "B", "W", "B", "W", "B", "WRB", "WBW", "WKB", "WKW", "WBB",
            "WRW" };

      StringJoiner sj = new StringJoiner("", "", "\n");

      for (int row = 0; row < 6; row++) {
         for (int col = 0; col < 6; col++) {
            sj.add(String.format("%6S", pieces[(row * Board.WIDTH) + col]));
         }
         sj.add("\n");
      }

      return sj.toString();
   }

}
