/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.objectcomponents.GameObjectComponent;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.StateManager;
import com.gugino.engine.util.debug.Debug;

public class GameObjectHandler {
	private HashMap<String, GameObject> enabledGameObjects = new HashMap<String, GameObject>();
	private HashMap<String, GameObject> disabledGameObjects = new HashMap<String, GameObject>();
	
	private GameManager gameManager;
	private Renderer renderer;
	
	public GameObjectCollisionHandler objectCollisionHandler;
	
	public GameObjectHandler(GameManager _gm, Renderer _r) {
		objectCollisionHandler = new GameObjectCollisionHandler();
		gameManager = _gm;
		renderer = _r;
	}
	
	public void update(GameManager _gm, double _deltaTime) {
		if(!enabledGameObjects.isEmpty()) {
			objectCollisionHandler.collisionUpdate(_gm);
			for(GameObject _object : enabledGameObjects.values()) {
				_object.update(_gm, _deltaTime);
				
				if(!_object.gameObjectComponents.isEmpty()) {
					for(GameObjectComponent _gameObjectComponent : _object.gameObjectComponents.values()) {
						if(_gameObjectComponent.isEnabled) {
							_gameObjectComponent.componentUpdate(_gm, _deltaTime);	
						}
					}
				}
			}	
		}
	}
	
	public void render(GameManager _gm, Renderer _r) {
		if(!enabledGameObjects.isEmpty()) {
			ArrayList<GameObject> _foregroundObjects = new ArrayList<GameObject>(); 
			ArrayList<GameObject> _midgroundObjects = new ArrayList<GameObject>(); 
			ArrayList<GameObject> _fargroundObjects = new ArrayList<GameObject>(); 
			ArrayList<GameObject> _backgroundObjects = new ArrayList<GameObject>(); 
			
			for(GameObject _object : enabledGameObjects.values()) {
				switch(_object.gameObjectSortingLayer) {
					case FOREGROUND:
						_foregroundObjects.add(_object);
						break;

					case MIDGROUND:
						_midgroundObjects.add(_object);
						break;

					case FARGROUND:
						_fargroundObjects.add(_object);
						break;

					case BACKGROUND:
						_backgroundObjects.add(_object);
						break;
				}
			}
			
			
			for(GameObject _background : _backgroundObjects) {
				_background.render(_gm, _r);
				if(!_background.gameObjectComponents.isEmpty()) {
					for(GameObjectComponent _gameObjectComponent : _background.gameObjectComponents.values()) {
						if(_gameObjectComponent.isEnabled) {
							_gameObjectComponent.componentRender(_gm, _r);	
						}
					}
				}
			}
			
			for(GameObject _farground : _fargroundObjects) {
				_farground.render(_gm, _r);
				if(!_farground.gameObjectComponents.isEmpty()) {
					for(GameObjectComponent _gameObjectComponent : _farground.gameObjectComponents.values()) {
						if(_gameObjectComponent.isEnabled) {
							_gameObjectComponent.componentRender(_gm, _r);	
						}
					}
				}
			}
			
			for(GameObject _midground : _midgroundObjects) {
				_midground.render(_gm, _r);
				if(!_midground.gameObjectComponents.isEmpty()) {
					for(GameObjectComponent _gameObjectComponent : _midground.gameObjectComponents.values()) {
						if(_gameObjectComponent.isEnabled) {
							_gameObjectComponent.componentRender(_gm, _r);	
						}
					}
				}
			}
			
			for(GameObject _foreground : _foregroundObjects) {
				_foreground.render(_gm, _r);
				if(!_foreground.gameObjectComponents.isEmpty()) {
					for(GameObjectComponent _gameObjectComponent : _foreground.gameObjectComponents.values()) {
						if(_gameObjectComponent.isEnabled) {
							_gameObjectComponent.componentRender(_gm, _r);	
						}
					}
				}
			}
		}
	}
	
	public void addGameObject(String _objectID, GameObject _object) {
		if(!enabledGameObjects.containsKey(_objectID) && !disabledGameObjects.containsKey(_objectID)) {
			enabledGameObjects.put(_objectID, _object);
			if(!_object.gameObjectActive) {
				_object.gameObjectActive = true;
				_object.gameObjectID = _objectID;
			}
			
			_object.start(gameManager, renderer);
		}else {
			System.err.println("GameObject ID already exists - " + _objectID);
		}
	}
	
	public void updateGameObjectsForStateSwitch() {
		ArrayList<GameObject> _gameObjectToDisable = new ArrayList<GameObject>();
		ArrayList<GameObject> _gameObjectToEnable = new ArrayList<GameObject>();
		
		for(GameObject _enabledObject : enabledGameObjects.values()) {
			if(_enabledObject.gameObjectParentState.getStateID() != StateManager.activeState.getStateID()) {
				_gameObjectToDisable.add(_enabledObject);
			}
		}
		
		for(GameObject _disabledObject : disabledGameObjects.values()) {
			if(_disabledObject.gameObjectParentState.getStateID() == StateManager.activeState.getStateID()) {
				_gameObjectToEnable.add(_disabledObject);
			}
		}
		
		for(GameObject _objectToDisable : _gameObjectToDisable) {
			disableGameObject(_objectToDisable.gameObjectID);
		}
		
		for(GameObject _objectToEnable : _gameObjectToEnable) {
			enableGameObject(_objectToEnable.gameObjectID);
		}
	}
	
	public void enableGameObject(String _objectID) {
		if(!enabledGameObjects.containsKey(_objectID) && disabledGameObjects.containsKey(_objectID)) {
			disabledGameObjects.get(_objectID).gameObjectActive = true;
			enabledGameObjects.put(_objectID, disabledGameObjects.get(_objectID));
			disabledGameObjects.get(_objectID).onEnable();
			disabledGameObjects.remove(_objectID);
		}else if(enabledGameObjects.containsKey(_objectID) && !disabledGameObjects.containsKey(_objectID)) {
			System.out.println("GameObject already enabled - " + _objectID);
		}else {
			System.err.println("GameObject doesn't exists - " + _objectID);
		}
	}
	
	public void disableGameObject(String _objectID) {
		if(enabledGameObjects.containsKey(_objectID) && !disabledGameObjects.containsKey(_objectID)) {
			
			enabledGameObjects.get(_objectID).gameObjectActive = false;
			disabledGameObjects.put(_objectID, enabledGameObjects.get(_objectID));
			enabledGameObjects.get(_objectID).onDisable();
			enabledGameObjects.remove(_objectID);
		}else if(!enabledGameObjects.containsKey(_objectID) && disabledGameObjects.containsKey(_objectID)) {
			System.out.println("GameObject already disabled - " + _objectID);
		}else {
			System.err.println("GameObject doesn't exists - " + _objectID);
		}
	}
	
	
	public boolean objectsColliding(String _objectOneID, String _objectTwoID) {
		GameObject _object01 = findGameObjectByID(_objectOneID);
		GameObject _object02 = findGameObjectByID(_objectTwoID);
		
		if(_object01 != null && _object02 != null) {
			if(_object01.getBoundingBox().intersects(_object02.getBoundingBox())) {
				return true;
			}else {
				return false;
			}
		}else {
			Debug.printError("One or both of the objects specified do not exist! Please check your spelling and try again: Object ID(object1/object2)" + _objectOneID + "/" + _objectTwoID);
			return false;
		}
	}
	
	public GameObject findGameObjectByID(String _objectID) {
		if(enabledGameObjects.containsKey(_objectID)) {
			return enabledGameObjects.get(_objectID);
		}else if(disabledGameObjects.containsKey(_objectID)) {
			return disabledGameObjects.get(_objectID);
		}else {
			//Debug.printWarning("GameObject not found - " + _objectID);
			return null;
		}
	}
	
	public HashMap<String, GameObject> getEnabledGameObjects(){
		return enabledGameObjects;
	}
	
	public HashMap<String, GameObject> getDisabledGameObjects(){
		return disabledGameObjects;
	}
}
