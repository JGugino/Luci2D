/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.game;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.abstractinterfacers.Game;
import com.gugino.engine.camera.CameraSettings;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.loops.Renderer;
import com.gugino.game.states.Menu;
import com.sun.glass.events.KeyEvent;

public class Main extends Game{
	
	public final int MENU_STATE = 0;
	
	private BufferedImage playerImage;
	
	private float x = 100, y = 100;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		new GameManager(1600, 800, this);
	}
	

	@Override
	public void start(GameManager _gm, Renderer _r) {
		_r.mainCamera.cameraSettings = new CameraSettings(true, 0, 0, 1600, 800);
		
		System.out.println(_r.mainCamera.cameraSettings.shouldFollow);
				
		_gm.stateManager.addState(MENU_STATE, new Menu(MENU_STATE, _gm));
		_gm.stateManager.setActiveState(MENU_STATE);
		
		playerImage = _r.imageRenderer.getImageFromPath("/player.png");
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_W)) {
			y -= 5 * _deltaTime;
		}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_S)){
			y += 5 * _deltaTime;
		}
		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_A)) {
			x -= 5 * _deltaTime;
		}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_D)){
			x += 5 * _deltaTime;
		}
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		_r.mainCamera.setCameraTarget(x, y);
		_r.imageRenderer.drawImage(playerImage, x, y);
		_r.shapeRenderer.drawFilledRect(WindowHandler.windowWidth / 2, WindowHandler.windowHeight / 2, 32, 32, Color.blue);
	} 
}
