package model.drawable;

import java.awt.Color;

import model.type.PegState;
import model.type.PegType;

public class Peg extends Drawable{

	public static final int DIAMETER = 20000;
	
	private PegState state;
	private PegType type;
	
	public Peg(int _pos_x, int _pos_y){
		super(_pos_x,_pos_y,DIAMETER);
		state = PegState.Undefined;
		type = PegType.Undefined;
	}

	public PegState getState() {
		return state;
	}

	public void setState(PegState state) {
		this.state = state;
		switch(state){
		case Checked:
			if (type==PegType.Blue){
				this.color = Color.lightGray;
			}else if (type == PegType.Orange){
				this.color = Color.yellow;
			}
			break;
		
		}
	}

	public PegType getType() {
		return type;
	}

	public void setType(PegType type) {
		this.type = type;
		switch(type){
		case Blue:
			this.color = Color.blue;
			break;
		case Orange:
			this.color = Color.orange;
			break;
		case Green:
			this.color = Color.green;
			break;
		case Pink:
			this.color = Color.pink;
			break;
			default:
				this.color = Color.gray;
				break;
		}
	}
	
}
