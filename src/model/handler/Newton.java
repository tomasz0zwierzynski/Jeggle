/*
 * This class is living all the time and can handle ball movement.
 */

package model.handler;

import model.Const;
import model.GameBoard;
import model.drawable.Ball;
import model.drawable.Peg;

public final class Newton {
		
	private static Ball ball;
	private static GameBoard gameBoard;
	
	private static Peg collisionPeg;
	
	//Here whole magic will happen
	public static Ball nextStep(Ball b, GameBoard gb){
		ball = b;
		gameBoard = gb;
		//TODO: here call checking function
		//		then call correct movement function
		
		//Check if ball has fallen down the screen.
		if (hasFallenDown())
			return null;
		
		//Check if ball hit side boundry
		if (hasHitSideWall())
			boundry();
		else{
			if (hasCollideWithPeg())
				collision();
			else
			freefall();	
		}			
		
		return ball;
	}
	
	private static void freefall(){
		
		//	System.out.println("freefall(), ball= " + ball.toString());
		double new_Vx = ball.getVx();
		double new_Vy = ball.getVy() + (Const.GRAVITY*Const.GAME_DELAY_MS);
		int new_x = (int) (ball.getX() + Math.round(new_Vx*Const.GAME_DELAY_MS));
		int new_y = (int) (ball.getY() + Math.round(new_Vy*Const.GAME_DELAY_MS));
		
		//System.out.println(ball.toString());
		ball.setBall(new_x, new_y,new_Vx , new_Vy);
	}
	
	private static void boundry(){
		
		//	System.out.println("boundry(), ball= " + ball.toString());
		double new_Vx = -ball.getVx()*Const.BOUNCE_FACTOR;
		double new_Vy = ball.getVy() + (Const.GRAVITY*Const.GAME_DELAY_MS);
		int new_x = (int) (ball.getX() + Math.round(new_Vx*Const.GAME_DELAY_MS));
		int new_y = (int) (ball.getY() + Math.round(new_Vy*Const.GAME_DELAY_MS));
		
		//System.out.println(ball.toString());
		ball.setBall(new_x, new_y,new_Vx , new_Vy);
	}
	
	//TODO: Have a loop for which angles bounce is fucked up and write special cases for it (where transformation is not needed)
	private static void collision(){
		//Not math again...
		double r = Ball.RADIUS * 0.5;
		double R = Peg.RADIUS * 0.5;
		double xb = ball.getX()+r;
		double yb = ball.getY()+r;
		double xp = (double)collisionPeg.getX()+R;
		double yp = (double)collisionPeg.getY()+R;
		
		double argX = xb - xp;
		double argY = yb - yp;
		//Calculating collision angle
		double theta = Math.atan2(argY,argX);
		
		//Transformating velocity vector
		double Vx = ball.getVx();
		double Vy = ball.getVy();
		double VxT = Vx * Math.cos(theta) - Vy * Math.sin(theta);
		double VyT = Vx * Math.sin(theta) + Vy * Math.cos(theta);
		
		//Calculating new velocities in transfored frame which is simple there
		double new_VxT = VxT * Const.BOUNCE_FACTOR;
		double new_VyT = -VyT * Const.BOUNCE_FACTOR;
		
		//Transforming back to original frame
		double new_Vx = new_VxT * Math.cos(theta) + new_VyT * Math.sin(theta);
		double new_Vy = - new_VxT * Math.sin(theta) + new_VyT * Math.cos(theta);
		
		//Correcting ball position to ensure it bounce only once
		ball.setX((int) (xp+(R+r)*Math.cos(theta)-r));
		ball.setY((int) (yp+(R+r)*Math.sin(theta)-r));
		
		//Calculating next step:
		int new_x = (int) (ball.getX() + Math.round(new_Vx*Const.GAME_DELAY_MS));
		int new_y = (int) (ball.getY() + Math.round(new_Vy*Const.GAME_DELAY_MS));
		
		//System.out.println(ball.toString());
		ball.setBall(new_x, new_y,new_Vx , new_Vy);
		
	}
	
	//Checking if ball is in contact with any peg
	private static boolean hasCollideWithPeg(){
		boolean value = false;
		for (Peg peg : gameBoard.getPegs()){
			//do some math...
			double r = Ball.RADIUS * 0.5;
			double R = Peg.RADIUS * 0.5;
			double xb = ball.getX()+r;
			double yb = ball.getY()+r;
			double xp = (double)peg.getX()+R;
			double yp = (double)peg.getY()+R;
			
			double dist = Math.sqrt(Math.pow((xp-xb), 2)+Math.pow((yp-yb), 2));
			if (dist < (R + r)){
				value = true;
				collisionPeg = peg;
			}
		}
		return value;
	}
	
	private static boolean hasFallenDown(){
		boolean value = false;
		if (ball.getY()>Const.BOARD_ENGINE_HEIGHT){			
			value = true;
		}
		return value;
	}
	
	private static boolean hasHitSideWall(){
		boolean value = false;
		if( ball.getX() < 0 ){
			//Set ball to boundry to ensure it will escape
			ball.setX(0);
			value = true;
		}
		if( ball.getX() > Const.BOARD_ENGINE_WIDTH - ball.RADIUS ){
			value = true;
			//Set ball to boundry to ensure it will escape
			ball.setX( Const.BOARD_ENGINE_WIDTH - ball.RADIUS );
		}		
		return value;
	}	
}
