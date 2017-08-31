/*
 * Class that represents main panel area.
 * 
 */

package view.panels;

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
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import view.ScreenMetrics;

import model.*;
import model.drawable.Ball;
import model.drawable.Drawable;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements GraphicalUpdater{

	//For example this component only take care about square so:
	Ball b;	
	List<Drawable> toErese;
	List<Drawable> toDraw;
	
	Dimension panelSize;
	
	/***
	//Backgrounding
	Image background;
	Image scaled;
	boolean refresh = false;
	 */
	
	public BoardPanel(){
		super();
		this.setPreferredSize(new Dimension(800,600));
		this.setBackground(Color.GREEN);
		
		//Turning off mouse cursor
		BufferedImage cursorImg = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0,0), "blank cursor");
		this.setCursor(blankCursor);
		
		//Prepare size change listener
		this.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				panelSize = e.getComponent().getSize();
			}
		});	
		
		//TODO:Think more about backgrounding
		/***
		//Preparing Background Image
		try{
		background = new ImageIcon("C://Documents and Settings//Nails//Eclipse_workspace//JegglePrototype//background.jpg").getImage();
		scaled = background.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
		refresh = true;
		}catch(Exception ex){
			System.out.println("File error.");
			background = null;
		}
		
		//Adding resizable controller
		this.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				scaled = background.getScaledInstance(e.getComponent().getWidth(), e.getComponent().getHeight(), Image.SCALE_FAST);
				refresh = true;
			}
		});
		*/
		
	}
	
	//Function that draws everything right away
	public void paintComponent(Graphics g){
		/***
		if (refresh){
			//Image scaled = background.getScaledInstance((int)dim.getWidth(), (int)dim.getHeight(), Image.SCALE_DEFAULT);
			g.drawImage(scaled,0,0,this);
			refresh = false;
		}*/
		
		g.setColor(Color.green);		
		g.fillRect(0, 0, (int)panelSize.getWidth(), (int)panelSize.getHeight());
				
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
	
	@Override
	public void updateGraphicalContent(Engine en){	
		toDraw = en.getDrawables();
		repaint();			
	}
	
}
