package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Point;
import view_interfaces.View;

/**
 * Event Listener, player selects a square on the chess board.
 * 
 * @author Bernard O'Meara
 */
public class PieceClickedListener extends MouseAdapter {
   private Point point;
   private View viewModel;
   
   public PieceClickedListener(View viewModel, Point point) {
      this.point = point;
      this.viewModel = viewModel;
   }

   public void mouseClicked(MouseEvent e) {
      viewModel.squareSelected(point);
   }
}
