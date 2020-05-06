/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.engine.util;

public class Distance {

	public static float getDistance(float _xPos1, float _yPos1, float _xPos2, float _yPos2) {
		
		float _xDistance = _xPos1 - _xPos2;
		float _yDistance = _yPos1 - _yPos2;
		
		return (float)Math.sqrt(Math.pow(_xDistance, 2) + Math.pow(_yDistance, 2));
	}
	
	public static double getDistance(double _xPos1, double _yPos1, double _xPos2, double _yPos2) {
		
		double _xDistance = _xPos1 - _xPos2;
		double _yDistance = _yPos1 - _yPos2;
		
		return Math.sqrt(Math.pow(_xDistance, 2) + Math.pow(_yDistance, 2));
	}
}
