package view.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.LoginListener;
import controllers.LogoutListener;
import controllers.NewGameListener;
import controllers.RegisterListener;
import view_interfaces.FontsAndColors;
import view_interfaces.View;

@SuppressWarnings("serial")
public class MenuBarChess extends JMenuBar implements FontsAndColors {
	
	public MenuBarChess(View viewModel) {
		
		setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		
		add(gameMenu(viewModel));
		add(helpMenu(viewModel));
	}


	/**
	 * A menu bar item for game setup. Includes, New Game, Register,
	 * Login and Logout.
	 * 
	 * @param viewModel The class handling user interface logic
	 * @return The menu item for game settings
	 */
   private JMenu gameMenu(View viewModel) {
	   JMenu gameMenu = new JMenu("Game");
	   gameMenu.setFont(FONT20);
      
	   // new game
      JMenuItem newGame = new JMenuItem("New Game");
      newGame.setFont(FONT20);
      newGame.addActionListener(new NewGameListener(viewModel));
      
      // register
      JMenuItem register = new JMenuItem("Register");
      register.setFont(FONT20);
      register.addActionListener(new RegisterListener(viewModel));
      
      // login
      JMenuItem login = new JMenuItem("Log In");
      login.setFont(FONT20);
      login.addActionListener(new LoginListener(viewModel));
      
      //Logout
      JMenuItem logout = new JMenuItem("Log Out");
      logout.setFont(FONT20);
      logout.addActionListener(new LogoutListener(viewModel));
      

      gameMenu.add(newGame);
      gameMenu.add(register);
      gameMenu.add(login);
      gameMenu.add(logout);
      
      return gameMenu;
   }
   

	/**
	 * A menu bar item that provides help and information
	 * 
	 * @param viewModel The class handling user interface logic
	 * @return The menu item for help and instructions
	 */
	private JMenu helpMenu(View viewModel) {
	   JMenu helpMenu = new JMenu("Help");
      helpMenu.setFont(FONT20);
      
      JMenuItem help = new JMenuItem("Help");
      help.setFont(FONT20);
      
      JMenuItem about = new JMenuItem("About");
      about.setFont(FONT20);
      
//      about.addActionListener(new NewGameListener(viewModel));

      helpMenu.add(help);
      helpMenu.add(about);
      
      return helpMenu;
   }

}
