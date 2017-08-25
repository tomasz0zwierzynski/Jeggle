/**
 * Class that represents pegs positions on level with division into 36 blocks that optimize collision calculations.
 */

package model.drawable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import model.Const;

public class PegConfiguration {
	private static int BLOCKS = 48;
	
	private List<Peg> pegs;
	
	private HashMap<Integer,List<Peg>> mappedPegs;
	private Integer[] blocks;
	
	//TODO: reading from file
	public PegConfiguration(){
		pegs = new ArrayList<Peg>();
		generateRandomPegDistribution();
		
		mappedPegs = new HashMap<Integer,List<Peg>>();
		blocks = new Integer[BLOCKS];
		for (int i=0;i<BLOCKS;i++){
			blocks[i] = new Integer(i);
		}
		assignPegsToBlocks();
		
				//System.out.println(toString()); debugging
	}
	
	public List<Peg> getPegsIn(int block){
		List<Peg> value = new ArrayList<Peg>();
		
		if ((block < BLOCKS)&&(block >= 0)){
			value = mappedPegs.get(blocks[block]);		
		}
		return value;
	}
	
	public List<Peg> getAllPegs(){
		return pegs;
	}
	
	public void addPeg(Peg peg){
		pegs.add(peg);
	}
	
	public static int calculateBlockByPosition(Point pos){
		int value = 0;
		int blockWidth = Const.BOARD_ENGINE_WIDTH/8;
		int blockHeight = Const.BOARD_ENGINE_HEIGHT/6;
				
		int col = (int) Math.floor((pos.x / blockWidth));
		int row = (int) Math.floor((pos.y / blockHeight));
		
		value = row * 8 + col;
		
		return value;
	}
	
	private void generateRandomPegDistribution(){
		Random rnd = new Random();
		for (int i=0; i<Const.PEG_COUNT; i++){
			pegs.add(new Peg(rnd.nextInt(Const.BOARD_ENGINE_WIDTH - 2 * Peg.DIAMETER),rnd.nextInt(Const.BOARD_ENGINE_HEIGHT - 2 * Peg.DIAMETER)));
		}
	}
	
	private void assignPegsToBlocks(){
		//I've decided to fix number of blocks to BLOCKS = 48.
		for (int i=0;i<BLOCKS;i++){
			//For every block, all pegs are scanned if they are in current processed block.
			//It seems time consuming, but it is done only once and saves time during gameplay.
			//For every block, list of pegs that are inside is created.
			List<Peg> currentList = new ArrayList<Peg>();
			mappedPegs.put(blocks[i], currentList);
			for (Peg peg: pegs){
				if (isPegInBlock(peg,i)){
					currentList.add(peg);
				}
			}			
		}
	}
	
	private boolean isPegInBlock(Peg peg, int block){
		boolean value = false;
		
		int posX = peg.getX() + Peg.DIAMETER/2;  //Getting center of peg
		int posY = peg.getY() + Peg.DIAMETER/2;
		int blockWidth = Const.BOARD_ENGINE_WIDTH/8; //That gives 8 columns with blockWidth each
		int blockHeight = Const.BOARD_ENGINE_HEIGHT/6; //That gives 6 rows with blockHeight each
		//Screen Engine should always be 4:3 ratio
		int overlap = Peg.DIAMETER; //This is to prevent situations where pegs on the border could be wrongly assigned
									//(nothing bad happens if one peg is assigned to more than one block)
		//Calculationg current block row and column
		int row = (int) Math.floor(block / 8);
		int col = block % 8;
		//Calculating real values of border
		int left = col*blockWidth - overlap;
		int right = (col+1)*blockWidth + overlap;
		int top = row*blockHeight - overlap;
		int bottom = (row+1)*blockHeight + overlap;
		
		//Finally check if peg is in block
		if ((posX > left && posX < right)&&(posY > top && posY < bottom)){
			value = true;
		}
		
		return value;
	}
	
	public String toString(){
		String value = new String();
		
		value += "Printing PegConfiguration informations by blocks:\n" ;
		for (int i=0; i<BLOCKS; i++){
			value += "Block #";
			value += i;
			value += mappedPegs.get(blocks[i]).toString();
			value += "\n";
		}		
		
		return value;
	}
	
	/*
	public List<Peg> getPegs(){
		return pegs;
	}
	*/
}
