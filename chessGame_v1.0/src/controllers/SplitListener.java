package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

public class SplitListener implements ActionListener {
   private View viewModel;

   public SplitListener(View viewModel) {
      super();
      this.viewModel = viewModel;
   }



   @Override
   public void actionPerformed(ActionEvent arg0) {
      viewModel.split();
   }

}
