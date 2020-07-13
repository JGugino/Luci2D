/*Created By: Gugino
 *Date Created: Jul 6, 2020
 */
package com.gugino.game.Maps;

import java.util.ArrayList;

import com.gugino.engine.tilemaps.NoiseMapInformation;

public class NoiseMap extends NoiseMapInformation{
	
	private ArrayList<MapEntry> _entries = new ArrayList<>();
	
	public NoiseMap() {
		_entries.add(new MapEntry("tile_0", 0, 65));
		_entries.add(new MapEntry("tile_1", 65, 110));
		_entries.add(new MapEntry("tile_2", 110, 255));
		
		mapEntries = _entries;
	}
}
