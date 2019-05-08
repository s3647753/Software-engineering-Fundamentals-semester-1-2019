package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view_interfaces.FontsAndColors;

/**
 * Dialog for getting the player preferences for a new game.
 * 
 * @author Bernard O'Meara
 *
 */
public class NewGameDialog implements FontsAndColors {

   private String[] userInput = new String[4];

   private JComboBox<String> whiteName;
   private JTextField whiteGameLen;

   private JComboBox<String> blackName;
   private JTextField blackGameLen;


   /**
    * Creates an Dialog to get player preferences for a new game. Player
    * preferences String[whiteName, blackName, whiteGameLength, blackGameLength].
    * Validates that the player names are unique and that gameLength is a valid
    * integer stored as a string.
    * 
    * @param names
    *           The names of logged in players
    * @param previous
    *           The field values from the previous game, may be null.
    * @return The player preferences.
    * @throws OperationCancelledException
    * @throws PlayersNotLoggedInException 
    */
   public String[] getGamePreferences(List<String> names, String[] previous)
         throws OperationCancelledException, PlayersNotLoggedInException {

      boolean validInput = false;
      int result;
      JPanel panel = new JPanel(new BorderLayout());

      String title = "New Game, Player Options.";
      JTextArea instructions = new JTextArea(instructions());
      instructions.setBackground(panel.getBackground());
      instructions.setFont(FONT20);

      panel.add(instructions, BorderLayout.NORTH);
      panel.add(inputPanel("White", names, previous), BorderLayout.WEST);
      panel.add(inputPanel("Black", names, previous), BorderLayout.EAST);
      
      if(names.size() < 2) {
         throw new PlayersNotLoggedInException();
      }

      // continue until the players get it right or gives up
      while (!validInput) {
         validInput = true;

         // get the user input
         result = JOptionPane.showConfirmDialog(null, panel, title,
               JOptionPane.OK_CANCEL_OPTION);

         // operation cancelled
         if (result != JOptionPane.OK_OPTION) {
            throw new OperationCancelledException("> Operation Cancelled");
         }

         userInput[0] = whiteName.getSelectedItem().toString();
         userInput[1] = blackName.getSelectedItem().toString();
         userInput[2] = whiteGameLen.getText();
         userInput[3] = blackGameLen.getText();

         validInput = validateInput();
      }

      return userInput;
   }


   /**
    * Validates that the player names are unique and that gameLength is a valid
    * integer stored as a string.
    * 
    * @return true if the input is valid, else false.
    */
   private boolean validateInput() {
      boolean valid = true;

      if (userInput[0].equals(userInput[1]) ||
            !userInput[2].matches("\\d+") ||
            !userInput[3].matches("\\d+")) {
         valid = false;
      }

      return valid;
   }


   /**
    * A player input panel that is used for each of the player preferences
    * 
    * @param color
    *           The piece colour that this panel is used for
    * @param names
    *           The names of the logged in players
    * @param previous
    *           The previous values from the previous game
    * @return The player preferences.
    */
   private JPanel inputPanel(String color, List<String> names,
         String[] previous) {
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(6, 1));
      panel.setPreferredSize(new Dimension(200, 300));
      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      if (previous == null) {
         previous = new String[4];
      }

      // labels
      JLabel colorLbl = new JLabel(color, JLabel.CENTER);
      JLabel nameLbl = new JLabel("Name", JLabel.CENTER);
      JLabel lengthLbl = new JLabel("Game Length", JLabel.CENTER);

      colorLbl.setFont(FONT35);
      nameLbl.setFont(FONT25);
      lengthLbl.setFont(FONT25);
      colorLbl.setForeground(fgHLite);

      // fields
      String[] namesArray = new String[names.size()];
      namesArray = names.toArray(namesArray);

      JComboBox<String> nameBox = new JComboBox<>(namesArray);
      JTextField movesFld = new JTextField("15");

      // white player panel
      if (color.equals("White")) {
         whiteName = nameBox;
         movesFld = new JTextField(previous[2]);
         whiteGameLen = movesFld;

         if (names.contains(previous[0])) {
            nameBox.setSelectedItem(previous[0]);
            movesFld.setText(previous[2]);
         }
      }

      // black player panel
      else {
         blackName = nameBox;
         movesFld = new JTextField(previous[3]);
         blackGameLen = movesFld;

         if (names.contains(previous[1])) {
            nameBox.setSelectedItem(previous[1]);
            movesFld.setText(previous[3]);
         }
      }

      nameBox.setFont(FONT20);
      movesFld.setFont(FONT20);
      movesFld.setBackground(panel.getBackground());

      // populate the panel
      panel.add(colorLbl);
      panel.add(nameLbl);
      panel.add(nameBox);
      panel.add(lengthLbl);
      panel.add(movesFld);
      panel.add(new JLabel(""));

      return panel;
   }


   /**
    * Instructions for the players entering preferences.
    * 
    * @return The player instructions.
    */
   private String instructions() {
      return "Enter Player Preferences\n"
            + "1) Player names must be different\n"
            + "2) Game length must be a positive Integer";
   }

}
