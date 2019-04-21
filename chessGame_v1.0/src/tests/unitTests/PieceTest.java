package tests.unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enums.Colr;
import model.Bishop;
import model.Knight;
import model_Interfaces.Piece;
import model.Rook;
import enums.Type;


/**
 * 
 * @author Ben Hunter
 * 
 */
public class PieceTest {

   private Piece piece1, piece2, piece3;

   @Before
   public void setUp() throws Exception {
      piece1 = new Bishop(Colr.WHITE);
      piece2 = new Bishop(Colr.BLACK);
      piece3 = new Knight(Colr.WHITE);
   }

   @Test
   public void testColourOfPiece() {
      assertEquals(Colr.WHITE, piece1.getColor());
      assertEquals(Colr.BLACK, piece2.getColor());
   }

   @Test
   public void testTypeOfPiece() {
      assertEquals(Type.KNIGHT, piece3.getType());
   }

}
