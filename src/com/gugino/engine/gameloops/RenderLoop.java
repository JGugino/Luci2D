/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.gameloops;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.renderer.GraphicsRenderer;

public class RenderLoop {
	
	//GraphicsRenderer object
	public GraphicsRenderer graphicsRenderer;
	
	//BufferStrategy object
	private BufferStrategy bufferStrategy;
	
	//Graphics2D object
	private Graphics2D graphics2D;
	
	public RenderLoop() {
		//Creates new instance of the GraphicsRenderer
		graphicsRenderer = new GraphicsRenderer();
	}
	
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
		
		if(graphicsRenderer.graphics == null) {
			graphicsRenderer.setGraphics(graphics2D);
		}
		
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
