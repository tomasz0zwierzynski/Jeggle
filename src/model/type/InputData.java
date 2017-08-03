package model.type;

import java.awt.Dimension;
import java.awt.event.MouseEvent;


public class InputData{
	private MouseEventType mouseEventType;	
	private MouseEvent mouseEvent;
	
	private int engiX;
	private int engiY;
	
	public InputData(MouseEvent me, MouseEventType met){
		mouseEvent = me;
		mouseEventType = met;
		engiX = 0;
		engiY = 0;
	}
	
	public InputData(MouseEvent me, MouseEventType met, Dimension xy){
		mouseEvent = me;
		mouseEventType = met;
		engiX = (int)xy.getWidth();
		engiY = (int)xy.getHeight();
	}

	public MouseEventType getMouseEventType() {
		return mouseEventType;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}
	
	public int getEngiX(){
		return engiX;
	}
	
	public int getEngiY(){
		return engiY;
	}
	
}
