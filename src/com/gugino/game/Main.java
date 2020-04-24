/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.game;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.abstractinterfacers.Game;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.loops.Renderer;
import com.gugino.game.states.Menu;

public class Main extends Game{
	
	public final int MENU_STATE = 0;
	
	private BufferedImage playerImage;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		new GameManager(800, 600, this);
	}
	

	@Override
	public void start(GameManager _gm, Renderer _r) {
		_gm.stateManager.addState(MENU_STATE, new Menu(MENU_STATE, _gm));
		_gm.stateManager.setActiveState(MENU_STATE);
		
		playerImage = _r.imageRenderer.getImageFromPath("/player.png");
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {		
		_r.fontRenderer.drawString("FPS: " + _gm.windowHandler.fps, 10, 20, Color.black);
		_r.imageRenderer.drawImage(playerImage, 100, 100);
		_r.shapeRenderer.drawFilledRect(WindowHandler.windowWidth / 2, WindowHandler.windowHeight / 2, 32, 32, Color.blue);
	} 
}
