/*Created By: Gugino
 *Date Created: Apr 28, 2020
 */
package com.gugino.engine.graphics.ui.uiobject;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;

public abstract class UIObject {
	protected String objectID;
	protected UIObjectType objectType;
	protected float objectXPosition, objectYPosition;
	protected int objectWidth, objectHeight;
	protected boolean isEnabled = false;
	
	public UIObject(float _objectX, float _objectY, UIObjectType _objectType) {
		this.objectXPosition = _objectX;
		this.objectYPosition = _objectY;
		this.objectType = _objectType;
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
		
	public UIObjectType getObjectType() {
		return objectType;
	}
	
	public enum UIObjectType{
		TEXT,BUTTON,PROGRESS_BAR,PANEL,TEXT_BOX
	}
}
