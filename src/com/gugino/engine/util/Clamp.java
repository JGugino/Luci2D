/*Created By: Gugino
 *Date Created: May 6, 2020
 */
package com.gugino.engine.util;

public class Clamp {
	public static int clampInt(int _value, int _minValue, int _maxValue) {
		if(_value >= _maxValue) {
			return _value = _maxValue;
		}else if(_value <= _minValue) {
			return _value = _minValue;
		}else {
			return _value;
		}
	}
	
	public static float clampFloat(float _value, float _minValue, float _maxValue) {
		if(_value >= _maxValue) {
			return _value = _maxValue;
		}else if(_value <= _minValue) {
			return _value = _minValue;
		}else {
			return _value;
		}
	}
	
	public static long clampLong(long _value, long _minValue, long _maxValue) {
		if(_value >= _maxValue) {
			return _value = _maxValue;
		}else if(_value <= _minValue) {
			return _value = _minValue;
		}else {
			return _value;
		}
	}
	
	public static double clampDouble(double _value, double _minValue, double _maxValue) {
		if(_value >= _maxValue) {
			return _value = _maxValue;
		}else if(_value <= _minValue) {
			return _value = _minValue;
		}else {
			return _value;
		}
	}
}
