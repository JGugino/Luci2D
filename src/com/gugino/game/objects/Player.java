/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectSpriteRenderer;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;

public class Player extends GameObject{

	private BufferedImage playerImage;
	
	public Player(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer,
			GameState _parentState) {
		super(_objectX, _objectY, _objectWidth, _objectHeight, _objectLayer, _parentState);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		playerImage = _r.imageRenderer.getImageFromPath("/player.png");
		
		GameObjectSpriteRenderer _spriteRenderer = new GameObjectSpriteRenderer(this, playerImage);
		addGameObjectComponent(_spriteRenderer);
	}
	
	@Override
	public void update(GameManager _gm, double _deltaTime) {
		characterMovement(_gm, _deltaTime);
	}

	private void characterMovement(GameManager _gm, double _deltaTime) {
	if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_W)) {
		gameObjectY -= 5 * _deltaTime;
	}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_S)){
		gameObjectY += 5 * _deltaTime;
	}
	
	if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_A)) {
		gameObjectX -= 5 * _deltaTime;
	}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_D)){
		gameObjectX += 5 * _deltaTime;
	}
}
	
	@Override
	public void render(GameManager _gm, Renderer _r) {}
	
	@Override
	public void onCollisionEnter(GameObject _collision) {}
	
	@Override
	public void onCollisionStay(GameObject _collision) {}
	
	@Override
	public void onCollisionExit(GameObject _collision) {}
}
