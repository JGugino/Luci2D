/*Created By: Gugino
*Date Created: June 6, 2020
*/

package com.gugino.engine.graphics.ui.uiobject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.ui.uiobject.enums.TextBoxStyle;
import com.gugino.engine.graphics.ui.uiobject.enums.UIObjectLayer;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.debug.Debug;

public class UITextBoxObject extends UIObject {

    protected String textBoxValue = "";
    
    protected int valueWidth = 0;
    
    protected boolean isSelected = false;
    protected boolean textBoxActive = true;
    protected boolean mouseHovering = false;

    protected TextBoxStyle textBoxStyle = TextBoxStyle.SQUARED;

    protected Color backgroundColor;
    protected Color textColor;

    protected int submitKey = KeyEvent.VK_ENTER;
    
    private float backspaceDelayCurrent, backspaceDelayMax = 1.5f;
    private float typeDelayCurrent, typeDelayMax = 0.6f;

    public UITextBoxObject(float _objectX, float _objectY, int _textBoxWidth, int _textBoxHeight, TextBoxStyle _textBoxStyle, Color _backgroundColor, Color _textColor, UIObjectLayer _objectLayer, GameState _parentState) {
        super(_objectX, _objectY, UIObjectType.TEXT_BOX, _objectLayer);
        this.textBoxStyle = _textBoxStyle;
        this.objectWidth = _textBoxWidth;
        this.objectHeight = _textBoxHeight;
        this.backgroundColor = _backgroundColor;
        this.textColor = _textColor;
    }


    @Override
    public void update(GameManager _gm) {
        if(textBoxActive) {
            if(isSelected){
            	if(_gm.keyboardHandler.isKeyPressed(submitKey)) {
            		Debug.printLine(textBoxValue);
            		
            		resetTextBoxValue();
            	}
            	
                if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_BACK_SPACE)){
                    if(textBoxValue.length() > 0) {
                    	if(backspaceDelayCurrent <= 0) {
                        	String[] _splitValueChars = textBoxValue.split("");
                        	String _newValue = "";
                        	
                        	for (int _i = 0; _i < _splitValueChars.length - 1; _i++) {
        						_newValue += _splitValueChars[_i];
        					}
                        	
                        	textBoxValue = _newValue;	
                    		
                        	valueWidth = _gm.renderer.getGraphics().getFontMetrics().stringWidth(textBoxValue); 
                        	
                    		backspaceDelayCurrent = backspaceDelayMax;
                    	}else {
                    		backspaceDelayCurrent -= 0.2f; 
                    	}
                    }
                }else if(_gm.keyboardHandler.isPressingKey() && !_gm.keyboardHandler.isKeyDown(KeyEvent.VK_BACK_SPACE)){
                	if(typeDelayCurrent <= 0) {
                        textBoxValue += _gm.keyboardHandler.getPressedKeys();	
                        typeDelayCurrent = typeDelayMax;
                	}else {
                		typeDelayCurrent -= 0.1f;
                	}
                	
                	valueWidth = _gm.renderer.getGraphics().getFontMetrics().stringWidth(textBoxValue);   
                }
            }

            Debug.printLine("Text Width: " + valueWidth);
            
            //TODO: Work on Text Wraping for textbox

			long _mouseX = _gm.mouseHandler.mouseX;
			long _mouseY = _gm.mouseHandler.mouseY;

			if(_mouseX >= objectXPosition && _mouseX <= objectXPosition + objectWidth) {
				if(_mouseY >= objectYPosition && _mouseY <= objectYPosition + objectHeight) {
					if(!mouseHovering) {
						mouseHovering = true;
					}
					
					if(mouseHovering) {
						if(_gm.mouseHandler.isMouseButtonPressed(MouseEvent.BUTTON1)) {
                            if(!isSelected){
                                isSelected = true;
                            }
						}	
					}
				}
			}else {
				if(mouseHovering) {
					mouseHovering = false;
                }
                if(_gm.mouseHandler.isMouseButtonPressed(MouseEvent.BUTTON1)) {
                    if(isSelected){
                        isSelected = false;
                    }
                }	
			}	
		}
    }

    @Override
    public void render(GameManager _gm, Renderer _r) {
        if(textBoxActive){
            if(textBoxStyle == TextBoxStyle.SQUARED){
                _r.shapeRenderer.drawFilledRect(objectXPosition, objectYPosition, objectWidth, objectHeight, backgroundColor);
                _r.fontRenderer.drawString(textBoxValue, objectXPosition + 5, objectYPosition + objectHeight / 2, textColor);	
            }
    
            if(textBoxStyle == TextBoxStyle.ROUNDED) {
                _r.shapeRenderer.drawFilledRoundedRect(objectXPosition, objectYPosition, objectWidth, objectHeight, 5, 5, backgroundColor);
                _r.fontRenderer.drawString(textBoxValue, objectXPosition + 5, objectYPosition, textColor);
            }
        }
    }
    
    public void resetTextBoxValue() {
    	textBoxValue = "";
    }
}