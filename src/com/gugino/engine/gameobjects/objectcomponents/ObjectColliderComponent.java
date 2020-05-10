/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.engine.gameobjects.objectcomponents;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.util.debug.Debug;

public class ObjectColliderComponent extends GameObjectComponent{

	private ObjectGravityComponent parentGravityComponent;

	public ObjectColliderComponent(GameObject _componentParent) {
		super(GameObjectComponentTypes.COLLIDER, _componentParent);
		this.parentGravityComponent = null;
	}

	public ObjectColliderComponent(GameObject _componentParent, ObjectGravityComponent _parentGravityComponent) {
		super(GameObjectComponentTypes.COLLIDER, _componentParent);
		this.parentGravityComponent = _parentGravityComponent;
	}

	@Override
	public void componentUpdate(GameManager _gm, double _deltaTime) {
		if(!componentParent.isColliding()){
			if(parentGravityComponent != null){
				//parentGravityComponent.setIsFalling(true);
			}
		}
	}
	
	@Override
	public void componentCollisionUpdate(GameObject _collision) {
		//TODO: Finish, needs to make objects with physics component push able
		objectCollision(_collision);
	}

	private void objectCollision(GameObject _collision){
		int _offset = 0;

		//TODO: Still glitchy when colliding on top and bottom

		//GameObject Y Collision
		//Top Collision
		if (((componentParent.gameObjectY + componentParent.gameObjectHeight) >= _collision.gameObjectY)
				&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) < (_collision.gameObjectY
						+ _collision.gameObjectHeight / 4))
				&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) > _collision.gameObjectX)
				&& ((componentParent.gameObjectX) < (_collision.gameObjectX + _collision.gameObjectWidth))) {
			if (componentParent.gameObjectYVelocity > 0) {
				if (parentGravityComponent != null) {
					if (parentGravityComponent.isFalling()) {
						//parentGravityComponent.setIsFalling(false);
						//Debug.printLine("isFalling: " + parentGravityComponent.isFalling());
					}
				}

				componentParent.gameObjectY = _collision.gameObjectY - componentParent.gameObjectHeight;
				componentParent.gameObjectYVelocity = 0;
				// Debug.printLine("Contacting top - Component Parent: " +
				// componentParent.getGameObjectID());
				return;
			}
		}
		
		//Bottom Collision

		if ((componentParent.gameObjectY <= (_collision.gameObjectY + _collision.gameObjectHeight))
				&& (componentParent.gameObjectY > (_collision.gameObjectY - _collision.gameObjectHeight / 4))
				&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) > _collision.gameObjectX)
				&& ((componentParent.gameObjectX) < (_collision.gameObjectX + _collision.gameObjectWidth))) {
			if (componentParent.gameObjectYVelocity < 0) {
				componentParent.gameObjectY = _collision.gameObjectY + _collision.gameObjectHeight + _offset;
				componentParent.gameObjectYVelocity = 0;
				// Debug.printLine("Contacting bottom - Component Parent: " +
				// componentParent.getGameObjectID());
				return;
			}
		}

		//GameObject X Collision
		//Left Collision
		if (((componentParent.gameObjectX + componentParent.gameObjectWidth) >= _collision.gameObjectX)
				&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) < (_collision.gameObjectX
						+ _collision.gameObjectWidth / 4))
				&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
				&& (componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))) {
			if (componentParent.gameObjectXVelocity > 0) {
				componentParent.gameObjectX = _collision.gameObjectX - componentParent.gameObjectWidth - _offset;
				componentParent.gameObjectXVelocity = 0;
				// Debug.printLine("Contacting left - Component Parent: " +
				// componentParent.getGameObjectID());
				return;
			}
		}

		//Right Collision

		if ((componentParent.gameObjectX <= (_collision.gameObjectX + _collision.gameObjectWidth))
				&& (componentParent.gameObjectX > (_collision.gameObjectX - _collision.gameObjectWidth /4))
				&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
				&& (componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))) {
			if (componentParent.gameObjectXVelocity < 0) {
				componentParent.gameObjectX = _collision.gameObjectX + _collision.gameObjectWidth + _offset;
				componentParent.gameObjectXVelocity = 0;
				// Debug.printLine("Contacting right - Component Parent: " +
				// componentParent.getGameObjectID());
				return;
			}
		}
		}

	public void resetFalling(){
		if(parentGravityComponent != null){
			if(!parentGravityComponent.isFalling()){
				parentGravityComponent.setIsFalling(true);
				Debug.printLine("isFalling: " + parentGravityComponent.isFalling());
			}
		}
	}

	@Override
	public void componentRender(GameManager _gm, Renderer _r) {
		if(componentParent.showBoundingBox) {
			_r.getGraphics().setColor(componentParent.getBoundingBoxColor());
			_r.getGraphics().drawRect((int)componentParent.gameObjectX, (int)componentParent.gameObjectY, (int)componentParent.gameObjectWidth, (int)componentParent.gameObjectHeight);
			_r.getGraphics().setColor(Color.white);
		}
	}
}
