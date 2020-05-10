/*Created By: Gugino
 *Date Created: May 6, 2020
 */
package com.gugino.engine.gameobjects.objectcomponents;

import java.awt.image.BufferedImage;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.loops.Renderer;

public class ObjectSpriteRenderer extends GameObjectComponent{

	public BufferedImage objectSprite;
	
	public ObjectSpriteRenderer(GameObject _componentParent, BufferedImage _objectSprite) {
		super(GameObjectComponentTypes.SPRITE_RENDERER, _componentParent);
		this.objectSprite = _objectSprite;
	}

	@Override
	public void componentRender(GameManager _gm, Renderer _r) {
		_r.imageRenderer.drawImage(objectSprite, componentParent.gameObjectX, componentParent.gameObjectY, componentParent.gameObjectWidth, componentParent.gameObjectHeight);
	}
	
}
