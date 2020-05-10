/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.graphics.particles;

import java.util.ArrayList;

import com.gugino.engine.GameManager;
import com.gugino.engine.loops.Renderer;

public class ParticleSystem {
    public ArrayList<Particle> particles = new ArrayList<Particle>();

    public int particlesLifeSpan = 5, maxParticles = 50;
    public float startingXPosition = 0, startingYPosition = 0;
    public double startingXVelocity = 0, startingYVelocity = -1;

    public void emitParticles(Particle _particle){
        if (particles.size() < maxParticles) {
            for (int _p = 0; _p < maxParticles; _p++) {
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
    }

    public void update(GameManager _gm, double _deltaTime){
        for (Particle _particle : particles) {
            _particle.particleX += _particle.particleXVelocity * _deltaTime;
            _particle.particleY += _particle.particleYVelocity * _deltaTime;
            _particle.particleLifeSpan-= _deltaTime;
            if(_particle.isDead()){
                particles.remove(_particle);
            }
        }
    }

    public void render(GameManager _gm, Renderer _r){
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
            }   
        }
    }

}