/**
 * Class that combines MouseMotion and Mouse listeners and implementing global input data passing it to engine
 * 
 */

package model.listeners;

//TODO: Change Dimension type to Point type in proper places

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import model.Engine;
import model.type.InputData;
import model.type.MouseEventType;

import view.ScreenMetrics;

public class MouseEventListener implements MouseMotionListener, MouseListener{

	private Engine engine;
	private JPanel panel;
	
	public MouseEventListener(Engine en, JPanel pan){
		engine = en;
		panel = pan;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Clicked, dim));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Entered, dim));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Exited, dim));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Pressed, dim));	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Released, dim));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Dragged, dim));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Dimension dim = ScreenMetrics.MapInv(panel, new Dimension(e.getX(), e.getY()));
		engine.processInputData(new InputData(e, MouseEventType.Moved, dim));
	}

}
