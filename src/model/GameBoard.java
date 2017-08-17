/**
 * Class that represents game progress and changes. It is created when starting an level
 * and deleted when finishing level.
 */

package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import model.drawable.Drawable;
import model.drawable.Peg;
import model.drawable.PegConfiguration;
import model.type.PegState;
import model.type.PegType;

//TODO: implement some new gameplay, like score, levels, balls left etc.

public class GameBoard {
	
	private List<Peg> pegs;
	private int ballsLeft;
	
	private List<Integer> touchedPegsIndexes;
	
	private int score;
	
	//Default constructor generates basic peg grid
	public GameBoard(){
		pegs = new ArrayList<Peg>();
		generateGrid(12,12);
		initializePegs();
	}
	
	public GameBoard(PegConfiguration pg){
		pegs = pg.getPegs();
		initializePegs();
		touchedPegsIndexes = new ArrayList<Integer>();
	}
	
	public List<Drawable> getDrawables(){
		List<Drawable> value = new ArrayList<Drawable>();
		for (Peg p : pegs){
			if (p.getState() == PegState.Checked || p.getState() == PegState.Visible){
				value.add((Drawable)p);			
			}	
		}
		return value;
	}
	
	public List<Peg> getPegs(){
		return pegs;
	}
	
	public List<Peg> getCollidablePegs(){
		List<Peg> list = new ArrayList<Peg>();
		
		for (Peg p : pegs){
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
			return pegs.get(touchedPegsIndexes.remove(0));			
		}
	}
	
	public void pegTouched(Peg peg){
		int pegIndex = pegs.indexOf(peg);
		//System.out.println("peg touched: " + temp);
		//Indexed touched pegs and do stuff later
		if (peg.getState() != PegState.Checked){
			touchedPegsIndexes.add(pegIndex);	
		}		
		peg.setState(PegState.Checked);
	}
	
	//Pegs have to be colorized etc.
	private void initializePegs(){
		for(Peg p:pegs){
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
	
	private void generateGrid(int rows, int columns){
		int x_interval = (int) ((double) Const.BOARD_ENGINE_WIDTH / (double) columns);
		int y_interval = (int) ((double) Const.BOARD_ENGINE_HEIGHT / (double) rows);
		for (int i=0; i<rows; i++){
			for (int j=0; j<columns; j++){
				Peg adder = new Peg(x_interval * (j+1), y_interval * (i+1));
				adder.setColor(Color.blue);
				pegs.add(adder);
			}
		}
	}
}
