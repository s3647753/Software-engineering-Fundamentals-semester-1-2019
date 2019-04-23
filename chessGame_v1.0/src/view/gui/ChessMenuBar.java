package view.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.DeRegesterListener;
import controllers.LoginListener;
import controllers.LogoutListener;
import controllers.NewGameListener;
import controllers.GameLengthListener;
import controllers.RegisterListener;
import view.model.ViewModel;
import view_interfaces.View;

@SuppressWarnings("serial")
public class ChessMenuBar extends JMenuBar {
   private static final Font FONT20 = new Font(Font.SERIF, Font.PLAIN, 20);
	private View viewModel;
	
	public ChessMenuBar(View viewModel) {
		this.viewModel = viewModel;
		
		setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		
		add(gameMenu());
		add(registerMenu());
		add(loginMenu());
		add(helpMenu());
	}


   private JMenu gameMenu() {
	   JMenu gameMenu = new JMenu("Game");
	   gameMenu.setFont(FONT20);
      
	   // new game
      JMenuItem newGame = new JMenuItem("New Game");
      newGame.setFont(FONT20);
      newGame.addActionListener(new NewGameListener(viewModel));
      
      // select color
      JMenuItem selectColor = new JMenuItem("Select Colour");
      selectColor.setFont(FONT20);
      selectColor.addActionListener(new GameLengthListener(viewModel));
      
      // set preferred game length
      JMenuItem preferedMoves = new JMenuItem("Game Length");
      preferedMoves.setFont(FONT20);
      preferedMoves.addActionListener(new GameLengthListener(viewModel));
      

      gameMenu.add(newGame);
      gameMenu.add(selectColor);
      gameMenu.add(preferedMoves);
      
      return gameMenu;
   }
   

   /**
	 * TODO
	 * @return
	 */
	private JMenu registerMenu() {
		JMenu registration = new JMenu("Registration");
		registration.setFont(FONT20);
		
		JMenuItem register = new JMenuItem("Register");
		register.setFont(FONT20);
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
		logging.setFont(FONT20);
		
		JMenuItem login = new JMenuItem("Log In");
		JMenuItem logout = new JMenuItem("Log Out");
		
		login.setFont(FONT20);
		logout.setFont(FONT20);

		login.addActionListener(new LoginListener(viewModel));
		logout.addActionListener(new LogoutListener(viewModel));

		logging.add(login);
		logging.add(logout);
		
		return logging;
	}
	
	private JMenu helpMenu() {
	   JMenu helpMenu = new JMenu("Help");
      helpMenu.setFont(FONT20);
      
      JMenuItem help = new JMenuItem("Help");
      help.setFont(FONT20);
      
      JMenuItem about = new JMenuItem("About");
      about.setFont(FONT20);
      
      about.addActionListener(new NewGameListener(viewModel));

      helpMenu.add(help);
      helpMenu.add(about);
      
      return helpMenu;
   }

}
