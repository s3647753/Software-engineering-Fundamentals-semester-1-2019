package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view_interfaces.FontsAndColors;

@SuppressWarnings("serial")
public class ToolBar extends JPanel implements FontsAndColors {   
   private JLabel statusMsg;
   
   public ToolBar() {
      setLayout(new GridLayout(1, 1));
      setPreferredSize(new Dimension(0, 50));
      setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      setBackground(bgDark);

      statusMsg = new JLabel("", JLabel.CENTER);
      statusMsg.setForeground(fgHLite);
      statusMsg.setFont(FONT35);

      add(statusMsg, BorderLayout.CENTER);

   }


   
   public void setTurnColor(String message) {
      statusMsg.setText(message);
   }
}
