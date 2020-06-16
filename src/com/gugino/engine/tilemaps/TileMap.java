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
import com.gugino.engine.tilemaps.enums.TileTypes;
import com.gugino.engine.util.debug.Debug;

public class TileMap {
    
    public String tileMapID;
    public SpriteSheet tileMapSprites;
    protected MapInformation tileMapInformation;
    protected Tile[] mapTiles;
    public int mapWidth;
    public int mapHeight;

    public ArrayList<Tile> generatedTiles = new ArrayList<>();
    public ArrayList<TileObject> generatedTileObjects = new ArrayList<>();

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

    private void initTileMap(){
        for (int _t = 0; _t < tileMapSprites.sprites.length; _t++) {
            Tile _newTile = new Tile("tile_"+_t,tileMapSprites.spriteWidth, tileMapSprites.spriteHeight, tileMapSprites.sprites[_t]);
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
}