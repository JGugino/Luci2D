/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.camera;

public class CameraSettings {	
	public float cameraFOV = 1f;
	
	public int cameraWidth, cameraHeight;
	
	public boolean shouldFollow = false;
	public boolean shouldFollowX = false;
	public boolean shouldFollowY = false;
	public float cameraSmoothingSpeed = 0.5f;
	
	public CameraSettings(boolean _shouldFollow, int _cameraWidth, int _cameraHeight) {
		this.shouldFollow = _shouldFollow;

		if(_shouldFollow) {
			this.shouldFollowX = true;
			this.shouldFollowY = true;
		}else {
			this.shouldFollowX = false;
			this.shouldFollowY = false;
		}
		
		this.cameraWidth = _cameraWidth;
		this.cameraHeight = _cameraHeight;
	}
	
	public CameraSettings(boolean _shouldFollow, int _cameraWidth, int _cameraHeight, float _speed) {
		this.shouldFollow = _shouldFollow;
		
		if(_shouldFollow) {
			this.shouldFollowX = true;
			this.shouldFollowY = true;
		}else {
			this.shouldFollowX = false;
			this.shouldFollowY = false;
		}
		
		this.cameraWidth = _cameraWidth;
		this.cameraHeight = _cameraHeight;
		
		this.cameraSmoothingSpeed = _speed;
	}
	
	public CameraSettings(boolean _shouldFollow, boolean _shouldFollowX, boolean _shouldFollowY, int _cameraWidth, int _cameraHeight) {
		this.shouldFollow = _shouldFollow;
		
		this.shouldFollowX = _shouldFollowX;
		this.shouldFollowY = _shouldFollowY;
		
		this.cameraWidth = _cameraWidth;
		this.cameraHeight = _cameraHeight;
	}
	
	public CameraSettings(boolean _shouldFollow, boolean _shouldFollowX, boolean _shouldFollowY, int _cameraWidth, int _cameraHeight, float _speed) {
		this.shouldFollow = _shouldFollow;
		
		this.shouldFollowX = _shouldFollowX;
		this.shouldFollowY = _shouldFollowY;
		
		this.cameraWidth = _cameraWidth;
		this.cameraHeight = _cameraHeight;
		
		this.cameraSmoothingSpeed = _speed;
	}
}
