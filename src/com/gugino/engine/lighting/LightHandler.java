/*Created By: Gugino
 *Date Created: Jun 24, 2020
 */
package com.gugino.engine.lighting;

import java.awt.Color;
//import java.awt.Graphics2D;
import java.util.HashMap;

import com.gugino.engine.loops.Renderer;
import com.gugino.engine.util.debug.Debug;

public class LightHandler {
	
	protected HashMap<String, BasicLight> enabledLights = new HashMap<String, BasicLight>();
	protected HashMap<String, BasicLight> disabledLights = new HashMap<String, BasicLight>();
	
	protected Color lightBackgroundColor = Color.black;
	
	public void lightRender(Renderer _r) {
		//Graphics2D _g2d = _r.getGraphics();
		
		if(!enabledLights.isEmpty()) {
			for (int _light = enabledLights.size(); _light >= 0; _light--) {
				if(((BasicLight)enabledLights.values().toArray()[_light]).isEnabled) {
					//TODO: Finish adding in lights, just need to make them render.
				}
			}
			
		}
	}
	
	
	public void addLight(String _lightID, BasicLight _light) {
		if(!enabledLights.containsKey(_lightID) && !disabledLights.containsKey(_lightID)) {
			enabledLights.put(_lightID, _light);
		}else {
			Debug.printWarning("Light with ID " + _lightID + " already exists!");
		}
	}
	
	public void removeLight(String _lightID) {
		if(enabledLights.containsKey(_lightID)) {
			enabledLights.remove(_lightID);
			return;
		}else if(disabledLights.containsKey(_lightID)) {
			disabledLights.remove(_lightID);
			return;
		}else {
			Debug.printWarning("Light with ID " + _lightID + " doesn't exist!");
			return;
		}
	}
	
	public BasicLight findLightByID(String _lightID) {
		if(enabledLights.containsKey(_lightID)) {
			return enabledLights.get(_lightID);
		}else if(disabledLights.containsKey(_lightID)) {
			return disabledLights.get(_lightID);
		}else {
			Debug.printWarning("Light with ID " + _lightID + " doesn't exist!");
			return null;
		}
	}
	
	public Color getLightBackgroundColor() {
		return lightBackgroundColor;
	}
	
	public HashMap<String, BasicLight> getEnabledLights(){
		return enabledLights;
	}
	
	public HashMap<String, BasicLight> getDisabledLights(){
		return disabledLights;
	}
	
	public void setLightBackgroundColor(Color _backgroundColor) {
		this.lightBackgroundColor = _backgroundColor;
	}
}
