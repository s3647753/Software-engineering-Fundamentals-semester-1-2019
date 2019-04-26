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
import enums.Colr;
import view_interfaces.FontsAndColors;
import view_interfaces.View;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements FontsAndColors {  
   private JLabel nameFld, colorFld, scoreFld, splitFld;
   private JButton splitBtn;
   

   // TODO decompose some of this
   public PlayerPanel(View viewModel, Colr color) {
      
      setLayout(new GridLayout(14, 1));
      setPreferredSize(new Dimension(150, 0));
      setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      setBackground(bgLight);
      
      // labels for player fields
      JLabel nameLbl = new JLabel("Name", JLabel.CENTER);
      nameLbl.setFont(FONT30);
      nameLbl.setForeground(fgHLite);
      
      JLabel colorLbl = new JLabel("Colour", JLabel.CENTER);
      colorLbl.setFont(FONT30);
      colorLbl.setForeground(fgHLite);
      
      JLabel scoreLbl = new JLabel("Score", JLabel.CENTER);
      scoreLbl.setFont(FONT30);
      scoreLbl.setForeground(fgHLite);
      
      // data fields
      nameFld = new JLabel("Testman", JLabel.CENTER); 
      nameFld.setFont(FONT25);
      
      String colorStr = "White";
      if(color == Colr.BLACK) {
         colorStr = "Black";
      }
      colorFld = new JLabel(colorStr, JLabel.CENTER);
      colorFld.setFont(FONT25);
      
      scoreFld = new JLabel("0", JLabel.CENTER);
      scoreFld.setFont(FONT25);
      
      // the split button and label
      splitBtn = new JButton("Split");
      splitBtn.setFont(FONT20);
      splitBtn.setEnabled(false);
      splitBtn.addActionListener(new SplitListener(viewModel));
      splitFld = new JLabel("", JLabel.CENTER);
      splitFld.setFont(FONT20);
      
  
      
      // add all the components
      add(new JLabel(""));
      add(colorLbl);
      add(colorFld);
      add(new JLabel());
      add(nameLbl);
      add(nameFld);
      add(new JLabel());
      add(scoreLbl);
      add(scoreFld);
      add(new JLabel(""));
      add(new JLabel(""));
      add(splitBtn);
      add(splitFld);
   }


   public void setMerged(Boolean merged) {
      String msg = merged ? "Merged" : "";
      
      splitFld.setText(msg);
      splitBtn.setEnabled(merged);
   }
   
   public void setName(String name) {
      nameFld.setText(name);
   }
   
   public void setScore(String score) {
      scoreFld.setText(score);
   }
}
