/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.gameobjects;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectComponent;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.debug.Debug;

public abstract class GameObject {
	
	protected String gameObjectID;
	public float gameObjectX, gameObjectY;
	public double gameObjectXVelocity, gameObjectYVelocity;
	public int gameObjectWidth, gameObjectHeight;
	public int gameObjectBoundingWidth, gameObjectBoundingHeight;
	public GameObjectLayers gameObjectSortingLayer;
	public GameState gameObjectParentState;
	public boolean gameObjectActive = false, showBoundingBox = false;
	protected Color boundingBoxColor = Color.red;
	protected boolean isColliding = false;
	protected HashMap<GameObjectComponentTypes, GameObjectComponent> gameObjectComponents = new HashMap<GameObjectComponentTypes, GameObjectComponent>();
	
	public GameObject(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayers _objectLayer, GameState _parentState) {
		this.gameObjectX = _objectX;
		this.gameObjectY = _objectY;
		this.gameObjectWidth = _objectWidth;
		this.gameObjectHeight = _objectHeight;
		this.gameObjectBoundingWidth = _objectWidth;
		this.gameObjectBoundingHeight = _objectHeight;
		this.gameObjectSortingLayer = _objectLayer;
		this.gameObjectParentState = _parentState;
	}

	public GameObject(float _objectX, float _objectY, int _objectWidth, int _objectHeight, int _objectBoundingWidth, int _objectBoundingHeight, GameObjectLayers _objectLayer, GameState _parentState) {
		this.gameObjectX = _objectX;
		this.gameObjectY = _objectY;
		this.gameObjectWidth = _objectWidth;
		this.gameObjectHeight = _objectHeight;
		this.gameObjectBoundingWidth = _objectBoundingWidth;
		this.gameObjectBoundingHeight = _objectBoundingHeight;
		this.gameObjectSortingLayer = _objectLayer;
		this.gameObjectParentState = _parentState;
	}
	
	public void addGameObjectComponent(GameObjectComponent _componentToAdd) {
		if(!gameObjectComponents.containsKey(_componentToAdd.componentType)) {
			gameObjectComponents.put(_componentToAdd.componentType, _componentToAdd);
		}else {
			Debug.printError("GameObject " + gameObjectID + " already contains the " + _componentToAdd.componentType + " component!");
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
	
	public void setIsColliding(boolean _isColliding){
		isColliding = _isColliding;
	}

	public Rectangle getBoundingBox(){
		return new Rectangle((int)gameObjectX, (int)gameObjectY, gameObjectBoundingWidth, gameObjectBoundingHeight);
	}

	public boolean isColliding(){
		return isColliding;
	}

	public String getGameObjectID() {
		return gameObjectID;
	}

	public Color getBoundingBoxColor(){
		return boundingBoxColor;
	}

	public HashMap<GameObjectComponentTypes, GameObjectComponent> getGameObjectsComponents(){
		return gameObjectComponents;
	}
}
