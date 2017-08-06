/*
 * Class that represents game progress and changes. It is created when starting an level
 * and deleted when finishing level.
 */

package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.drawable.Drawable;
import model.drawable.Peg;
import model.drawable.PegConfiguration;
import model.type.PegState;
import model.type.PegType;

/**
 * 
 * @author Nails
 *
 */

public class GameBoard {
	
	private List<Peg> pegs;
	
	//Default constructor generates basic peg grid
	public GameBoard(){
		pegs = new ArrayList<Peg>();
		generateGrid(12,12);
		initializePegs();
	}
	
	public GameBoard(PegConfiguration pg){
		pegs = pg.getPegs();
		initializePegs();		
	}
	
	public List<Drawable> getDrawables(){
		List<Drawable> value = new ArrayList<Drawable>();
		for (Peg p : pegs){
			value.add((Drawable)p);
		}
		return value;
	}
	
	public List<Peg> getPegs(){
		return pegs;
	}
	
	public void pegTouched(Peg peg){
		int temp = pegs.indexOf(peg);
		System.out.println("peg touched: " + temp);
		peg.setState(PegState.Checked);
	}
	
	//Pegs have to be colorized etc.
	private void initializePegs(){
		for(Peg p:pegs){
			p.setType(PegType.Blue);
		}
		Random rnd = new Random();
		int pegCount = pegs.size();
		for(int i=0; i<10; i++){
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
