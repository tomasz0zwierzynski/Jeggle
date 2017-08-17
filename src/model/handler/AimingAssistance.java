/**
 * Set of methods that contains mathematical content to calculate and predict ball movement
 * 
 * Math frame - normal right XY frame at origin in ball initiation point.
 * Graph frame - frame in which are described all drawables on the screen.
 */

package model.handler;

import java.awt.Point;

import model.Const;

public final class AimingAssistance {

	//For given x0, y0 (point of click - ball should fall through this point)
	public static double calculateShootingAngle(int x0, int y0) throws Exception{
		double v = Const.ABSOLUTE_INITIAL_VELOCITY;
		double g = Const.GRAVITY;
		
		Point p0 = new Point(x0,y0);
		Point p = toMathematicalFrame(p0);
				
		double x = p.getX();
		double y = p.getY();
		
		//Mathematical formulas can be found on wikipedia page describing throws and ballistics
		double root_arg = Math.pow(v, 4)-g*(g*x*x+2*y*v*v);
		if (root_arg<0)
			throw new Exception("Not reachable point!");
		double root = Math.sqrt(root_arg);
		
		double angle;
		try{
			//second_angle_solution = Math.atan((v*v+root)/(g*x));
			//This solution above corresponds to longer curve and is not needed, because shortest path should be chosen
			angle = Math.atan((v*v-root)/(g*x));
		}catch(Exception ex){
			throw ex;
		}
		
		//Preparing return value due to 90 degree rotation in ball velocity creator
		double value;
		if (x < 0){
			value = Math.toDegrees(angle) - 90;
		}else{
			value = Math.toDegrees(angle) + 90;
		}
		
		return value;
	}
	
	//Transforms point from Math frame to Graph frame
	public static Point toGraphicalFrame(Point p){
		
		int xC = (int) Const.X_SHOOTING_POINT; //X point of shoot
		int yC = (int) Const.Y_SHOOTING_POINT; //Y point of shoot
		
		yC = Const.BOARD_ENGINE_HEIGHT - yC;
		
		//Shift
		int x = p.x + xC;
		int y = p.y + yC;	
		
		//Flip
		y = Const.BOARD_ENGINE_HEIGHT - y;
		
		return new Point(x,y);
	}
	
	//Transforms point from Graph frame to Math frame
	public static Point toMathematicalFrame(Point p){
		
		double xC = Const.X_SHOOTING_POINT; //X point of shoot
		double yC = Const.Y_SHOOTING_POINT; //Y point of shoot
		//Flipping Y
		double y = Const.BOARD_ENGINE_HEIGHT - p.y;
		yC = Const.BOARD_ENGINE_HEIGHT - yC;
		double x = p.x;
		//Shifting frame
		x = x - xC;
		y = y - yC;
		
		return new Point((int)x,(int)y);
	}
	
	//Method calculates value of predicted ball parabole for given argument
	public static double calculateParabole(double angle_in_degrees, double arg){
		double v = Const.ABSOLUTE_INITIAL_VELOCITY;
		double g = Const.GRAVITY;
		double Q = Math.toRadians(angle_in_degrees);
		
		double value = Math.tan(Q)*arg - ((g*arg*arg)/(2*v*v*Math.cos(Q)*Math.cos(Q)));
			
		return value;
	}
	
}
