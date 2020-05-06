/*Created By: Gugino
 *Date Created: May 6, 2020
 */
package com.gugino.engine.gameobjects.objectcomponents;

import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.GameObject;
import com.gugino.engine.gameobjects.enums.GameObjectComponentTypes;
import com.gugino.engine.gameobjects.interfaces.IKillable;
import com.gugino.engine.loops.Renderer;

public class GameObjectHealthManagerComponent extends GameObjectComponent{
	
	public float currentHealth;
	public float maxHealth;
	
	protected boolean showHealthBar = false;
	
	public int healthBarWidth;
	public int healthBarHeight;
	
	public Color healthBarBackgroundColor = Color.darkGray;
	public Color healthBarFillColor = Color.green;
	
	protected IKillable killAction;
	
	public GameObjectHealthManagerComponent(GameObject _componentParent, float _maxHealth) {
		super(GameObjectComponentTypes.HEALTH_MANAGER, _componentParent);
		this.maxHealth = _maxHealth;
		this.currentHealth = _maxHealth;
		this.showHealthBar = false;
	}
	
	public GameObjectHealthManagerComponent(GameObject _componentParent, float _maxHealth, boolean _showHealthBar) {
		super(GameObjectComponentTypes.HEALTH_MANAGER, _componentParent);
		this.maxHealth = _maxHealth;
		this.currentHealth = _maxHealth;
		this.showHealthBar = _showHealthBar;
	}
	
	@Override
	public void componentRender(GameManager _gm, Renderer _r) {
		if(showHealthBar) {
			healthBarWidth = componentParent.gameObjectWidth - 10;
			healthBarHeight = componentParent.gameObjectHeight / 5;
			
			//Background
			_r.shapeRenderer.drawFilledRect(componentParent.gameObjectX + 5, componentParent.gameObjectY - healthBarHeight - 5, healthBarWidth, healthBarHeight, healthBarBackgroundColor);
			//Fill
			_r.shapeRenderer.drawFilledRect(componentParent.gameObjectX + 5, componentParent.gameObjectY - healthBarHeight - 5, (int)((currentHealth/maxHealth) * healthBarWidth), healthBarHeight, healthBarFillColor);
		}
	}
	
	public float takeHealth(int _amount) {
		float _final = currentHealth - _amount;
		
		if(_final > 0) {
			return currentHealth = _final;
		}else {
			if(killAction != null) {
				killAction.kill();
			}
			return currentHealth = 0;
		}
	}
	
	public float addHealth(int _amount) {
		float _final = currentHealth + _amount;
		if(_final <= maxHealth) {
			return currentHealth = _final;
		}else {
			return currentHealth = maxHealth;
		}
	}

	public void addKillAction(IKillable _killAction) {
		this.killAction = _killAction;
	}
	
	public float resetHealth() {
		return currentHealth = maxHealth;
	}
	
}
