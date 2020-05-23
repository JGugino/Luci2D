/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.game;

import java.awt.event.WindowEvent;

import com.gugino.engine.Game;
import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.camera.Camera;
import com.gugino.engine.graphics.camera.CameraSettings;
import com.gugino.engine.loops.Renderer;
import com.gugino.game.states.LevelOne;
import com.gugino.game.states.Menu;

public class Main extends Game{
	
	public static final int MENU_STATE = 0;
	public static final int LEVEL_ONE_STATE = 1;
	
	public static CameraSettings mainCameraSettings;
	
	public static Camera cameraRef;

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		new GameManager(800, 600, this);
	}
	

	@Override
	public void start(GameManager _gm, Renderer _r) {
		mainCameraSettings = new CameraSettings(true, 800, 600);
		
		cameraRef = _r.mainCamera;

		cameraRef.cameraSettings = mainCameraSettings;

		_gm.stateManager.addState(MENU_STATE, new Menu(MENU_STATE));
		_gm.stateManager.addState(LEVEL_ONE_STATE, new LevelOne(LEVEL_ONE_STATE));
		_gm.stateManager.setActiveState(MENU_STATE);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {}
	
	@Override
	public void onWindowClose(WindowEvent e) {}
}
