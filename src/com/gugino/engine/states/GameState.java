/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.states;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;

public abstract class GameState {
	//Game State's ID
	protected int stateID;

	protected boolean startRan = false;
	
	//Constructor for Game States
	public GameState(int _stateID) {
		//sets the game states id to the set id
		this.stateID = _stateID;
	}

	//Runs when state gets set to active for the first time
	public abstract void start(GameManager _gm, Renderer _r);
	
	//Runs every frame
	public abstract void update(GameManager _gm, double _deltaTime);
	
	//Runs every frame if shouldRender in GameManager is true
	public abstract void render(GameManager _gm, Renderer _r);
	
	//Runs whenever the state gets set to active
	public void onActive(GameManager _gm) {}
	
	//Runs whenever the state is removed from active
	public void onDisable(GameManager _gm){}

	//Getter for getting the states ID
	public int getStateID() {
		return this.stateID;
	}
}
