package model_Interfaces;

import enums.Colr;

public interface Player {
	
	/**
	 * Returns the name of the player
	 * @return player name as string
	 */
	public String getName(); 
	
	/**
	 * Returns the player current points
	 * @return player points as int
	 */
	public int getPoints();
	
	/**
	 * Returns the colour of the player
	 * @return player colour as Colr
	 */
	public Colr getColour();
	
	/**
	 * Increase the player's score by the number given
	 * @param points to add to score
	 */
	public void increaseScore(int increaseAmount);
	
	/**
	 * Reset player score to zero
	 */
	public void resetScore();
}
