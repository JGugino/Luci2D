/*Created By: Gugino
*Date Created: May 23, 2020
*/

package com.gugino.engine.tilemaps;

import com.gugino.engine.graphics.renderers.Sprites.Sprite;
import com.gugino.engine.tilemaps.enums.TileTypes;

public class Tile {
    public String tileID;
    public int tileWidth;
    public int tileHeight;
    public Sprite tileSprite;

    public TileTypes tileType;
    
    protected float tileX;
    protected float tileY;

    public Tile(String _tileID, int _tileWidth, int _tileHeight, Sprite _tileSprite){
        this.tileID = _tileID;
        this.tileWidth = _tileWidth;
        this.tileHeight = _tileHeight;
        this.tileSprite = _tileSprite;
        this.tileType = TileTypes.TRIGGER;
    }

    public Tile(String _tileID, int _tileWidth, int _tileHeight, int _tileX, int _tileY, Sprite _tileSprite, TileTypes _tileType){
        this.tileID = _tileID;
        this.tileWidth = _tileWidth;
        this.tileHeight = _tileHeight;
        this.tileX = _tileX;
        this.tileY = _tileY;
        this.tileSprite = _tileSprite;
        this.tileType = _tileType;
    }

    public void setTilePosition(float _x, float _y){
        this.tileX = _x;
        this.tileY = _y;
    }
}