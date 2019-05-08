package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view_interfaces.FontsAndColors;

/**
 * A panel that notifies the players whose turn it is.
 * 
 * @author Bernard O'Meara
 *
 */
@SuppressWarnings("serial")
public class ToolBar extends JPanel implements FontsAndColors {
   private JLabel turnColorMsg;


   public ToolBar() {
      setLayout(new GridLayout(1, 1));
      setPreferredSize(new Dimension(0, 50));
      setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      setBackground(bgDark);

      turnColorMsg = new JLabel("", JLabel.CENTER);
      turnColorMsg.setForeground(fgHLite);
      turnColorMsg.setFont(FONT35);

      add(turnColorMsg, BorderLayout.CENTER);

   }


   /**
    * Sets a message telling the players whose turn it is.
    * 
    * @param message
    *           The message tp display.
    */
   public void setTurnColor(String message) {
      turnColorMsg.setText(message);
   }
}
