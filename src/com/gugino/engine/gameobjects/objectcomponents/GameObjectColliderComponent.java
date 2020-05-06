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
	
	private boolean yCollision = false, xCollision = false;
	
	public GameObjectColliderComponent(GameObject _componentParent) {
		super(GameObjectComponentTypes.COLLIDER, _componentParent);
	}
	
	@Override
	public void componentCollisionUpdate(GameObject _collision) {
		//TODO: Finish, needs to make objects seem solid and if has physics object make objects push able
		//(Kinda works still needs tweaking, favors y collision over x)

		//GameObject Y Collision
		//Top Collision
		if(!xCollision) {
			if(((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
					&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) < (_collision.gameObjectY + _collision.gameObjectHeight/4))
					&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) > _collision.gameObjectX)
					&& ((componentParent.gameObjectX) < (_collision.gameObjectX + _collision.gameObjectWidth))) {
				yCollision = true;
				componentParent.gameObjectY = _collision.gameObjectY - componentParent.gameObjectHeight;
				System.out.println("Contacting top - Component Parent: " + componentParent.getGameObjectID());
				return;
			}else {
				if(yCollision) {
					yCollision = false;
					return;
				}
			}
			//Bottom Collision
			if((componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))
					&& (componentParent.gameObjectY > (_collision.gameObjectY - _collision.gameObjectHeight/4))
					&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) > _collision.gameObjectX)
					&& ((componentParent.gameObjectX) < (_collision.gameObjectX + _collision.gameObjectWidth))) {
				yCollision = true;
				componentParent.gameObjectY = _collision.gameObjectY + _collision.gameObjectHeight;
				System.out.println("Contacting bottom - Component Parent: " + componentParent.getGameObjectID());
				return;
			}else {
				if(yCollision) {
					yCollision = false;
					return;
				}
			}
		}
		//GameObject X Collision
		//Left Collision
		//Makes sures colliding component is on the left side
		if(!yCollision) {
			if(((componentParent.gameObjectX + componentParent.gameObjectWidth) >= _collision.gameObjectX)
					&& ((componentParent.gameObjectX + componentParent.gameObjectWidth) < (_collision.gameObjectX + _collision.gameObjectWidth/4))
					//Makes sure colliding component isn't above or below the object their colliding with
					&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
					&& (componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))){
				xCollision = true;
				componentParent.gameObjectX = _collision.gameObjectX - componentParent.gameObjectWidth;
				System.out.println("Contacting left - Component Parent: " + componentParent.getGameObjectID());
				return;
			}else {
				if(xCollision) {
					xCollision = false;
					return;
				}
			}
			
			//Right Collision
			if((componentParent.gameObjectX <= (_collision.gameObjectX + _collision.gameObjectWidth))
					&& (componentParent.gameObjectX > (_collision.gameObjectX - _collision.gameObjectWidth/4))
					&& ((componentParent.gameObjectY + componentParent.gameObjectHeight) > _collision.gameObjectY)
					&& (componentParent.gameObjectY < (_collision.gameObjectY + _collision.gameObjectHeight))) {
				xCollision = true;
				componentParent.gameObjectX = _collision.gameObjectX + _collision.gameObjectWidth;
				System.out.println("Contacting right - Component Parent: " + componentParent.getGameObjectID());
				return;
			}else {
				if(xCollision) {
					xCollision = false;
					return;
				}
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
