package model.handler;

import java.util.ArrayList;
import java.util.List;

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
	
	private int pegsMultiplier = 0;
	private int totalValue = 0;
	
	public ScoringHandler(Engine par){
		parent = par;
	}
	
	@Override
	public void process() {
		
		if ((staticCounter++ % 3) == 0){
			Peg peg = gameBoard.getLastTouchedPeg();

			if(peg == null){
				parent.changeState(GameState.Aiming);
				parent.getGameBoard().addScore(totalValue*pegsMultiplier);
				parent.setSideValueChanged();
				pegsMultiplier = 0;
				totalValue = 0;
				gameBoard.setShotScore(0);
				if (gameBoard.getOrangePegsLeft() == 0){
					gameBoard.nextLevel();
				}
				return;
			}
		
			pegsMultiplier++;
			if (peg.getType() == PegType.Blue){
				totalValue+=10;
			}else if(peg.getType() == PegType.Orange){
				totalValue+=100;
			}
			gameBoard.setShotScore(totalValue*pegsMultiplier);
			parent.setSideValueChanged();
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
