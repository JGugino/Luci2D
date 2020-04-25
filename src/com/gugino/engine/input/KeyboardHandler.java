/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.input;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyboardHandler extends KeyAdapter{
	//HashMap for the keys that are pressed
	private HashMap<String, Dimension> keyPressed = new HashMap<String, Dimension>();
	
	//HashMap for previously pressed keys
	private HashMap<String, Dimension> prevPressedKeys = new HashMap<String, Dimension>();
	
	//Prefix for key when stored in HashMap
	private String keyPrefix = "key_";
	
	//Triggered when a keyboard button is pressed
	public void keyPressed(KeyEvent _event) {
		//Checks if the key isn't in pressed keys HashMap and isn't in the previous pressed keys HashMap(To check for when a key is pressed for the first time)
		if(!keyPressed.containsKey(keyPrefix + _event.getKeyCode()) && !prevPressedKeys.containsKey(keyPrefix + _event.getKeyCode())) {
			//Puts the key into the pressed keys HashMap
			keyPressed.put(keyPrefix + _event.getKeyCode(), new Dimension(_event.getKeyCode(), _event.getKeyCode()));
			//Puts the key into the previously pressed keys HashMap
			prevPressedKeys.put(keyPrefix + _event.getKeyCode(), new Dimension(_event.getKeyCode(), _event.getKeyCode()));
		}//triggered if key has been pressed at least once already
		else {
			//Put the key into the pressed keys HashMap
			keyPressed.put(keyPrefix + _event.getKeyCode(), new Dimension(_event.getKeyCode(), _event.getKeyCode()));
		}
	}
	
	//Triggered when a keyboard key is released
	public void keyReleased(KeyEvent _event) {
		//Removes the key from the pressed keys HashMap
		keyPressed.remove(keyPrefix + _event.getKeyCode());
		//Puts the key into the previous pressed keys HashMap
		prevPressedKeys.put(keyPrefix + _event.getKeyCode(), new Dimension(_event.getKeyCode(), _event.getKeyCode()));
	}
	
	//Method to check if a key is down
	public boolean isKeyDown(int _keyCode) {
		//Checks if key is inside the keys pressed HashMap
		if(keyPressed.containsKey(keyPrefix + _keyCode)) {
			//Returns true if its in there
			return true;
		}else {
			//Returns false if its not in there
			return false;	
		}
	}
	
	//Method to check if a key is pressed
	public boolean isKeyPressed(int _keyCode) {
		//Checks if key is in the pressed keys and previous pressed keys HashMaps
		if(keyPressed.containsKey(keyPrefix + _keyCode) && prevPressedKeys.containsKey(keyPrefix + _keyCode)) {
			//Removes the key from the previous pressed keys HashMap
			prevPressedKeys.remove(keyPrefix + _keyCode);
			//Return true if key is in both
			return true;
		}else {
			//Return false if its not
			return false;	
		}
	}
	
	//Method to check if a key is released
	public boolean isKeyReleased(int _keyCode) {
		//Checks if key is in previous pressed keys but not in pressed keys
		if(!keyPressed.containsKey(keyPrefix + _keyCode) && prevPressedKeys.containsKey(keyPrefix + _keyCode)) {
			//Remove key from previous pressed keys
			prevPressedKeys.remove(keyPrefix + _keyCode);
			//Return true if key was in previous pressed keys HashMap
			return true;
		}else {
			//Return false if its not
			return false;	
		}
	}
}
