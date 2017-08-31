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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import view.panels.BallsPanel;
import view.panels.BoardPanel;
import view.panels.FeverPanel;
import view.panels.GraphicalUpdater;
import view.panels.LeftPanel;
import view.panels.RightPanel;
import view.panels.ScorePanel;

import model.*;
import model.listeners.GameLoopListener;
import model.listeners.MouseEventListener;

@SuppressWarnings("serial")
public class MainWnd extends JFrame implements GameLoopListener{
		
	private BoardPanel boardPanel;
	private BallsPanel ballsPanel;
	private FeverPanel feverPanel;
	private ScorePanel scorePanel;
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private MouseEventListener mListener;

	private List<GraphicalUpdater> panels = new ArrayList<GraphicalUpdater>();
	
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
			boardPanel.updateGraphicalContent(engine);
			if (engine.isRedrawSideNeeded()){
				redrawSide();
				engine.clearSideValueChanged();
			}
	}
	
	public void redrawSide(){
		for (GraphicalUpdater gu : panels){
			gu.updateGraphicalContent(engine);
		}
	}
	
	private void setupUI(){
		layoutComponents(this.getContentPane());
	}
	
	private void layoutComponents(Container pane){
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Filling with different stuff
		
		rightPanel = new RightPanel();
		rightPanel.add(new JButton("RIGHT"));
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(rightPanel,c);
		panels.add(rightPanel);
		
		scorePanel = new ScorePanel();
		scorePanel.add(new JLabel("score..."));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(scorePanel,c);
		panels.add(scorePanel);
		
		feverPanel = new FeverPanel();
		feverPanel.add(new JLabel("fever..."));
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(feverPanel,c);
		panels.add(feverPanel);
		
		boardPanel = new BoardPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(boardPanel,c);		
		
		leftPanel = new LeftPanel();
		leftPanel.add(new JButton("LEFT"));
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(leftPanel,c);
		panels.add(leftPanel);
		
		ballsPanel = new BallsPanel();
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(ballsPanel,c);
		panels.add(ballsPanel);
		
	}
	
	private void setupMouse(Engine en){
		engine = en;
		mListener = new MouseEventListener(engine,boardPanel);
		boardPanel.addMouseListener(mListener);
		boardPanel.addMouseMotionListener(mListener);
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
