/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.states;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;

public abstract class GameState {
	//Game State's ID
	protected int stateID;

	//Constructor for Game States
	public GameState(int _stateID, GameManager _gm) {
		//sets the game states id to the set id
		this.stateID = _stateID;
	}

	//Runs when state gets added to StateManager
	//Runs before first update frame
	public abstract void start(GameManager _gm);
	//Runs every frame
	public abstract void update(GameManager _gm, double _deltaTime);
	//Runs every frame if shouldRender in GameManager is true
	public abstract void render(GameManager _gm, Renderer _r);
	
	//Getter for getting the states ID
	public int getStateID() {
		return this.stateID;
	}
}
