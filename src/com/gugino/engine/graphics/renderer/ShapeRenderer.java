/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.graphics.renderer;

import java.awt.Color;
import java.awt.Graphics2D;

import com.gugino.engine.GameManager;

public class ShapeRenderer {
	
	protected Graphics2D graphics;
	
	//Draw Rectangle
	public void drawFilledRect(float _x, float _y, int _width, int _height) {
		graphics.setColor(Color.white);
		graphics.fillRect((int)_x, (int)_y, _width, _height);
		graphics.setColor(Color.white);
	}
	
	public void drawFilledRect(float _x, float _y, int _width, int _height, Color _color) {
		graphics.setColor(_color);
		graphics.fillRect((int)_x, (int)_y, _width, _height);
		graphics.setColor(Color.white);
	}
	
	public void drawFilledRect(float _x, float _y, int _width, int _height, Color _color, boolean _hasBorder , Color _borderColor) {
		graphics.setColor(_color);
		graphics.fillRect((int)_x, (int)_y, _width, _height);
		
		if(_hasBorder) {
			graphics.setColor(_borderColor);
			graphics.drawRect((int)_x, (int)_y, _width, _height);
		}
		
		graphics.setColor(Color.white);
	}
	
	
	//Draw Rounded Rectangle
	public void drawFilledRoundedRect(float _x, float _y, int _width, int _height, int _arcWidth, int _arcHeight) {
		graphics.setColor(Color.white);
		graphics.fillRoundRect((int)_x, (int)_y, _width, _height, _arcWidth, _arcHeight);
		graphics.setColor(Color.white);
	}
	
	public void drawFilledRoundedRect(float _x, float _y, int _width, int _height,  int _arcWidth, int _arcHeight, Color _color) {
		graphics.setColor(_color);
		graphics.fillRoundRect((int)_x, (int)_y, _width, _height, _arcWidth, _arcHeight);
		graphics.setColor(Color.white);
	}
	
	public void drawFilledRoundedRect(float _x, float _y, int _width, int _height, int _arcWidth, int _arcHeight, Color _color, boolean _hasBorder , Color _borderColor) {
		graphics.setColor(_color);
		graphics.fillRoundRect((int)_x, (int)_y, _width, _height, _arcWidth, _arcHeight);
		
		if(_hasBorder) {
			graphics.setColor(_borderColor);
			graphics.drawRoundRect((int)_x, (int)_y, _width, _height, _arcHeight, _arcHeight);
		}
		
		graphics.setColor(Color.white);
	}
	
	//Draw Circle
	public void drawFilledCricle(float _x, float _y, int _width, int _height) {
		graphics.setColor(Color.white);
		graphics.fillOval((int)_x,(int)_y, _width, _height);
		graphics.setColor(Color.white);
	}
	
	public void drawFilledCircle(float _x, float _y, int _width, int _height, Color _color) {
		graphics.setColor(_color);
		graphics.fillOval((int)_x, (int)_y, _width, _height);
		graphics.setColor(Color.white);	
	}
	
	public void drawFilledCircle(float _x, float _y, int _width, int _height, Color _color, boolean _hasBorder , Color _borderColor) {
		graphics.setColor(_color);
		graphics.fillOval((int)_x, (int)_y, _width, _height);
		
		if(_hasBorder) {
			graphics.setColor(_borderColor);
			graphics.drawOval((int)_x, (int)_y, _width, _height);
		}
		
		graphics.setColor(Color.white);
	}
}
