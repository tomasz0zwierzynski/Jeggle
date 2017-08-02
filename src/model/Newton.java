/*
 * This class is living all the time and can handle ball movement.
 */

package model;

public final class Newton {
		
	private static Ball ball;
	
	//Here whole magic will happen
	public static Ball nextStep(Ball b){
		ball = b;
		//TODO: here call checking function
		//		then call correct movement function
		
		//Check if ball has fallen down the screen.
		if (hasFallenDown())
			return null;
		
		//Check if ball hit side boundry
		if (hasHitSideWall())
			boundry();
		else	
			freefall();
		
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
	
	private static void pegCheck(){
		
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
