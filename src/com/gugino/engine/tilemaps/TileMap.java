/*Created By: Gugino
*Date Created: May 23, 2020
*/

package com.gugino.engine.tilemaps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.ObjectColliderComponent;
import com.gugino.engine.graphics.renderers.Sprites.Sprite;
import com.gugino.engine.graphics.renderers.Sprites.SpriteSheet;
import com.gugino.engine.states.StateManager;
import com.gugino.engine.tilemaps.NoiseMapInformation.MapEntry;
import com.gugino.engine.tilemaps.enums.TileTypes;
import com.gugino.engine.util.Distance;
import com.gugino.engine.util.debug.Debug;

public class TileMap {
    
    public String tileMapID;
    public SpriteSheet tileMapSprites;
    protected MapInformation tileMapInformation;
    protected NoiseMapInformation noiseMapInformation;
    protected GameObject tileLoadingTarget;
    protected Tile[] mapTiles;
    
    protected int tileWidth;
    protected int tileHeight;
    
    public int mapWidth;
    public int mapHeight;
    
    public float chunkLoadingDistance = 100f;
    
    public ArrayList<Tile> generatedTiles = new ArrayList<>();
    public ArrayList<TileObject> generatedTileObjects = new ArrayList<>();
    public ArrayList<TileChunk> generatedChunks = new ArrayList<>();
    
    
    public Tile previousCreatedTile;

    public TileMap(String _mapID, SpriteSheet _tileSprites, MapInformation _mapInformation, int _mapWidth, int _mapHeight){
        this.tileMapID = _mapID;
        this.tileMapSprites = _tileSprites;
        this.mapTiles = new Tile[_tileSprites.sprites.length];
        this.tileMapInformation = _mapInformation;
        this.mapWidth = _mapWidth;
        this.mapHeight = _mapHeight;

        initTileMap();
    }
    
    public TileMap(String _mapID, BufferedImage[] _mapTiles, MapInformation _mapInformation, int _mapWidth, int _mapHeight){
        this.tileMapID = _mapID;
        this.tileMapSprites = null;
        this.mapTiles = new Tile[_mapTiles.length];
        this.tileMapInformation = _mapInformation;
        this.mapWidth = _mapWidth;
        this.mapHeight = _mapHeight;

        initTileMap(_mapTiles);
    }
    
    public TileMap(String _mapID, SpriteSheet _tileSprites, NoiseMapInformation _noiseMapInformation, int _mapWidth, int _mapHeight){
        this.tileMapID = _mapID;
        this.tileMapSprites = _tileSprites;
        this.mapTiles = new Tile[_tileSprites.sprites.length];
        this.tileMapInformation = null;
        this.noiseMapInformation = _noiseMapInformation;
        this.mapWidth = _mapWidth;
        this.mapHeight = _mapHeight;

        initTileMap();
    }
    
    public TileMap(String _mapID, BufferedImage[] _mapTiles, NoiseMapInformation _noiseMapInformation, int _mapWidth, int _mapHeight){
        this.tileMapID = _mapID;
        this.tileMapSprites = null;
        this.mapTiles = new Tile[_mapTiles.length];
        this.tileMapInformation = null;
        this.noiseMapInformation = _noiseMapInformation;
        this.mapWidth = _mapWidth;
        this.mapHeight = _mapHeight;

        initTileMap(_mapTiles);
    }

    private void initTileMap(){
        for (int _t = 0; _t < tileMapSprites.sprites.length; _t++) {
            Tile _newTile = new Tile("tile_"+_t,tileMapSprites.spriteWidth, tileMapSprites.spriteHeight, tileMapSprites.sprites[_t]);
            
            this.tileWidth = tileMapSprites.spriteWidth;
            this.tileHeight = tileMapSprites.spriteHeight;
            
            mapTiles[_t] = _newTile;
        }
    }
    
    private void initTileMap(BufferedImage[] _tiles){
        for (int _t = 0; _t < _tiles.length; _t++) {
        	Sprite _tileSprite = new Sprite("tile_"+_t, _tiles[_t], _tiles[_t].getWidth(), _tiles[_t].getHeight());
            Tile _newTile = new Tile("tile_"+_t,_tileSprite.spriteWidth, _tileSprite.spriteHeight, _tileSprite);
            mapTiles[_t] = _newTile;
        }
    }

    public void generateTileMap(GameManager _gm, float _startXPos, float _startYPos, GameObjectLayers _mapLayer, TileTypes _tilesType){
        int _index = 0;
        
        for (int _r = 0; _r < tileMapInformation.mapPattern.length; _r++) {
            String[] _rowSplit = tileMapInformation.mapPattern[_r].split(",");
            
            for (String _rowChar : _rowSplit) {
                if(tileMapInformation.mapKey.containsKey(_rowChar)){
                    Tile _foundTile = findTileByID(tileMapInformation.mapKey.get(_rowChar));

                    if(_foundTile != null){
                        if(previousCreatedTile == null){
                            Tile _createdTile = new Tile(tileMapID + " - tile_"+_index, _foundTile.tileWidth, _foundTile.tileHeight, (int)_startXPos, (int)(_startYPos + _r * _foundTile.tileHeight), _foundTile.tileSprite, _tilesType);
                            previousCreatedTile = _createdTile;
                            generatedTiles.add(_createdTile);
                        }else{
                            Tile _createdTile = new Tile(tileMapID + " - tile_"+_index, _foundTile.tileWidth, _foundTile.tileHeight, (int)(previousCreatedTile.tileX + _foundTile.tileWidth), (int)(_startYPos + _r * _foundTile.tileHeight), _foundTile.tileSprite, _tilesType);
                            previousCreatedTile = _createdTile;
                            generatedTiles.add(_createdTile);
                        }
                        _index++;
                    }else{
                        Debug.printError("No tile found for character '" + _rowChar + "'!");
                    }
                }
            }

            previousCreatedTile = null;
        }

        for (Tile _tile : generatedTiles) {
            GameObject _foundObject = _gm.gameObjectHandler.findGameObjectByID(_tile.tileID);

            if(_foundObject != null){
                Debug.printWarning("Tile already exists! - " + _tile.tileID);
            }else if(_foundObject == null){
            	TileObject _newTile = new TileObject(_tile.tileID, _tile.tileX, _tile.tileY, _tile.tileWidth, _tile.tileHeight, _mapLayer, _tile.tileSprite, _tile.tileType, StateManager.activeState);
            	
            	if(_newTile.tileType == TileTypes.SOLID) {
            		ObjectColliderComponent _collider = new ObjectColliderComponent(_newTile);
            		_newTile.addGameObjectComponent(_collider);
            	}
            	
            	generatedTileObjects.add(_newTile);
            }
        }
        
        for(TileObject _tO : generatedTileObjects) {
        	_gm.gameObjectHandler.addGameObject(_tO.tileID, _tO);
        }
        
        generatedTiles.clear();
    }
    
    public void generateTileMapWithNoise(GameManager _gm, float _startXPos, float _startYPos, GameObjectLayers _mapLayer, TileTypes _tilesType, int _featureSize, int _chunkSize) {
    	BufferedImage _generatedNoise = _gm.renderer.imageRenderer.generateSimplexNoiseImage(mapWidth, mapHeight, _featureSize);
    
    	//TODO: Not generating all the tiles in world only generating tiles for one chunk
    	
		if (_generatedNoise != null) {
			int _chunkIndex = 0;
			for(int _y = 0; _y < mapHeight; _y += _chunkSize) {
				for(int _x = 0; _x < mapWidth; _x += _chunkSize) {
					TileChunk _newChunk = new TileChunk("Chunk:" + _chunkIndex, new Tile[_chunkSize * _chunkSize], new float[] {_x, _y}, _chunkSize);
					//Debug.printLine(_newChunk.chunkID + ", Position (x/y): " + _newChunk.chunkPosition[0] + "/" + _newChunk.chunkPosition[1]);
					generatedChunks.add(_newChunk);
					_chunkIndex++;
				}
			}
			
			int _totalTilesCreated = 0;
			
			Debug.printLine("Total Generated Chunks: " + generatedChunks.size());
			
			for(TileChunk _c : generatedChunks) {
				int _tileIndex = 0;
				TileChunk _chunk = _c;
				Debug.printLine("Pos: " + _chunk.chunkPosition[0] + "/" + _chunk.chunkPosition[1]);
				for(int _y = (int)_chunk.chunkPosition[1]; _y < _chunkSize; _y ++){
					for(int _x = (int)_chunk.chunkPosition[0]; _x < _chunkSize; _x ++) {
						int _color = _gm.renderer.imageRenderer.getPixelColor(_generatedNoise, _x, _y)[0];
						
						Tile _foundTile = findTileBasedOnColor(_color);
						
						if(_foundTile != null) {
							Tile _createdTile = new Tile(_chunk.chunkID + " - tile_"+_tileIndex, _foundTile.tileWidth, _foundTile.tileHeight, (int)(_startXPos + (_x+tileWidth)), (int)(_startYPos + (_y + tileHeight)), _foundTile.tileSprite, _tilesType);;
							_chunk.tilesInChunk[_tileIndex] = _createdTile;
							generatedTiles.add(_createdTile);
							_tileIndex++;
							_totalTilesCreated++;
							
						}
					}
				}
				
				//Debug.printLine("Total tiles in " + _chunk.chunkID + ": " + _chunk.tilesInChunk.length + ", Chunk Position: " + _chunk.chunkPosition[0] + "/" + _chunk.chunkPosition[1]);
			}
			
			Debug.printLine("Total Tiles: " + _totalTilesCreated);
			
			generateTileObjects(_gm, _mapLayer);
	        
	        generatedTiles.clear();
			
    	}	
    }
    
    protected void generateTileObjects(GameManager _gm, GameObjectLayers _mapLayer) {
    	for (Tile _tile : generatedTiles) {
            GameObject _foundObject = _gm.gameObjectHandler.findGameObjectByID(_tile.tileID);

            if(_foundObject != null){
                Debug.printWarning("Tile already exists! - " + _tile.tileID);
            }else if(_foundObject == null){
            	TileObject _newTile = new TileObject(_tile.tileID, _tile.tileX, _tile.tileY, _tile.tileWidth, _tile.tileHeight, _mapLayer, _tile.tileSprite, _tile.tileType, StateManager.activeState);
            	
            	if(_newTile.tileType == TileTypes.SOLID) {
            		ObjectColliderComponent _collider = new ObjectColliderComponent(_newTile);
            		_newTile.addGameObjectComponent(_collider);
            	}
            	
            	generatedTileObjects.add(_newTile);
            }
        }
		
	    for(TileObject _tO : generatedTileObjects) {
	       _gm.gameObjectHandler.addGameObject(_tO.tileID, _tO);
	       //_gm.gameObjectHandler.disableGameObject(_tO.getGameObjectID());
	    }
    }
    
    public void assignTileLoadingTarget(GameObject _loadingTarget) {
    	if(_loadingTarget != null) {
    		if(tileLoadingTarget != null) {
    			for(TileChunk _chunk : generatedChunks) {
    				for(int i = 0; i < _chunk.tilesInChunk.length; i++) {
        				GameManager.gameManager.gameObjectHandler.disableGameObject(_chunk.tilesInChunk[i].tileID);	
    				}
    			}
    			tileLoadingTarget = _loadingTarget;
    			updateTilesBasedOnTarget();
    		}else {
    			tileLoadingTarget = _loadingTarget;
    			updateTilesBasedOnTarget();
    		}
    	}
    }
    
    public void updateTilesBasedOnTarget() {
    	float _targetX = tileLoadingTarget.gameObjectX;
    	float _targetY = tileLoadingTarget.gameObjectY;
    	
    	for(TileChunk _c : generatedChunks) {
    		
    		float _centerX = _c.getChunkCenter()[0];
    		float _centerY = _c.getChunkCenter()[1];
    		
        	float _distance = Distance.getDistance(_targetX, _targetY, _centerX, _centerY);
        	
        	//Debug.printLine(_c.chunkID + " - " + _distance);
        	
        	if(_distance <= chunkLoadingDistance) {
        		if(!_c.chunkLoaded) {
        			_c.chunkLoaded = true;
        			for(int i = 0; i < _c.tilesInChunk.length; i++) {
        				if(_c.tilesInChunk[i] != null) {
                    		GameManager.gameManager.gameObjectHandler.enableGameObject(_c.tilesInChunk[i].tileID);		
        				}
        			}	
        		}
        	}
        	//else {
//        		if(_c.chunkLoaded) {
//        			_c.chunkLoaded = false;
//        			for(int i = 0; i < _c.tilesInChunk.length; i++) {
//        				if(_c.tilesInChunk[i] != null) {
//        					GameManager.gameManager.gameObjectHandler.disableGameObject(_c.tilesInChunk[i].tileID);
//        				}
//        			}	
//        		}
//    
//    		}
    	}
    	
    }
    
    public void resetTileMapObjects() {
    	for (TileObject _object : generatedTileObjects) {
			if(!_object.gameObjectActive) {
				_object.gameObjectActive = true;
			}
		}
    }
    
    public int calculateTotalActiveTileObjects() {
    	ArrayList<TileObject> _activeObjects = new ArrayList<TileObject>();
    	
    	for (TileObject _object : generatedTileObjects) {
			if(_object.gameObjectActive) {
				_activeObjects.add(_object);
			}
		}
    	
    	return _activeObjects.size();
    }
    
    protected Tile findTileBasedOnColor(int _color) {
    	for(MapEntry _entry : noiseMapInformation.mapEntries) {
    		if((_color >= _entry.selectColors[0]) && (_color <= _entry.selectColors[1])) {
    			return findTileByID(_entry.tileID);
    		}
    	}
    	return null;
    }
    
    protected Tile findTileByID(String _tileID){
        for (int _t = 0; _t < mapTiles.length; _t++) {
            if(mapTiles[_t].tileID.equals(_tileID)){
                return mapTiles[_t];
            }
        }

        Debug.printWarning("Tile " + _tileID + " could not be found!");
        return null;
    }
    
    public GameObject getTileLoadingTarget() {
    	return tileLoadingTarget;
    }
}