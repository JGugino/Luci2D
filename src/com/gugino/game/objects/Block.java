/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.game.objects;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectColliderComponent;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;

public class Block extends GameObject{

	public Block(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer,
			GameState _parentState) {
		super(_objectX, _objectY, _objectWidth, _objectHeight, _objectLayer, _parentState);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		GameObjectColliderComponent _collider = new GameObjectColliderComponent(this);
		addGameObjectComponent(_collider);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		_r.shapeRenderer.drawFilledRect(gameObjectX, gameObjectY, gameObjectWidth, gameObjectHeight, Color.cyan);
	}

}
