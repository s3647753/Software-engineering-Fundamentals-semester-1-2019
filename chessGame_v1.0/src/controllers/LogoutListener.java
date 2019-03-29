package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

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
