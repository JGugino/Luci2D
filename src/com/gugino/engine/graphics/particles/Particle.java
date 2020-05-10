/*Created By: Gugino
*Date Created: May 09, 2020
*/

package com.gugino.engine.graphics.particles;

import java.awt.Color;

import com.gugino.engine.graphics.particles.enums.ParticleShape;

public class Particle {
    
    public int particleWidth, particleHeight, particleLifeSpan;
    public float particleX, particleY;
    public double particleXVelocity, particleYVelocity;
    public ParticleShape particleShape;
    public Color particleColor;

    public Particle(int _particleWidth, int _particleHeight, ParticleShape _particleShape, Color _particleColor){
        this.particleWidth = _particleWidth;
        this.particleHeight = _particleHeight;
        this.particleShape = _particleShape;
        this.particleColor = _particleColor;
    }

    public boolean isDead(){
        return particleLifeSpan <= 0;
    }
}