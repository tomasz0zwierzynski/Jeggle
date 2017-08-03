package model.handler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Const;
import model.Engine;
import model.drawable.Ball;
import model.drawable.Drawable;
import model.type.GameState;
import model.type.InputData;

import view.ScreenMetrics;

public class AimingHandler implements GameProcessor {

	private Engine parent;
	private ActionHandler action;
	
	public AimingHandler(Engine par) throws Exception{
		parent = par;
		//Aiming Handler has to have a corresponding action handler.
		action = (ActionHandler) parent.getGameStateHandler(GameState.Action);
		if (action==null){
			throw new Exception("Cannot create AimingHandler while ActionHandler is not instantiated");
		}
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input(InputData id) {
		
		switch (id.getMouseEventType()){
		case Moved:
			break;
		case Pressed:
			//System.out.println("Pressed");
			break;
		case Released:
			//System.out.println("Released");
			break;
		case Clicked:
			int arg = id.getEngiX();
			double angle = ScreenMetrics.map(arg, 0, Const.BOARD_ENGINE_WIDTH, -120, +120);			
			Ball nextBall = new Ball(Const.BOARD_ENGINE_WIDTH / 2,2000, angle);
			action.nextBall(nextBall);
			parent.changeState(GameState.Action);
			break;
		default:
		
			break;
		}	
	}
	
	@Override
	public List<Drawable> getDrawables() {
		return new ArrayList<Drawable>();
	}

}
