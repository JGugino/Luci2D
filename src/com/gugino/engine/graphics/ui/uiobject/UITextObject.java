/*Created By: Gugino
 *Date Created: Apr 28, 2020
 */
package com.gugino.engine.graphics.ui.uiobject;

import java.awt.Color;
import java.awt.Font;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.enums.UIObjectLayer;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;

public class UITextObject extends UIObject{
	public String objectText;
	
	public Font objectFont;
	
	public Color objectColor;
	
	public UITextObject(String _text, float _objectX, float _objectY, Color _color, UIObjectLayer _objectLayer, GameState _parentState) {
		super(_objectX, _objectY,  UIObjectType.TEXT, _objectLayer, _parentState);
		this.objectText = _text;
		this.objectFont = new Font("San-Serif", Font.PLAIN, 18);
		this.objectColor = _color;
	}
	
	public UITextObject(String _text, float _objectX, float _objectY, Font _font, UIObjectLayer _objectLayer, GameState _parentState) {
		super(_objectX, _objectY, UIObjectType.TEXT, _objectLayer, _parentState);
		this.objectText = _text;
		this.objectFont = _font;
		this.objectColor = Color.BLACK;
	}
	
	public UITextObject(String _text, float _objectX, float _objectY, Font _font, Color _color, UIObjectLayer _objectLayer, GameState _parentState) {
		super(_objectX, _objectY, UIObjectType.TEXT, _objectLayer, _parentState);
		this.objectText = _text;
		this.objectFont = _font;
		this.objectColor = _color;
	}

	@Override
	public void update(GameManager _gm) {}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		_r.fontRenderer.drawString(objectText, objectXPosition, objectYPosition, objectColor, objectFont);
	}
}
