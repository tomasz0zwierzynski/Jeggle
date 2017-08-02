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
	private AimingHandler hAiming;
	private ActionHandler hAction;
	
	private GameProcessor hCurrentHandler;	
	private GameState currentState;
	
	private List<GameLoopListener> graphicUpdaters = new ArrayList<GameLoopListener>();
		
	
	public Engine(){
		timer = new Timer(Const.GAME_DELAY_MS,this);

		hAction = new ActionHandler(this);	
		hAiming = new AimingHandler(this,hAction);
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
		
		hCurrentHandler.process();
			
	}
	
	public void redraw(){
		for (GameLoopListener gl : graphicUpdaters){
			gl.onGraphicUpdate();
		}
	}
	
	public void processInputData(InputData id){

		hCurrentHandler.input(id);		
				
	}
	
	//Method called when gameLoop has to start.
	public void start(){
		timer.start();
		
		currentState = GameState.Aiming;
		hCurrentHandler = hAiming;
	}
	
	/*
	public void ballFellDown(){
		currentState = GameState.Aiming;
	}
	*/
	
	public void changeState(GameState gs){
		currentState = gs;
		hCurrentHandler = handlerFromGameState(gs);		
	}
	
	public List<Drawable> getDrawables(){
		return hCurrentHandler.getDrawables();
	}
	
	private GameProcessor handlerFromGameState(GameState gs){
		switch (gs){
		case Aiming:
			return hAiming;
		case Action:
			return hAction;
		default:
			return null;
		}
		//and so on...
	}
	
	//TEMP
	public Ball getBall(){
		return hAction.getBall();
	}
	
}
