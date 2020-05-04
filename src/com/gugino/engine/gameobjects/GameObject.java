/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.gameobjects;

import java.util.ArrayList;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectLayer;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectComponent;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.sun.javafx.geom.Rectangle;

public abstract class GameObject {
	
	protected String gameObjectID;
	public float gameObjectX, gameObjectY;
	public int gameObjectWidth, gameObjectHeight;
	public GameObjectLayer gameObjectSortingLayer;
	public GameState gameObjectParentState;
	public boolean gameObjectActive = false;
	
	protected ArrayList<GameObjectComponent> gameObjectComponents = new ArrayList<GameObjectComponent>();
	
	public GameObject(float _objectX, float _objectY, int _objectWidth, int _objectHeight, GameObjectLayer _objectLayer, GameState _parentState) {
		this.gameObjectX = _objectX;
		this.gameObjectY = _objectY;
		this.gameObjectWidth = _objectWidth;
		this.gameObjectHeight = _objectHeight;
		this.gameObjectSortingLayer = _objectLayer;
		this.gameObjectParentState = _parentState;
	}
	
	public Rectangle getGameObjectBounds() {
		return new Rectangle((int)gameObjectX, (int)gameObjectY, gameObjectWidth, gameObjectHeight);
	}
	
	//Abstract methods for update and rendering that all GameObjects must have
	public abstract void start(GameManager _gm, Renderer _r);
	public abstract void update(GameManager _gm, double _deltaTime);
	public abstract void render(GameManager _gm, Renderer _r);
	
	//Optional methods for collision detection
	public void onCollisionEnter() {}
	public void onCollisionStay() {}
	public void onCollisionExit() {}
	
	//Optional methods for when GameObject is enabled or disabled
	public void onEnable() {}
	public void onDisable() {}
}
