package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;


/**
 * Event Listener, player selects to logout.
 * 
 * @author Bernard O'Meara
 */
public class LogoutListener implements ActionListener {
	
	private View viewModel;

	public LogoutListener(View viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.logoutPlayer();
	}

}
