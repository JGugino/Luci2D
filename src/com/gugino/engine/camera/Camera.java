/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.camera;

import com.gugino.engine.graphics.WindowHandler;

public class Camera {
	
	public  CameraSettings cameraSettings;
	
	public float cameraX = 0, cameraY = 0;
	
	protected float cameraTargetX = 0, cameraTargetY = 0;
	
	public Camera(CameraSettings _cameraSettings) {
		this.cameraSettings = _cameraSettings;
	}
	
	public void update(double _deltaTime) {
		if(cameraSettings.shouldFollow) {
			if(cameraSettings.shouldFollowX) {
				cameraX += ((cameraTargetX - cameraX) - WindowHandler.windowWidth/2) * cameraSettings.cameraSmoothingSpeed;
				if(cameraSettings.shouldFollowY) {
					cameraY += ((cameraTargetY - cameraY ) - WindowHandler.windowHeight/2) * cameraSettings.cameraSmoothingSpeed;
				}
			}
		}
	}
	
	public void setCameraTarget(float _targetX, float _targetY) {
		cameraTargetX = _targetX;
		cameraTargetY = _targetY;
	}
	
}
