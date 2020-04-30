/*Created By: Gugino
 *Date Created: Apr 28, 2020
 */
package com.gugino.engine.graphics.ui.uiobject;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;

public class UIButtonObject extends UIObject{
	public String buttonText;
	public Color buttonColor, buttonHoverColor, buttonDisabledColor, buttonTextColor;
	public BufferedImage buttonImage;
	public float buttonTextXOffset = 3.5f, buttonTextYOffset = -3.5f;
	public ButtonStyle buttonStyle;
	protected IClickable buttonListener;
	
	public boolean mouseHovering = false, buttonActive = true;
	
	private boolean useImage = false, hasHoverColor = false;
	
	public UIButtonObject(String _buttonText, Color _buttonTextColor ,Color _buttonColor, float _objectX, float _objectY, int _objectWidth, int _objectHeight, ButtonStyle _buttonStyle) {
		super(_objectX, _objectY, UIObjectType.BUTTON);
		this.buttonText = _buttonText;
		this.buttonColor = _buttonColor;
		this.buttonTextColor = _buttonTextColor;
		this.objectWidth = _objectWidth;
		this.objectHeight = _objectHeight;
		this.buttonStyle = _buttonStyle;
		useImage = false;
		hasHoverColor = false;
	}
	
	public UIButtonObject(String _buttonText, Color _buttonTextColor ,Color _buttonColor, Color _buttonHoverColor, Color _buttonDisabledColor, float _objectX, float _objectY, int _objectWidth, int _objectHeight, ButtonStyle _buttonStyle) {
		super(_objectX, _objectY, UIObjectType.BUTTON);
		this.buttonText = _buttonText;
		this.buttonColor = _buttonColor;
		this.buttonDisabledColor = _buttonDisabledColor;
		this.buttonHoverColor = _buttonHoverColor;
		this.buttonTextColor = _buttonTextColor;
		this.objectWidth = _objectWidth;
		this.objectHeight = _objectHeight;
		this.buttonStyle = _buttonStyle;
		useImage = false;
		hasHoverColor = true;
	}
	
	public UIButtonObject(String _buttonText, Color _buttonTextColor, BufferedImage _buttonImage, float _objectX, float _objectY, int _objectWidth, int _objectHeight) {
		super(_objectX, _objectY, UIObjectType.BUTTON);
		this.buttonText = _buttonText;
		this.buttonImage = _buttonImage;
		this.buttonTextColor = _buttonTextColor;
		useImage = true;
		hasHoverColor = false;
		this.objectWidth = _objectWidth;
		this.objectHeight = _objectHeight;
	}

	@Override
	public void update(GameManager _gm) {
		if(buttonActive) {
			long _mouseX = _gm.mouseHandler.mouseX;
			long _mouseY = _gm.mouseHandler.mouseY;

			if(_mouseX >= objectXPosition && _mouseX <= objectXPosition + objectWidth) {
				if(_mouseY >= objectYPosition && _mouseY <= objectYPosition + objectHeight) {
					if(!mouseHovering) {
						mouseHovering = true;
					}
					
					if(mouseHovering) {
						//Left click
						if(_gm.mouseHandler.isMouseButtonPressed(MouseEvent.BUTTON1)) {
							if(buttonListener != null) {
								buttonListener.onClick(objectID);	
							}
						}	
					}
				}
			}else {
				if(mouseHovering) {
					mouseHovering = false;
				}
			}	
		}
	}
	
	public void assignButtonListener(IClickable _listener) {
		if(_listener != null) {
			buttonListener = _listener;	
		}
	}
	
	@Override
	public void render(GameManager _gm, Renderer _r) {
		if(!useImage) {
			if(buttonStyle == ButtonStyle.SQUARE) {
				if(!hasHoverColor) {
					if(buttonActive) {
						_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, buttonColor);
						_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset)
								, objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
					}else {
						_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, buttonDisabledColor);
						_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset)
								, objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
					}	
				}else{
					if(buttonActive) {
						if(!mouseHovering) {
							_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, buttonColor);
							_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset)
									, objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
						}else {
							_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, buttonHoverColor);
							_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset)
									, objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
						}	
					}else {
						_r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, buttonDisabledColor);
						_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset)
								, objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
					}
				}
			}else if(buttonStyle == ButtonStyle.ROUNDED) {
				if(!hasHoverColor) {
					if(buttonActive) {
						_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, buttonColor);
						_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset), objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
					}else {
						_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, buttonDisabledColor);
						_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset), objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);			
					}
				}else {
					if(buttonActive) {
						if(!mouseHovering) {
							_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, buttonColor);
							_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset), objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
						}else {
							_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, buttonHoverColor);
							_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset), objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
						}	
					}else {
						_r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, buttonDisabledColor);
						_r.fontRenderer.drawString(buttonText, objectXPosition + (objectWidth / 2 - buttonText.length() * buttonTextXOffset), objectYPosition + objectHeight / 2 - buttonTextYOffset, buttonTextColor);	
					}
				}
			}
		}else {
			
		}
		
	}
	
	public enum ButtonStyle{
		SQUARE, ROUNDED
	}
}
