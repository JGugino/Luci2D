/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.graphics.renderers;

import java.awt.Color;

import com.gugino.engine.loops.Renderer;

public class ShapeRenderer {
	
	private Renderer renderer;
	
	public ShapeRenderer(Renderer _r) {
		renderer = _r;
	}
	
	//Draw Rectangle
	public void drawFilledRect(float _x, float _y, int _width, int _height) {
		renderer.getGraphics().setColor(Color.white);
		renderer.getGraphics().fillRect((int)_x, (int)_y, _width, _height);
		renderer.getGraphics().setColor(Color.white);
	}
	
	public void drawFilledRect(float _x, float _y, int _width, int _height, Color _color) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().fillRect((int)_x, (int)_y, _width, _height);
		renderer.getGraphics().setColor(Color.white);
	}
	
	public void drawFilledRect(float _x, float _y, int _width, int _height, Color _color, boolean _hasBorder , Color _borderColor) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().fillRect((int)_x, (int)_y, _width, _height);
		
		if(_hasBorder) {
			renderer.getGraphics().setColor(_borderColor);
			renderer.getGraphics().drawRect((int)_x, (int)_y, _width, _height);
		}
		
		renderer.getGraphics().setColor(Color.white);
	}
	
	
	//Draw Rounded Rectangle
	public void drawFilledRoundedRect(float _x, float _y, int _width, int _height, int _arcWidth, int _arcHeight) {
		renderer.getGraphics().setColor(Color.white);
		renderer.getGraphics().fillRoundRect((int)_x, (int)_y, _width, _height, _arcWidth, _arcHeight);
		renderer.getGraphics().setColor(Color.white);
	}
	
	public void drawFilledRoundedRect(float _x, float _y, int _width, int _height,  int _arcWidth, int _arcHeight, Color _color) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().fillRoundRect((int)_x, (int)_y, _width, _height, _arcWidth, _arcHeight);
		renderer.getGraphics().setColor(Color.white);
	}
	
	public void drawFilledRoundedRect(float _x, float _y, int _width, int _height, int _arcWidth, int _arcHeight, Color _color, boolean _hasBorder , Color _borderColor) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().fillRoundRect((int)_x, (int)_y, _width, _height, _arcWidth, _arcHeight);
		
		if(_hasBorder) {
			renderer.getGraphics().setColor(_borderColor);
			renderer.getGraphics().drawRoundRect((int)_x, (int)_y, _width, _height, _arcHeight, _arcHeight);
		}
		
		renderer.getGraphics().setColor(Color.white);
	}
	
	//Draw Circle
	public void drawFilledCricle(float _x, float _y, int _width, int _height) {
		renderer.getGraphics().setColor(Color.white);
		renderer.getGraphics().fillOval((int)_x,(int)_y, _width, _height);
		renderer.getGraphics().setColor(Color.white);
	}
	
	public void drawFilledCircle(float _x, float _y, int _width, int _height, Color _color) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().fillOval((int)_x, (int)_y, _width, _height);
		renderer.getGraphics().setColor(Color.white);	
	}
	
	public void drawFilledCircle(float _x, float _y, int _width, int _height, Color _color, boolean _hasBorder , Color _borderColor) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().fillOval((int)_x, (int)_y, _width, _height);
		
		if(_hasBorder) {
			renderer.getGraphics().setColor(_borderColor);
			renderer.getGraphics().drawOval((int)_x, (int)_y, _width, _height);
		}
		
		renderer.getGraphics().setColor(Color.white);
	}
}
