/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.gameloops;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.GraphicsRenderer;

public class RenderLoop {
	
	//BufferStrategy object
	private BufferStrategy bufferStrategy;
	
	//Graphics2D object
	private Graphics2D graphics2D;
	
	public void render(GameManager _gm, GraphicsRenderer _gr) {
		//Gets the BufferStrategy from the windows canvas
		bufferStrategy = _gm.windowHandler.windowCanvas.getBufferStrategy();
		
		//Checks if there is no BufferStrategy created
		if (bufferStrategy == null) {
			//Creates 3 Bufferers
			_gm.windowHandler.windowCanvas.createBufferStrategy(3);
			return;
		}
		
		//Gets the graphics object from the buffer strategy
		graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
		
		//Clears the screen based on the windows width/height
		graphics2D.clearRect(0, 0, _gm.windowHandler.windowCanvas.getWidth(),
				_gm.windowHandler.windowCanvas.getHeight());
		
		/*
		 * RENDER STUFF HERE
		 */
		
		
		//Runs the render method for the current abstract Game object
		_gm.currentGame.render(_gm, _gr);
		
		//Runs the render method for the state manager
		_gm.stateManager.render(_gm, _gr);
		
		//Sets color to black
		graphics2D.setColor(Color.black);
		//Draws FPS at x:10 y:30
		graphics2D.drawString("FPS: " + _gm.windowHandler.fps, 10, 30);
		
		
		
		/*
		 * RENDER STUFF HERE
		 */
		
		//Disposes of graphics object
		graphics2D.dispose();
		
		//Shows the BufferStrategy
		bufferStrategy.show();
	}
	
	//Getter for graphics object
	public Graphics2D getGraphics() {
		return graphics2D;
	}
}
