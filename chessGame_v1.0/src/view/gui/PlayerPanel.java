package view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.SplitListener;
import enums.Colr;
import view_interfaces.View;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
   private static final Font FONT20 = new Font(Font.SERIF, Font.BOLD, 20);
   private static final Font FONT25 = new Font(Font.SERIF, Font.BOLD, 25);
   private static final Font FONT30 = new Font(Font.SERIF, Font.BOLD, 30);
   private static final Color bgColor = new Color(220, 250, 255);
   
   private View viewModel;
   private Colr color;
   private JLabel nameFld, colorFld, scorefld, splitFld;
   
   
   // TODO temp for development
   private String[] names = {"Ben", "Bernie", "Matt", "Shaun"};
   

   public PlayerPanel(View viewModel, Colr color) {
      this.viewModel = viewModel;
      this.color = color;
      
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
      
      JLabel scoreLbl = new JLabel("Score", JLabel.CENTER);
      scoreLbl.setFont(FONT30);
      scoreLbl.setForeground(Color.BLUE);
      
      // data fields
      nameFld = new JLabel("Testman", JLabel.CENTER); 
      nameFld.setFont(FONT25);
      
      String colorStr = "White";
      if(color == Colr.BLACK) {
         colorStr = "Black";
      }
      colorFld = new JLabel(colorStr, JLabel.CENTER);
      colorFld.setFont(FONT25);

      
      scorefld = new JLabel("20", JLabel.CENTER);
      scorefld.setFont(FONT25);
//      score.setForeground(Color.BLUE);
      
      // the split button and label
      JButton splitBtn = new JButton("Split");
      splitBtn.setFont(FONT20);
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
      add(scorefld);
      add(new JLabel(""));
      add(new JLabel(""));
      add(splitBtn);
      add(splitFld);
   }


   public void setMerged(Boolean merged) {
      if(merged) {
         splitFld.setText("Merged");
      }
      else {
         splitFld.setText("");
      }

   }
}
