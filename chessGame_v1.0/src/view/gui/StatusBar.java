package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class StatusBar extends JPanel {
   private static final Font FONT25 = new Font(Font.SERIF, Font.BOLD, 25);
   private static final Font FONT35 = new Font(Font.SERIF, Font.BOLD, 35);
   private static final Color bgColor = new Color(180, 240, 255);
   private static final Color fgColor = Color.BLUE;
   
   
 private JLabel statusMsg, movesRemaining;
   
   public StatusBar() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setPreferredSize(new Dimension(0, 50));
      setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
      setBackground(bgColor);

      statusMsg = new JLabel("", JLabel.CENTER);
      statusMsg.setForeground(fgColor);
      statusMsg.setFont(FONT35);

      add(remainingMovesPanel());
      add(statusMsg, BorderLayout.CENTER);
      add(new JLabel(""));
      
   }
   
   // panel to display the remaining moves for the current game
   private JPanel remainingMovesPanel() {
      JPanel remainingMovesPanel = new JPanel();
      remainingMovesPanel.setBackground(bgColor);
      
      JLabel movesLabel = new JLabel("Moves Remaining: ", JLabel.LEFT);
      movesLabel.setForeground(Color.BLACK);
      movesLabel.setFont(FONT25);
//      movesLabel.setPreferredSize(new Dimension(250, 30));
      
      movesRemaining = new JLabel("0");
      movesRemaining.setForeground(Color.BLACK);
      movesRemaining.setFont(FONT25);
      movesRemaining.setPreferredSize(new Dimension(70, 35));
      
      remainingMovesPanel.add(movesLabel);
      remainingMovesPanel.add(movesRemaining);
      
      return remainingMovesPanel;
   }
   
   public void setMovesRemaining(int remaining) {
      movesRemaining.setText(String.valueOf(remaining));
   }
   
   
//   private static final Font FONT35 = new Font(Font.SERIF, Font.BOLD, 35);
//   private static final Color bgColor = new Color(180, 240, 255);
//   private JLabel statusMsg;
//   
//   public StatusBar() {
//      setLayout(new GridLayout(1, 1));
//      setPreferredSize(new Dimension(0, 50));
//      setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//      setBackground(bgColor);
//
//      statusMsg = new JLabel("", JLabel.CENTER);
//      statusMsg.setForeground(Color.BLUE);
//      statusMsg.setFont(FONT35);
//
//      add(statusMsg, BorderLayout.CENTER);
//   }

   
   public void setMessage(String message) {
      statusMsg.setText(message);
   }
}
