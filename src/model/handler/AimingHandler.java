/**
 * One of handlers that processing game at particular game state.
 * This one tracks mouse position, draws proper aiming curve and passing shot position when click.
 * It uses AimingAssistance methods to calculate proper angle of ball initial velocity. 
 */

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
import model.drawable.PegConfiguration;
import model.type.GameState;
import model.type.InputData;

import view.ScreenMetrics;

public class AimingHandler implements GameProcessor {

	private Engine parent;
	private ActionHandler action;
	
	private List<AimDot> aimDots;
	
	public AimingHandler(Engine par) throws Exception{
		parent = par;
		//Aiming Handler has to have a corresponding action handler, because it has to create new ball
		// and pass it further to ActionHandler
		action = (ActionHandler) parent.getGameStateHandler(GameState.Action);
		if (action==null){
			throw new Exception("Cannot create AimingHandler while ActionHandler is not instantiated");
		}
		
		aimDots = new ArrayList<AimDot>();
	}
	
	@Override
	public void process() {
		// In this case, nothing is here to be done.		
	}

	@Override
	public void input(InputData id) {
		
		int x = id.getEngiX();
		int y = id.getEngiY();
		
		switch (id.getMouseEventType()){
		case Moved:
			aimDots.clear();
			//Current location pointer
			prepareCursor(x,y);
			
			try {
				double angle = AimingAssistance.calculateShootingAngle(x, y);// +90;
				double mappedAngle = ScreenMetrics.map(angle, -130, +130, -1, +1);
				
				if (angle == 0){
					prepareAimingLine();
					break;
				}
				
				if (angle < 0){
					angle += 90;
					int iInit = (int) (mappedAngle*Const.BOARD_ENGINE_HEIGHT/2);
					int di = (int)(iInit/10);
					for(int i=iInit;i<0;i-=di){
						prepareAimingPoints(angle, i);
					}
				}else{
					angle += 90;
					int iFinite = (int) (mappedAngle*Const.BOARD_ENGINE_HEIGHT/2);
					int di = (int)(iFinite/10);
					for(int i=0;i<iFinite;i+=di){					
						prepareAimingPoints(angle, i);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			break;
		case Pressed:
			break;
		case Released:
			break;
		case Clicked:
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

	private void prepareAimingLine() {
		int cX = (int) Const.X_SHOOTING_POINT;
		int offset = (int) Const.Y_SHOOTING_POINT;
		int di = (int) (Const.BOARD_ENGINE_HEIGHT/40);
		int max = (int) (Const.BOARD_ENGINE_HEIGHT/4);
		for (int i=0;i<=max;i+=di){
			AimDot ad = new AimDot(cX,i+offset);
			ad.setColor(Color.WHITE);
			aimDots.add(ad);
		}
	}

	private void prepareAimingPoints(double angle, int i) {
		double val = AimingAssistance.calculateParabole(angle, i); // i should be in math
		Point mathXY = new Point(i,(int)val);
		Point graphXY = AimingAssistance.toGraphicalFrame(mathXY);
		
		AimDot td = new AimDot(0,0);
		td.setX(graphXY.x);
		td.setY(graphXY.y);
		td.setColor(Color.WHITE);
		aimDots.add(td);
	}
	
	@Override
	public List<Drawable> getDrawables() {
		List<Drawable> value = new ArrayList<Drawable>();
		for(AimDot ap: aimDots){
			value.add((Drawable)ap);
		}
		return value;
	}
	
	private void prepareCursor(int x, int y){
		AimDot ad = new AimDot(x,y);
		ad.setColor(Color.MAGENTA);
		aimDots.add(ad);
	}	
}
