/*
 * This class is living all the time and can handle ball movement.
 */

package model;

public class Newton {
	
	private Ball ball;
	private boolean alive;
	
	public Newton(){
		ball = null;
		alive = false;
	}
	
		
	//Here whole magic will happen
	public void nextStep(){
		//TODO: here call checking function
		//		then call correct movement function
		
		//Check if ball has fallen down the screen.
		if (hasFallenDown())
			return;
		//Check if ball hit side boundry
		
		if (hasHitSideWall())
			boundry();
		else	
			freefall();
		
	}
	
	public void nextBall(Ball b){
		ball = b;
		alive = true;
	}

	public boolean isAlive(){
		return alive;
	}	
	
	private void freefall(){
		
		//	System.out.println("freefall(), ball= " + ball.toString());
		double new_Vx = ball.getVx();
		double new_Vy = ball.getVy() + (Const.GRAVITY*Const.GAME_DELAY_MS);
		int new_x = (int) (ball.getX() + Math.round(new_Vx*Const.GAME_DELAY_MS));
		int new_y = (int) (ball.getY() + Math.round(new_Vy*Const.GAME_DELAY_MS));
		
		//System.out.println(ball.toString());
		ball.setBall(new_x, new_y,new_Vx , new_Vy);		
	}
	
	private void boundry(){
		
		//	System.out.println("boundry(), ball= " + ball.toString());
		double new_Vx = -ball.getVx()*Const.BOUNCE_FACTOR;
		double new_Vy = ball.getVy() + (Const.GRAVITY*Const.GAME_DELAY_MS);
		int new_x = (int) (ball.getX() + Math.round(new_Vx*Const.GAME_DELAY_MS));
		int new_y = (int) (ball.getY() + Math.round(new_Vy*Const.GAME_DELAY_MS));
		
		//System.out.println(ball.toString());
		ball.setBall(new_x, new_y,new_Vx , new_Vy);
	}
	
	private void pegCheck(){
		
	}
	
	private boolean hasFallenDown(){
		boolean value = false;
		if (ball.getY()>Const.BOARD_ENGINE_HEIGHT){			
			alive = false;
			ball = null;
			value = true;
		}
		return value;
	}
	
	private boolean hasHitSideWall(){
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
