/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.listeners;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.WindowHandler;

public class ResizeListener extends ComponentAdapter{

	private GameManager gameManager;
	
	public ResizeListener(GameManager _gm) {
		gameManager = _gm;
	}
	
	public void componentResized(ComponentEvent _componentEvent) {
		int currentWindowWidth = gameManager.windowHandler.windowCanvas.getWidth();
		int currentWindowHeight = gameManager.windowHandler.windowCanvas.getHeight();
		
		WindowHandler.windowWidth = currentWindowWidth;
		WindowHandler.windowHeight = currentWindowHeight;
	}
}
