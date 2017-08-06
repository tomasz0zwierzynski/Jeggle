/*
 * Static class that contains all constances used in game. (Configuration!!)
 * 
 */

package model;

public final class Const {
	public static final int GAME_DELAY_MS = 20;					//[ms]
	public static final int COMPUTE_MULTIPLIER = 1;				//[1]
	public static final int BOARD_ENGINE_WIDTH = 800000;		//[px]
	public static final int BOARD_ENGINE_HEIGHT = 600000;		//[px]
	public static final int ABSOLUTE_INITIAL_VELOCITY = 600;	//[px/ms]   default=300
	public static final double GRAVITY = 0.8;					//[px/ms2]  default=0.8
	public static final double BOUNDRY_BOUNCE_FACTOR = 0.85;			//[1]
	public static final double PEG_BOUNCE_FACTOR = 0.85;			//[1]
	public static final int PEG_COLLISION_OFFSET = 100;			//[px]
}
