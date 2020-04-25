/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.camera;

import com.gugino.engine.graphics.WindowHandler;

public class Camera {
	
	public  CameraSettings cameraSettings;
	
	protected float cameraTargetX, cameraTargetY;
	
	public Camera(CameraSettings _cameraSettings) {
		this.cameraSettings = _cameraSettings;
	}
	
	public void update(double _deltaTime) {
		if(cameraSettings.shouldFollow) {
			if(cameraSettings.shouldFollowX) {
				cameraSettings.cameraX += ((cameraTargetX - cameraSettings.cameraX) - WindowHandler.windowWidth/2) * _deltaTime / cameraSettings.cameraSmoothingSpeed;
				
				if(cameraSettings.shouldFollowY) {
					cameraSettings.cameraY += ((cameraTargetY - cameraSettings.cameraY ) - WindowHandler.windowHeight/2) * _deltaTime / cameraSettings.cameraSmoothingSpeed;
				}
			}
		}
	}
	
	public void setCameraTarget(float _targetX, float _targetY) {
		cameraTargetX = _targetX;
		cameraTargetY = _targetY;
	}
	
}
