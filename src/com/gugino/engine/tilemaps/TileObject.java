/*Created By: Gugino
 *Date Created: Jun 15, 2020
 */
package com.gugino.engine.tilemaps;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.gameobjects.objectcomponents.ObjectColliderComponent;
import com.gugino.engine.gameobjects.objectcomponents.ObjectSpriteRenderer;
import com.gugino.engine.graphics.renderers.Sprites.Sprite;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.tilemaps.enums.TileTypes;

public class TileObject extends GameObject{

	protected Sprite tileSprite;
	
	protected TileTypes tileType = TileTypes.TRIGGER;
	
	protected String tileID;
	
	protected ObjectSpriteRenderer tileSpriteRenderer;
	protected ObjectColliderComponent tileCollider;
	
	public TileObject(String _tileID, float _objectX, float _objectY, int _objectWidth, int _objectHeight,
			GameObjectLayers _objectLayer, Sprite _tileSprite, TileTypes _tileType, GameState _parentState) {
		super(_objectX, _objectY, _objectWidth, _objectHeight, _objectLayer, _parentState);
		
		this.tileID = _tileID;
		this.tileSprite = _tileSprite;
		this.tileType = _tileType;
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		tileSpriteRenderer = new ObjectSpriteRenderer(this, tileSprite.spriteImage);

		addGameObjectComponent(tileSpriteRenderer);
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {}

	@Override
	public void render(GameManager _gm, Renderer _r) {}

}
