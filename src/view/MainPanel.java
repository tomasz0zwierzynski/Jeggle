/*
 * Class that represents main panel area.
 * 
 */

package view;

/**
 * This class should take care about giving drawing tasks further to child JPanels
 * 
 * @author Nails
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import model.*;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{

	//private Engine engine;
	//For example this component only take care about square so:
	Ball b;	
	
	public MainPanel(){
		super();
		this.setPreferredSize(new Dimension(800,600));
		this.setBackground(Color.GREEN);
	}
	
	//Function that draws everything right away
	public void paintComponent(Graphics g){
		Dimension dim = this.getSize();
		
		g.setColor(getBackground());
		g.fillRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		
		g.setColor(Color.cyan);
		try{
			Dimension screen_dim = ScreenMetrics.Map( this, new Dimension( b.getX(), b.getY() ) );
			Dimension ball_dim = ScreenMetrics.Map( this, new Dimension( b.RADIUS, b.RADIUS ) );
			g.fillOval((int)screen_dim.getWidth(),(int)screen_dim.getHeight(), (int)ball_dim.getWidth(),(int)ball_dim.getHeight());
		}catch(Exception ex){
			System.out.println("Warning: Uncompleted MainPanel when screen is refreshing:");
		}
	}
	
	//TODO it will be in every child JPanel so interface is to made
	public void updateGraphicalContent(Engine en){
		b = en.getBall();		
		repaint();
	}
	
}
