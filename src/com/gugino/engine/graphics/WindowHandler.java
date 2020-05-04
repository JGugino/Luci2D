/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class WindowHandler {
	
	//Default title for untitled windows
	public static final String DEFAULT_WINDOW_TITLE = "Luci2D - v0.2.2a";
	
	public static int windowWidth;
	public static int windowHeight;
	
	//Games FPS
	public long fps = 0;
	
	//Windows JFrame object
	public JFrame windowFrame;
	
	//Windows Canvas object
	public Canvas windowCanvas;
	
	//Constructor to create window with title, width and height
	public WindowHandler(String _windowTitle, int _windowWidth, int _windowHeight) {
		//Creates new instance of JFrame with the title passed in
		windowFrame = new JFrame(_windowTitle);
		
		//Creates new instance of Canvas
		windowCanvas = new Canvas();
		
		windowWidth = _windowWidth;
		windowHeight = _windowHeight;
		
		//Create new Dimensions object using defined width/height
		Dimension _windowDimensions = new Dimension(_windowWidth, _windowHeight);
		
		//Sets preferred window size to defined window dimensions
		windowFrame.setPreferredSize(_windowDimensions);
		//Sets minimum window size to defined window dimensions
		windowFrame.setMinimumSize(_windowDimensions);
		
		//Sets the windows default close operation
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Sets if the window can be resized to false
		windowFrame.setResizable(true);
		//Sets the windows start location to the center of the screen
		windowFrame.setLocationRelativeTo(null);
		//Adds the canvas to the windows frame
		windowFrame.add(windowCanvas);
		
		//Sets the frame to visible
		windowFrame.setVisible(true);
		
		windowCanvas.requestFocus();
	}
}
