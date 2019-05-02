package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

/**
 * Event Listener, player selects to start a new game.
 * 
 * @author Bernard O'Meara
 */
public class NewGameListener implements ActionListener {
   private View viewModel;

   public NewGameListener(View viewModel) {
      this.viewModel = viewModel;
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      viewModel.newGame();
   }

}
