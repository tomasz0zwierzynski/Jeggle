/*
 * Class that represents main game window.
 * 
 * TODO: add configuration initializer
 */

package view;

/**
 * 
 * 
 * @author Nails
 *
 */

import javax.swing.*;

import model.*;
import model.listeners.GameLoopListener;
import model.listeners.MouseEventListener;

@SuppressWarnings("serial")
public class MainWnd extends JFrame implements GameLoopListener{
		
	private MainPanel mainPanel;
	private MouseEventListener mListener;

	//Engine object containing informations about stuff to draw.
	private Engine engine;
	
	public MainWnd(Engine en){
		this("Jeggle", en);
	}
	public MainWnd(String windowTitle, Engine en){
		super(windowTitle);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setupUI();
		setupMouse(en);
		this.pack();
	}
	
	//Function called to repaint graphic component
	public void redraw(){
		mainPanel.updateGraphicalContent(engine);
	}
	
	private void setupUI(){
		mainPanel = new MainPanel();
		this.add(mainPanel);
	}
	
	private void setupMouse(Engine en){
		engine = en;
		mListener = new MouseEventListener(engine,mainPanel);
		mainPanel.addMouseListener(mListener);
		mainPanel.addMouseMotionListener(mListener);
	}
	
	//Function comes from GameLoopListener interface, called when Engine updated state of the game.
	@Override
	public void onGraphicUpdate() {
		redraw();		
	}
	
	public void setCurrentEngine(Engine en){
		engine = en;
	}
	
	public Engine getCurrentEngine(){
		return engine;
	}
}
