package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

public class RegisterListener implements ActionListener {
	private View viewModel;

	public RegisterListener(View viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		viewModel.registerPlayer();
	}

}
