package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BallsPanel extends JPanel{

	public BallsPanel(){
		super();
		this.setPreferredSize(new Dimension(160,600));
		this.setBackground(Color.GRAY);
	}
	
}
