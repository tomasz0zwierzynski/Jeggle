package test;

import model.Engine;
import view.MainWnd;

public class MainTestNewton {

	/**
	 * @param args
	 */
	

	static private MainWnd mWnd;
	static private Engine engine;
	
	
	public static void main(String[] args){
		
		engine = new Engine();
		mWnd = new MainWnd(engine);
		
		engine.addGraphicListener(mWnd);
		engine.start();
	}

}
