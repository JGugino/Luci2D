/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.graphics.renderers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.gugino.engine.graphics.renderers.Sprites.Sprite;
import com.gugino.engine.graphics.renderers.Sprites.SpriteSheet;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.util.OpenSimplexNoise;
import com.gugino.engine.util.debug.Debug;

public class ImageRenderer {

	private Renderer renderer;

	public HashMap<String, SpriteSheet> generatedSpriteSheets = new HashMap<String, SpriteSheet>();

	public ImageRenderer(Renderer _r) {
		renderer = _r;
	}
	
	public void drawImage(BufferedImage _image, float _x, float _y) {
		renderer.getGraphics().drawImage(_image, (int)_x, (int)_y, null);	
	}
	public void drawImage(BufferedImage _image, float _x, float _y, int _width, int _height) {
		renderer.getGraphics().drawImage(_image, (int)_x, (int)_y, _width, _height, null);	
	}
	
	public SpriteSheet generateSpriteSheet(String _spriteSheetID, BufferedImage _spriteSheet, int _totalCols, int _totalRows, int _spriteWidth, int _spriteHeight){

		if(_spriteSheet != null){

		SpriteSheet _createdSheet;
		Sprite[] _spritesToAdd = new Sprite[(_totalCols * _totalRows)];

		int _index = 0;

		for (int _col = 1; _col <= _totalCols; _col++) {
			if(_totalRows <= 1){
				_spritesToAdd[_index] = new Sprite("sprite_" + _index, _spriteSheet.getSubimage(_col + _spriteWidth, 0, _spriteWidth, _spriteHeight), _spriteWidth, _spriteHeight);
				_index++;
			}else if(_totalRows > 1){
				for (int _rows = 1; _rows <= _totalRows; _rows++) {
					_spritesToAdd[_index] = new Sprite("sprite_" + _index, _spriteSheet.getSubimage(_col + _spriteWidth, _rows + _spriteHeight, _spriteWidth, _spriteHeight), _spriteWidth, _spriteHeight);
					_index++;
				}
			}
		}

		_createdSheet = new SpriteSheet(_spriteSheetID, _spritesToAdd);

		_createdSheet.spriteWidth = _spriteWidth;
		_createdSheet.spriteHeight = _spriteHeight;

		if(!generatedSpriteSheets.containsKey(_spriteSheetID)){
			generatedSpriteSheets.put(_spriteSheetID, _createdSheet);
		}else{
			Debug.printWarning("SpriteSheet " + _spriteSheetID + " has already been loaded!");
		}

		return _createdSheet;
		}else{
			Debug.printError("SpriteSheet is null! Check your spelling or make sure the image is in the correct location.");
			return null;
		}
	}

	public SpriteSheet generateSpriteSheet(String _spritePrefix, String _spriteSheetID, BufferedImage _spriteSheet, int _totalCols, int _totalRows, int _spriteWidth, int _spriteHeight){

		if(_spriteSheet != null){

			SpriteSheet _createdSheet;
			Sprite[] _spritesToAdd = new Sprite[(_totalCols * _totalRows)];
	
			int _index = 0;
	
			for (int _col = 1; _col <= _totalCols; _col++) {
				if(_totalRows <= 1){
					_spritesToAdd[_index] = new Sprite(_spritePrefix + "_" + _index, _spriteSheet.getSubimage((_col *_spriteWidth) - _spriteWidth, 0, _spriteWidth, _spriteHeight), _spriteWidth, _spriteHeight);
					_index++;
				}else if(_totalRows > 1){
					for (int _rows = 1; _rows <= _totalRows; _rows++) {
						_spritesToAdd[_index] = new Sprite(_spritePrefix + "_" + _index, _spriteSheet.getSubimage((_col *_spriteWidth) - _spriteWidth, (_rows * _spriteHeight) - _spriteHeight, _spriteWidth, _spriteHeight), _spriteWidth, _spriteHeight);
						_index++;
					}
				}
			}
	
			_createdSheet = new SpriteSheet(_spriteSheetID, _spritesToAdd);
	
			if(!generatedSpriteSheets.containsKey(_spriteSheetID)){
				generatedSpriteSheets.put(_spriteSheetID, _createdSheet);
			}else{
				Debug.printWarning("SpriteSheet " + _spriteSheetID + " has already been loaded!");
			}
	
			return _createdSheet;
			}else{
				Debug.printError("SpriteSheet is null! Check your spelling or make sure the image is in the correct location.");
				return null;
			}
	}

	public BufferedImage generateSimplexNoiseImage(int _width, int _height, double _featureSize) {
		OpenSimplexNoise _noise = new OpenSimplexNoise();
		BufferedImage _holderImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < _height; y++) {
			for(int x = 0; x < _width; x ++) {
				double _noiseValue = _noise.eval(x / _featureSize, y / _featureSize);	
				int rgb = 0x010101 * (int)((_noiseValue + 1) * 127.5);
				_holderImage.setRGB(x, y, rgb);
			}	
		}
		
		return _holderImage;
	}
	
	public int[] getPixelColor(BufferedImage _image, int _x, int _y) {
		int _pixelColor = _image.getRGB(_x, _y);
		int _red = (_pixelColor & 0x00ff0000) >> 16;
		int _green = (_pixelColor & 0x0000ff00) >> 8;
		int _blue = (_pixelColor & 0x000000ff);
		
		return new int[] {_red, _green, _blue};
	}
	
	public BufferedImage getImageFromPath(String _path) {
		BufferedImage _tempImage = null;
		try {
			_tempImage = ImageIO.read(getClass().getResource(_path));
		} catch (IOException e) {
			Debug.printError("Couldn't find image at : " + _path + " -- " + e);
			return null;
		}
		return _tempImage;
	}
}
