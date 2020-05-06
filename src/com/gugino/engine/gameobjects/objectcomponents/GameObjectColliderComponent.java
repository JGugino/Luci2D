/*Created By: Gugino
 *Date Created: May 5, 2020
 */
package com.gugino.engine.gameobjects.objectcomponents;

import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;

public class GameObjectColliderComponent extends GameObjectComponent{

	public GameObjectColliderComponent(GameObject _componentParent) {
		super(GameObjectComponentTypes.COLLIDER, _componentParent);
	}
	
	@Override
	public void componentCollisionUpdate(GameObject _collision) {
		//TODO: Finish, needs to make objects seem solid and if has physics object make objects push able
	}
}
