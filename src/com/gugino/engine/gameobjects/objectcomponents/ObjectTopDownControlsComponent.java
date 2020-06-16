/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.gameobjects.objectcomponents;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.gameobjects.enums.TopDownControlLayouts;

import java.awt.event.KeyEvent;


public class ObjectTopDownControlsComponent extends GameObjectComponent {

    public final int UP_KEY = KeyEvent.VK_W, DOWN_KEY = KeyEvent.VK_S, LEFT_KEY = KeyEvent.VK_A, RIGHT_KEY = KeyEvent.VK_D;

    public float objectWalkingSpeed = 2.5f;

    public TopDownControlLayouts controlsLayout;
    
    public ObjectTopDownControlsComponent(GameObject _componentParent, TopDownControlLayouts _layout) {
        super(GameObjectComponentTypes.TOP_DOWN_MOVEMENT_MANAGER, _componentParent);
        this.controlsLayout = _layout;
    }

    @Override
    public void componentUpdate(GameManager _gm, double _deltaTime) {
    	
    	if(controlsLayout == TopDownControlLayouts.VERTICAL || controlsLayout == TopDownControlLayouts.BOTH) {
            //Vertical Movement
            if(_gm.keyboardHandler.isKeyDown(UP_KEY)){
                componentParent.gameObjectYVelocity = (float)(-objectWalkingSpeed *_deltaTime);
                componentParent.gameObjectY += componentParent.gameObjectYVelocity;
            }else if(_gm.keyboardHandler.isKeyDown(DOWN_KEY)){
                componentParent.gameObjectYVelocity = (float)(objectWalkingSpeed *_deltaTime);
                componentParent.gameObjectY += componentParent.gameObjectYVelocity;
            }else{
                componentParent.gameObjectYVelocity = 0;
            }	
    	}
    	
    	if(controlsLayout == TopDownControlLayouts.HORIZONTAL || controlsLayout == TopDownControlLayouts.BOTH) {
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
    }
}