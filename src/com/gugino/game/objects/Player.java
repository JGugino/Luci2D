/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.interfaces.IKillable;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectColliderComponent;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectHealthManagerComponent;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectSpriteRenderer;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.debug.Debug;

public class Player extends GameObject implements IKillable{

	private BufferedImage playerImage;
	
	private GameObjectHealthManagerComponent _healthManager;
	
	public Player(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer,
			GameState _parentState) {
		super(_objectX, _objectY, _objectWidth, _objectHeight, _objectLayer, _parentState);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		playerImage = _r.imageRenderer.getImageFromPath("/player.png");
		
		GameObjectSpriteRenderer _spriteRenderer = new GameObjectSpriteRenderer(this, playerImage);
		addGameObjectComponent(_spriteRenderer);
		
		_healthManager = new GameObjectHealthManagerComponent(this, 100, true);
		_healthManager.addKillAction(this);
		addGameObjectComponent(_healthManager);
		
		GameObjectColliderComponent _collider = new GameObjectColliderComponent(this);
		
		addGameObjectComponent(_collider);
	}
	
	@Override
	public void update(GameManager _gm, double _deltaTime) {
		characterMovement(_gm, _deltaTime);
		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_9)) {
			_healthManager.takeHealth(2);
		}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_0)) {
			_healthManager.addHealth(2);
		}
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
	public void kill() {
		Debug.printError("Player killed");
	}
	
	@Override
	public void render(GameManager _gm, Renderer _r) {
		//_r.shapeRenderer.drawFilledRect(gameObjectX, gameObjectY, gameObjectWidth, gameObjectHeight, Color.green);
	}
	
	@Override
	public void onCollisionEnter(GameObject _collision) {}
	
	@Override
	public void onCollisionStay(GameObject _collision) {}
	
	@Override
	public void onCollisionExit(GameObject _collision) {}
}
