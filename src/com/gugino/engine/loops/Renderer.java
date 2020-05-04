/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.loops;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.graphics.camera.Camera;
import com.gugino.engine.graphics.camera.CameraSettings;
import com.gugino.engine.graphics.renderers.FontRenderer;
import com.gugino.engine.graphics.renderers.ImageRenderer;
import com.gugino.engine.graphics.renderers.ShapeRenderer;
import com.gugino.engine.graphics.ui.UICanvas;

public class Renderer {
		
	//BufferStrategy object
	private BufferStrategy bufferStrategy;
	
	//Graphics2D object
	private Graphics2D graphics2D;
	
	public Camera mainCamera;
	
	public UICanvas canvas;
	
	public FontRenderer fontRenderer;
	public ShapeRenderer shapeRenderer;
	public ImageRenderer imageRenderer;
	
	public Renderer() {
		mainCamera = new Camera(new CameraSettings(false, WindowHandler.windowWidth, WindowHandler.windowHeight));
		
		canvas = new UICanvas();
		
		fontRenderer = new FontRenderer(this);
		shapeRenderer = new ShapeRenderer(this);
		imageRenderer = new ImageRenderer(this);
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
		graphics2D.clearRect(0, 0, WindowHandler.windowWidth,
				WindowHandler.windowHeight);
		
		if(graphics2D != null) {
			/*
			 * RENDER STUFF HERE TO TRANSLATE WITH CAMERA POSITION  - START
			 */			
			
			//Sets start point for graphics to translate based in cameras x and y positions
			graphics2D.translate(-mainCamera.cameraX,-mainCamera.cameraY);
			
			//Scales graphics based on camera field of view (Can be changed in camera settings)
			graphics2D.scale(mainCamera.cameraSettings.cameraFOV, mainCamera.cameraSettings.cameraFOV);
			
			//Sets the default font for font renderer
			fontRenderer.updateFont(FontRenderer.DEFAULT_FONT);
			
			//Runs the render method for the current abstract Game object
			_gm.currentGame.render(_gm, this);
			
			//Runs the render method for the state manager
			_gm.stateManager.render(_gm, this);
			
			//Runs the render method for the GameObjectHandler
			_gm.gameObjectHandler.render(_gm, this);
			
			//Sets end point for graphics to translate based in cameras x and y positions
			graphics2D.translate(mainCamera.cameraX, mainCamera.cameraY);
			
			//Sets scale back to normal
			graphics2D.scale(1/mainCamera.cameraSettings.cameraFOV, 1/mainCamera.cameraSettings.cameraFOV);
			
			/*
			 * RENDER STUFF HERE TO TRANSLATE WITH CAMERA POSITION - END
			 */
			
			
			/*
			 * RENDER UI STUFF HERE - START
			 */
			
			//Draws the frames per second to the screen
			fontRenderer.drawString("FPS: " + _gm.windowHandler.fps, 10, 20, Color.black);
			
			canvas.render(_gm, this);
			
			/*
			 * RENDER UI STUFF HERE - END
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
