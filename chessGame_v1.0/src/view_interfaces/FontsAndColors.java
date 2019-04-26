package view_interfaces;

import java.awt.Color;
import java.awt.Font;

/**
 * Fonts and colors used throughout the GUI.
 * 
 * @author Bernard O'Meara
 *
 */
public interface FontsAndColors {
   // Fonts
   static final Font FONT20 = new Font(Font.SERIF, Font.BOLD, 20);
   static final Font FONT25 = new Font(Font.SERIF, Font.BOLD, 25);
   static final Font FONT30 = new Font(Font.SERIF, Font.BOLD, 30);
   static final Font FONT35 = new Font(Font.SERIF, Font.BOLD, 35);
   
   // Background Colors
   static final Color bgDark = new Color(180, 240, 255);
   static final Color bgLight = new Color(220, 250, 255);
   
   // Foreground  colors
   static final Color fgHLite = Color.BLUE;
   static final Color fgPlain = Color.BLACK;

}
