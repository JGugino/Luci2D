/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.engine.gameobjects;

import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.gameobjects.objectcomponents.ObjectColliderComponent;
import com.gugino.engine.gameobjects.objectcomponents.ObjectPlatformerControlsComponent;

public class GameObjectCollisionHandler {
	private HashMap<String, GameObject[]> collidingObjects = new HashMap<String, GameObject[]>();
	private HashMap<String, GameObject[]> prevCollidingObjects = new HashMap<String, GameObject[]>();	
	
	private boolean didEnterTrigger = false, didExitTrigger = false;
	
	public void update(GameManager _gm) {
		if(!_gm.gameObjectHandler.getEnabledGameObjects().isEmpty()) {
			for(GameObject _object01 : _gm.gameObjectHandler.getEnabledGameObjects().values()) {
				for(GameObject _object02 : _gm.gameObjectHandler.getEnabledGameObjects().values()) {
					if(_object01.gameObjectID != _object02.gameObjectID) {
						checkForCollision(_object01, _object02);
					}
				}
			}	
		}
	}

	protected void checkForCollision(GameObject _object01, GameObject _object02) {
		if(_object01.getBoundingBox().intersects(_object02.getBoundingBox())){
			doCollisionEnterTrigger(_object01, _object02);
		}else{
			doCollisionExitTrigger(_object01, _object02);
		}
	}
	
	private void doCollisionEnterTrigger(GameObject _object01, GameObject _object02) {
		if(!didEnterTrigger) {
			didEnterTrigger = true;
			didExitTrigger = false;
			_object01.isColliding = true;
			_object02.isColliding = true;

			if(_object01.getGameObjectsComponents().containsKey(GameObjectComponentTypes.PLATFORMER_MOVEMENT_MANAGER)){
				ObjectPlatformerControlsComponent _platformer = (ObjectPlatformerControlsComponent)_object01.getGameObjectsComponents().get(GameObjectComponentTypes.PLATFORMER_MOVEMENT_MANAGER);
				_platformer.isJumping = false;
				_platformer.resetRemainingJumps();
			}

			_object01.onCollisionEnter(_object02);
			_object02.onCollisionEnter(_object01);
			if(!collidingObjects.containsKey(_object01.gameObjectID + "-" + _object02.gameObjectID)) {
				collidingObjects.put(_object01.gameObjectID + "-" + _object02.gameObjectID, new GameObject[] {_object01, _object02});	
			}
		}
		
		if((_object01.gameObjectComponents.containsKey(GameObjectComponentTypes.COLLIDER))
				&& (_object02.gameObjectComponents.containsKey(GameObjectComponentTypes.COLLIDER))) {
			
			ObjectColliderComponent _object01Collider = (ObjectColliderComponent)_object01.gameObjectComponents.get(GameObjectComponentTypes.COLLIDER);
			ObjectColliderComponent _object02Collider = (ObjectColliderComponent)_object02.gameObjectComponents.get(GameObjectComponentTypes.COLLIDER);
			
			_object01Collider.componentCollisionUpdate(_object02);
			_object02Collider.componentCollisionUpdate(_object01);
			
		}
		
		_object01.onCollisionStay(_object02);
		_object02.onCollisionStay(_object01);
	}
	
	private void doCollisionExitTrigger(GameObject _object01, GameObject _object02) {
		if(didEnterTrigger) {
			if(!didExitTrigger && didEnterTrigger) {
				didExitTrigger = true;
				didEnterTrigger = false;
				
				_object01.isColliding = false;
				_object02.isColliding = false;

				_object01.onCollisionExit(_object02);
				_object02.onCollisionExit(_object01);

				if(collidingObjects.containsKey(_object01.gameObjectID + "-" + _object02.gameObjectID) && 
						!prevCollidingObjects.containsKey(_object01.gameObjectID + "-" + _object02.gameObjectID)) {
					prevCollidingObjects.put(_object01.gameObjectID + "-" + _object02.gameObjectID, collidingObjects.get(_object01.gameObjectID + "-" + _object02.gameObjectID));
					collidingObjects.remove(_object01.gameObjectID + "-" + _object02.gameObjectID);
				}
			}
		}
	}
	
	public HashMap<String, GameObject[]> getCollidingObjects() {
		return collidingObjects;
	}

	public HashMap<String, GameObject[]> getPrevCollidingObjects() {
		return prevCollidingObjects;
	}
}
