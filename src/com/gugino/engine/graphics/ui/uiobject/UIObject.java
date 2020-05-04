/*Created By: Gugino
 *Date Created: Apr 28, 2020
 */
package com.gugino.engine.graphics.ui.uiobject;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.enums.UIObjectLayer;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;

public abstract class UIObject {
	protected String objectID;
	protected UIObjectType objectType;
	protected UIObjectLayer objectSortingLayer;
	protected float objectXPosition, objectYPosition;
	protected int objectWidth, objectHeight;
	protected boolean isEnabled = false;
	protected GameState parentState;
	
	public UIObject(float _objectX, float _objectY, UIObjectType _objectType, UIObjectLayer _objectLayer) {
		this.objectXPosition = _objectX;
		this.objectYPosition = _objectY;
		this.objectType = _objectType;
		this.objectSortingLayer = _objectLayer;
		this.parentState = null;
	}
	
	public UIObject(float _objectX, float _objectY, UIObjectType _objectType, UIObjectLayer _objectLayer, GameState _parentState) {
		this.objectXPosition = _objectX;
		this.objectYPosition = _objectY;
		this.objectType = _objectType;
		this.objectSortingLayer = _objectLayer;
		this.parentState = _parentState;
	}
	
	public abstract void update(GameManager _gm);
	public abstract void render(GameManager _gm, Renderer _r);
	
	public void setEnabled(boolean _enabled) {
		isEnabled = _enabled;
	}
	
	public void setObjectPosition(float _objectX, float _objectY) {
		this.objectXPosition = _objectX;
		this.objectYPosition = _objectY;
	}
	
	public void setObjectDimensions(int _objectWidth, int _objectHeight) {
		this.objectWidth = _objectWidth;
		this.objectHeight = _objectHeight;
	}
	
	public void setObjectID(String _id) {
		this.objectID = _id;
	}
		
	public boolean getEnabled() {
		return isEnabled;
	}
	
	public String getObjectID() {
		return objectID;
	}
	
	public UIObjectLayer getUIObjectSortingLayer() {
		return objectSortingLayer;
	}
	
	public UIObjectType getObjectType() {
		return objectType;
	}
	
	public GameState getParentState() {
		return parentState;
	}
	
	public enum UIObjectType{
		TEXT,BUTTON,PROGRESS_BAR,PANEL,TEXT_BOX
	}
}
