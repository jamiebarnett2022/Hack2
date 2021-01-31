package alienGame;

import processing.core.PApplet;
import processing.core.PImage;

public class LocationConfirmer {
	
	private PApplet parent;
	private String location;
	private String name;
	

	
	public LocationConfirmer(PApplet p) 
	{
		parent = p;
		location = "";
		name="";
	}
	public void draw()
	{
		parent.fill(255, 255, 255);
		parent.rect(250, 0, 500, 100);
		parent.fill(0, 0, 0);
		parent.textSize(20);
		parent.text(location, 378, 25);	
		parent.text(name, 378, 75);
	}
	
	public void updateLocation(String loc, String name) {
		location = loc;
		this.name = name;
	}
	

}
