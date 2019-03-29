package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * http://comexile.blogspot.com/2015/07/how-to-add-background-image-to-jpanel.html
 */

@SuppressWarnings("serial")
public class BoardSquare extends JPanel {
	private Image img;

	
	public BoardSquare(Color color, String imagePath) {
		img = new ImageIcon(imagePath).getImage();
		
		
		setLayout(new GridLayout(1,1));
//		setBackground(color);
		
		JLabel label = new JLabel("X", JLabel.CENTER);
		Font font = new Font("Verdana", Font.PLAIN, 56);
		label.setFont(font);
		label.setForeground(Color.RED);
		
//		add(label);
//		add(new JLabel(new ImageIcon(imagePath)));

	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);		
	}
	
	

}