package alienGame;

import processing.core.PApplet;

public class ArtifactCounter {
	
	private PApplet parent;
	private int artifacts;

	
	public ArtifactCounter(PApplet p) 
	{
		parent = p;
		artifacts=0;
	}
	public void draw()
	{
		parent.fill(255, 255, 255);
		parent.rect(0, 0, 200, 100);
		parent.fill(0, 0, 0);
		parent.textSize(20);
		parent.text("artifacts: " + artifacts, 5, 25);	
	}
	
	public void updateArtifacts(int artifacts) {
		this.artifacts = artifacts;
	}

}
