package alienGame;

import processing.core.PApplet;

public class GameTile extends Tile{

	private String minigame;
	
	public GameTile(PApplet p, int x, int y) 
	{
		super(p, x, y);
		
	}
	
	public void setMinigame(String game)
	{
		minigame = game;
	}
	

}
