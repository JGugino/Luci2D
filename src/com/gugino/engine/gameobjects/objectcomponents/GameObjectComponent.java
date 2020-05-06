/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.gameobjects.objectcomponents;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.loops.Renderer;

public abstract class GameObjectComponent {

	public GameObjectComponentTypes componentType;
	public GameObject componentParent;
	
	public GameObjectComponent(GameObjectComponentTypes _componentType, GameObject _componentParent) {
		this.componentType = _componentType;
		this.componentParent = _componentParent;
	}
	
	public void componentUpdate(GameManager _gm, double _deltaTime) {}
	public void componentCollisionUpdate(GameObject _collision) {}
	public void componentRender(GameManager _gm, Renderer _r) {}
}
