package model;

import java.util.ArrayList;
import java.util.List;

public class ActionHandler implements GameProcessor{

	Ball ball;
	
	Engine parent;
	
	public ActionHandler(Engine par){
		parent = par;
		ball = new Ball();
	}
	
	@Override
	public void process() {
		ball = Newton.nextStep(ball);
		if (ball == null){
			parent.changeState(GameState.Aiming);
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
		list.add(ball);
		return list;
	}
	
}
