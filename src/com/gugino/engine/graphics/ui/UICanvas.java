/*Created By: Gugino
 *Date Created: Apr 28, 2020
 */
package com.gugino.engine.graphics.ui;

import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.UIObject;
import com.gugino.engine.loops.Renderer;

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
			for(UIObject _object : enabledUIObjects.values()) {
				if(_object.getEnabled()) {
					_object.render(_gm, _r);
				}
			}
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
	
	public void enableUIObject(String _componentID) {
		UIObject _component = disabledUIObjects.get(_componentID);
		
		if(_component != null) {
			_component.setEnabled(true);
			enabledUIObjects.put(_componentID, _component);
			disabledUIObjects.remove(_componentID, _component);
		}else {
			if(enabledUIObjects.containsKey(_componentID)) {
				System.err.println("UIObject: " + _componentID + " is already enabled");
			}
		}
	}
	
	public void disableUIObject(String _componentID) {
		UIObject _component = enabledUIObjects.get(_componentID);
		
		if(_component != null) {
			_component.setEnabled(false);
			disabledUIObjects.put(_componentID, _component);
			enabledUIObjects.remove(_componentID, _component);
		}else {
			if(disabledUIObjects.containsKey(_componentID)) {
				System.err.println("UIObject: " + _componentID + " is already disabled");
			}
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
}
