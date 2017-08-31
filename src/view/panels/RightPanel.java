package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Engine;

public class RightPanel extends JPanel implements GraphicalUpdater{
	
	public RightPanel(){
		super();
		this.setPreferredSize(new Dimension(160,140));
		this.setBackground(Color.RED);
	}

	@Override
	public void updateGraphicalContent(Engine en) {
		// TODO Auto-generated method stub
		
	}
	
}
