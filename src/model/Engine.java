/**
 * Class that represents implements gameLoop()
 * 
 */

package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import model.drawable.Ball;
import model.drawable.Drawable;
import model.drawable.PegConfiguration;
import model.handler.ActionHandler;
import model.handler.AimingHandler;
import model.handler.GameProcessor;
import model.handler.ScoringHandler;
import model.listeners.GameLoopListener;
import model.type.GameState;
import model.type.InputData;

public class Engine implements ActionListener {

	private Timer timer;
	private int computeMultiplierCounter = 0;
	private AimingHandler hAiming;
	private ActionHandler hAction;
	private ScoringHandler hScoring;
	
	private GameProcessor hCurrentHandler;	
	private GameBoard gameBoard;
	
	private List<GameLoopListener> graphicUpdaters = new ArrayList<GameLoopListener>();
		
	public Engine(){
		timer = new Timer(Const.GAME_DELAY_MS,this);

		hAction = new ActionHandler(this);
		try{
			hAiming = new AimingHandler(this);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		hScoring = new ScoringHandler(this);

	}
	
	//Game loop takes place here, when timer posts action.
	@Override
	public void actionPerformed(ActionEvent arg) {
		gameLoop();
	}
		
	private void gameLoop(){
		//System.out.println("Update();");
		update();
		if ((computeMultiplierCounter++ % Const.COMPUTE_MULTIPLIER) == 0){
			//System.out.println("Redraw();");
			redraw();
		}
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
		//Creating Scene with pegs (here loading from file or smth)
		PegConfiguration level = new PegConfiguration();
		gameBoard = new GameBoard(level);		
		hScoring.setGameBoard(gameBoard);
		//Starting game timer to update and draw in loop
		timer.start();
		
		//Jumping into first state of game
		hCurrentHandler = hAiming;
	}
	
	/*
	public void ballFellDown(){
		currentState = GameState.Aiming;
	}
	*/
	
	public void changeState(GameState gs){
		hCurrentHandler = handlerFromGameState(gs);		
	}
	
	public List<Drawable> getDrawables(){
		List<Drawable> value = new ArrayList<Drawable>();
		for (Drawable d : gameBoard.getDrawables()){
			value.add(d);
		}
		for (Drawable d : hCurrentHandler.getDrawables()){
			value.add(d);
		}
		return value;
	}
	
	private GameProcessor handlerFromGameState(GameState gs){
		switch (gs){
		case Aiming:
			return hAiming;
		case Action:
			return hAction;
		case Score:
			return hScoring;
		default:
			return null;
		}
		//and so on...
	}
		
	public GameProcessor getGameStateHandler(GameState gs){
		return handlerFromGameState(gs);
	}
	
	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	//TEMP
	public Ball getBall(){
		return hAction.getBall();
	}
	
}
