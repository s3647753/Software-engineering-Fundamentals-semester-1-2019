package view.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model_Interfaces.Board;
import view_interfaces.View;
import view_interfaces.ViewType;

@SuppressWarnings("serial")
public class GuiView extends JFrame implements ViewType {
	
	private View viewModel;

	public GuiView() {
		super();
		
		setTitle("Showing the board");
		setBounds(300, 50, 600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

//		// TODO move to the init method
//		setJMenuBar(new ChessMenuBar(viewModel));
//		add(new ChessBoard(viewModel), BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void initView(View viewModel) {
		this.viewModel = viewModel;
		
		setJMenuBar(new ChessMenuBar(viewModel));
		add(new ChessBoard(viewModel), BorderLayout.CENTER);
	}

	@Override
	public String[] registerPlayer() throws OperationCancelledException {
		String title = "Player Registration";
		
		return new NameAndPasswordDialog().getNameAndPassword(title);
	}

//	@Override
//	public String deRegisterPlayer() throws UserInputException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String[] loginPlayer() throws OperationCancelledException {
		String title = "Player Login";
		
		return new NameAndPasswordDialog().getNameAndPassword(title);
	}

	@Override
	public String logoutPlayer() throws OperationCancelledException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBoard(Board gameBoard) {
		// have as a field a 2D array of 
		
		// get the list of pieces from the board
		
		
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatus(String message) {
		// TODO Auto-generated method stub
		
	}

	

}
