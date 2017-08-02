package model;

import java.util.List;

public interface GameProcessor {
	
	public void process();
	public void input(InputData in);
	public List<Drawable> getDrawables();
	
}
