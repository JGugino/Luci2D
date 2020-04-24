/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.gameloops;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.GraphicsRenderer;

public class UpdateLoop {
	
	public void start(GameManager _gm, GraphicsRenderer _gr) {
		//GameLoop
		long _lastTime = System.nanoTime();
		double _amountOfTicks = 60.0;
		double _ns = 1000000000 / _amountOfTicks;
		double _deltaTime = 0;
		long _timer = System.currentTimeMillis();
		int _frames = 0;
		
		//Runs start method for the currently created abstract game
		_gm.currentGame.start(_gm, _gr);
		
		//Runs while the game is running
		while(_gm.isRunning) {	
			//Sets shouldRender back to false
			_gm.shouldRender = false;
			
			long _now = System.nanoTime();
			_deltaTime += (_now - _lastTime) / _ns;
			_lastTime = _now;
			
			while(_deltaTime >= 1) {
				//Runs update method for the currently created abstract game
				_gm.currentGame.update(_gm, _deltaTime);
				
				//Runs the update method for the state manager
				_gm.stateManager.update(_gm, _deltaTime);
				
				//Sets thats the frame should render equal to true
				_gm.shouldRender = true;
				_deltaTime --;	
			}
			
			//Checks if the frame should render
			if(_gm.shouldRender) {
				//Runs the render loop
				_gm.getRenderLoop().render(_gm, _gr);	
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
