/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.gameloops;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.renderer.FontRenderer;
import com.gugino.engine.graphics.renderer.ShapeRenderer;

public class Renderer {
	
	public FontRenderer fontRenderer;
	public ShapeRenderer shapeRenderer;
	
	//BufferStrategy object
	private BufferStrategy bufferStrategy;
	
	//Graphics2D object
	private Graphics2D graphics2D;
	
	public Renderer() {
		fontRenderer = new FontRenderer(this);
		shapeRenderer = new ShapeRenderer(this);
	}
	
	public void render(GameManager _gm) {
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
		
		
		if(graphics2D != null) {
			/*
			 * RENDER STUFF HERE - START
			 */
			
			//Sets the default font for font renderer
			fontRenderer.updateFont(FontRenderer.DEFAULT_FONT);
			
			//Runs the render method for the current abstract Game object
			_gm.currentGame.render(_gm, this);
			
			//Runs the render method for the state manager
			_gm.stateManager.render(_gm, this);
			
			/*
			 * RENDER STUFF HERE - END
			 */
			
		}
		
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
