package model;

import java.util.ArrayList;
import java.util.List;

import view.ScreenMetrics;

public class AimingHandler implements GameProcessor {

	private Engine parent;
	private ActionHandler action;
	
	public AimingHandler(Engine par, ActionHandler ah){
		parent = par;
		action = ah;
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
			action.nextBall(new Ball(Const.BOARD_ENGINE_WIDTH / 2,2000, angle));
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
