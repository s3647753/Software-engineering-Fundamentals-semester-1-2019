package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view_interfaces.View;

/**
 * Event Listener, player selects to register a new player.
 * 
 * @author Bernard O'Meara
 */
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
