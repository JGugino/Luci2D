/*Created By: Gugino
 *Date Created: Jul 5, 2020
 */
package com.gugino.engine.tilemaps;

import java.util.ArrayList;

public class NoiseMapInformation {
	public ArrayList<MapEntry> mapEntries = new ArrayList<>();

	protected class MapEntry{
		String tileID;
		double[] selectThreshold = new double[2];
		int[] selectColors = new int[2];
		
		public MapEntry(String _tileID, double _minThreshold, double _maxThreshold) {
			this.tileID = _tileID;
			
			selectThreshold = checkThresholdValues(_minThreshold, _maxThreshold);;
		}
		
		public MapEntry(String _tileID, int _colorOne, int _colorTwo) {
			this.tileID = _tileID;

			selectColors = checkColorValues(_colorOne, _colorTwo);
		}
		
		protected int[] checkColorValues(int _colorOne, int _colorTwo) {
			int _tempColorOne = 0;
			int _tempColorTwo = 0;
			
			if(_colorOne > 255) {
				_tempColorOne = 255;
			}else if(_colorOne < 0) {
				_tempColorOne = 0;
			}else {
				_tempColorOne = _colorOne;
			}
			
			if(_colorTwo > 255) {
				_tempColorTwo = 255;
			}else if(_colorTwo < 0) {
				_tempColorTwo = 0;
			}else {
				_tempColorTwo = _colorTwo;
			}
			
			return new int[] {_tempColorOne, _tempColorTwo};
		}
		
		protected double[] checkThresholdValues(double _minValue, double _maxValue) {
			double _tempMin = 0.0;
			double _tempMax = 0.0;
			
			if(_minValue > 1) {
				_tempMin = 1;
			}else if(_minValue < -1) {
				_tempMin = -1;
			}else {
				_tempMin = _minValue;
			}
			
			if(_maxValue > 1) {
				_tempMax = 1;
			}else if(_maxValue < -1) {
				_tempMax = -1;
			}else {
				_tempMax = _maxValue;
			}
			
			return new double[] {_tempMin, _tempMax};
		}
	}
}