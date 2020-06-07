/*Created By: Gugino
*Date Created: May 23, 2020
*/

package com.gugino.engine.tilemaps;

import com.gugino.engine.graphics.renderers.Sprites.Sprite;

public class Tile {
    public String tileID;
    public int tileWidth;
    public int tileHeight;
    public Sprite tileSprite;

    protected float tileX;
    protected float tileY;

    public Tile(String _tileID, int _tileWidth, int _tileHeight, Sprite _tileSprite){
        this.tileID = _tileID;
        this.tileWidth = _tileWidth;
        this.tileHeight = _tileHeight;
        this.tileSprite = _tileSprite;
    }

    public Tile(String _tileID, int _tileWidth, int _tileHeight, int _tileX, int _tileY, Sprite _tileSprite){
        this.tileID = _tileID;
        this.tileWidth = _tileWidth;
        this.tileHeight = _tileHeight;
        this.tileX = _tileX;
        this.tileY = _tileY;
        this.tileSprite = _tileSprite;
    }

    public void setTilePosition(float _x, float _y){
        this.tileX = _x;
        this.tileY = _y;
    }
}