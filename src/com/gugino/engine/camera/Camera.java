/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.camera;

public class Camera {
	
	public  CameraSettings cameraSettings;
	
	public Object cameraTarget = null;
	
	public Camera(CameraSettings _cameraSettings) {
		this.cameraSettings = _cameraSettings;
	}
	
	public void update(double _deltaTime) {
		if(cameraSettings.shouldFollow) {
			if(cameraTarget != null) {
				if(cameraSettings.shouldFollowX) {
					//cameraPosition.x += ((cameraTarget.getObjectPosition().x - cameraPosition.x) - _gc.getWidth()/2) * _deltaTime / cameraSettings.cameraSmoothingSpeed;
					//if(cameraSettings.shouldFollowY) {
						//cameraPosition.y += ((cameraTarget.getObjectPosition().y - cameraPosition.y) - _gc.getHeight()/2) * _deltaTime / cameraSettings.cameraSmoothingSpeed;
					//}
				}
			}	
		}
	}
}
