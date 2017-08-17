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

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		this.setSize(1000, 800);
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
		layoutComponents(this.getContentPane());
	}
	
	private void layoutComponents(Container pane){
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Filling with different stuff
		
		JPanel jp1 = new JPanel();
		jp1.setBackground(Color.BLUE);
		//jp1.setPreferredSize(new Dimension(50,50));
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(jp1,c);
		
		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.BLACK);
		//jp2.setPreferredSize(new Dimension(50,50));
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(jp2,c);
		
		JPanel jp3 = new JPanel();
		jp3.setBackground(Color.GRAY);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(jp3,c);
		
		mainPanel = new MainPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(mainPanel,c);
		
		JPanel jp5 = new JPanel();
		jp5.setBackground(Color.DARK_GRAY);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(jp5,c);
		
		JPanel jp6 = new JPanel();
		jp6.setBackground(Color.MAGENTA);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(jp6,c);
		
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
