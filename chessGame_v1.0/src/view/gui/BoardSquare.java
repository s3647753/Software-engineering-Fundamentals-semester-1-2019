package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Point;


@SuppressWarnings("serial")
public class BoardSquare extends JPanel {
	private Image img;
	private int borderWidth = 4;
	private JLabel mark = new JLabel();
	private static final Font FONT = new Font("Verdana", Font.PLAIN, 56);

	public BoardSquare() {
	   
	}
	
	public BoardSquare(String imagePath) {
	   setImage(imagePath);		
		
		setLayout(new GridLayout(1,1));
		
		mark = new JLabel("", JLabel.CENTER);
		mark.setFont(FONT);
		mark.setForeground(Color.red);
		add(mark);
		
		// TODO this code sets a label on top of the image (keep for now)
//		JLabel label = new JLabel("X", JLabel.CENTER);
//		Font font = new Font("Verdana", Font.PLAIN, 56);
//		label.setFont(font);
//		label.setForeground(Color.red);
//		add(label);
		
//		add(new JLabel(new ImageIcon(imagePath)));


	}
	
	public void setImage(String imagePath) {
	   img = new ImageIcon(imagePath).getImage();
	   repaint();
	}
	
	public void setBorder(Color color, boolean set) {
	   if(set)
	      setBorder(BorderFactory.createLineBorder(color, borderWidth));
	   else
	      setBorder(null);
	}
	


   @Override
	public void paintComponent(Graphics g) {
      super.paintComponent(g);
      
		g.drawImage(img, 0, 0, null);		
	}

   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      if(set) {
         mark.setText("x");
      }
      
      else {
         mark.setText("");
      }
      
   }
	
	

}