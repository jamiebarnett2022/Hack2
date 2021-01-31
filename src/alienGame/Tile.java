package alienGame;

import processing.core.PApplet;

import processing.core.PImage;

public class Tile {
	
	private int locX, locY;
	private PApplet p;

	private final int size;
	
	private boolean clicked;
	
	private String game;

	public Tile(PApplet p, int x, int y) {

		locX = x;
		locY = y;
		
		this.p = p;	
		
		size = 100;
		
		clicked = false;
		
		game = "";

	}
	
	public void draw()
	{
		if(clicked)
			p.fill(150, 200, 150);
		else
			p.fill(50, 50, 50);
				
		p.rect(locX, locY, size, size);
	}
	
	public boolean isClicked(int mouseX, int mouseY)
	{
		if((mouseX > locX && mouseX < locX + size) && (mouseY > locY && mouseY < locY + size))
			return true;
		return false;
	}
	public void changeClicked()
	{
		clicked = !clicked;
	}
	public int getX()
	{
		return locX;
	}
	public int getY()
	{
		return locY;
	}
	
	public void setGame(String s)
	{
		game = s;
	}

}
