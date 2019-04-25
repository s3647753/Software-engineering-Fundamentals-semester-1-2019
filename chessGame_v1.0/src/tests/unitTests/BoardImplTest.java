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
   private Piece piece;
   private Point from, to, point;


   @Before
   public void setUp() throws Exception {
      board = new BoardImpl();
   }


   @Test
   public void testHeightAnsWidth() {
      assertEquals("board.getHeight()", 6, board.getHeight());
      assertEquals("board.getWidth()", 6, board.getWidth());
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
      assertTrue("contains White Knight",
            board.getPiecesAt(point).contains(new Knight(Colr.WHITE)));
      assertTrue("contains White Bisshop",
            board.getPiecesAt(point).contains(new Bishop(Colr.WHITE)));
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
      assertTrue("contains Black Knight",
            board.getPiecesAt(point).contains(new Knight(Colr.BLACK)));
      assertTrue("contains Black Rook",
            board.getPiecesAt(point).contains(new Rook(Colr.BLACK)));
      assertFalse("does not contain Black Bishop",
            board.getPiecesAt(point).contains(new Bishop(Colr.BLACK)));

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
      assertTrue("contains Black Knight",
            board.getPiecesAt(point).contains(new Rook(Colr.WHITE)));

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


   @Test
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


   @Test
   public void testGetLegalMoves_MergedPiece() throws IllegalMoveException {
      // add two pieces to a point near the middle of the board
      Point point = new Point(3, 3);
      board.setPiece(new Bishop(Colr.WHITE), point);
      board.setPiece(new Rook(Colr.WHITE), point);

      List<Point> moves = board.getLegalMoves(point);

      // there should be thirteen legal moves
      assertEquals("number of legal moves", 13, moves.size());

      // test one each of the Bishops moves an the Roks move
      assertTrue(moves.contains(new Point(3, 5)));
      assertTrue(moves.contains(new Point(2, 2)));
   }


   @Test
   public void testMoveSinglePiece_0()
         throws IllegalMoveException, PieceNotFoundException {
      // moving to an empty cell
      from = new Point(5, 0);
      to = new Point(4, 0);
      piece = board.getPiecesAt(from).get(0);

      int returned = board.moveSinglePiece(piece, from, to);

      assertEquals("Moving to an empty cell", 0, returned);
   }


   @Test
   public void testMoveSinglePiece_1()
         throws IllegalMoveException, PieceNotFoundException {
      // moving to cell containing a single enemy piece
      from = new Point(5, 0);
      to = new Point(4, 0);

      piece = board.getPiecesAt(from).get(0);

      board.setPiece(new Bishop(Colr.BLACK), to);

      int returned = board.moveSinglePiece(piece, from, to);

      assertEquals("Taking a single piece", 1, returned);

      assertEquals("Test the 'to' cell contains one piece)", 1,
            board.getPiecesAt(to).size());

      assertTrue("test the correct piece is in the 'to' cell",
            board.getPiecesAt(to).get(0).equals(piece));

      assertFalse("test the from cell no longer contains the piece",
            board.getPiecesAt(from).contains(piece));

   }


//   @Test(expected = PieceNotFoundException.class)
//   public void testMoveSinglePiece_pnfe()
//         throws IllegalMoveException, PieceNotFoundException {
//
//      // moving to cell containing a single enemy piece
//      from = new Point(5, 0);
//      to = new Point(4, 0);
//      piece = new Bishop(Colr.WHITE);
//
//      // the argument piece does not match the piece in the cell
//      board.moveSinglePiece(piece, from, to);
//   }


   @Test
   public void testMoveSinglePiece_2()
         throws IllegalMoveException, PieceNotFoundException {
      // moving to cell containing a merged enemy piece
      from = new Point(5, 0);
      to = new Point(4, 0);
      piece = board.getPiecesAt(from).get(0);

      board.setPiece(new Bishop(Colr.BLACK), to);
      board.setPiece(new Knight(Colr.BLACK), to);

      int returned = board.moveSinglePiece(piece, from, to);

      assertEquals("Taking a merged piece", 2, returned);
   }


   @Test
   public void testMoveMergedPiece_LegalMove()
         throws IllegalMoveException, PieceNotFoundException {
      // moving a merged piece to empty locations, no obstructions

      from = new Point(0, 2); // a black knight.

      // merge a piece and test that it is a merged piece
      board.setPiece(new Rook(Colr.BLACK), from);
      List<Piece> mergedPiece = board.getPiecesAt(from);
      assertTrue(mergedPiece.size() == 2);

      List<Point> legalMoves = board.getLegalMoves(from);

      // moving the piece to legal places
      for (Point too : legalMoves) {
         assertEquals(0, board.moveMergedPiece(from, too));

         // check the piece is in the target location
         assertTrue(too.toString(),
               board.getPiecesAt(too).contains(new Knight(Colr.BLACK)));
         assertTrue(too.toString(),
               board.getPiecesAt(too).contains(new Rook(Colr.BLACK)));

         // check the 'from' cell is empty
         assertEquals(new ArrayList<Piece>(), board.getPiecesAt(from));

         // put the piece back
         board.moveMergedPiece(too, from);
      }
   }


   @Test(expected = IllegalMoveException.class)
   public void testMoveMergedPiece_NotLegalMove()
         throws IllegalMoveException, PieceNotFoundException {
      // throwing an exception for an illegal move

      from = new Point(0, 2); // a black knight.
      to = new Point(2, 0);

      // merge a piece and test that it is a merged piece
      board.setPiece(new Rook(Colr.BLACK), from);
      List<Piece> mergedPiece = board.getPiecesAt(from);
      assertTrue(mergedPiece.size() == 2);

      // move piece to illegal position
      assertEquals(0, board.moveMergedPiece(from, to));
   }


   @Test // TODO presentation
   public void testIsObstructed_NoObstructions() throws IllegalMoveException {
      Point knightFrom = new Point(5, 2);
      Point knightTo = new Point(3, 1);

      Point bishopFrom = new Point(5, 1);
      Point bishopTo = new Point(3, 3);

      Point rookFrom = new Point(0, 5);
      Point rookTo = new Point(3, 5);

      // both can move as there is no obstruction
      assertFalse(board.isObstructed(bishopFrom, bishopTo));
      assertFalse(board.isObstructed(knightFrom, knightTo));
      assertFalse(board.isObstructed(rookFrom, rookTo));

   }


   @Test // TODO presentation
   public void testIsObstructed_WithObstructions() throws IllegalMoveException {
      Point knightFrom = new Point(5, 2);
      Point knightTo = new Point(3, 1);

      Point bishopFrom = new Point(5, 1);
      Point bishopTo = new Point(3, 3);

      Point rookFrom = new Point(0, 5);
      Point rookTo = new Point(3, 5);

      // add obstructions
      board.setPiece(new Knight(Colr.BLACK), new Point(4, 2));
      board.setPiece(new Rook(Colr.BLACK), new Point(4, 1));
      board.setPiece(new Rook(Colr.BLACK), new Point(1, 5));

      // the bishop and rook should be obstructed and the rook not
      assertTrue(board.isObstructed(bishopFrom, bishopTo));
      assertFalse(board.isObstructed(knightFrom, knightTo));
      assertTrue(board.isObstructed(rookFrom, rookTo));

   }


   @Test
   public void testAreSameColor() {
      // tests the methods that compares for similar colour

      // matching colors
      assertTrue("Matching color", board.areSameColor(new Point(0, 0), new Point(0, 4)));
      assertTrue("Matching color", board.areSameColor(new Point(5, 4), new Point(5, 2)));

      // not matching color
      assertFalse("Not matching color",
            board.areSameColor(new Point(0, 1), new Point(5, 1)));
      assertFalse("Not matching color",
            board.areSameColor(new Point(5, 4), new Point(0, 2)));

      // one or both cells are empty
      assertFalse("second cell is empty",
            board.areSameColor(new Point(5, 4), new Point(3, 5)));
      assertFalse("first cell is empty",
            board.areSameColor(new Point(4, 5), new Point(5, 5)));
      assertFalse("both cells are empty",
            board.areSameColor(new Point(2, 2), new Point(3, 3)));

   }


   // the toString() method is used by the textual interface to display the board
   @Test
   public void testToString() {
      assertEquals(defaultBoardStr(), board.toString());
   }

   // tests for is merged and piece splitting
   @Test
   public void testIsMerged_single() {
      // testing single pieces and empty square
      assertFalse("isMerged: single piece", board.isMerged(new Point(5, 3)));
      assertFalse("isMerged: empty square", board.isMerged(new Point(2, 3)));
   }
   
   @Test
   public void testIsMerged_merged() throws IllegalMoveException {
      // testing isMerged on a merged square
      point = new Point(5, 1);
      board.setPiece(new Rook(Colr.WHITE), point);
      
      assertTrue("isMerged: merged piece", board.isMerged(point));
   }
   
   @Test
   public void testSplit_single() {
      // testing the return on splitting a single piece of empty square
      
      point = new Point(5, 1);
      assertFalse("split: single piece", board.split(point));
      
      point = new Point(4, 5);
      assertFalse("split: empty square", board.split(point));
   }
   
   @Test
   public void testSplit_merged() throws IllegalMoveException {
      // testing a merged splits
      point = new Point(5, 1);
      board.setPiece(new Rook(Colr.WHITE), point);
      assertTrue("isMerged: merged piece", board.isMerged(point));
      
      assertTrue("Splitting a merged piece", board.split(point));
      assertFalse("isMerged: merged piece", board.isMerged(point));
      
   }
   
   
   @Test // TODO
   public void testAllPiecesGone() {
//      board.allPiecesGone(Colr.BLACK); // TODO
//      fail("test not implemented");
      assertFalse(board.allPiecesGone(Colr.BLACK));
      assertFalse(board.allPiecesGone(Colr.WHITE));
   }


   private String defaultBoardStr() {
      String[] pieces = { "BRW", "BBB", "BKW", "BKB", "BBW", "BRB", "B", "W", "B", "W",
            "B", "W", "W", "B", "W", "B",
            "W", "B", "B", "W", "B", "W", "B", "W", "W", "B", "W", "B", "W", "B", "WRB",
            "WBW", "WKB", "WKW", "WBB", "WRW" };

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
