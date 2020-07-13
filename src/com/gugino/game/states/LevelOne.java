/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.states;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.graphics.renderers.Sprites.SpriteSheet;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.tilemaps.TileMap;
import com.gugino.engine.tilemaps.enums.TileTypes;
import com.gugino.game.Main;
import com.gugino.game.Maps.NoiseMap;
import com.gugino.game.objects.Player;

public class LevelOne extends GameState{
	private Player player;

	private TileMap map;
	private SpriteSheet groundTiles;

	private BufferedImage image;
	
	public LevelOne(int _stateID) {
		super(_stateID);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		
		_gm.audioManager.importAudioClip("Clip", "/button_click.wav", false);
		
		groundTiles = _r.imageRenderer.generatedSpriteSheets.get("ground_tiles");

		NoiseMap _map = new NoiseMap();

		BufferedImage[] _images = new BufferedImage[3];
		
		_images[0] = groundTiles.sprites[0].spriteImage;
		_images[1] = groundTiles.sprites[1].spriteImage;
		_images[2] = groundTiles.sprites[2].spriteImage;
		
		map = new TileMap("Map01", _images, _map, 128, 128);

		map.generateTileMapWithNoise(_gm, 0, 0, GameObjectLayers.BACKGROUND, TileTypes.TRIGGER, 24, 32);
		
		player = new Player(0, 0, 64, 64, GameObjectLayers.MIDGROUND, this);

		_gm.gameObjectHandler.addGameObject("Player", player);
		
		map.assignTileLoadingTarget(player);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
		//Sets cameras target to the x and y of the character
		_gm.renderer.mainCamera.setCameraTarget(player.gameObjectX, player.gameObjectY);
		
		if(map.getTileLoadingTarget() != null) {
			map.updateTilesBasedOnTarget();	
		}
		
		//Return to main menu
		if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			_gm.stateManager.setActiveState(Main.MENU_STATE);
		}
	}	
	
	@Override
	public void render(GameManager _gm, Renderer _r) {
		_r.imageRenderer.drawImage(image, 100, 100);
	
	}
	
	@Override
	public void onActive(GameManager _gm, Renderer _r) {
		_gm.showDebugInformation = true;
		player.gameObjectX = 300;
		player.gameObjectY = 100;
		player.gameObjectXVelocity = 0;
		player.gameObjectYVelocity = 0;
	}
}
