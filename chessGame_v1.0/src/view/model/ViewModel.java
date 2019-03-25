package view.model;

import view_interfaces.ViewType;
import model_Interfaces.GameEngine;
import view_interfaces.View;

public class ViewModel implements View {
	// can be either text or GUI
	private ViewType viewType;
	private GameEngine engine;
	
	public ViewModel(GameEngine engine, ViewType viewType) {
		this.engine = engine;
		this.viewType = viewType;

	}

	@Override
	public boolean splitPieces() {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	
}
