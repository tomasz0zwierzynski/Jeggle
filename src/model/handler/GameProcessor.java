package model.handler;

import java.util.List;

import model.drawable.Drawable;
import model.type.InputData;

public interface GameProcessor {
	
	public void process();
	public void input(InputData in);
	public List<Drawable> getDrawables();
	
}
