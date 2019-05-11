package tests.integration;

//import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import enums.Colr;
import model.*;
import model_Interfaces.Board;
import model_Interfaces.Piece;

/**
 * These are an integration test for the Board-Cell-Piece combo
 * 
 * @author Bernard O'Meara
 *
 */
public class BoardImpIntegrationlTest {

   private Board board;
   private Piece piece;
   private Point point, from, to;
   private static Map<Integer, Point> points;


   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      points = new HashMap<>();

      // game board points, row is the tens place and column is the units place
      // e.g., row 3, column 4 has a map index of 34
      for (int row = 0; row < 6; row++) {
         for (int col = 0; col < 6; col++) {
            points.put(row * 10 + col, new Point(row, col));
         }
      }

   }


   @Before
   public void setUp() throws Exception {
      board = new BoardImpl();
   }


   // testing that the pieceCode generated from an empty cell
   @Test
   public void testGetCode_EmptyCells() {
      assertEquals("Empty black cell", "B", board.getCode(new Point(1, 0)));
      assertEquals("Empty white cell", "W", board.getCode(new Point(1, 1)));
   }


   // testing that the piece code generated from a cell containing one piece
   @Test
   public void testGetCode_SinglePiece() {
      // testing the piece code returned when a single piece is in a cell
      assertEquals("Black Rook on Black", "BRB", board.getCode(new Point(0, 5)));
      assertEquals("Black Bishop on White", "BBW",
            board.getCode(new Point(0, 4)));

      assertEquals("Wite Knight on Black", "WKB", board.getCode(new Point(5, 2)));
      assertEquals("White Knight on White", "WKW",
            board.getCode(new Point(5, 3)));
   }


   // testing the pieceCode returned from merged pieces
   @Test
   public void testGetCode_MergedPiece()
         throws IllegalMoveException, PieceNotFoundException {

      // merging a black rook into a black bishop
      board.movePiece(points.get(05), points.get(04));

      assertEquals("merged pieceCode", "BBBRW", board.getCode(points.get(04)));

      // merging a white knight with a white bishop
      board.movePiece(points.get(51), points.get(33));
      board.movePiece(points.get(52), points.get(33));

      assertEquals("merged pieceCode", "WBWKW", board.getCode(points.get(33)));

   }


   // testing getting single piece from board
   @Test
   public void testGetPieceAt_Unmerged() {
      piece = board.getPiecesAt(points.get(52)).get(0);
      assertEquals("getWhiteKnight", new Knight(Colr.WHITE), piece);

      piece = board.getPiecesAt(points.get(04)).get(0);
      assertEquals("getBlackBishop", new Bishop(Colr.BLACK), piece);
   }


   // testing that the list contents returned from a merged cell
   @Test
   public void testGetPieceAt_Merged()
         throws IllegalMoveException, PieceNotFoundException {

      point = points.get(33);

      // merging a white knight with a white bishop
      board.movePiece(points.get(51), point);
      board.movePiece(points.get(52), point);

      // test that the list contains the correct pieces
      assertEquals("number of pieces in the cell", 2,
            board.getPiecesAt(point).size());
      assertTrue("contains White Knight",
            board.getPiecesAt(point).contains(new Knight(Colr.WHITE)));
      assertTrue("contains White Bishop",
            board.getPiecesAt(point).contains(new Bishop(Colr.WHITE)));
   }


   // testing that a piece is correctly taken
   @Test
   public void testTakingAPiece()
         throws IllegalMoveException, PieceNotFoundException {
      point = points.get(25);

      // set up for a white knight to take a black rook
      board.movePiece(points.get(52), points.get(33));
      board.movePiece(points.get(05), point);

      // assert that the point contains the black rook
      assertEquals(new Rook(Colr.BLACK), board.getPiecesAt(point).get(0));

      // white knight takes black rook
      board.movePiece(points.get(33), point);

      // assert that the point contains only the white knight
      assertEquals(new Knight(Colr.WHITE), board.getPiecesAt(point).get(0));
      assertEquals(1, board.getPiecesAt(point).size());

      // move the white knight away
      board.movePiece(point, points.get(33));

      // the cell should now be empty
      assertEquals(0, board.getPiecesAt(point).size());

   }


   // testing getLegalMoves from a knight
   @Test
   public void testGetLegalMoves_Knight() {
      List<Point> moves = board.getLegalMoves(points.get(52));

      // there should be four legal moves
      assertEquals("number of legal moves", 4, moves.size());

      // test each of the four moves are correct
      assertTrue(moves.contains(new Point(4, 0)));
      assertTrue(moves.contains(new Point(4, 4)));
      assertTrue(moves.contains(new Point(3, 1)));
      assertTrue(moves.contains(new Point(3, 3)));

   }


   // testing legal moves returned from a merged piece
   // white bishop and knight merging at point(3,3)
   @Test
   public void testGetLegalMoves_MergedPiece()
         throws IllegalMoveException, PieceNotFoundException {

      // add two pieces to a point near the middle of the board
      point = points.get(33);

      // set up the merged piece
      board.movePiece(points.get(51), point);
      board.movePiece(points.get(52), point);

      List<Point> moves = board.getLegalMoves(point);

      // there should be fourteen legal moves
      assertEquals("number of legal moves", 14, moves.size());

      // test one each of the Bishops moves an the Knights move
      assertTrue(moves.contains(points.get(11)));
      assertTrue(moves.contains(points.get(12)));

   }


   // testing moving a single piece
   @Test
   public void testMoveASinglePiece_1()
         throws IllegalMoveException, PieceNotFoundException {

      // moving a piece to an empty cell
      point = points.get(30);
      assertEquals("testing the square is empty", 0,
            board.getPiecesAt(point).size());
      board.movePiece(points.get(50), point);
      assertEquals("testing the square is not empty", 1,
            board.getPiecesAt(point).size());

      // set up to take the oponents piece
      point = points.get(11);
      board.movePiece(points.get(51), points.get(33));
      board.movePiece(points.get(33), point);

      // moving to cell containing a single enemy piece
      assertEquals("take an enemy piece and get num pieces takes", 1,
            board.movePiece(points.get(03), point));
      assertEquals("number of pieces in square", 1,
            board.getPiecesAt(point).size());
      assertEquals(new Knight(Colr.BLACK), board.getPiecesAt(point).get(0));

   }


   @Test
   public void testMergePieces()
         throws PieceNotFoundException, IllegalMoveException {

      // moving to a cell and merging
      point = points.get(01);
      assertEquals("testing the square is size 1", 1,
            board.getPiecesAt(point).size());
      board.movePiece(points.get(00), point);

      // the square should hold two pieces after the merge
      assertEquals("testing the square is not empty", 2,
            board.getPiecesAt(point).size());

      // test piece types in the square
      assertEquals(new Bishop(Colr.BLACK), board.getPiecesAt(point).get(0));
      assertEquals(new Rook(Colr.BLACK), board.getPiecesAt(point).get(1));

      // test that the status is merged
      assertTrue("checking merged status", board.isMerged(point));
   }
   
   
   @Test(expected = IllegalMoveException.class)
   public void testMoveSinglePiece_IllegalMove()
         throws IllegalMoveException, PieceNotFoundException {

      // the argument piece does not match the piece in the cell
      board.movePiece(points.get(25), points.get(45));
   }
}
