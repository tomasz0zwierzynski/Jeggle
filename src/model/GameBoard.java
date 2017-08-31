/**
 * Class that represents game progress and changes. It is created when starting an level
 * and deleted when finishing level.
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.drawable.Drawable;
import model.drawable.Peg;
import model.drawable.PegConfiguration;
import model.type.PegState;
import model.type.PegType;

//TODO: implement some new gameplay, like score, levels, balls left etc.

public class GameBoard {
	
	//private List<Peg> pegs;
	private PegConfiguration pegsConfiguration;
	private int ballsLeft = 0;
	private int pegsLeft;
	private int orangePegsLeft;
	
	private List<Integer> touchedPegsIndexes;
	
	private int score = 0;
	private int shotScore = 0;
	
	//Default constructor generates basic peg grid
	public GameBoard(){
		init();
	}
	
	private void init(){
		pegsConfiguration = new PegConfiguration();
		initializePegs();
		touchedPegsIndexes = new ArrayList<Integer>();
		pegsLeft = pegsConfiguration.getAllPegs().size();
		orangePegsLeft = pegsConfiguration.getOrangePegs().size();
		ballsLeft += 10;
	}
	
	public List<Drawable> getDrawables(){
		List<Drawable> value = new ArrayList<Drawable>();
		for (Peg p : pegsConfiguration.getAllPegs()){
			if (p.getState() == PegState.Checked || p.getState() == PegState.Visible){
				value.add((Drawable)p);			
			}	
		}
		return value;
	}
	
	public List<Peg> getPegs(){
		return pegsConfiguration.getAllPegs();
	}
	
	public List<Peg> getCollidablePegs(){
		List<Peg> list = new ArrayList<Peg>();
		
		for (Peg p : pegsConfiguration.getAllPegs()){
			if ((p.getState() == PegState.Visible) || (p.getState() == PegState.Checked)){
				list.add(p);
			}
		}		
		return list;
	}
	
	public List<Peg> getCollidablePegsInBlock(int block){
		List<Peg> list = new ArrayList<Peg>();
		
		for (Peg p : pegsConfiguration.getPegsIn(block)){
			if ((p.getState() == PegState.Visible) || (p.getState() == PegState.Checked)){
				list.add(p);
			}
		}	
		return list;
	}
	
	public Peg getLastTouchedPeg(){
		if (touchedPegsIndexes.isEmpty()){
			return null;
		}else{
			return pegsConfiguration.getAllPegs().get(touchedPegsIndexes.remove(0));			
		}
	}
	
	public void pegTouched(Peg peg){
		int pegIndex = pegsConfiguration.getAllPegs().indexOf(peg);
		//Indexed touched pegs and do stuff later
		if (peg.getState() != PegState.Checked){
			touchedPegsIndexes.add(pegIndex);
			pegsLeft--;
			if (peg.getType() == PegType.Orange)
			{
				orangePegsLeft--;
			}
		}		
		peg.setState(PegState.Checked);
	}
	
	//Pegs have to be colorized etc.
	private void initializePegs(){
		List<Peg> pegs = pegsConfiguration.getAllPegs();
		
		for(Peg p: pegs){
			p.setType(PegType.Blue);
			p.setState(PegState.Visible);
		}
		Random rnd = new Random();
		int pegCount = pegs.size();
		for(int i=0; i<Const.PEG_ORANGE_COUNT; i++){
			int index;
			do{
				index = rnd.nextInt(pegCount);
			}while(pegs.get(index).getType() == PegType.Orange);		
			
			pegs.get(index).setType(PegType.Orange);
		}
	}
	
	public void nextLevel(){
		pegsConfiguration = null;
		touchedPegsIndexes = null;
		init();
	}
	
	public int getPegsLeft(){
		return pegsLeft;
	}
	
	public int getOrangePegsLeft(){
		return orangePegsLeft;
	}
	
	public void decreaseBalls(){
		ballsLeft--;
	}
	
	public int getBallsLeft(){
		return ballsLeft;
	}
	
	public void increaseBalls(){
		ballsLeft++;
	}
	
	public int getScore(){
		return score;
	}
	
	public void addScore(int add){
		score = score + add;
	}
	
	public void setShotScore(int score){
		shotScore = score;
	}
	
	public int getShotScore(){
		return shotScore;
	}
}
