package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolBar extends JPanel {
   private static final Font FONT35 = new Font(Font.SERIF, Font.BOLD, 35);
   private static final Color bgColor = new Color(180, 240, 255);
   private static final Color fgColor = Color.BLUE;
   
   private JLabel statusMsg, movesRemaining;
   
   public ToolBar() {
      setLayout(new GridLayout(1, 1));
      setPreferredSize(new Dimension(0, 50));
      setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      setBackground(bgColor);

      statusMsg = new JLabel("", JLabel.CENTER);
      statusMsg.setForeground(fgColor);
      statusMsg.setFont(FONT35);

      add(statusMsg, BorderLayout.CENTER);

   }


   
   public void setTurnColor(String message) {
      statusMsg.setText(message);
   }
}
