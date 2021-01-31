package Jamie;

import java.util.List;

import org.geonames.Toponym;
import org.geonames.WebService;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class Interaction extends PApplet{
	
	private UnfoldingMap map;
	private int lastX, lastY;
	private boolean startLatDisplay = false;
	private Location location = new Location(40, 40);
	private String name = "";
	
	public static void main(String[] args) {
		PApplet.main("Jamie.Interaction");
	}
	
	public void setup() {

		size(800, 600);
		map = new UnfoldingMap(this, new Microsoft.AerialProvider());
		map.setZoomRange(2, 8);
		MapUtils.createDefaultEventDispatcher(this,  map);
	}
	
	public void draw() {
		map.draw();
		Location location = map.getLocation(mouseX, mouseY);
		fill(0);
		text(location.getLat() + ", " + location.getLon(), mouseX, mouseY);

	}
	
	
	
}
