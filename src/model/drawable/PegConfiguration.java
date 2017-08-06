/*
 * Class that represents pegs positions on level.
 */

package model.drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Const;

/**
 * 
 * 
 * @author Nails
 *
 */

public class PegConfiguration {
	private List<Peg> pegs;
	
	//TODO: reading from file
	public PegConfiguration(){
		pegs = new ArrayList<Peg>();
		Random rnd = new Random();
		for (int i=0; i<40; i++){
			pegs.add(new Peg(rnd.nextInt(Const.BOARD_ENGINE_WIDTH),rnd.nextInt(Const.BOARD_ENGINE_HEIGHT)));
		}
	}
	
	public void addPeg(Peg peg){
		pegs.add(peg);
	}
	
	public List<Peg> getPegs(){
		return pegs;
	}
}
