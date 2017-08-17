/**
 * Class represents object that is seen by JPanels as object to draw
 * 
 */

package model.drawable;

import java.awt.Color;

import model.type.DrawableType;

public class Drawable {

	private DrawableType drawableType;
	
	protected int pos_x;
	protected int pos_y;
	
	protected int diameter;
	protected int width;
	protected int height;
	
	protected Color color;
	//proteceted BufferedImage 
	
	
	public Drawable(int _pos_x, int _pos_y, int _diameter){
		pos_x = _pos_x;
		pos_y = _pos_y;
		diameter = _diameter;
		width = 0;
		height = 0;
		drawableType = DrawableType.Circle;
	}
	
	public Drawable(int _pos_x, int _pos_y, int _width, int _height){
		pos_x = _pos_x;
		pos_y = _pos_y;
		diameter = 0;
		width = _width;
		height = _height;
		drawableType = DrawableType.Rectangle;
	}
	
	public DrawableType getDrawableType(){
		return drawableType;
	}
	
	public int getX(){
		return pos_x;
	}
	public void setX(int x){
		pos_x = x;
	}
	public int getY(){
		return pos_y;
	}
	public void setY(int y){
		pos_y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
}
