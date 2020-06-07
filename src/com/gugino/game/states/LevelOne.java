/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.states;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.graphics.renderers.Sprites.SpriteSheet;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.tilemaps.TileMap;
import com.gugino.engine.util.debug.Debug;
import com.gugino.game.Main;
import com.gugino.game.Maps.Map01;
import com.gugino.game.objects.*;
import com.sun.glass.events.KeyEvent;

public class LevelOne extends GameState{
	private Player player;

	private TileMap map;
	private SpriteSheet groundTiles;

	public LevelOne(int _stateID) {
		super(_stateID);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		groundTiles = _r.imageRenderer.generatedSpriteSheets.get("ground_tiles");

		Map01 _info = new Map01();

		map = new TileMap("Map01", groundTiles, _info, 1280, 768);

		map.generateTileMap(_gm);

		player = new Player(0, 0, 64, 64, GameObjectLayers.MIDGROUND, this);

		_gm.gameObjectHandler.addGameObject("Player", player);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
		//Sets cameras target to the x and y of the character
		_gm.renderer.mainCamera.setCameraTarget(player.gameObjectX, player.gameObjectY);
		
		//Return to main menu
		if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			_gm.stateManager.setActiveState(Main.MENU_STATE);
		}
	}	
	
	@Override
	public void render(GameManager _gm, Renderer _r) {
	}
	
	@Override
	public void onActive(GameManager _gm) {
		_gm.showDebugInformation = true;
		player.gameObjectX = 300;
		player.gameObjectY = 100;
		player.gameObjectXVelocity = 0;
		player.gameObjectYVelocity = 0;
	}
}
