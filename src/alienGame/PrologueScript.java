package alienGame;

import java.util.ArrayList;

public class PrologueScript {
	
	private MainCharacter one, two;
	
	private ArrayList<String> script;
	private int speechIndex;
	private boolean scriptFinished;
	
	public PrologueScript(MainCharacter one, MainCharacter two) {
		this.one = one;
		this.two = two;
		script = new ArrayList<String>();
		uploadScript();
		speechIndex = 0;
		scriptFinished = false;
	}
	
	public void uploadScript() {
		script.add("hi there Dork!");
		script.add("hey grandpa! whats up!");
		script.add("just thinking about humans");
		script.add("classic gramps - so fascinated by humans");
		script.add("i just feel like it would be so cool to actually see some human artifacts!");
		script.add("you know what gramps? I'm visiting Earth next week!");
		script.add("Are you really?");
		script.add("Maybe I can bring you some stuff back!!");
		script.add("OH DORK THAT WOULD BE SO LOVELY!");
	}
	
	public boolean speak() {
		if(speechIndex%2 == 0)
			one.say(script.get(speechIndex));
		else
			two.say(script.get(speechIndex));
		
		return scriptFinished;
	}
	
	public void back() {
		if(speechIndex > 0)
			speechIndex--;
	}
	
	public void forward() {
		if(speechIndex < script.size()-1)
			speechIndex++;
		else
			scriptFinished = true;
			
	}

}
