package view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.SplitListener;
import view_interfaces.View;

public class PlayerPanel extends JPanel {
   
   private static final Font FONT30 = new Font(Font.SERIF, Font.BOLD, 30);
   private static final Color bgColor = new Color(220, 250, 255);
   
   private View viewModel;
   private JLabel nameFld, colorFld, remainingMovesFld, scorefld, splitLblFld;
   private String splitMsg = "Splitting";
   private String notSplitMsg = "Not Splitting";
   

   public PlayerPanel(View viewModel) {
      this.viewModel = viewModel;
      
      setLayout(new GridLayout(14, 1));
      setPreferredSize(new Dimension(150, 0));
      setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      setBackground(bgColor);
      
      // labels for player fields
      JLabel nameLbl = new JLabel("Name", JLabel.CENTER);
      nameLbl.setFont(FONT30);
      nameLbl.setForeground(Color.BLUE);
      
      JLabel colorLbl = new JLabel("Colour", JLabel.CENTER);
      colorLbl.setFont(FONT30);
      colorLbl.setForeground(Color.BLUE);
      
      JLabel movesLbl = new JLabel("Moves", JLabel.CENTER);
      movesLbl.setFont(FONT30);
      movesLbl.setForeground(Color.BLUE);
      
      JLabel scoreLbl = new JLabel("Score", JLabel.CENTER);
      scoreLbl.setFont(FONT30);
      scoreLbl.setForeground(Color.BLUE);
      
      // data fields
      nameFld = new JLabel("testman", JLabel.CENTER); 
      nameFld.setFont(FONT30);
//      name.setForeground(Color.BLUE);
      
      colorFld = new JLabel("white", JLabel.CENTER);
      colorFld.setFont(FONT30);
//      color.setForeground(Color.BLUE);
      
      remainingMovesFld = new JLabel("10", JLabel.CENTER);
      remainingMovesFld.setFont(FONT30);
//      remainingMoves.setForeground(Color.BLUE);
      
      scorefld = new JLabel("20", JLabel.CENTER);
      scorefld.setFont(FONT30);
//      score.setForeground(Color.BLUE);
      
      // the split button and label
      JButton splitBtn = new JButton("Split");
      splitBtn.addActionListener(new SplitListener(viewModel));
      splitLblFld = new JLabel("", JLabel.CENTER);
      
      
  
      
      // add all the components
      add(nameLbl);
      add(nameFld);
      add(new JLabel());
      add(colorLbl);
      add(colorFld);
      add(new JLabel());
      add(movesLbl);
      add(remainingMovesFld);
      add(new JLabel());
      add(scoreLbl);
      add(scorefld);
      add(new JLabel(""));
      add(splitBtn);
      add(splitLblFld);
   }


   public void updateSplit(boolean split) {
      if(split) {
         splitLblFld.setText(splitMsg);
      }
      
      else if(!split) {
         splitLblFld.setText(notSplitMsg);
      }
      
   }
}
