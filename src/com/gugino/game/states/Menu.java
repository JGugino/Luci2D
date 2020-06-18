/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.game.states;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.graphics.renderers.Sprites.SpriteSheet;
import com.gugino.engine.graphics.ui.uiobject.UIButtonObject;
import com.gugino.engine.graphics.ui.uiobject.UITextObject;
import com.gugino.engine.graphics.ui.uiobject.enums.ButtonStyle;
import com.gugino.engine.graphics.ui.uiobject.enums.UIObjectLayer;
import com.gugino.engine.graphics.ui.uiobject.interfaces.IClickable;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.debug.Debug;
import com.gugino.game.Main;

public class Menu extends GameState{
	
	private UITextObject titleText;
	
	private UIButtonObject playButton;
	
	private BufferedImage spriteSheet;
	public SpriteSheet groundTiles;

	public Menu(int _stateID) {
		super(_stateID);
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		titleText = new UITextObject("Luci2D Demo Game", WindowHandler.windowWidth/2 - 75, 25, UIObjectLayer.FOREGROUND, this);
		playButton = new UIButtonObject("Play", Color.black, Color.lightGray, Color.gray, Color.white, WindowHandler.windowWidth/2 - 125, WindowHandler.windowHeight/2 - 100, 250, 100, ButtonStyle.ROUNDED, UIObjectLayer.FOREGROUND, this);

		spriteSheet = _r.imageRenderer.getImageFromPath("/ground_tiles_sprite_sheet.png");

		groundTiles = _r.imageRenderer.generateSpriteSheet("ground_tiles", spriteSheet, 7, 1, 64, 64);

		playButton.assignButtonListener(new IClickable() {
			@Override
			public void onClick(String _inputSource) {
				_gm.stateManager.setActiveState(Main.LEVEL_ONE_STATE);
			}
		});
		
		UIButtonObject settingsButton = new UIButtonObject("Settings", Color.black, Color.lightGray, Color.gray, Color.white, WindowHandler.windowWidth/2 - 125, WindowHandler.windowHeight/2 + 100, 250, 100, ButtonStyle.ROUNDED, UIObjectLayer.FOREGROUND, this);

		settingsButton.assignButtonListener(new IClickable() {
			
			@Override
			public void onClick(String _inputSource) {
				Debug.printLine("Settings");
			}
		});
		
		_r.canvas.addUIObject("Title_Text", titleText);
		_r.canvas.addUIObject("Play_Button", playButton);
		_r.canvas.addUIObject("Settings_Button", settingsButton);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		
	}
	
	@Override
	public void onActive(GameManager _gm) {
		_gm.showDebugInformation = false;
	}
	
}
