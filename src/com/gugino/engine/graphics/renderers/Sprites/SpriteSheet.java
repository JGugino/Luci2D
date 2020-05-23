/*Created By: Gugino
*Date Created: May 17, 2020
*/

package com.gugino.engine.graphics.renderers.Sprites;

public class SpriteSheet {

    public String spriteSheetID;

    public Sprite[] sprites;

    public int spriteSheetWidth, spriteSheetHeight;

    public int spriteWidth, spriteHeight;
    
    public SpriteSheet(String _sheetID, Sprite[] _sprites){
        this.spriteSheetID = _sheetID;
        this.sprites = _sprites;
    }
}