/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.game;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.abstractinterfacers.Game;
import com.gugino.engine.gameloops.Renderer;
import com.gugino.game.states.Menu;

public class Main extends Game{
	
	public final int MENU_STATE = 0;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		new GameManager(800, 600, this);
	}
	

	@Override
	public void start(GameManager _gm) {
		_gm.stateManager.addState(MENU_STATE, new Menu(MENU_STATE, _gm));
		_gm.stateManager.setActiveState(MENU_STATE);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
		
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {		
		_r.fontRenderer.drawString("FPS: " + _gm.windowHandler.fps, 10, 20, Color.black);
	} 
}
