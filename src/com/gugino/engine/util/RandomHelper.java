/*Created By: Gugino
 *Date Created: May 6, 2020
 */
package com.gugino.engine.util;

import java.util.Random;

public class RandomHelper {

	public static int getRandomInt(int _minValue, int _maxValue) {
		Random _r = new Random();
		return _r.nextInt((_maxValue + 1) - _minValue) + _minValue;
	}
	
	public static float getRandomFloat() {
		Random _r = new Random();
		return _r.nextFloat();
	}
	
}
