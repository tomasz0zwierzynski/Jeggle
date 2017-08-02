package model;

public class Drawable {

	//Drawable Type: 0 - circle; 1 - rectangle
	//TODO: Add enum
	private int drawableType;
	
	protected int pos_x;
	protected int pos_y;
	
	protected int diameter;
	protected int width;
	protected int height;
	
	public Drawable(int _pos_x, int _pos_y, int _diameter){
		pos_x = _pos_x;
		pos_y = _pos_y;
		diameter = _diameter;
		width = 0;
		height = 0;
		drawableType = 0;
	}
	
	public Drawable(int _pos_x, int _pos_y, int _width, int _height){
		pos_x = _pos_x;
		pos_y = _pos_y;
		diameter = 0;
		width = _width;
		height = _height;
		drawableType = 1;
	}
	
	public int getDrawableType(){
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
	
}
