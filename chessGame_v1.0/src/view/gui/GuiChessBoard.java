package view.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controllers.PieceClickedListener;
import model.Point;
import model_Interfaces.Board;
import view_interfaces.View;

/**
 * ChessBoard is the main board panel in the GUI that displays the board in a
 * human friendly manner.
 * 
 * @author Bernard O'Meara
 * 
 */
@SuppressWarnings("serial")
public class GuiChessBoard extends JPanel {
   private View viewModel;
   Map<Point, BoardSquare> squares;


   public GuiChessBoard(View viewModel, Board board) {
      this.viewModel = viewModel;
      squares = new HashMap<>();

      setLayout(new GridLayout(board.WIDTH, board.HEIGHT));
      init(board);

   }


   /**
    * Initializes the GUI board with squares containing images of either the
    * background color or the piece occupying the square.
    * 
    * @param board
    *           The collection of squares that make up the board.
    */
   public void init(Board board) {
      Point point;

      for (int row = 0; row < board.HEIGHT; row++) {
         for (int col = 0; col < board.WIDTH; col++) {
            point = new Point(row, col);

            squares.put(point, new BoardSquare(
                  String.format("images/%s.png", board.getCode(point))));

            squares.get(point)
                  .addMouseListener(new PieceClickedListener(viewModel, point));

            add(squares.get(point));
         }
      }
   }


   /**
    * Updates the users view of the chess board to match the game board passed in.
    * 
    * @param board
    *           The current game board to display to the user.
    */
   public void update(Board board) {

      for (Point point : squares.keySet()) {
         squares.get(point).setImage(String.format("images/%s.png",
               board.getCode(point)));
      }
   }


   /**
    * Turns the highlight for square on or off.
    * 
    * @param point
    *           The location of the square to highlight.
    * @param set
    *           If true the square is highlighted else no highlight.
    */
   public void highlight(Point point, Boolean set) {
      squares.get(point).setBorder(Color.BLUE, set);
   }


   /**
    * Turns on the legal move indication for the required squares.
    * 
    * @param legalMoves
    *           The squares that need to be indicated a legal moves.
    * @param set
    *           True to turn on square indication, false to turn off.
    */
   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      for (Point point : legalMoves) {
         squares.get(point).showLegalMoves(set);
      }

   }

}