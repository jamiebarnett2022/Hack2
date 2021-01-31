package Jamie;

import java.io.IOException;
import java.lang.Exception;
import java.util.List;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.WebService;

import processing.core.PApplet;

public class blah extends PApplet {
	
	private String searchName = "berlin"; 
	private ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria(); 
	private boolean searchEvent = false;
	private List<Toponym> nearby;
	
	public static void main(String[] args) {
		PApplet.main("Jamie.blah");
	}
	
	public void setup() {
		size(800, 600);
		nearby = null;
		WebService.setUserName("jbarnett");                                                                       
	}
	
	public void draw() {
		searchCriteria.setQ(searchName);
		
		try {
			List<Toponym> lost = WebService.findNearbyPlaceName(39.909718, -97.96874);
			if(lost.size() > 0)
				System.out.println(lost.get(0).getName() + ", " + lost.get(0).getCountryName());
		}
		catch (java.lang.Exception e) {
			System.out.println("wrong!");
		}
		
//		if(searchEvent == true) {
//			try {
//	            ToponymSearchResult searchResult = WebService.search(searchCriteria); // a toponym search result as returned by the geonames webservice.
//
//	            for (Toponym toponym : searchResult.getToponyms()) {
//	                println(toponym.getName() + " " + toponym.getCountryName()
//	                        + " " + toponym.getLongitude() + " "
//	                        + toponym.getLatitude()); // prints the search results. We have access on certain get-Functions. In our Case the Name, Country, Longitude and Latitude
//	                    }
//
//	                    } catch (Exception e) {
//	                        // TODO Auto-generated catch block
//	                        e.printStackTrace();
//	            }   
//			searchEvent = false;
//		}
	}
	
	public void keyPressed() {
		switch(key) {
			case  'a':
				searchName = "berlin";
				searchEvent = true;
				break;
		
		case 's':
			searchName = "monaco";
			searchEvent = true;
			break;
		}
	}
}


