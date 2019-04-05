package tests.integration;

//import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;

import java.util.List;
import java.util.StringJoiner;

import org.junit.Before;
import org.junit.Test;

import enums.Colr;
import model.*;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * This is an integration test for Board-Cell-Piece
 * 
 * @author Bernard O'Meara
 *
 */
public class BoardImpIntegrationlTest {

   private Board board;
   Piece piece;

   @Before
   public void setUp() throws Exception {
      board = new BoardImpl();
   }

   @Test
   public void testGetCode_EmptyCells() {
      // testing the piece code returned for empty cells
      assertEquals("Empty black cell", "B", board.getCode(new Point(1, 0)));
      assertEquals("Empty white cell", "W", board.getCode(new Point(1, 1)));
   }

   @Test
   public void testGetCode_SinglePiece() {
      // testing the piece code returned when a single piece is in a cell
      assertEquals("Black Rook on Black", "BRB", board.getCode(new Point(0, 5)));
      assertEquals("Black Bishop on White", "BBW", board.getCode(new Point(0, 4)));

      assertEquals("Wite Knight on Black", "WKB", board.getCode(new Point(5, 2)));
      assertEquals("White Knight on White", "WKW", board.getCode(new Point(5, 3)));
   }

   @Test
   public void testGetCode_MergedPiece() throws IllegalMoveException {
      // testing the code returned for merged pieces
      Point point = new Point(0, 3);

      // merge the pieces on a black square
      board.setPiece(new Rook(Colr.BLACK), point);

      // test the returned code
      assertEquals("merged ", "BKBRB", board.getCode(point));
   }

   @Test
   public void testGetCode_MergedPieceSorted() throws IllegalMoveException {
      // testing the code returned for merged pieces
      // and that pieces are in sorted order
      Point point = new Point(0, 5);

      // merge the pieces on a black square
      board.setPiece(new Knight(Colr.BLACK), point);

      // test the returned code
      assertEquals("merged ", "BKBRB", board.getCode(point));
   }

   @Test
   public void testGetPieceAt_Unmerged() {
      piece = board.getPiecesAt(new Point(5, 2)).get(0);
      assertEquals("getWhiteKnight", new Knight(Colr.WHITE), piece);

      piece = board.getPiecesAt(new Point(0, 4)).get(0);
      assertEquals("getBlackBishop", new Bishop(Colr.BLACK), piece);
   }

   @Test
   public void testGetPieceAt_Merged() throws IllegalMoveException {
      // tests the merged pieces are both in the pieces list
      Point point = new Point(5, 4);

      // merge the pieces on a black square
      board.setPiece(new Knight(Colr.WHITE), point);

      // test that the list contains the correct pieces
      assertEquals("number of pieces in the cell", 2, board.getPiecesAt(point).size());
      assertTrue("contains White Knight", board.getPiecesAt(point).contains(new Knight(Colr.WHITE)));
      assertTrue("contains White Bisshop", board.getPiecesAt(point).contains(new Bishop(Colr.WHITE)));
   }

   @Test
   public void testSetPieceSingle() throws IllegalMoveException {
      // sets a single piece to an empty cell and then checks the contents of the cell

      Point point = new Point(2, 0);
      // test that the location is empty
      assertEquals("checking if cell is empty", 0, board.getPiecesAt(point).size());

      // set a white bishop to the location
      board.setPiece(new Bishop(Colr.WHITE), point);

      // testing the number of pieces now in the cell
      assertEquals("cell should have one piece", 1, board.getPiecesAt(point).size());

      // testing that the correct piece is in the cell
      piece = board.getPiecesAt(point).get(0);
      assertEquals("getWhiteBishop", new Bishop(Colr.WHITE), piece);

   }

   @Test(expected = IllegalMoveException.class)
   public void testSetPieceSingle_ThrowsException() throws IllegalMoveException {
      // attempts to set a piece to a cell containing a like piece

      board.setPiece(new Bishop(Colr.WHITE), new Point(5, 1));

   }

   @Test
   public void testSetPiece_Merge() throws IllegalMoveException {
      // moves a piece and merges with the existing piece
      Point point = new Point(0, 3);

      // test that a Black Knight is in the cell
      piece = board.getPiecesAt(point).get(0);
      assertEquals("test for black knight", new Knight(Colr.BLACK), piece);

      // merge the piece
      board.setPiece(new Rook(Colr.BLACK), point);

      // check that we now have a merged piece
      assertEquals("number of pieces in the cell", 2, board.getPiecesAt(point).size());
      assertTrue("contains Black Knight", board.getPiecesAt(point).contains(new Knight(Colr.BLACK)));
      assertTrue("contains Black Rook", board.getPiecesAt(point).contains(new Rook(Colr.BLACK)));
      assertFalse("does not contain Black Bishop", board.getPiecesAt(point).contains(new Bishop(Colr.BLACK)));

   }

   @Test
   public void testTakingAPiece() throws IllegalMoveException {
      // taking a black knight with a white rook
      Point point = new Point(0, 3);

      // test the knight is there
      piece = board.getPiecesAt(point).get(0);
      assertEquals("test for black knight", new Knight(Colr.BLACK), piece);

      // take the knight with a white rook
      board.setPiece(new Rook(Colr.WHITE), point);

      // test that there is a single white rook in the cell
      assertEquals("number of pieces in the cell", 1, board.getPiecesAt(point).size());
      assertTrue("contains Black Knight", board.getPiecesAt(point).contains(new Rook(Colr.WHITE)));

   }
   
   @Test
   public void testGetLegalMoves_KnightDefault() {
      // testing getLegalMoves from a knight on the bottom edge of the default board
      Point point = new Point(5, 2);
      List<Point> moves = board.getLegalMoves(point);
 
      // there should be four legal moves
      assertEquals("number of legal moves", 4, moves.size());
      
      // test each of the four moves are correct
      assertTrue(moves.contains(new Point(4, 0)));
      assertTrue(moves.contains(new Point(4, 4)));
      assertTrue(moves.contains(new Point(3, 1)));
      assertTrue(moves.contains(new Point(3, 3)));
      
   }
   
   @Test // this test fails because rook and bishop are jumping pieces (milestone 2)
   public void testGetLegalMoves_RookDefault() {
      // testing getLegalMoves from a Rook on the top RH of the default board
      Point point = new Point(0, 5);
      List<Point> moves = board.getLegalMoves(point);
 
      // there should be three legal moves
      assertEquals("number of legal moves", 3, moves.size());
      
      // test each of the four moves are correct
      assertTrue(moves.contains(new Point(1, 5)));
      assertTrue(moves.contains(new Point(2, 5)));
      assertTrue(moves.contains(new Point(0, 4)));
      
   }
   
   

   // the toString() method is used by the textual interface to display the board
   @Test
   public void testToString() {
      assertEquals(defaultBoardStr(), board.toString());
   }

   private String defaultBoardStr() {
      String[] pieces = { "BRW", "BBB", "BKW", "BKB", "BBW", "BRB", "B", "W", "B", "W", "B", "W", "W", "B", "W", "B",
            "W", "B", "B", "W", "B", "W", "B", "W", "W", "B", "W", "B", "W", "B", "WRB", "WBW", "WKB", "WKW", "WBB",
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
