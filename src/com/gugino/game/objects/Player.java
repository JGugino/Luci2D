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
import com.gugino.engine.gameobjects.objectcomponents.*;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.debug.Debug;

public class Player extends GameObject implements IKillable{

	private BufferedImage playerImage;

	private ObjectHealthManagerComponent _healthManager;
	
	private ObjectGravityComponent _gravity;

	public Player(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer,
			GameState _parentState) {
		super(_objectX, _objectY, _objectWidth, _objectHeight, _objectLayer, _parentState);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		playerImage = _r.imageRenderer.getImageFromPath("/player.png");
		
		//Adds SpriteRenderer to Player GameObject
		ObjectSpriteRenderer _spriteRenderer = new ObjectSpriteRenderer(this, playerImage);
		addGameObjectComponent(_spriteRenderer);
		
		//Adds HealthManager to Player GameObject
		_healthManager = new ObjectHealthManagerComponent(this, 100);
		_healthManager.addKillAction(this);
		addGameObjectComponent(_healthManager);
		
		//Adds Gravity Component to Player GameObject
		_gravity = new ObjectGravityComponent(this);
		addGameObjectComponent(_gravity);

		//Adds Collider to Player GameObject
		ObjectColliderComponent _collider = new ObjectColliderComponent(this, _gravity);
		addGameObjectComponent(_collider);

		//Adds platformer style controls to Player GameObject
		ObjectPlatformerControlsComponent _controls = new ObjectPlatformerControlsComponent(this);
		_controls.hasDoubleJump(true);
		addGameObjectComponent(_controls);
	}
	
	@Override
	public void update(GameManager _gm, double _deltaTime) {
		//characterMovement(_gm, _deltaTime);
		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_9)) {
			_healthManager.takeHealth(2);
		}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_0)) {
			_healthManager.addHealth(2);
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
	public void onCollisionEnter(GameObject _collision) {
		//Debug.printLine("Player Enter - " + _collision.getGameObjectID());
	}
	
	@Override
	public void onCollisionStay(GameObject _collision) {}
	
	@Override
	public void onCollisionExit(GameObject _collision) {
		//Debug.printLine("Player Exit - " + _collision.getGameObjectID());
	}
}
