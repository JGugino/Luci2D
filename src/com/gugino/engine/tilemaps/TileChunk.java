/*Created By: Gugino
 *Date Created: Jul 11, 2020
 */
package com.gugino.engine.tilemaps;

public class TileChunk {
	
	public String chunkID;
	public int chunkSize;
	public Tile[] tilesInChunk;
	public float[] chunkPosition = new float[2];
	public boolean chunkLoaded = false;
	
	public TileChunk(String _chunkID, Tile[] _tilesInChunk, float[] _chunkPosition, int _chunkSize) {
		this.chunkID = _chunkID;
		this.tilesInChunk = _tilesInChunk;
		this.chunkPosition = _chunkPosition;
		this.chunkSize = _chunkSize;
	}
	
	public float[] getChunkCenter() {
		return new float[] {chunkPosition[0] + chunkSize, chunkPosition[1] + chunkSize};
	}
}
