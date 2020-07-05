/*Created By: Gugino
 *Date Created: Jun 28, 2020
 */
package com.gugino.engine.splashscreen;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.loops.Renderer;

public class SplashScreenManager {

	protected BufferedImage splashScreenImage;
	
	protected float splashScreenDuration = 250f;
	
	protected int splashImageWidth, splashImageHeight;
	
	protected float splashImageX, splashImageY;
	
	private float maxSplashDuration = 0;
	
	protected Color splashBackgroundColor = Color.white;
	
	protected boolean splashScreenActive = false;
	
	private final BufferedImage DEFAULT_IMAGE;
	
	public SplashScreenManager(Renderer _r) {
		DEFAULT_IMAGE = _r.imageRenderer.getImageFromPath("/Luci2D_logo_1920x1080.png");
		this.splashScreenImage = DEFAULT_IMAGE;
		this.splashImageWidth = this.splashScreenImage.getWidth() / 3;
		this.splashImageHeight = this.splashScreenImage.getHeight() / 3;
		this.maxSplashDuration = this.splashScreenDuration;
		
		this.splashImageX = WindowHandler.windowWidth / 2;
		this.splashImageY = WindowHandler.windowHeight / 2;
		
		splashScreenActive = true;
	}
	
	public void splashUpdate(double _delta) {
		if(splashScreenActive) {
			if(splashScreenDuration <= 0) {
				splashScreenActive = false;
				resetSplashScreenTimer();
			}else {
				splashScreenDuration -= _delta;
			}
		}
	}
	
	public void splashRender(GameManager _gm, Renderer _r) {
		if(splashScreenActive) {
			_r.shapeRenderer.drawFilledRect(0, 0, WindowHandler.windowWidth, WindowHandler.windowHeight, splashBackgroundColor);
			_r.imageRenderer.drawImage(splashScreenImage, splashImageX - splashImageWidth / 2, splashImageY - splashImageHeight / 2, splashImageWidth, splashImageHeight);
		}
	}
	
	protected void resetSplashScreenTimer() {
		if(splashScreenDuration < maxSplashDuration) {
			splashScreenDuration = maxSplashDuration;
		}
	}
	
	public void setSplashImage(BufferedImage _image) {
		splashScreenImage = _image;
	}
	
	public void setSplashScreenDuration(float _duration) {
		splashScreenDuration = _duration;
	}
	
	public void setSplashImageSize(int _width, int _height) {
		this.splashImageWidth = _width;
		this.splashImageHeight = _height;
	}
	
	public void setSplashImagePosition(float _x, float _y) {
		this.splashImageX = _x;
		this.splashImageY = _y;
	}
	
	public void setSplashBackgroundColor(Color _backgroundColor) {
		this.splashBackgroundColor = _backgroundColor;
	}
	
	public boolean isSplashScreenActive() {
		return splashScreenActive;
	}
}
