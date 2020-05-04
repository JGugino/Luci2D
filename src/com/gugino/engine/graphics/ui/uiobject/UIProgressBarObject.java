/*Created By: Gugino
 *Date Created: Apr 29, 2020
 */
package com.gugino.engine.graphics.ui.uiobject;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.enums.ProgressBarStyle;
import com.gugino.engine.graphics.ui.uiobject.enums.UIObjectLayer;
import com.gugino.engine.loops.Renderer;

public class UIProgressBarObject extends UIObject{

	public Color barBackgroundColor, barFillColor;
	public BufferedImage barBackgroundImage, barFillImage;
	public float barCurrentValue, barMaxValue;
	public ProgressBarStyle barStyle;
	
	private boolean hasImage = false;
	
	public UIProgressBarObject(float _objectX, float _objectY, Color _barBackgroundColor ,Color _barFillColor, int _objectWidth, int _objectHeight, ProgressBarStyle _barStyle, UIObjectLayer _objectLayer, float _maxValue) {
		super(_objectX, _objectY, UIObjectType.PROGRESS_BAR, _objectLayer);
		this.barBackgroundColor = _barBackgroundColor;
		this.barFillColor = _barFillColor;
		this.objectWidth = _objectWidth;
		this.objectHeight = _objectHeight;
		this.barStyle = _barStyle;
		this.barMaxValue = _maxValue;
		barCurrentValue = barMaxValue;
		hasImage = false;
	}
	
	public UIProgressBarObject(float _objectX, float _objectY, BufferedImage _barBackgroundImage ,BufferedImage _barFillImage, int _objectWidth, int _objectHeight, ProgressBarStyle _barStyle, UIObjectLayer _objectLayer, float _maxValue) {
		super(_objectX, _objectY, UIObjectType.PROGRESS_BAR, _objectLayer);
		this.barBackgroundImage = _barBackgroundImage;
		this.barFillImage = _barFillImage;
		this.objectWidth = _objectWidth;
		this.objectHeight = _objectHeight;
		this.barStyle = _barStyle;
		this.barMaxValue = _maxValue;
		barCurrentValue = barMaxValue;
		hasImage = true;
	}

	@Override
	public void update(GameManager _gm) {}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		if(!hasImage) {
			if(barStyle == ProgressBarStyle.SQUARE) {
				//Background
				_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, barBackgroundColor);
				//Fill
				_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, (int)((barCurrentValue/barMaxValue) * objectWidth), objectHeight, barFillColor);
			}
			
			if(barStyle == ProgressBarStyle.ROUNDED) {
				_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, barBackgroundColor);
				//Fill
				_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, (int)((barCurrentValue/barMaxValue) * objectWidth), objectHeight, 5, 5, barFillColor);
			}
		}else {
			//TODO: Bar with image background/fill
		}
	}
	
	public void setBarCurrentValue(float _barCurrentValue) {
		if(_barCurrentValue > this.barMaxValue) {
			this.barCurrentValue = this.barMaxValue;
		}else if(_barCurrentValue > 0){
			this.barCurrentValue = _barCurrentValue;
		}else {
			this.barCurrentValue = 0;
		}
	}
}
