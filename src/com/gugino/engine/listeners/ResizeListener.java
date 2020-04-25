/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.listeners;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.gugino.engine.GameManager;

public class ResizeListener extends ComponentAdapter{

	private GameManager gameManager;
	
	public ResizeListener(GameManager _gm) {
		gameManager = _gm;
	}
	
	public void componentResized(ComponentEvent _componentEvent) {
		int currentWindowWidth = gameManager.windowHandler.windowCanvas.getWidth();
		int currentWindowHeight = gameManager.windowHandler.windowCanvas.getHeight();
		
		gameManager.renderer.mainCamera.cameraSettings.cameraWidth = currentWindowWidth;
		gameManager.renderer.mainCamera.cameraSettings.cameraHeight = currentWindowHeight;
	}
}
