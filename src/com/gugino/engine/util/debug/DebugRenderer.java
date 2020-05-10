/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.util.debug;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;
import java.awt.Color;

public class DebugRenderer {
    
    public void render(GameManager _gm, Renderer _r){
        //Draws the frames per second to the screen
        
        Color _fontColor = new Color(0, 0, 0, 0.65f);

		_r.fontRenderer.drawString("FPS: " + _gm.windowHandler.fps, 5, 20, _fontColor);
        _r.fontRenderer.drawString("Frame Time (milli):" + _gm.windowHandler.frameTimeMilliSec, 5, 40, _fontColor);
        _r.fontRenderer.drawString("Mouse Position (X/Y): " + _gm.mouseHandler.mouseX + "/" + _gm.mouseHandler.mouseY, 5, 60, _fontColor);
        _r.fontRenderer.drawString("GameObjects (Enabled/Disabled): " + _gm.gameObjectHandler.getEnabledGameObjects().size() + "/" + _gm.gameObjectHandler.getDisabledGameObjects().size(), 5, 80, _fontColor);
        _r.fontRenderer.drawString("UI Objects (Enabled/Disabled): " + _r.canvas.getEnabledUIObjects().size() + "/" + _r.canvas.getDisabledUIObjects().size(), 5, 100, _fontColor);
        _r.fontRenderer.drawString("Game States (Enabled/Disabled): " + _gm.stateManager.getEnabledStates().size() + "/" + _gm.stateManager.getDisabledStates().size(), 5, 120, _fontColor);
        _r.fontRenderer.drawString("Particle Systems (Enabled/Disabled): " + _r.particleHandler.getEnabledParticleSystems().size() + "/" + _r.particleHandler.getDisabledParticleSystems().size(), 5, 140, _fontColor);
        _r.fontRenderer.drawString("Active Particles: " + _r.particleHandler.calculateTotalParticles(), 5, 160, _fontColor);
        _r.fontRenderer.drawString("Camera Position (X/Y): " + Math.round(_r.mainCamera.cameraX) + "/" + Math.round(_r.mainCamera.cameraY), 5, 180, _fontColor);
        _r.fontRenderer.drawString("Camera Target (X/Y - shouldFollow): " + Math.round(_r.mainCamera.getCameraTargetX()) + "/" + Math.round(_r.mainCamera.getCameraTargetY()) + " - " + _r.mainCamera.cameraSettings.shouldFollow, 5, 200, _fontColor);
    }
}