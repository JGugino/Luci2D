/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.loops;

import com.gugino.engine.GameManager;

public class Updater {
	
	private boolean gameStartRan = false;
	
	public void start(GameManager _gm, Renderer _r) {
		//GameLoop
		long _lastTime = System.nanoTime();
		double _amountOfTicks = 60.0;
		double _ns = 1000000000 / _amountOfTicks;
		double _deltaTime = 0;
		long _timer = System.currentTimeMillis();
		long _frameTimeDiffNanoSeconds = 0;
		long _frameTimeDiffMilliSeconds = 0;
		int _frames = 0;
		int _renderedFrames = 0, _missedRenderFrames = 0;
		
		if(!gameStartRan) {
			gameStartRan = true;
			//Runs start method for the currently created abstract game
			_gm.currentGame.start(_gm, _r);
			
		}
		
		//Runs while the game is running
		while(_gm.isRunning) {	
			//Sets shouldRender back to false
			_gm.shouldRender = false;

			long _now = System.nanoTime();
			_deltaTime += (_now - _lastTime) / _ns;
			_frameTimeDiffNanoSeconds = (_now - _lastTime);
			_frameTimeDiffMilliSeconds = (_now - _lastTime)/1000000;
			_lastTime = _now;

			_gm.windowHandler.frameTimeNanoSec = _frameTimeDiffNanoSeconds;
			_gm.windowHandler.frameTimeMilliSec = _frameTimeDiffMilliSeconds;

			while(_deltaTime >= 1) {
				//Runs update method on camera
				_r.mainCamera.update(_gm, _deltaTime);
				
				//Runs the update method on the UICanvas
				_r.canvas.update(_gm);
				
				//Runs update method for the currently created abstract game
				_gm.currentGame.update(_gm, _deltaTime);
				
				//Runs the update method for the state manager
				_gm.stateManager.update(_gm, _deltaTime);
				
				//Runs the update method for the particle handler
				_r.particleHandler.update(_gm, _deltaTime);

				//Runs the update method for the GameObjectHandler
				_gm.gameObjectHandler.update(_gm, _deltaTime);
				
				//Sets thats the frame should render equal to true
				_gm.shouldRender = true;
				_deltaTime --;	
			}
			
			//Checks if the frame should render
			if(_gm.shouldRender) {				
				//Runs the render loop
				_gm.renderer.render(_gm);
				_renderedFrames++;
				_gm.windowHandler.renderedFrames = _renderedFrames;
			}else{
				_missedRenderFrames++;
				_gm.windowHandler.missedRenderFrames = _missedRenderFrames;
			}

			_frames++;	
			
			if(System.currentTimeMillis() - _timer > 1000) {
				_timer += 1000;
				_gm.windowHandler.fps = _frames;
				_frames = 0;
			}
		}
		//Stops the main thread when the game is no longer running
		_gm.stop();
	}
}
