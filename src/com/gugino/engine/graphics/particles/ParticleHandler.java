/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.graphics.particles;

import java.util.ArrayList;
import java.util.HashMap;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.StateManager;
import com.gugino.engine.util.debug.Debug;

public class ParticleHandler {
    
    protected HashMap<String, ParticleSystem> enabledParticleSystems = new HashMap<String, ParticleSystem>();
    protected HashMap<String, ParticleSystem> disabledParticleSystems = new HashMap<String, ParticleSystem>();

    public void update(GameManager _gm, double _deltaTime){
        if(!enabledParticleSystems.isEmpty()){
            for (ParticleSystem _system : enabledParticleSystems.values()) {
                _system.update(_gm, _deltaTime);
            }
        }
    }

    public void render(GameManager _gm, Renderer _r){
        if(!enabledParticleSystems.isEmpty()){
            for (ParticleSystem _system : enabledParticleSystems.values()) {
                _system.render(_gm, _r);
            }
        }
    }

    public void addParticleSystem(ParticleSystem _system){
        if(!enabledParticleSystems.containsKey(_system.particleSystemID) 
        && !disabledParticleSystems.containsKey(_system.particleSystemID)){
            enabledParticleSystems.put(_system.particleSystemID, _system);
            _system.isEnabled = true;
        }else{
            Debug.printError("Particle System - " + _system.particleSystemID + " already exists!");
        }
    }

    public void removeParticleSystem(ParticleSystem _system){
        if(enabledParticleSystems.containsKey(_system.particleSystemID)){
            enabledParticleSystems.remove(_system.particleSystemID, _system);
            _system.isEnabled = false;
        }else{
            if(disabledParticleSystems.containsKey(_system.particleSystemID)){
                disabledParticleSystems.remove(_system.particleSystemID, _system);
                _system.isEnabled = false;
            }else{
                Debug.printError("Particle System - " + _system.particleSystemID );
            }
        }
    }

    public void  enableParticleSystem(ParticleSystem _system){
        if(!enabledParticleSystems.containsKey(_system.particleSystemID) && disabledParticleSystems.containsKey(_system.particleSystemID)){
            enabledParticleSystems.put(_system.particleSystemID, _system);
            disabledParticleSystems.remove(_system.particleSystemID, _system);
            _system.resetParticleSystem();
            _system.isEnabled = true;
        }else{
            Debug.printError("Particle System - " + _system.particleSystemID + "doesn't exist or is already enabled");
        }
    }

    public void  disableParticleSystem(ParticleSystem _system){
        if(enabledParticleSystems.containsKey(_system.particleSystemID) && !disabledParticleSystems.containsKey(_system.particleSystemID)){
            disabledParticleSystems.put(_system.particleSystemID, _system);
            enabledParticleSystems.remove(_system.particleSystemID, _system);
            _system.isEnabled = false;
            _system.resetParticleSystem();
        }else{
            Debug.printError("Particle System - " + _system.particleSystemID + "doesn't exist or is already disabled");
        }
    }

	public void updateParticleSystemsForStateSwitch() {
		ArrayList<ParticleSystem> _systemsToDisable = new ArrayList<ParticleSystem>();
		ArrayList<ParticleSystem> _systemsToEnable = new ArrayList<ParticleSystem>();
		
		for(ParticleSystem _enabledSystem : enabledParticleSystems.values()) {
			if(_enabledSystem.parentState.getStateID() != StateManager.activeState.getStateID()) {
				_systemsToDisable.add(_enabledSystem);
			}
		}
		
		for(ParticleSystem _disabledSystem : disabledParticleSystems.values()) {
			if(_disabledSystem.parentState.getStateID() == StateManager.activeState.getStateID()) {
				_systemsToEnable.add(_disabledSystem);
			}
		}
		
		for(ParticleSystem _objectToDisable : _systemsToDisable) {
			disableParticleSystem(_objectToDisable);
		}
		
		for(ParticleSystem _objectToEnable : _systemsToEnable) {
			enableParticleSystem(_objectToEnable);
		}
	}

    public int calculateTotalParticles(){
        int _tempValue = 0;
        for (ParticleSystem _system : enabledParticleSystems.values()) {
            _tempValue += _system.particles.size();
        }

        return _tempValue;
    }

    public ParticleSystem findParticleSystemByID(String _systemID){
        if(enabledParticleSystems.containsKey(_systemID)){
            return enabledParticleSystems.get(_systemID);
        }else{
            if(disabledParticleSystems.containsKey(_systemID)){
                return disabledParticleSystems.get(_systemID);
            }else{
                Debug.printError("Particle System - " + _systemID + " doesn't exist");
                return null;
            }
        }
    }

    public HashMap<String, ParticleSystem> getEnabledParticleSystems(){
        return enabledParticleSystems;
    }

    public HashMap<String, ParticleSystem> getDisabledParticleSystems(){
        return disabledParticleSystems;
    }

}