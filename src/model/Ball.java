/*
 * Class that represents ball object.
 * Ball should be created when shot and deleted when fall down.
 * 
 */
//TODO: eliminate magic numbers

package model;

public class Ball {

	public final int RADIUS = 1500;	
	
	private int pos_x;
	private int pos_y;
	private double vel_x;
	private double vel_y;
		
	public Ball(){
		//Default constructor creates ball with 0 initial speed and at center of board
		//Testing cases
		pos_x = (int)(Const.BOARD_ENGINE_WIDTH*0.5);
		pos_y = 2000;
		vel_x = 0;
		vel_y = 0;		
	}
	
	//Creates ball with initial momentum and position, angle in range -90 to +90 where 0 is straight down.
	public Ball(int _pos_x, int _pos_y, double _angle_in_deg){
		pos_x = _pos_x;
		pos_y = _pos_y;
		double _angle_in_rad = Math.toRadians(_angle_in_deg);
		vel_x = Const.ABSOLUTE_INITIAL_VELOCITY * Math.sin(_angle_in_rad);
		vel_y = Const.ABSOLUTE_INITIAL_VELOCITY * Math.cos(_angle_in_rad);
	}
	
	public void setBall(int _pos_x, int _pos_y, double _vel_x, double _vel_y){
		pos_x = _pos_x;
		pos_y = _pos_y;
		vel_x = _vel_x;
		vel_y = _vel_y;
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
	public double getVx(){
		return vel_x;
	}
	public double getVy(){
		return vel_y;
	}
	
	@Override
	public String toString(){
		return "Ball x = " + pos_x + "; y = " + pos_y + "; Vx = " + vel_x + "; Vy = " + vel_y;
	}
}
