/*Created By: Gugino
*Date Created: May 23, 2020
*/

package com.gugino.engine.tilemaps;

import java.util.ArrayList;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.ObjectSpriteRenderer;
import com.gugino.engine.graphics.renderers.Sprites.SpriteSheet;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.StateManager;
import com.gugino.engine.util.debug.Debug;

public class TileMap {
    
    public String tileMapID;
    public SpriteSheet tileMapSprites;
    protected MapInformation tileMapInformation;
    protected Tile[] mapTiles;
    public int mapWidth;
    public int mapHeight;

    public ArrayList<Tile> generatedTiles = new ArrayList<>();

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

    private void initTileMap(){
        for (int _t = 0; _t < tileMapSprites.sprites.length; _t++) {
            Tile _newTile = new Tile("tile_"+_t,tileMapSprites.spriteWidth, tileMapSprites.spriteHeight, tileMapSprites.sprites[_t]);
            mapTiles[_t] = _newTile;
        }
    }

    public void generateTileMap(GameManager _gm){
        int _index = 0;
        
        for (int _r = 0; _r < tileMapInformation.mapPattern.length; _r++) {
            String[] _rowSplit = tileMapInformation.mapPattern[_r].split(",");
            
            for (String _rowChar : _rowSplit) {
                if(tileMapInformation.mapKey.containsKey(_rowChar)){
                    Tile _foundTile = findTileByID(tileMapInformation.mapKey.get(_rowChar));

                    if(_foundTile != null){
                        if(previousCreatedTile == null){
                            Tile _createdTile = new Tile(tileMapID + " - tile_"+_index, _foundTile.tileWidth, _foundTile.tileHeight, 0, (int)(_r * _foundTile.tileHeight), _foundTile.tileSprite);
                            previousCreatedTile = _createdTile;
                            generatedTiles.add(_createdTile);
                        }else{
                            Tile _createdTile = new Tile(tileMapID + " - tile_"+_index, _foundTile.tileWidth, _foundTile.tileHeight, (int)(previousCreatedTile.tileX + _foundTile.tileWidth), (int)(_r * _foundTile.tileHeight), _foundTile.tileSprite);
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
            _gm.gameObjectHandler.addGameObject(_tile.tileID, new GameObject(_tile.tileX, _tile.tileY, _tile.tileWidth, _tile.tileHeight, GameObjectLayers.BACKGROUND,
                StateManager.activeState) {
             @Override
             public void start(GameManager _gm, Renderer _r) {
                 addGameObjectComponent(new ObjectSpriteRenderer(this, _tile.tileSprite.spriteImage));
             }

             @Override
             public void update(GameManager _gm, double _deltaTime) {
             }
         
             @Override
             public void render(GameManager _gm, Renderer _r) {   
             }
             });
            }
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