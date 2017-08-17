/**
 * GameProcessor interface. All different game handler should implement this.
 * 
 */

package model.handler;

import java.util.List;

import model.drawable.Drawable;
import model.type.InputData;

public interface GameProcessor {
	
	//Function called every game loop, here inherited class should do it's game objects calculations
	public void process();
	
	//When there is a need to read mouse from user, every time this method is called.
	public void input(InputData in);
	
	//Method returns list of all prepared game objects to draw.
	public List<Drawable> getDrawables();
	
}
