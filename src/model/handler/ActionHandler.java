package model.handler;

import java.util.ArrayList;
import java.util.List;

import model.Engine;
import model.drawable.Ball;
import model.drawable.Drawable;
import model.type.GameState;
import model.type.InputData;

public class ActionHandler implements GameProcessor{

	private Ball ball;
	
	private Engine parent;
	
	public ActionHandler(Engine par){
		parent = par;
		ball = new Ball();
	}
	
	@Override
	public void process() {
		ball = Newton.nextStep(ball,parent.getGameBoard());
		if (ball == null){
			parent.changeState(GameState.Score);
		}
	}	
	
	public void nextBall(Ball b){
		ball = b;
	}
	
	public Ball getBall(){
		return ball;
	}

	@Override
	public void input(InputData in) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> list = new ArrayList<Drawable>();
		list.add((Drawable)ball);
		return list;
	}
	
}
