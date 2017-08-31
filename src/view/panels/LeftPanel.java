package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Engine;

public class LeftPanel extends JPanel implements GraphicalUpdater{
	
	public LeftPanel(){
		super();
		this.setPreferredSize(new Dimension(160,140));
		this.setBackground(Color.BLUE);
	}

	@Override
	public void updateGraphicalContent(Engine en) {
		// TODO Auto-generated method stub
		
	}
	
}
