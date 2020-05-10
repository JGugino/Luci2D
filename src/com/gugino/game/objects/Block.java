/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.game.objects;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.ObjectColliderComponent;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.RandomHelper;

public class Block extends GameObject{

	private Color blockColor;

	private Color[] possibleColors = new Color[] {Color.red, Color.blue, Color.green, Color.pink,
		Color.orange, Color.cyan, Color.magenta, Color.lightGray, Color.yellow, Color.darkGray, Color.gray};
 
	public Block(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer,
			GameState _parentState) {
		super(_objectX, _objectY, _objectWidth, _objectHeight, _objectLayer, _parentState);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		ObjectColliderComponent _collider = new ObjectColliderComponent(this);
		addGameObjectComponent(_collider);

		pickBlockColor();
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {}

	private void pickBlockColor(){
		int _rand = RandomHelper.getRandomInt(0, possibleColors.length - 1);

		blockColor = possibleColors[_rand];
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		_r.shapeRenderer.drawFilledRect(gameObjectX, gameObjectY, gameObjectWidth, gameObjectHeight, blockColor);
	}

}
