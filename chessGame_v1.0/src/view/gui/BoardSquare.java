package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * A panel that represents a single square of the chess board.
 * 
 * @author Bernard O'Meara
 *
 */
@SuppressWarnings("serial")
public class BoardSquare extends JPanel {
   private Image img;
   private int borderWidth = 4;
   private JLabel mark = new JLabel();
   private static final Font FONT = new Font("Verdana", Font.PLAIN, 56);


   public BoardSquare(String imagePath) {
      setImage(imagePath);

      setLayout(new GridLayout(1, 1));

      mark = new JLabel("", JLabel.CENTER);
      mark.setFont(FONT);
      mark.setForeground(Color.red);
      add(mark);

   }


   /**
    * Takes an image filename with path and sets if as a background image
    * 
    * @param The
    *           imagePath and filename
    */
   public void setImage(String imagePath) {
      img = new ImageIcon(imagePath).getImage();
      repaint();
   }


   /**
    * Sets border color and visibility
    * 
    * @param color
    *           The border color
    * @param set
    *           Boolean to show/hide the border
    */
   public void setBorder(Color color, boolean set) {
      setBorder(BorderFactory.createLineBorder(color, set ? borderWidth : 0));
   }


   /**
    * Overrides the default method allowing images to be used as backgrounds.
    * 
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawImage(img, 0, 0, null);
   }


   /**
    * Sets an indication that a move is legal on or off.
    * 
    * @param set
    *           True to show the move is legal.
    */
   public void showLegalMoves(boolean set) {
      mark.setText(set ? "x" : "");
   }

}