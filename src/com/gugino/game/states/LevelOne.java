/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.states;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectLayer;
import com.gugino.engine.graphics.ui.uiobject.UIProgressBarObject;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.game.Main;
import com.gugino.game.objects.Player;
import com.sun.glass.events.KeyEvent;

public class LevelOne extends GameState{
	
	private UIProgressBarObject progressBar;
	
	private float value = 100;
	
	private Player player;
	
	public LevelOne(int _stateID) {
		super(_stateID);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		player = new Player(100, 100, 64, 64, GameObjectLayer.FOREGROUND, this);
		
		_gm.gameObjectHandler.addGameObject("Player", player);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
		//Sets cameras target to the x and y of the character
		_gm.renderer.mainCamera.setCameraTarget(player.gameObjectX, player.gameObjectY);
		
		//Decrease progress bar value
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_1)) {
			value--;
			progressBar.barCurrentValue = value;
		}
		
		//Increase progress bar value
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_2)) {
			value ++;
			progressBar.barCurrentValue = value;
		}
		
		//Return to main menu
		if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			_gm.stateManager.setActiveState(Main.MENU_STATE);
		}
	}	
	
	@Override
	public void render(GameManager _gm, Renderer _r) {
	}

}
