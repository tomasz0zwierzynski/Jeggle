/*
 * Class that represents implements gameLoop()
 * 
 */

package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import view.ScreenMetrics;

/**
 * 
 * @author Nails
 *
 */

public class Engine implements ActionListener {

	private Timer timer;
	private GameState currentState;
	private AimingHandler hAiming;
	private ActionHandler hAction;
	private GameProcessor hCurrentHandler;	
	
	private List<GameLoopListener> graphicUpdaters = new ArrayList<GameLoopListener>();
		
	
	public Engine(){
		timer = new Timer(Const.GAME_DELAY_MS,this);

		hAction = new ActionHandler(this);	
		hAiming = new AimingHandler(this);
	}
	
	//Game loop takes place here, when timer posts action.
	@Override
	public void actionPerformed(ActionEvent arg) {
		gameLoop();		
	}
		
	private void gameLoop(){
		update();
		redraw();
	}
	
	public void addGraphicListener(GameLoopListener toAdd){
		graphicUpdaters.add(toAdd);
	}
	
	private void update(){
		
		if (currentState == GameState.Aiming)
			hAiming.process();
		else if (currentState == GameState.Action)
			hAction.process();
		
	}
	
	public void redraw(){
		for (GameLoopListener gl : graphicUpdaters){
			gl.onGraphicUpdate();
		}
	}
	
	public void processInputData(InputData id){
		
		if (currentState == GameState.Aiming){
		
			switch (id.getMouseEventType()){
			case Moved:
				break;
			case Pressed:
				System.out.println("Pressed");
				break;
			case Released:
				System.out.println("Released");
				break;
			case Clicked:
				int arg = id.getEngiX();
				double angle = ScreenMetrics.map(arg, 0, Const.BOARD_ENGINE_WIDTH, -120, +120);			
				hAction.nextBall(new Ball(Const.BOARD_ENGINE_WIDTH / 2,2000, angle));
				hAction.prepareBall();
				currentState = GameState.Action;
				
				break;
			default:
			
				break;
			}
		}
		
	}
	
	public void start(){
		timer.start();
		
		currentState = GameState.Aiming;
	}
	
	public void ballFellDown(){
		currentState = GameState.Aiming;
	}
	
	//TEMP
	public Ball getBall(){
		return hAction.getBall();
	}
	
}
