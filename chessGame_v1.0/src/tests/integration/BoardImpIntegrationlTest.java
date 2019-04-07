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
 * These are an integration test for the Board-Cell-Piece combo
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

}
