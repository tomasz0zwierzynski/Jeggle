package model.drawable;

import model.type.PegState;
import model.type.PegType;

public class Peg extends Drawable{

	public static final int RADIUS = 75000;
	
	private PegState state;
	private PegType type;
	
	public Peg(int _pos_x, int _pos_y){
		super(_pos_x,_pos_y,RADIUS);
		state = PegState.Undefined;
		type = PegType.Undefined;
	}

	public PegState getState() {
		return state;
	}

	public void setState(PegState state) {
		this.state = state;
	}

	public PegType getType() {
		return type;
	}

	public void setType(PegType type) {
		this.type = type;
	}
	
}
