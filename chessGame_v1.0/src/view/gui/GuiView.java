package view.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import model.Point;
import model_Interfaces.Board;
import view_interfaces.View;
import view_interfaces.ViewType;

@SuppressWarnings("serial")
public class GuiView extends JFrame implements ViewType {
	
	private View viewModel;
	private ChessBoard guiBoard;
	private StatusBar upperStatus, lowerStatus;
	private PlayerPanel player1, player2;

	public GuiView() {
		super();
		
		setTitle("Chess Like Game");
		setBounds(300, 50, 900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		

//		// TODO move to the init method
//		setJMenuBar(new ChessMenuBar(viewModel));
//		add(new ChessBoard(viewModel), BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void initView(View viewModel, Board board) {
		this.viewModel = viewModel;
		guiBoard = new ChessBoard(viewModel, board);
		upperStatus = new StatusBar();
		lowerStatus = new StatusBar();
		player1 = new PlayerPanel(viewModel);
		player2 = new PlayerPanel(viewModel);
		
		
		setJMenuBar(new ChessMenuBar(viewModel));
		add(guiBoard, BorderLayout.CENTER);
		add(upperStatus, BorderLayout.NORTH);
		add(lowerStatus, BorderLayout.SOUTH);
		add(player1, BorderLayout.WEST);
		add(player2, BorderLayout.EAST);
		
		// TODO this is temp
		upperStatus.setMessage("Welcome to the Chess Like Game");
		
		setVisible(true);
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

	// called by the ViewModel to update the game board
	@Override
	public void updateBoard(Board gameBoard) {
	   guiBoard.update(gameBoard); // TODO this is init should be set
	   revalidate();
	   repaint();
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatus(String message) {
	   lowerStatus.setMessage(message);		
	}

   @Override
   public void highlight(Point point, boolean set) {
      guiBoard.highlight(point, set);
      
   }

   @Override
   public void showLegalMoves(List<Point> legalMoves, boolean set) {
      guiBoard.showLegalMoves(legalMoves, set);
      
   }

   @Override
   public void setPlayerTurn(String message) {
      upperStatus.setMessage(message);
   }

   @Override
   public void updateSplit(boolean split) {
      player1.updateSplit(split);
      player2.updateSplit(split);
   }

   

	

}
