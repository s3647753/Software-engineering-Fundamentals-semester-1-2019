package controllers;

/**
 * Event Listener, player selects to About.
 * 
 * @author Bernard O'Meara
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class AboutListener implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      String about = 
            "RMIT University\n"
            + "ISYS1118, Software Engineering Fundamentals\n"
            + "2019 Semester 1, Assignment\n"
            + "Benjamin Hunter\n"
            + "Bernard O'Meara\n"
            + "Matthew Eletva\n"
            + "Shaun Davis";
      
      JOptionPane.showMessageDialog(null, about);
   }
   
   

}
