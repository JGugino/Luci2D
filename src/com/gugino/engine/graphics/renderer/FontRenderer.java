/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.graphics.renderer;

import java.awt.Color;
import java.awt.Font;

import com.gugino.engine.gameloops.Renderer;

public class FontRenderer {

	private Renderer renderer;
	
	public FontRenderer(Renderer _r) {
		renderer = _r;
	}
	
	public static final Font DEFAULT_FONT = new Font("San-Serif", Font.PLAIN, 18);
	
	public void drawString(String _content, float _x, float _y) {
		renderer.getGraphics().setColor(Color.white);
		renderer.getGraphics().drawString(_content, _x, _y);
	}
	
	public void drawString(String _content, float _x, float _y, Color _color) {
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().drawString(_content, _x, _y);
	}
	
	public void drawString(String _content, float _x, float _y, Color _color, Font _font) {
		updateFont(_font);
		renderer.getGraphics().setColor(_color);
		renderer.getGraphics().drawString(_content, _x, _y);
		updateFont(DEFAULT_FONT);
	}
	
	public void updateFont(Font _font) {
		renderer.getGraphics().setFont(_font);
	}
}
