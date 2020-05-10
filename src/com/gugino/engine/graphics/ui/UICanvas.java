/*Created By: Gugino
 *Date Created: Apr 28, 2020
 */
package com.gugino.engine.graphics.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.UIObject;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.StateManager;

public class UICanvas {
	private HashMap<String, UIObject> enabledUIObjects = new HashMap<String, UIObject>();
	private HashMap<String, UIObject> disabledUIObjects = new HashMap<String, UIObject>();
	
	public void update(GameManager _gm) {
		if(!enabledUIObjects.isEmpty()) {
			for(UIObject _object : enabledUIObjects.values()) {
				if(_object.getEnabled()) {
					_object.update(_gm);	
				}
			}
		}
	}
	
	public void render(GameManager _gm, Renderer _r) {
		if(!enabledUIObjects.isEmpty()) {
			ArrayList<UIObject> _foregroundObjects = new ArrayList<UIObject>();
			ArrayList<UIObject> _midgroundObjects = new ArrayList<UIObject>();
			ArrayList<UIObject> _backgroundObjects = new ArrayList<UIObject>();
			
			for(UIObject _object : enabledUIObjects.values()) {
				if(_object.getEnabled()) {
					switch(_object.getUIObjectSortingLayer()) {
						case FOREGROUND:
						_foregroundObjects.add(_object);
							break;
						case MIDGROUND:
						_midgroundObjects.add(_object);
							break;
						case BACKGROUND:
						_backgroundObjects.add(_object);
							break;
					}
				}
			}
			
			for(UIObject _backgroundObject : _backgroundObjects) {
				_backgroundObject.render(_gm, _r);
			}
			
			for(UIObject _midgroundObject : _midgroundObjects) {
				_midgroundObject.render(_gm, _r);
			}
			
			for(UIObject _foregroundObject : _foregroundObjects) {
				_foregroundObject.render(_gm, _r);
			}
			
			_backgroundObjects.clear();
			_midgroundObjects.clear();
			_foregroundObjects.clear();
		}
	
	}
	
	
	public void addUIObject(String _objectID, UIObject _objectToAdd) {
		if(!enabledUIObjects.containsKey(_objectID) && !disabledUIObjects.containsKey(_objectID)) {
			enabledUIObjects.put(_objectID, _objectToAdd);
			_objectToAdd.setEnabled(true);
			_objectToAdd.setObjectID(_objectID);
		}else {
			System.err.println("UIObject ID already taken: " + _objectID);
		}
	}
	
	public void enableUIObject(String _objectID) {
		UIObject _object = disabledUIObjects.get(_objectID);
		
		if(_object != null) {
			_object.setEnabled(true);
			enabledUIObjects.put(_objectID, _object);
			disabledUIObjects.remove(_objectID, _object);
		}else {
			if(enabledUIObjects.containsKey(_objectID)) {
				System.err.println("UIObject: " + _objectID + " is already enabled");
			}
		}
	}
	
	public void disableUIObject(String _objectID) {
		UIObject _object = enabledUIObjects.get(_objectID);
		
		if(_object != null) {
			_object.setEnabled(false);
			disabledUIObjects.put(_objectID, _object);
			enabledUIObjects.remove(_objectID, _object);
		}else {
			if(disabledUIObjects.containsKey(_objectID)) {
				System.err.println("UIObject: " + _objectID + " is already disabled");
			}
		}
	}
	
	public void updateUIForStateChange() {
		ArrayList<UIObject> _uiToDisable = new ArrayList<UIObject>();
		ArrayList<UIObject> _uiToEnable = new ArrayList<UIObject>();
		
		for(UIObject _enabledObject : enabledUIObjects.values()) {
			if(_enabledObject.getParentState().getStateID() != StateManager.activeState.getStateID()) {
				_uiToDisable.add(_enabledObject);
			}	
		}
	
		for(UIObject _disabledObject : disabledUIObjects.values()) {
			if(_disabledObject.getParentState().getStateID() == StateManager.activeState.getStateID()) {
				_uiToEnable.add(_disabledObject);
			}
		}
		
		for(UIObject _objectToDisable : _uiToDisable) {
			disableUIObject(_objectToDisable.getObjectID());
		}
		
		for(UIObject _objectToEnable : _uiToEnable) {
			enableUIObject(_objectToEnable.getObjectID());
		}
	}
	
	public UIObject findUIObjectByID(String _objectID) {
		if(enabledUIObjects.containsKey(_objectID)) {
			return enabledUIObjects.get(_objectID);
		}else if(disabledUIObjects.containsKey(_objectID)) {
			return disabledUIObjects.get(_objectID);
		}else {
			System.err.println("UIObject not found - " + _objectID);
			return null;
		}
	}

	public HashMap<String, UIObject> getEnabledUIObjects(){
		return enabledUIObjects;
	}

	public HashMap<String, UIObject> getDisabledUIObjects(){
		return disabledUIObjects;
	}
}
