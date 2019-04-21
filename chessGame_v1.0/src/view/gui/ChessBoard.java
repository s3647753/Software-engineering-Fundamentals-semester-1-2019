package view.gui;

import java.awt.Color;

import java.awt.GridLayout;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PieceClickedListener;
import model.Point;
import model_Interfaces.Board;
import model_Interfaces.Piece;
import view_interfaces.View;

/**
 * ChessBoard is the main board panel in the GUI that displays the board in a
 * human friendly manner.
 * 
 * @author Bernard O'Meara
 *
 */

@SuppressWarnings("serial")
public class ChessBoard extends JPanel {
   // TODO get rid of magic numbers when GE is ready
   private int width = 6;
   private int height = 6;
   private View viewModel;
   private BoardSquare squares[][] = new BoardSquare[height][width];

   public ChessBoard(View viewModel, Board board) {
      this.viewModel = viewModel;

      setLayout(new GridLayout(width, height));
      init(board);

   }

   /**
    * TODO is this required ? adds the background to the chess board The images of
    * the pieces are the background when there is no piece an image of just the
    * background is given
    * 
    */
   public void init(Board board) {
      Point point;

      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            point = new Point(row, col);

            squares[point.getRow()][point.getCol()] = new BoardSquare(
                  String.format("images/%s.png",
                        board.getCode(point)));
            
            squares[row][col]
                  .addMouseListener(new PieceClickedListener(viewModel, point));
            
            add(squares[row][col]);
         }
      }
   }

   
   public void update(Board board) {
      Point point;
      
      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            point = new Point(row, col);
            squares[row][col].setImage(String.format("images/%s.png",
                  board.getCode(point)));

         }
      }
   }

   public void highlight(Point point, Boolean set) {
      square(point).setBorder(Color.BLUE, set);
   }


   private BoardSquare square(Point point) {
      return squares[point.getRow()][point.getCol()];
   }

   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      for(Point point: legalMoves) {
         square(point).showLegalMoves(legalMoves, set);
      }
      
   }

 
}