package model.handler;

import java.util.ArrayList;
import java.util.List;

import model.Const;
import model.Engine;
import model.GameBoard;
import model.drawable.Drawable;
import model.drawable.Peg;
import model.type.GameState;
import model.type.InputData;
import model.type.PegState;
import model.type.PegType;

public class ScoringHandler implements GameProcessor{

	private Engine parent;
	private GameBoard gameBoard;
	
	private int staticCounter = 0;
	
	public ScoringHandler(Engine par){
		parent = par;
	}
	
	@Override
	public void process() {
		
		if ((staticCounter++ % 3) == 0){
			Peg peg = gameBoard.getLastTouchedPeg();

			if(peg == null){
				parent.changeState(GameState.Aiming);
				return;
			}
		
			if (peg.getType() == PegType.Blue){
				//blah blah
			}else if(peg.getType() == PegType.Orange){
				//blah blah
			}
			peg.setState(PegState.Unvisible);
		}
	}

	@Override
	public void input(InputData in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Drawable> getDrawables() {
		// TODO Auto-generated method stub
		return new ArrayList<Drawable>();
	}

	public void setGameBoard(GameBoard gb) {
		gameBoard = gb;		
	}
}
