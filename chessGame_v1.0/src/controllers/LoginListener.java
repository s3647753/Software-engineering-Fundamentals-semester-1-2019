package controllers;

/**
 * Event Listener, player selects to login.
 * 
 * @author Bernard O'Meara
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

public class LoginListener implements ActionListener {
	private View viewModel;

	public LoginListener(View viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.loginPlayer();
	}

}
