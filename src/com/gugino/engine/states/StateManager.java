/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.states;

import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.graphics.renderer.GraphicsRenderer;

public class StateManager {
	
	private GameManager gameManager;
	
	public static GameState activeState;
	
	private HashMap<Integer, GameState> enabledStates = new HashMap<Integer, GameState>();
	
	private HashMap<Integer, GameState> disabledStates = new HashMap<Integer, GameState>();
	
	public StateManager(GameManager _gm) {
		gameManager = _gm;
	}
	
	//Update method for StateManager
	public void update(GameManager _gm, double _deltaTime) {
		//Checks to make sure there are enabled states
		if(!enabledStates.isEmpty()) {
			//Loops through all the enabled states
			for(GameState _state : enabledStates.values()) {
				//Runs update method for currently selected state
				_state.update(_gm, _deltaTime);
			}
		}
	}
	
	//Render method for StateManager
	public void render(GameManager _gm, GraphicsRenderer _gr) {
		//Checks to make sure there are enabled states
		if(!enabledStates.isEmpty()) {
			//Loops through all the enabled states
			for(GameState _state : enabledStates.values()) {
				//Runs render method for currently selected state
				_state.render(_gm, _gr);
			}
		}
	}
	
	public void addState(int _id, GameState _state) {
		//Checks to make sure state doesn't exist in either enabled or disabled states
		if(!enabledStates.containsKey(_id) && !disabledStates.containsKey(_id)) {
			//Adds the newly added state to the disabled states HashMap
			disabledStates.put(_id, _state);
		}
	}
	
	
	public void removeState(int _id) {
		//Checks if the state to be removed is in the disabled states
		if(disabledStates.containsKey(_id)) {
			//removes the state from the HashMap
			disabledStates.remove(_id);
		}//Checks if the state to be removed is in the enabled states if its not in the disabled states
		else if(enabledStates.containsKey(_id)){
			//removes the state from the HashMap
			enabledStates.remove(_id);
		}
	}
	
	//Method to get state by ID
	public void setActiveState(int _id) {
		//Checks if the state is in the disabled states
		if(disabledStates.containsKey(_id)) {
			//checks if the activeState is not null
			if(activeState != null) {
				//removes current state from the enabled states
				enabledStates.remove(activeState.stateID);	
			}
			//sets the current state equal to the state inside the disabled states HashMap
			activeState = disabledStates.get(_id);
			//Puts the current state into the enabled states HashMap
			enabledStates.put(activeState.stateID, activeState);
			
			activeState.start(gameManager);
			return;
		}else {
			System.err.println("State doesnt exist or already active, state-id: " + _id );
		}
	}
	
	//Method to get state by ID
	public GameState getStateByID(int _id) {
		//Checks if state is in enabled states HashMap
		if(enabledStates.containsKey(_id)) {
			//Returns the state inside the enabled states HashMap
			return enabledStates.get(_id);
		}//Checks if state is in disabled states HashMap
		else if(disabledStates.containsKey(_id)) {
			//Returns the state inside the disabled states HashMap
			return disabledStates.get(_id);
		}else {
			//Returns null and prints out error that state couldn't be found
			System.err.println("State not found - " + _id);
			return null;
		}
	}
	
	//Returns the HashMap of enabled states
	public HashMap<Integer, GameState> getEnabledStates(){
		return enabledStates;
	}
	
	//Returns the HashMap of disabled states
	public HashMap<Integer, GameState> getDisabledStates(){
		return disabledStates;
	}
}
