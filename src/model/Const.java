/*
 * Static class that contains all constances used in game. (Configuration!!)
 * 
 */

package model;

public final class Const {
	public static final int GAME_DELAY_MS = 10;					//[ms]
	public static final int COMPUTE_MULTIPLIER = 1;				//[1]
	public static final int BOARD_ENGINE_WIDTH = 800000;		//[px]
	public static final int BOARD_ENGINE_HEIGHT = 600000;		//[px]
	public static final int ABSOLUTE_INITIAL_VELOCITY = 600;	//[px/ms]   default: 600
	public static final double GRAVITY = 0.8;					//[px/ms2]  default: 0.8
	public static final double BOUNDRY_BOUNCE_FACTOR = 0.85;	//[1]		default: 0.85
	public static final double PEG_BOUNCE_FACTOR = 0.85;		//[1]		default: 0.85
	public static final int PEG_COLLISION_OFFSET = 1000;		//[px]		default: 1000
	public static final double X_SHOOTING_POINT = Const.BOARD_ENGINE_WIDTH * 0.5;
	public static final double Y_SHOOTING_POINT = Const.BOARD_ENGINE_HEIGHT * 0.1;
	public static final int PEG_COUNT = 40;						//[pegs]	default: 40
	public static final int PEG_ORANGE_COUNT = 10;				//[pegs]	default: 10
	
}
