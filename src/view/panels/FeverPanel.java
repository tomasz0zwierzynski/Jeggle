package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Engine;

@SuppressWarnings("serial")
public class FeverPanel extends JPanel implements GraphicalUpdater{

	public FeverPanel(){
		super();
		this.setPreferredSize(new Dimension(160,600));
		this.setBackground(Color.PINK);
	}

	@Override
	public void updateGraphicalContent(Engine en) {
		// TODO Auto-generated method stub
		
	}
	
}
