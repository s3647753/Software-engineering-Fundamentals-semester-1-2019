package view.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view_interfaces.ViewType;

public class GuiView extends JFrame implements ViewType {

	public GuiView() {
		setTitle("Showing the board");
		setBounds(300, 50, 600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new ChessBoard(), BorderLayout.CENTER);

		setVisible(true);
	}

	// TODO devekioment purposes only delete when finished
	public static void main(String[] args) {
		new GuiView();
	}
}
