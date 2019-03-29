package view.text;

import model_Interfaces.Board;
import view.gui.OperationCancelledException;
import view_interfaces.View;
import view_interfaces.ViewType;

public class TextView implements ViewType {
	private View viewModel;
	
	

	public TextView(View viewModel) {
		super();
		this.viewModel = viewModel;
		
		// TODO
		System.out.println("\n> User textual interface not implemented.");
		System.exit(0);
	}



	@Override
	public void initView(View viewModel) {
		this.viewModel = viewModel;
	}



	@Override
	public String[] registerPlayer() throws OperationCancelledException {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public String deRegisterPlayer() throws UserInputException {
//		// TODO Auto-generated method stub
//		return null;
//	}



	@Override
	public String[] loginPlayer() throws OperationCancelledException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String logoutPlayer() throws OperationCancelledException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean updateBoard(Board gameBoard) {
		// TODO Auto-generated method stub
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
