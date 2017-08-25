package view.panels;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{

	private Image image = null;
	private int width;
	private int height;
	
	public BackgroundPanel(Image img){
		this.image = img;
		this.width = image.getWidth(this)/2;
		this.height = image.getHeight(this)/2;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (image != null){
			int x = this.getParent().getWidth()/2 - width;
			int y = this.getParent().getHeight()/2 - height;
			g.drawImage(image,x,y,this);
		}
	}
}
