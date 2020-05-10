/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.gameobjects.objectcomponents;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import java.awt.event.KeyEvent;

public class ObjectPlatformerControlsComponent extends GameObjectComponent{
    
    public final int LEFT_KEY = KeyEvent.VK_A, RIGHT_KEY = KeyEvent.VK_D, JUMP_KEY = KeyEvent.VK_SPACE;

    public float objectWalkingSpeed = 2.5f;
    public float objectJumpForce = 7.5f;

    public boolean isJumping = false;

    protected boolean hasDoubleJump = false;
    protected int jumpsRemaining, totalJumps = 2;

    public ObjectPlatformerControlsComponent(GameObject _componentParent) {
        super(GameObjectComponentTypes.MOVEMENT_MANAGER, _componentParent);
        jumpsRemaining = totalJumps;
    }

    @Override
    public void componentUpdate(GameManager _gm, double _deltaTime) {
        //Jumping
        if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_SPACE)) {
            if(hasDoubleJump){
                if(jumpsRemaining > 0){
                    componentParent.gameObjectYVelocity = (float)(-objectJumpForce * _deltaTime);
                    componentParent.gameObjectY += componentParent.gameObjectYVelocity;
                    jumpsRemaining--;
                }
            }else{
                if(!isJumping){
                    isJumping = true;
                    componentParent.gameObjectYVelocity = (float)(-objectJumpForce * _deltaTime);
                    componentParent.gameObjectY += componentParent.gameObjectYVelocity;
                }
            }
        }

        //Horizontal Movement
        if(_gm.keyboardHandler.isKeyDown(LEFT_KEY)){
            componentParent.gameObjectXVelocity = (float)(-objectWalkingSpeed *_deltaTime);
            componentParent.gameObjectX += componentParent.gameObjectXVelocity;
        }else if(_gm.keyboardHandler.isKeyDown(RIGHT_KEY)){
            componentParent.gameObjectXVelocity = (float)(objectWalkingSpeed *_deltaTime);
            componentParent.gameObjectX += componentParent.gameObjectXVelocity;
        }else{
            componentParent.gameObjectXVelocity = 0;
        }
    }

    public void hasDoubleJump(boolean _hasDoubleJump){
        hasDoubleJump = _hasDoubleJump;
    }

    public void resetRemainingJumps(){
        if(jumpsRemaining < totalJumps){
            jumpsRemaining = totalJumps;
        }
    }
}