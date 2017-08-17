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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.*;

import model.*;
import model.drawable.Ball;
import model.drawable.Drawable;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{

	//For example this component only take care about square so:
	Ball b;	
	List<Drawable> toDraw;
	
	public MainPanel(){
		super();
		this.setPreferredSize(new Dimension(800,600));
		//this.setMinimumSize(new Dimension(800,600));
		this.setBackground(Color.GREEN);
		
		BufferedImage cursorImg = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0,0), "blank cursor");
		
		this.setCursor(blankCursor);
	}
	
	//Function that draws everything right away
	public void paintComponent(Graphics g){
		Dimension dim = this.getSize();
		
		g.setColor(getBackground());
		g.fillRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		
		g.setColor(Color.cyan);
		
		try{
		for (Drawable d : toDraw){
			try{
				Color color = d.getColor();
				g.setColor(color);
				Dimension screen_dim = ScreenMetrics.Map( this, new Dimension( d.getX(), d.getY() ) );
				Dimension ball_dim = ScreenMetrics.Map( this, new Dimension( d.getDiameter(), d.getDiameter() ) );
				g.fillOval((int)screen_dim.getWidth(),(int)screen_dim.getHeight(), (int)ball_dim.getWidth(),(int)ball_dim.getHeight());
			}catch(Exception ex){
				System.out.println("Warning: null pointer reference");
			}
		}
		}catch(Exception ex){
			System.out.println("Exception cought ;)");
		}
	}
	
	//TODO it will be in every child JPanel so interface is to made
	public void updateGraphicalContent(Engine en){
		//b = en.getBall();		
		toDraw = en.getDrawables();
		repaint();			
	}
	
}
