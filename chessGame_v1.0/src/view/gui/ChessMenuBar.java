package view.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.DeRegesterListener;
import controllers.LoginListener;
import controllers.LogoutListener;
import controllers.RegisterListener;
import view.model.ViewModel;
import view_interfaces.View;

@SuppressWarnings("serial")
public class ChessMenuBar extends JMenuBar {
	private View viewModel;
	
	public ChessMenuBar(View viewModel) {
		this.viewModel = viewModel;
		
		add(registerMenu());
		add(loginMenu());
	}

	/**
	 * TODO
	 * @return
	 */
	private JMenu registerMenu() {
		JMenu registration = new JMenu("Registration");
		
		JMenuItem register = new JMenuItem("Register");
//		JMenuItem deRegister = new JMenuItem("De-Register");
		
		register.addActionListener(new RegisterListener(viewModel));
//		deRegister.addActionListener(new DeRegesterListener(viewModel));

		
		registration.add(register);
//		registration.add(deRegister);
		
		return registration;
	}
	
	/**
	 * TODO
	 * @return
	 */
	private JMenu loginMenu() {
		JMenu logging = new JMenu("Login/out");
		
		JMenuItem login = new JMenuItem("Log In");
		JMenuItem logout = new JMenuItem("Log Out");

		login.addActionListener(new LoginListener(viewModel));
		logout.addActionListener(new LogoutListener(viewModel));

		logging.add(login);
		logging.add(logout);
		
		return logging;
	}
	


}
