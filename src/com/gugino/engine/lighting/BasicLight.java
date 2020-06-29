/*Created By: Gugino
 *Date Created: Jun 24, 2020
 */
package com.gugino.engine.lighting;

import java.awt.Color;
import java.awt.geom.Point2D;

import com.gugino.engine.lighting.enums.LightTypes;
import com.gugino.engine.states.GameState;

public class BasicLight {

	protected float lightX, lightY;
	protected Point2D lightCenter;
	protected float lightRadius;
	protected float[] lightIntensity;
	protected Color lightColor;
	protected LightTypes lightType;
	protected GameState parentState;
	protected boolean isEnabled = true;
	
	public BasicLight(float _lightX, float _lightY, float _lightRadius, Color _lightColor, LightTypes _lightType, GameState _parentState) {
		this.lightX = _lightX;
		this.lightY = _lightY;
		this.lightCenter = new Point2D.Float(_lightX, _lightY);
		this.lightRadius = _lightRadius;
		this.lightColor = _lightColor;
		this.lightType = _lightType;
		this.parentState = _parentState;
		this.lightIntensity = new float[] {0.0f, 0.8f};
	}
	
	public float getLightRadius() {
		return lightRadius;
	}
	
	public GameState getParentState() {
		return parentState;
	}
	
	public Color getLightColor() {
		return lightColor;
	}
	
	public LightTypes getLightType() {
		return lightType;
	}
	
	public Point2D getLightCenter() {
		return lightCenter;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void setIsEnabled(boolean _isEnabled) {
		this.isEnabled = _isEnabled;
	}
	
	public void updatePosition(float _x, float _y) {
		this.lightX = _x;
		this.lightY = _y;
		this.lightCenter.setLocation(new Point2D.Float(_x, _y));
	}
	
	public void setLightIntensity(float[] _lightIntensity) {
		this.lightIntensity = _lightIntensity;
	}
}
