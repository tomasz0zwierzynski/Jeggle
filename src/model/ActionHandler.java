package model;

import java.util.Random;

public class ActionHandler implements GameProcessor{

	Ball ball;
	Newton newton;
	
	Engine parent;
	
	public ActionHandler(Engine par){
		newton = new Newton();
		parent = par;
		prepareBall();
	}
	
	@Override
	public void process() {
		
		if (newton.isAlive()){
			newton.nextStep();
		}else{
			prepareBall();
			parent.ballFellDown();
		}
		
	}	
	
	public void nextBall(Ball b){
		ball = b;
	}
	
	public Ball getBall(){
		return ball;
	}
	
	public void prepareBall(){
		newton.nextBall(ball);
	}
	
}
