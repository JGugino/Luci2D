/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.game;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import com.gugino.engine.Game;
import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.graphics.camera.CameraSettings;
import com.gugino.engine.graphics.renderers.FontRenderer;
import com.gugino.engine.graphics.ui.uiobject.*;
import com.gugino.engine.graphics.ui.uiobject.enums.*;
import com.gugino.engine.loops.Renderer;
import com.gugino.game.states.Menu;
import com.sun.glass.events.KeyEvent;

public class Main extends Game{
	
	public final int MENU_STATE = 0;
	
	private BufferedImage playerImage;
	
	private UIButtonObject button;
	
	private UIProgressBarObject progressBar;
	
	private float x = 100, y = 100;
	
	private float value = 100;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		new GameManager(800, 600, this);
	}
	

	@Override
	public void start(GameManager _gm, Renderer _r) {
		_r.mainCamera.cameraSettings = new CameraSettings(true, 800, 600);

		_gm.stateManager.addState(MENU_STATE, new Menu(MENU_STATE, _gm));
		_gm.stateManager.setActiveState(MENU_STATE);
		
		playerImage = _r.imageRenderer.getImageFromPath("/player.png");
		
		_r.canvas.addUIObject("panel", new UIPanelObject(WindowHandler.windowWidth / 2 - 150, 0, 250, 100, 100, 100, Color.lightGray, PanelStyle.ROUNDED, UIObjectLayer.MIDGROUND));
		
		_r.canvas.addUIObject("Text", new UITextObject("GAME", WindowHandler.windowWidth / 2 - 50, 50, FontRenderer.DEFAULT_FONT, Color.BLACK, UIObjectLayer.FOREGROUND));
	
		button = new UIButtonObject("Button", Color.black, Color.cyan, Color.pink, Color.green, 200, 200, 200, 100, ButtonStyle.ROUNDED, UIObjectLayer.FOREGROUND);
		
		progressBar = new UIProgressBarObject(400, 400, Color.darkGray, Color.green, 150, 50, ProgressBarStyle.ROUNDED, UIObjectLayer.FOREGROUND, 100);
		
		button.assignButtonListener(new IClickable() {
			@Override
			public void onClick(String _inputSource) {
				System.out.println("Button " + _inputSource + " clicked");
				
			}
		});
		
		//_r.canvas.addUIObject("Test_Progress_Bar", progressBar);
		
		//_r.canvas.addUIObject("Button_Test", button);
	
		
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
		_gm.renderer.mainCamera.setCameraTarget(x, y);
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_W)) {
			y -= 5 * _deltaTime;
		}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_S)){
			y += 5 * _deltaTime;
		}
		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_A)) {
			x -= 5 * _deltaTime;
		}else if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_D)){
			x += 5 * _deltaTime;
		}
		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_1)) {
			value--;
			progressBar.barCurrentValue = value;
		}
		
		if(_gm.keyboardHandler.isKeyDown(KeyEvent.VK_2)) {
			value ++;
			progressBar.barCurrentValue = value;
		}
		
		
		if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_F3)) {
			if(button.buttonActive) {
				button.buttonActive = false;
			}else {
				button.buttonActive = true;
			}
		}
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {
		_r.imageRenderer.drawImage(playerImage, x, y);
		_r.shapeRenderer.drawFilledRect(WindowHandler.windowWidth / 2, WindowHandler.windowHeight / 2, 32, 32, Color.blue);
	}
	
	@Override
	public void onWindowClose(WindowEvent e) {
		
	}
}
