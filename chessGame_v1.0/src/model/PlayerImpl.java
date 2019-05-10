package model;

import enums.Colr;
import model_Interfaces.Player;

/**
 * Application of the Player Interface
 * This class stores informaton related to players
 * @author Matt Eletva
 *
 */

public class PlayerImpl implements Player{
	String name;
	int points;
	//Colr colour;
	
	public PlayerImpl(String name){
		this.name = name;
		points = 0;
		//colour = null;
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public int getPoints() {
		return points;
	}

	
	/*@Override
	public Colr getColour() {
		// TODO Auto-generated method stub
		return colour;
	}
	*/

	@Override
	public void increaseScore(int increaseAmount) {
		points = points + increaseAmount;
	}
	

	@Override
	public void resetScore() {
		points = 0;
	}
	

	/*@Override
	public void setColour(Colr colour) {
		this.colour = colour;
	}*/
}
