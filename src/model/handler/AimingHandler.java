package model.handler;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Const;
import model.Engine;
import model.drawable.AimDot;
import model.drawable.Ball;
import model.drawable.Drawable;
import model.type.GameState;
import model.type.InputData;

import view.ScreenMetrics;

public class AimingHandler implements GameProcessor {

	private Engine parent;
	private ActionHandler action;
	
	private List<AimDot> aimDots;
	
	public AimingHandler(Engine par) throws Exception{
		parent = par;
		//Aiming Handler has to have a corresponding action handler.
		action = (ActionHandler) parent.getGameStateHandler(GameState.Action);
		if (action==null){
			throw new Exception("Cannot create AimingHandler while ActionHandler is not instantiated");
		}
		
		aimDots = new ArrayList<AimDot>();
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input(InputData id) {
		
		int x,y;
		
		switch (id.getMouseEventType()){
		case Moved:
			x = id.getEngiX();
			y = id.getEngiY();
	
			aimDots.clear();
	
			//Current location pointer
			AimDot ad = new AimDot(x,y);
			ad.setColor(Color.MAGENTA);
			aimDots.add(ad);
		
			try {
				double angle = AimingAssistance.calculateShootingAngle(x, y);// +90;
				if (angle < 0){
					double mappedAngle = ScreenMetrics.map(angle, -130, +130, -1, +1);
					angle += 90;
					int iInit = (int) (mappedAngle*Const.BOARD_ENGINE_HEIGHT/2);
					int di = (int)(iInit/10);
					for(int i=iInit;i<0;i-=di){
												
						double val = AimingAssistance.calculateParabole(angle, i); // i should be in math
						Point mathXY = new Point(i,(int)val);
						Point graphXY = AimingAssistance.toGraphicalFrame(mathXY);
						
						AimDot td = new AimDot(0,0);
						td.setX(graphXY.x);
						td.setY(graphXY.y);
						td.setColor(Color.WHITE);
						aimDots.add(td);
					}
				}else{
					double mappedAngle = ScreenMetrics.map(angle, -130, +130, -1, +1);
					angle += 90;
					int iFinite = (int) (mappedAngle*Const.BOARD_ENGINE_HEIGHT/2);
					int di = (int)(iFinite/10);
					for(int i=0;i<iFinite;i+=di){
												
						double val = AimingAssistance.calculateParabole(angle, i); // i should be in math
						Point mathXY = new Point(i,(int)val);
						Point graphXY = AimingAssistance.toGraphicalFrame(mathXY);
						
						AimDot td = new AimDot(0,0);
						td.setX(graphXY.x);
						td.setY(graphXY.y);
						td.setColor(Color.WHITE);
						aimDots.add(td);
					}
				}
				
				/*
				for(int i=(int) -Const.X_SHOOTING_POINT;i<Const.BOARD_ENGINE_WIDTH/2;i=i+Const.BOARD_ENGINE_WIDTH/100){
										
					double val = AimingAssistance.calculateParabole(angle, i); // i should be in math
					Point mathXY = new Point(i,(int)val);
					Point graphXY = AimingAssistance.toGraphicalFrame(mathXY);
					
					AimDot td = new AimDot(0,0);
					td.setX(graphXY.x);
					td.setY(graphXY.y);
					td.setColor(Color.WHITE);
					aimDots.add(td);
				}
				*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			break;
		case Pressed:
			//System.out.println("Pressed");
			break;
		case Released:
			//System.out.println("Released");
			break;
		case Clicked:
			x = id.getEngiX();
			y = id.getEngiY();
			//double angle = ScreenMetrics.map(arg, 0, Const.BOARD_ENGINE_WIDTH, -120, +120);			
			double angle = 0;				
			try {
				angle = AimingAssistance.calculateShootingAngle(x, y);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Ball nextBall = new Ball( (int)(Const.X_SHOOTING_POINT-(Ball.DIAMETER/2)), (int)(Const.Y_SHOOTING_POINT-(Ball.DIAMETER/2)), angle);
			action.nextBall(nextBall);
			parent.changeState(GameState.Action);
			break;
		default:
		
			break;
		}	
	}
	
	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> value = new ArrayList<Drawable>();
		for(AimDot ap: aimDots){
			value.add((Drawable)ap);
		}
		return value;
	}

}
