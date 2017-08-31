package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Engine;

public class ScorePanel extends JPanel implements GraphicalUpdater{
	
	private Integer shotScore;
	private Integer totalScore;
	
	public ScorePanel(){
		super();
		this.setPreferredSize(new Dimension(800,140));
		this.setBackground(Color.ORANGE);
	}

	public void paintComponent(Graphics g){
		try{
		g.setColor(this.getBackground());		
		g.fillRect(0, 0, (int)this.getWidth(), (int)this.getHeight());
		g.setColor(Color.black);
		g.drawString(shotScore.toString(), 10, 10);
		g.drawString(totalScore.toString(), 10, 100);
		}catch(Exception ex){
			
		}
	}
	
	@Override
	public void updateGraphicalContent(Engine en) {
		shotScore = new Integer(en.getGameBoard().getShotScore());
		totalScore = new Integer(en.getGameBoard().getScore());
		repaint();
	}
	
}
