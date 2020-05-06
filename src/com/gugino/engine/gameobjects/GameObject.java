/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.gameobjects;

import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectComponent;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;

public abstract class GameObject {
	
	protected String gameObjectID;
	public float gameObjectX, gameObjectY;
	public int gameObjectWidth, gameObjectHeight;
	public GameObjectLayers gameObjectSortingLayer;
	public GameState gameObjectParentState;
	public boolean gameObjectActive = false;
	
	protected HashMap<GameObjectComponentTypes, GameObjectComponent> gameObjectComponents = new HashMap<GameObjectComponentTypes, GameObjectComponent>();
	
	public GameObject(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer, GameState _parentState) {
		this.gameObjectX = _objectX;
		this.gameObjectY = _objectY;
		this.gameObjectWidth = _objectWidth;
		this.gameObjectHeight = _objectHeight;
		this.gameObjectSortingLayer = _objectLayer;
		this.gameObjectParentState = _parentState;
	}
	
	public void addGameObjectComponent(GameObjectComponent _componentToAdd) {
		if(!gameObjectComponents.containsKey(_componentToAdd.componentType)) {
			gameObjectComponents.put(_componentToAdd.componentType, _componentToAdd);
		}else {
			System.err.println("GameObject " + gameObjectID + " already contains the " + _componentToAdd.componentType + " component!");
		}
	}
	
	//Abstract methods for update and rendering that all GameObjects must have
	public abstract void start(GameManager _gm, Renderer _r);
	public abstract void update(GameManager _gm, double _deltaTime);
	public abstract void render(GameManager _gm, Renderer _r);
	
	//Optional methods for collision detection
	public void onCollisionEnter(GameObject _collision) {}
	public void onCollisionStay(GameObject _collision) {}
	public void onCollisionExit(GameObject _collision) {}
	
	//Optional methods for when GameObject is enabled or disabled
	public void onEnable() {}
	public void onDisable() {}
	
	public String getGameObjectID() {
		return gameObjectID;
	}
}
