package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Engine;

@SuppressWarnings("serial")
public class BallsPanel extends JPanel implements GraphicalUpdater{

	private Integer ballsLeft;
	
	public BallsPanel(){
		super();
		this.setPreferredSize(new Dimension(160,600));
		this.setBackground(Color.GRAY);
	}

	public void paintComponent(Graphics g){
		try{
		g.setColor(this.getBackground());		
		g.fillRect(0, 0, (int)this.getWidth(), (int)this.getHeight());
		g.setColor(Color.black);
		g.drawString(ballsLeft.toString(), 10, 10);
		}catch(Exception ex){
			
		}
	}
	
	@Override
	public void updateGraphicalContent(Engine en) {
		ballsLeft = new Integer(en.getGameBoard().getBallsLeft());
		repaint();
	}
	
}
