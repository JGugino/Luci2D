/*Created By: Gugino
 *Date Created: Apr 24, 2020
 */
package com.gugino.engine.input;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MouseHandler extends MouseAdapter{
	
	//Long variable to store mouse x and y position
	public long mouseX, mouseY;
	
	//HashMap for pressed mouse buttons
	private HashMap<String, Dimension> pressedButton = new HashMap<String, Dimension>();
	
	//HashMap for previous pressed mouse buttons
	private HashMap<String, Dimension> prevPressedButton = new HashMap<String, Dimension>();
	
	//Prefix for button when it gets added to the HashMap
	private String buttonPrefix = "button_";
	
	//Updates mouse x/y positions when mouse moves
	public void mouseMoved(MouseEvent _event) {
		//checks to make sure not updating position unnecessarily and then updates x and y positions
		if(mouseX != _event.getX()) {
			mouseX = _event.getX();	
		}
		
		if(mouseY != _event.getY()) {
			mouseY = _event.getY();	
		}
	}
	
	//Triggered when mouse button is pressed
	public void mousePressed(MouseEvent _event) {
		//Checks thats button isn't already in button pushed list and checks that it hasn't already been pressed(To check when a button is first pressed)
		if(!pressedButton.containsKey(buttonPrefix+_event.getButton()) && !prevPressedButton.containsKey(buttonPrefix+_event.getButton())) {
			//Puts the pressed button into the pressed button HashMap
			pressedButton.put(buttonPrefix+_event.getButton(), new Dimension(_event.getButton(), _event.getButton()));
			//Puts the pressed button into the previous pressed button HashMap
			prevPressedButton.put(buttonPrefix+_event.getButton(), new Dimension(_event.getButton(), _event.getButton()));
		}//Triggered after a button get pressed at least once
		else{
			//Puts the pressed into the pressed button HashMap
			pressedButton.put(buttonPrefix+_event.getButton(), new Dimension(_event.getButton(), _event.getButton()));
		}
		
	}
	
	//Triggered when mouse button is released
	public void mouseReleased(MouseEvent _event) {
		//Removes button from pressed buttons HashMap
		pressedButton.remove(buttonPrefix+_event.getButton());
		//Puts the pressed button into the previous pressed button HashMap
		prevPressedButton.put(buttonPrefix+_event.getButton(), new Dimension(_event.getButton(), _event.getButton()));
	}

	//Method for checking if mouse button is down
	public boolean isMouseButtonDown(int _buttonCode) {
		//checks if the button that is being checked is inside the pressed buttons HashMap
		if(pressedButton.containsKey(buttonPrefix+_buttonCode)) {
			//If it is return true
			return true;
		}else {
			//If it's not return false
			return false;	
		}
	}
	
	//Method for checking if mouse button is pressed
	public boolean isMouseButtonPressed(int _buttonCode) {
		//Checks if the button is in the pressed buttons HashMap and in the previous buttons pressed HashMap
		if(pressedButton.containsKey(buttonPrefix+_buttonCode) && prevPressedButton.containsKey(buttonPrefix+_buttonCode)) {
			//Remove button from previous pressed buttons HashMap
			prevPressedButton.remove(buttonPrefix+_buttonCode);
			//Return with a value of true
			return true;
		}else {
			//If it not in either return false
			return false;	
		}
	}
	
	//Method for checking if mouse button is released
	public boolean isMouseButtonReleased(int _buttonCode) {
		//Checks if the button is not in the pressed buttons HashMap and is in the previous pressed buttons HashMap
		if(!pressedButton.containsKey(buttonPrefix+_buttonCode) && prevPressedButton.containsKey(buttonPrefix+_buttonCode)) {
			//Remove the button from the previous pressed buttons HashMap
			prevPressedButton.remove(buttonPrefix+_buttonCode);
			//Return true
			return true;
		}//if its not released
		else {
			//Return false
			return false;	
		}
	}
}
