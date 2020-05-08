/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.engine.gameobjects.objectcomponents;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.loops.Renderer;

public class GameObjectColliderComponent extends GameObjectComponent{

	public boolean showBoundingBox = true;
	
	public Color boundingBoxColor = Color.red;

	//private boolean yCollision = false, xCollision = false;

	public GameObjectColliderComponent(GameObject _componentParent) {
		super(GameObjectComponentTypes.COLLIDER, _componentParent);
	}
	
	@Override
	public void componentCollisionUpdate(GameObject _collision) {
		//TODO: Finish, needs to make objects with physics component push able
		objectCollision(_collision);
	}

	private void objectCollision(GameObject _collision){
		int _offset = 2;

		//TODO: Still glitchy when colliding on top and bottom

		//GameObject Y Collision
		//Top Collision
		if(componentParent.gameObjectYVelocity > 0){
			if(((componentParent.gameObjectY + componentParent.gameObjectHeight) >= _collision.gameObjectY)
			&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) < (_collision.gameObjectY + _collision.gameObjectHeight/4 + _offset))
			&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) > _collision.gameObjectX)
			&& ((componentParent.gameObjectX) < (_collision.gameObjectX + _collision.gameObjectWidth))) {
				componentParent.gameObjectY = _collision.gameObjectY - componentParent.gameObjectHeight;
				componentParent.gameObjectYVelocity = 0;
				//Debug.printLine("Contacting top - Component Parent: " + componentParent.getGameObjectID());
				return;
			}
		}
		
		//Bottom Collision
		if(componentParent.gameObjectYVelocity < 0){
			if((componentParent.gameObjectY <= (_collision.gameObjectY + _collision.gameObjectHeight))
			&& (componentParent.gameObjectY > (_collision.gameObjectY - _collision.gameObjectHeight/4 - _offset))
			&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) > _collision.gameObjectX)
			&& ((componentParent.gameObjectX) < (_collision.gameObjectX + _collision.gameObjectWidth))) {
				componentParent.gameObjectY = _collision.gameObjectY + _collision.gameObjectHeight + _offset;
				componentParent.gameObjectYVelocity = 0;
				//Debug.printLine("Contacting bottom - Component Parent: " + componentParent.getGameObjectID());
				return;
			}
		}

		//GameObject X Collision
		//Left Collision
		if(componentParent.gameObjectXVelocity > 0){
			if(((componentParent.gameObjectX + componentParent.gameObjectWidth) >= _collision.gameObjectX)
			&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) < (_collision.gameObjectX + _collision.gameObjectWidth/4 + _offset))
			&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
			&& (componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))){
				componentParent.gameObjectX = _collision.gameObjectX - componentParent.gameObjectWidth - _offset;
				componentParent.gameObjectXVelocity = 0;
				//Debug.printLine("Contacting left - Component Parent: " + componentParent.getGameObjectID());
				return;
			}
		}

		//Right Collision
		if(componentParent.gameObjectXVelocity < 0){
			if((componentParent.gameObjectX <= (_collision.gameObjectX + _collision.gameObjectWidth))
			&& (componentParent.gameObjectX > (_collision.gameObjectX - _collision.gameObjectWidth/4 - _offset))
			&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
			&& (componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))) {
				componentParent.gameObjectX = _collision.gameObjectX + _collision.gameObjectWidth + _offset;
				componentParent.gameObjectXVelocity = 0;
				//Debug.printLine("Contacting right - Component Parent: " + componentParent.getGameObjectID());
				return;
			}	
		}
	}

	@Override
	public void componentRender(GameManager _gm, Renderer _r) {
		if(showBoundingBox) {
			_r.getGraphics().setColor(boundingBoxColor);
			_r.getGraphics().drawRect((int)componentParent.gameObjectX, (int)componentParent.gameObjectY, (int)componentParent.gameObjectWidth, (int)componentParent.gameObjectHeight);
			_r.getGraphics().setColor(Color.white);
		}
	}
}
