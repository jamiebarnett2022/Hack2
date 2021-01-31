package alienGame;

import java.util.ArrayList;
import java.util.List;

import org.geonames.Toponym;
import org.geonames.WebService;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.core.PImage;

public class AllTogether extends PApplet{
	
	private Tile[][] grid;
	
	
	private MainCharacter mc;
	private MainCharacter prologueMC;
	private MainCharacter oldMC;
	private PrologueScript ps;
	
	private Tile clickedTile;
	private boolean prologue;
	private boolean atLocation;
	
	private UnfoldingMap map;
	private Location location = new Location(0, 0);
	private Toponym place = null;
	private LocationConfirmer lc;
	
	private int artifactCount;
	private ArrayList<String> placesVisited;
	private ArtifactCounter ac;

	

	public static void main(String[] args) {
		
		PApplet.main("alienGame.AllTogether");

	}

	public void setup()
	{
		size(1000, 1000);
		
		WebService.setUserName("jbarnett");
		
		prologue = true;
		atLocation = false;
		
		grid = new Tile[10][10];	
		clickedTile = null;
		makeTiles();
			
		mc = new MainCharacter(this, 600, 500, "alienImages/dorkSpace.png");
		prologueMC = new MainCharacter(this, 600, 500, "alienImages/dork.png");
		oldMC = new MainCharacter(this, 300, 500, "alienImages/fred.png");
		ps = new PrologueScript(oldMC, prologueMC);
		
		artifactCount = 0;
		placesVisited = new ArrayList<String>();
		ac = new ArtifactCounter(this);
		
		
		map = new UnfoldingMap(this, new Microsoft.AerialProvider());
		map.setZoomRange(2, 8);
		MapUtils.createDefaultEventDispatcher(this,  map);
		lc = new LocationConfirmer(this);
		
	}
	public void draw()
	{
		if(prologue) {
			PImage img = loadImage("alienImages/mars.png");
			image(img, 0, 0);
			prologueMC.draw();
			oldMC.draw();
			writePrologue();
		}
		else if(atLocation) {
			
			PImage img = loadImage("countryImages/" + place.getCountryName() + ".jpg");
			
			try {
				img.resize(1000, 1000);
				image(img, 0, 0);
			}
			catch (Exception e) {
				img = loadImage("countryImages/black.jpg");
				
			}
			img.resize(1000, 1000);
			image(img, 0, 0);
			
			mc.draw();
			ac.draw();
			lc.draw();
		}
		else {
			map.draw();
			
			mc.draw();
			lc.draw();
			ac.draw();
		}
		
		if(clickedTile != null && (mc.getX() != clickedTile.getX() || mc.getY() != clickedTile.getY()))
		{
			mc.moveTo(clickedTile.getX(), clickedTile.getY());
		}
	}
	
	public void makeTiles()
	{
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				
				grid[x][y] = new Tile(this, x * 100, y * 100);
				
			}
		}
	}
	
	public void drawTiles()
	{
		for(Tile[] row : grid)
		{
			for(Tile t : row)
			{
				t.draw();
			}
		}
	}
	
	public void moveCharacter(int x, int y) {
		
		System.out.println("Hello?");
		
		for(Tile[] row : grid)
		{
			for(Tile t : row)
			{
				boolean isClicked = t.isClicked(x, y);
				
				if(isClicked)
				{
					t.changeClicked();					
					clickedTile = t;
				}
			}
		}
	}
	
	public void mouseClicked()
	{
		moveCharacter(mouseX, mouseY);
		
		if(!prologue && !atLocation) {
			location = map.getLocation(mouseX, mouseY);
			place = getPlace(location);
			if(place != null)
				lc.updateLocation(location.getLat() + ", " + location.getLon(), place.getName() + " " + place.getCountryName());	
		}
			
		
		
	}
	
	public Toponym getPlace(Location l) {
		try {
			List<Toponym> list = WebService.findNearbyPlaceName(l.getLat(), l.getLon(), 100, 1);
			if(list.size() > 0)
				return list.get(0);
		}
		catch (java.lang.Exception e) {
			System.out.println("wrong!");
			
		}
		return null;
	}
	
	public void keyPressed()
	{
		
		switch(key) {
		case 'a':
			ps.back();
			break;
		case 'd':
			ps.forward();
			break;
		case ' ':
			if(place != null) {
				String name = place.getName() + " " + place.getCountryName();
				if(!(placesVisited.contains(name))) {
					artifactCount++;
					ac.updateArtifacts(artifactCount);
					placesVisited.add(name);
				}		
				atLocation = true;
			}
				
			break;
		case 'b':
			atLocation = false;
			break;
		}
	}
	
	public void writePrologue()
	{
		boolean finished = ps.speak();
		
		if(finished) {
			prologue = false;
			
		}
	}
			
}
