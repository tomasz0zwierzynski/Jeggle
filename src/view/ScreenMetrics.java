/*
 * Engine always holds object in fixed sized abstract integer game board: 80000 on 60000.
 * So conversions must be always processed to fit real screen size.
 */

package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import model.Const;

public final class ScreenMetrics {

	private ScreenMetrics(){
		
	}

	//Conversion from absolute engine object XY to current object XY on screen
	public static Dimension Map(JPanel screen, Dimension engineDimension){
		Dimension dim = screen.getSize();
		
		//Mapping X:
		int object_x = (int)engineDimension.getWidth();
		int screen_width = (int)dim.getWidth();
		double ratio = screen_width/(double)Const.BOARD_ENGINE_WIDTH;
		double object_x_on_screen = ratio*object_x;
		
		//Mapping Y:
		int object_y = (int)engineDimension.getHeight();
		int screen_height = (int)dim.getHeight();
		ratio = screen_height/(double)Const.BOARD_ENGINE_HEIGHT;
		double object_y_on_screen = ratio*object_y;
				
		return new Dimension((int)Math.round(object_x_on_screen),(int)Math.round(object_y_on_screen));
	}
	
	
	//Conversion from current object XY on screen to absolute engine object XY
	public static Dimension MapInv(JPanel screen, Dimension objectDimension){
		Dimension dim = screen.getSize();
		
		//Mapping X:
		int object_x_on_screen = (int)objectDimension.getWidth();
		int screen_width = (int)dim.getWidth();
		double ratio = (double)Const.BOARD_ENGINE_WIDTH/screen_width;
		double object_x = ratio*object_x_on_screen;
		
		//Mapping Y:
		int object_y_on_screen = (int)objectDimension.getHeight();
		int screen_height = (int)dim.getHeight();
		ratio = (double)Const.BOARD_ENGINE_HEIGHT/screen_height;
		double object_y = ratio*object_y_on_screen;
		
		return new Dimension((int)Math.round(object_x),(int)Math.round(object_y));
	}
	
	//Yet non tested, but 99% it works
	//Conversion from current object XY on screen to percentage value
	public static Dimension MapNorm(JPanel screen, Dimension objectDimension){
		Dimension dim = screen.getSize();
		
		//Mapping X:
		int object_x_on_screen = (int)objectDimension.getWidth();
		int screen_width = (int)dim.getWidth();
		double per_x = object_x_on_screen/(double)screen_width;
			
		//Mapping Y:
		int object_y_on_screen = (int)objectDimension.getHeight();
		int screen_height = (int)dim.getHeight();
		double per_y = object_y_on_screen/(double)screen_height;
		
		return new Dimension((int)per_x,(int)per_y);
	}
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max){
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
}
