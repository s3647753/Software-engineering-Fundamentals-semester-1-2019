package view.gui;

import java.awt.Color;

import java.awt.GridLayout;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model_Interfaces.Piece;
import view_interfaces.View;

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
	
	private View viewModel;
	
	private BoardSquare squares[][] = new BoardSquare[height][width];

	public ChessBoard(View viewModel) {
		this.viewModel = viewModel;
		
		setLayout(new GridLayout(width, height));
		setBackgroundImages();
		
	}
	
	
	/**
	 * adds the background to the chess board
	 * The images of the pieces are the background
	 * when there is no piece an image of just the background is given
	 * 
	 */
	private void setBackgroundImages() {
		Color color = null;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				// a white square
				if ((row + col) % 2 == 0) {
					color = Color.WHITE;
					squares[row][col] = new BoardSquare(color, "images/w.png");
				}
				
				// a black square
				else {
					color = Color.LIGHT_GRAY;
					squares[row][col] = new BoardSquare(color, "images/b.png");
					
				}

				add(squares[row][col]);
				
			}
		}
	}
	
	private String getBackgroundColor(int row, int col) {
		// TODO this is a bit sloppy
		if ((row + col) % 2 == 0) {
			return "w";
		}
		
		return "b";
		
	}
	
	public String getImageFullPath(int row, int col) {
		// the piece/pieces that reside on a single board square
		List<Piece> pieces = viewModel.getPieceList(row, col);
		// the full filename and path for the image for the piece/s on the square
		StringJoiner filename = new StringJoiner("", "images/", ".png");
		
		switch(pieces.size()) {
		case 2: 
			filename.add(pieces.get(1).getColour().toString().substring(0, 0).toLowerCase());
			filename.add(pieces.get(1).getType().toString().substring(0, 0).toLowerCase());	
		case 1:
			filename.add(pieces.get(0).getColour().toString().substring(0, 0).toLowerCase());
			filename.add(pieces.get(0).getType().toString().substring(0, 0).toLowerCase());
			filename.add("On");
		case 0:
			filename.add(getBackgroundColor(row, col));
		}

		//TODO I am working on this I have not tested it
		
		return filename.toString();
	}
}