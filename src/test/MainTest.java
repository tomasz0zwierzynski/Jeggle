package test;

import view.*;
import model.*;

public class MainTest {

	/**
	 * @param args
	 */
	
	static private MainWnd mWnd;
	static private Engine engine;
	
	public static void main(String[] args) {

		
		engine = new Engine();
		mWnd = new MainWnd(engine);
		
		engine.addGraphicListener(mWnd);
		
		
	}
}
