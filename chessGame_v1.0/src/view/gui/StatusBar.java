package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view_interfaces.FontsAndColors;

/**
 * A status bar for displaying status messages and the remaining moves in the
 * current game.
 * 
 * @author Bernard O'Meara
 *
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel implements FontsAndColors {
   private JLabel statusMsg, movesRemaining;


   public StatusBar() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setPreferredSize(new Dimension(0, 50));
      setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
      setBackground(bgDark);

      statusMsg = new JLabel("", JLabel.CENTER);
      statusMsg.setForeground(fgHLite);
      statusMsg.setFont(FONT30);

      add(remainingMovesPanel());
      add(statusMsg, BorderLayout.CENTER);
      add(new JLabel(""));

   }


   /**
    * A sub panel of the status bar that displays the remaining moves.
    * 
    * @return The panel for remaining moves
    */
   private JPanel remainingMovesPanel() {
      JPanel remainingMovesPanel = new JPanel();
      remainingMovesPanel.setBackground(bgDark);

      JLabel movesLabel = new JLabel("Moves Remaining: ", JLabel.LEFT);
      movesLabel.setForeground(fgPlain);
      movesLabel.setFont(FONT25);

      movesRemaining = new JLabel("0");
      movesRemaining.setForeground(fgPlain);
      movesRemaining.setFont(FONT25);
      movesRemaining.setPreferredSize(new Dimension(70, 35));

      remainingMovesPanel.add(movesLabel);
      remainingMovesPanel.add(movesRemaining);

      return remainingMovesPanel;
   }


   /**
    * Sets the remaining moves on the UI
    * 
    * @param remaining
    *           The number of moves to display
    */
   void setMovesRemaining(int remaining) {
      movesRemaining.setText(String.valueOf(remaining));
   }


   /**
    * Sets a message to the status bar
    * 
    * @param message
    *           The message to display
    */
   void setMessage(String message) {
      statusMsg.setText(message);
   }
}
