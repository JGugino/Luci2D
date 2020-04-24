/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.graphics.renderers;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gugino.engine.loops.Renderer;

public class ImageRenderer {

	private Renderer renderer;

	public ImageRenderer(Renderer _r) {
		renderer = _r;
	}
	
	public void drawImage(BufferedImage _image, float _x, float _y) {
		renderer.getGraphics().drawImage(_image, (int)_x, (int)_y, null);	
	}
	public void drawImage(BufferedImage _image, float _x, float _y, int _width, int _height) {
		renderer.getGraphics().drawImage(_image, (int)_x, (int)_y, _width, _height, null);	
	}
	
	public BufferedImage getImageFromPath(String _path) {
		BufferedImage _tempImage = null;
		try {
			_tempImage = ImageIO.read(getClass().getResource(_path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return _tempImage;
	}
}
