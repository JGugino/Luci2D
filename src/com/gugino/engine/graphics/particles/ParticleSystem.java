/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.graphics.particles;

import java.util.ArrayList;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;

public class ParticleSystem {
    public ArrayList<Particle> particles = new ArrayList<Particle>();

    protected String particleSystemID;
    public int particlesLifeSpan = 0, maxParticles = 0;
    public float startingXPosition = 0, startingYPosition = 0;
    public double startingXVelocity = 0, startingYVelocity = -1;
    public boolean isEnabled = false;

    public GameState parentState;

    public ParticleSystem(String _systemID, float _startingX, float _startingY, double _startingXVel, double _startingYVel, int _particleLifeSpan, int _maxParticles, GameState _parentState){
        this.particleSystemID = _systemID;
        this.startingXPosition = _startingX;
        this.startingYPosition = _startingY;
        this.startingXVelocity = _startingXVel;
        this.startingYVelocity = _startingYVel;

        this.particlesLifeSpan = _particleLifeSpan;
        this.maxParticles = _maxParticles;

        this.parentState = _parentState;
    }

    public void emitParticles(Particle _particle){
        if (particles.size() <= maxParticles) {
            Particle _newParticle = new Particle(_particle.particleWidth, _particle.particleHeight,
                    _particle.particleShape, _particle.particleColor);
            _newParticle.particleX = startingXPosition;
            _newParticle.particleY = startingYPosition;
            _newParticle.particleLifeSpan = particlesLifeSpan;
            _newParticle.particleXVelocity = startingXVelocity;
            _newParticle.particleYVelocity = startingYVelocity;

            particles.add(_newParticle);
        }
    }    

    public void update(GameManager _gm, double _deltaTime){
        if(isEnabled){
            ArrayList<Particle> _particleToRemove = new ArrayList<Particle>();
            for (Particle _particle : particles) {
                _particle.particleX += _particle.particleXVelocity * _deltaTime;
                _particle.particleY += _particle.particleYVelocity * _deltaTime;
                if(!_particle.isDead()){
                    _particle.particleLifeSpan--;
                }else{
                    _particleToRemove.add(_particle);
                }
            }
    
            for (Particle _particle : _particleToRemove) {
                particles.remove(_particle);
            }
    
            _particleToRemove.clear();
        }
    }

    public void render(GameManager _gm, Renderer _r){
        if(isEnabled){
            for (Particle _particle : particles) {
                switch(_particle.particleShape){
                    case SQUARE:
                        _r.shapeRenderer.drawFilledRect(_particle.particleX, _particle.particleY, _particle.particleWidth,
                         _particle.particleHeight, _particle.particleColor);
                    break;
    
                    case ROUNDED_SQUARE:
                        _r.shapeRenderer.drawFilledRoundedRect(_particle.particleX, _particle.particleY, _particle.particleWidth,
                        _particle.particleHeight, 5, 5, _particle.particleColor);
                    break;
    
                    case CIRCLE:
                        _r.shapeRenderer.drawFilledCircle(_particle.particleX, _particle.particleY, _particle.particleWidth,
                        _particle.particleHeight, _particle.particleColor);
                    break;
                    
                    case IMAGE:
                    
                    break;
                }   
            }
        }
    }

    public void resetParticleSystem(){
        ArrayList<Particle> _particles = new ArrayList<Particle>();
        if(particles.size() > 0){
            for (Particle _p : particles) {
                _particles.add(_p);
            }
        
            for (Particle _p : _particles) {
                particles.remove(_p);
            }

            _particles.clear();
        }
    }

    public String getParticleSystemID(){
        return particleSystemID;
    }

}