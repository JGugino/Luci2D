/*Created By: Gugino
*Date Created: May 8, 2020
*/
package com.gugino.engine.gameobjects.objectcomponents;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;

public class ObjectGravityComponent extends GameObjectComponent {

    protected double gravity = 0.485;
    protected double maxVelocity = 5.85;
    protected boolean isFalling = true;

    public ObjectGravityComponent(GameObject _componentParent) {
        super(GameObjectComponentTypes.GRAVITY, _componentParent);
    }

    @Override
    public void componentUpdate(GameManager _gm, double _deltaTime) {
        if(isFalling){
            objectFall(_deltaTime);
        }
    }

    private void objectFall(double _deltaTime){
        componentParent.gameObjectYVelocity += gravity;
        componentParent.gameObjectY += componentParent.gameObjectYVelocity;
        if(componentParent.gameObjectYVelocity > maxVelocity) componentParent.gameObjectYVelocity = maxVelocity;
    }

	public boolean isFalling(){
		return isFalling;
	}

	public void setIsFalling(boolean _falling){
		isFalling = _falling;
	}
}