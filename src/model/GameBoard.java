/*
 * Class that represents game progress and changes. It is created when starting an level
 * and deleted when finishing level.
 */

package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.drawable.Drawable;
import model.drawable.Peg;

/**
 * 
 * @author Nails
 *
 */

public class GameBoard {
	
	private List<Peg> pegs;
	
	public GameBoard(){
		pegs = new ArrayList<Peg>();

		generateGrid(12,12);
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
