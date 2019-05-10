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


   // trivial tests
   @Test
   public void testConstantValues() {
      assertEquals("board height", 6, board.HEIGHT);
      assertEquals("board width", 6, board.WIDTH);
   }

   



//
//
//   @Test
//   public void testGetLegalMoves_RookDefault() {
//      // testing getLegalMoves from a Rook on the top RH of the default board
//      Point point = new Point(0, 5);
//      List<Point> moves = board.getLegalMoves(point);
//
//      // there should be three legal moves
//      assertEquals("number of legal moves", 3, moves.size());
//
//      // test each of the four moves are correct
//      assertTrue(moves.contains(new Point(1, 5)));
//      assertTrue(moves.contains(new Point(2, 5)));
//      assertTrue(moves.contains(new Point(0, 4)));
//
//   }
//
//
//   @Test
//   public void testGetLegalMoves_MergedPiece() throws IllegalMoveException {
//      // add two pieces to a point near the middle of the board
//      Point point = new Point(3, 3);
//      board.setPiece(new Bishop(Colr.WHITE), point);
//      board.setPiece(new Rook(Colr.WHITE), point);
//
//      List<Point> moves = board.getLegalMoves(point);
//
//      // there should be thirteen legal moves
//      assertEquals("number of legal moves", 13, moves.size());
//
//      // test one each of the Bishops moves an the Roks move
//      assertTrue(moves.contains(new Point(3, 5)));
//      assertTrue(moves.contains(new Point(2, 2)));
//   }
//
//
////   @Test
////   public void testMoveSinglePiece_0()
////         throws IllegalMoveException, PieceNotFoundException {
////      // moving to an empty cell
////      from = new Point(5, 0);
////      to = new Point(4, 0);
////      piece = board.getPiecesAt(from).get(0);
////
////      int returned = board.moveSinglePiece(piece, from, to);
////
////      assertEquals("Moving to an empty cell", 0, returned);
////   }
////
////
////   @Test
////   public void testMoveSinglePiece_1()
////         throws IllegalMoveException, PieceNotFoundException {
////      // moving to cell containing a single enemy piece
////      from = new Point(5, 0);
////      to = new Point(4, 0);
////
////      piece = board.getPiecesAt(from).get(0);
////
////      board.setPiece(new Bishop(Colr.BLACK), to);
////
////      int returned = board.moveSinglePiece(piece, from, to);
////
////      assertEquals("Taking a single piece", 1, returned);
////
////      assertEquals("Test the 'to' cell contains one piece)", 1,
////            board.getPiecesAt(to).size());
////
////      assertTrue("test the correct piece is in the 'to' cell",
////            board.getPiecesAt(to).get(0).equals(piece));
////
////      assertFalse("test the from cell no longer contains the piece",
////            board.getPiecesAt(from).contains(piece));
////
////   }
//
//
////   @Test(expected = PieceNotFoundException.class)
////   public void testMoveSinglePiece_pnfe()
////         throws IllegalMoveException, PieceNotFoundException {
////
////      // moving to cell containing a single enemy piece
////      from = new Point(5, 0);
////      to = new Point(4, 0);
////      piece = new Bishop(Colr.WHITE);
////
////      // the argument piece does not match the piece in the cell
////      board.moveSinglePiece(piece, from, to);
////   }
//
//
////   @Test
////   public void testMoveSinglePiece_2()
////         throws IllegalMoveException, PieceNotFoundException {
////      // moving to cell containing a merged enemy piece
////      from = new Point(5, 0);
////      to = new Point(4, 0);
////      piece = board.getPiecesAt(from).get(0);
////
////      board.setPiece(new Bishop(Colr.BLACK), to);
////      board.setPiece(new Knight(Colr.BLACK), to);
////
////      int returned = board.moveSinglePiece(piece, from, to);
////
////      assertEquals("Taking a merged piece", 2, returned);
////   }
////
////
////   @Test
////   public void testMoveMergedPiece_LegalMove()
////         throws IllegalMoveException, PieceNotFoundException {
////      // moving a merged piece to empty locations, no obstructions
////
////      from = new Point(0, 2); // a black knight.
////
////      // merge a piece and test that it is a merged piece
////      board.setPiece(new Rook(Colr.BLACK), from);
////      List<Piece> mergedPiece = board.getPiecesAt(from);
////      assertTrue(mergedPiece.size() == 2);
////
////      List<Point> legalMoves = board.getLegalMoves(from);
////
////      // moving the piece to legal places
////      for (Point too : legalMoves) {
////         assertEquals(0, board.moveMergedPiece(from, too));
////
////         // check the piece is in the target location
////         assertTrue(too.toString(),
////               board.getPiecesAt(too).contains(new Knight(Colr.BLACK)));
////         assertTrue(too.toString(),
////               board.getPiecesAt(too).contains(new Rook(Colr.BLACK)));
////
////         // check the 'from' cell is empty
////         assertEquals(new ArrayList<Piece>(), board.getPiecesAt(from));
////
////         // put the piece back
////         board.moveMergedPiece(too, from);
////      }
////   }
//
//
////   @Test(expected = IllegalMoveException.class)
////   public void testMoveMergedPiece_NotLegalMove()
////         throws IllegalMoveException, PieceNotFoundException {
////      // throwing an exception for an illegal move
////
////      from = new Point(0, 2); // a black knight.
////      to = new Point(2, 0);
////
////      // merge a piece and test that it is a merged piece
////      board.setPiece(new Rook(Colr.BLACK), from);
////      List<Piece> mergedPiece = board.getPiecesAt(from);
////      assertTrue(mergedPiece.size() == 2);
////
////      // move piece to illegal position
////      assertEquals(0, board.moveMergedPiece(from, to));
////   }
//
//
//   @Test // TODO presentation
//   public void testIsObstructed_NoObstructions() throws IllegalMoveException {
//      Point knightFrom = new Point(5, 2);
//      Point knightTo = new Point(3, 1);
//
//      Point bishopFrom = new Point(5, 1);
//      Point bishopTo = new Point(3, 3);
//
//      Point rookFrom = new Point(0, 5);
//      Point rookTo = new Point(3, 5);
//
//      // both can move as there is no obstruction
//      assertFalse(board.isObstructed(bishopFrom, bishopTo));
//      assertFalse(board.isObstructed(knightFrom, knightTo));
//      assertFalse(board.isObstructed(rookFrom, rookTo));
//
//   }
//
//
//   @Test // TODO presentation
//   public void testIsObstructed_WithObstructions() throws IllegalMoveException {
//      Point knightFrom = new Point(5, 2);
//      Point knightTo = new Point(3, 1);
//
//      Point bishopFrom = new Point(5, 1);
//      Point bishopTo = new Point(3, 3);
//
//      Point rookFrom = new Point(0, 5);
//      Point rookTo = new Point(3, 5);
//
//      // add obstructions
//      board.setPiece(new Knight(Colr.BLACK), new Point(4, 2));
//      board.setPiece(new Rook(Colr.BLACK), new Point(4, 1));
//      board.setPiece(new Rook(Colr.BLACK), new Point(1, 5));
//
//      // the bishop and rook should be obstructed and the rook not
//      assertTrue(board.isObstructed(bishopFrom, bishopTo));
//      assertFalse(board.isObstructed(knightFrom, knightTo));
//      assertTrue(board.isObstructed(rookFrom, rookTo));
//
//   }
//
//
//   @Test
//   public void testAreSameColor() {
//      // tests the methods that compares for similar colour
//
//      // matching colors
//      assertTrue("Matching color", board.areSameColor(new Point(0, 0), new Point(0, 4)));
//      assertTrue("Matching color", board.areSameColor(new Point(5, 4), new Point(5, 2)));
//
//      // not matching color
//      assertFalse("Not matching color",
//            board.areSameColor(new Point(0, 1), new Point(5, 1)));
//      assertFalse("Not matching color",
//            board.areSameColor(new Point(5, 4), new Point(0, 2)));
//
//      // one or both cells are empty
//      assertFalse("second cell is empty",
//            board.areSameColor(new Point(5, 4), new Point(3, 5)));
//      assertFalse("first cell is empty",
//            board.areSameColor(new Point(4, 5), new Point(5, 5)));
//      assertFalse("both cells are empty",
//            board.areSameColor(new Point(2, 2), new Point(3, 3)));
//
//   }
//
//
//   // the toString() method is used by the textual interface to display the board
//   @Test
//   public void testToString() {
//      assertEquals(defaultBoardStr(), board.toString());
//   }
//
//   // tests for is merged and piece splitting
//   @Test
//   public void testIsMerged_single() {
//      // testing single pieces and empty square
//      assertFalse("isMerged: single piece", board.isMerged(new Point(5, 3)));
//      assertFalse("isMerged: empty square", board.isMerged(new Point(2, 3)));
//   }
//   
//   @Test
//   public void testIsMerged_merged() throws IllegalMoveException {
//      // testing isMerged on a merged square
//      point = new Point(5, 1);
//      board.setPiece(new Rook(Colr.WHITE), point);
//      
//      assertTrue("isMerged: merged piece", board.isMerged(point));
//   }
//   
//   @Test
//   public void testSplit_single() {
//      // testing the return on splitting a single piece of empty square
//      
//      point = new Point(5, 1);
//      assertFalse("split: single piece", board.split(point));
//      
//      point = new Point(4, 5);
//      assertFalse("split: empty square", board.split(point));
//   }
//   
//   @Test
//   public void testSplit_merged() throws IllegalMoveException {
//      // testing a merged splits
//      point = new Point(5, 1);
//      board.setPiece(new Rook(Colr.WHITE), point);
//      assertTrue("isMerged: merged piece", board.isMerged(point));
//      
//      assertTrue("Splitting a merged piece", board.split(point));
//      assertFalse("isMerged: merged piece", board.isMerged(point));
//      
//   }
//   
//   
//   @Test // TODO
//   public void testAllPiecesGone() {
////      board.allPiecesGone(Colr.BLACK); // TODO
////      fail("test not implemented");
//      assertFalse(board.allPiecesGone(Colr.BLACK));
//      assertFalse(board.allPiecesGone(Colr.WHITE));
//   }
//
//
//   private String defaultBoardStr() {
//      String[] pieces = { "BRW", "BBB", "BKW", "BKB", "BBW", "BRB", "B", "W", "B", "W",
//            "B", "W", "W", "B", "W", "B",
//            "W", "B", "B", "W", "B", "W", "B", "W", "W", "B", "W", "B", "W", "B", "WRB",
//            "WBW", "WKB", "WKW", "WBB", "WRW" };
//
//      StringJoiner sj = new StringJoiner("", "", "\n");
//
//      for (int row = 0; row < 6; row++) {
//         for (int col = 0; col < 6; col++) {
//            sj.add(String.format("%6S", pieces[(row * Board.WIDTH) + col]));
//         }
//         sj.add("\n");
//      }
//
//      return sj.toString();
//   }

}
