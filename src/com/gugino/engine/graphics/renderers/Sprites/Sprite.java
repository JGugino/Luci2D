/*Created By: Gugino
*Date Created: May 17, 2020
*/

package com.gugino.engine.graphics.renderers.Sprites;

import java.awt.image.BufferedImage;

public class Sprite {
    
    public String spriteName;

    public BufferedImage spriteImage;

    public int spriteWidth, spriteHeight;

    public Sprite(String _spriteName, BufferedImage _sprite){
        this.spriteName = _spriteName;
        this.spriteImage = _sprite;
        this.spriteWidth = _sprite.getWidth();
        this.spriteHeight = _sprite.getHeight();
    }

    public Sprite(String _spriteName, BufferedImage _sprite, int _spriteWidth, int _spriteHeight){
        this.spriteName = _spriteName;
        this.spriteImage = _sprite;
        this.spriteWidth = _spriteWidth;
        this.spriteHeight = _spriteHeight;
    }
}