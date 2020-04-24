/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.graphics.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.gugino.engine.GameManager;

public class FontRenderer {

	protected Graphics2D graphics;
	
	public static final Font DEFAULT_FONT = new Font("San-Serif", Font.PLAIN, 18);
	
	public void drawString(String _content, float _x, float _y) {
		graphics.setColor(Color.white);
		graphics.drawString(_content, _x, _y);
	}
	
	public void drawString(String _content, float _x, float _y, Color _color) {
		graphics.setColor(_color);
		graphics.drawString(_content, _x, _y);
	}
	
	public void drawString(String _content, float _x, float _y, Color _color, Font _font) {
		updateFont(_font);
		graphics.setColor(_color);
		graphics.drawString(_content, _x, _y);
		updateFont(DEFAULT_FONT);
	}
	
	public void updateFont(Font _font) {
		graphics.setFont(_font);
	}
}
