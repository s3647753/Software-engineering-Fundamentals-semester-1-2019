package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

public class GameLengthListener implements ActionListener {

   public GameLengthListener(View viewModel) {
      // TODO Auto-generated constructor stub
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      System.out.println("set prefered moves listener called");

   }

}
