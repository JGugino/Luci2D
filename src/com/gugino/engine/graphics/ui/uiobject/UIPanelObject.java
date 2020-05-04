/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.graphics.ui.uiobject;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.enums.PanelStyle;
import com.gugino.engine.graphics.ui.uiobject.enums.UIObjectLayer;
import com.gugino.engine.loops.Renderer;

public class UIPanelObject extends UIObject{

	public Color panelColor;
	public PanelStyle panelStyle;
	public float panelRoundedArcWidth, panelRoundedArcHeight;
	
	public UIPanelObject(float _objectX, float _objectY, int _panelWidth, int _panelHeight, Color _panelColor, PanelStyle _panelStyle, UIObjectLayer _objectLayer) {
		super(_objectX, _objectY, UIObjectType.PANEL, _objectLayer);
		
		this.objectWidth = _panelWidth;
		this.objectHeight = _panelHeight;
		
		this.panelColor = _panelColor;
		this.panelStyle = _panelStyle;
		
		this.panelRoundedArcWidth = 5;
		this.panelRoundedArcHeight = 5;
	}
	
	public UIPanelObject(float _objectX, float _objectY, int _panelWidth, int _panelHeight, float _roundedArcWidth, float _roundedArcHeight, Color _panelColor, PanelStyle _panelStyle, UIObjectLayer _objectLayer) {
		super(_objectX, _objectY, UIObjectType.PANEL, _objectLayer);
		
		this.objectWidth = _panelWidth;
		this.objectHeight = _panelHeight;
		
		this.panelColor = _panelColor;
		this.panelStyle = _panelStyle;
		
		this.panelRoundedArcWidth = _roundedArcWidth;
		this.panelRoundedArcHeight = _roundedArcHeight;
	}

	@Override
	public void update(GameManager _gm) {}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		if(panelStyle == PanelStyle.SQUARE) {
			_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectHeight, objectHeight, panelColor);
		}else if(panelStyle == PanelStyle.ROUNDED) {
			_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, (int)panelRoundedArcWidth, (int)panelRoundedArcHeight, panelColor);
		}
	}
}
