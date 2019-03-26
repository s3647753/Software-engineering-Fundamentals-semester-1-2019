package view.views;

import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ChessBoard is the main board panel in the GUI
 * that displays the board in a human friendly manner.
 * 
 * @author Bernard O'Meara
 *
 */

@SuppressWarnings("serial")
public class ChessBoard extends JPanel {
	// TODO get rid of magic numbers when GE is ready
	private int width = 6;
	private int height = 6;
	
	// TODO get rid of magic numbers
	private BoardSquare squares[][] = new BoardSquare[height][width];

	public ChessBoard() {
		setLayout(new GridLayout(width, height));
		colorBackground();
	}
	
	
	/**
	 * adds the background color to the chess board
	 */
	private void colorBackground() {
		Color color = null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				// a white square
				if ((i+j) % 2 == 0) {
					color = Color.WHITE;
					squares[i][j] = new BoardSquare(color, "images/brOnW.png");
				}
				// a black square
				else {
					color = Color.LIGHT_GRAY;
					squares[i][j] = new BoardSquare(color, "images/brOnB.png");
					
				}
				
				

				add(squares[i][j]);
				
				
			}
		}
	}
}