/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.states;

import java.util.ArrayList;
import java.awt.Color;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.graphics.particles.*;
import com.gugino.engine.graphics.particles.enums.ParticleShape;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.*;
import com.gugino.engine.util.RandomHelper;
import com.gugino.game.Main;
import com.gugino.game.objects.*;
import com.sun.glass.events.KeyEvent;

public class LevelOne extends GameState{
	private Player player;
	
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private int maxBlocks = 500;
	private Block prevBlock = null;



	private ParticleSystem particleSystem, particleSystem2;

	private int yChangeDelay, yChangeDelayMax = 10;

	public LevelOne(int _stateID) {
		super(_stateID);
		yChangeDelay = yChangeDelayMax;
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		player = new Player(300, 100, 64, 64, GameObjectLayers.MIDGROUND, this);

		_gm.gameObjectHandler.addGameObject("Player", player);

		particleSystem = new ParticleSystem("System_01", _gm.mouseHandler.mouseX, _gm.mouseHandler.mouseY, 0, 0, 0, -1, 400, 10000, this);
		particleSystem2 = new ParticleSystem("System_02", _gm.mouseHandler.mouseX, _gm.mouseHandler.mouseY, 0, 0, 0, -1, 400, 10000, this);
		
		_r.particleHandler.addParticleSystem(particleSystem);
		_r.particleHandler.addParticleSystem(particleSystem2);

		_r.particleHandler.disableParticleSystem(particleSystem);
		_r.particleHandler.disableParticleSystem(particleSystem2);

		generateBlocks(_gm);
	}

	private void generateBlocks(GameManager _gm){
		for (int b = 0; b <= maxBlocks; b++) {
			Block _tempBlock = null;
			if(prevBlock == null){
				_tempBlock = new Block(100, 300, 64, 64, GameObjectLayers.FARGROUND, this);
				prevBlock = _tempBlock;
			}else{
				int _blockY = 0;

				if(yChangeDelay <= 0){
					int _randY = RandomHelper.getRandomInt(-150, 150);
					_blockY = (int)prevBlock.gameObjectY + _randY;
					yChangeDelay = yChangeDelayMax;
				}else{
					int _newY = (int)prevBlock.gameObjectY;
					_blockY = _newY;
					yChangeDelay --;
				}

				_tempBlock = new Block(prevBlock.gameObjectX + 64, _blockY, 64, 64, GameObjectLayers.FARGROUND, this);
				prevBlock = _tempBlock;
			}

			_gm.gameObjectHandler.addGameObject("Block" + b, _tempBlock);

			blocks.add(_tempBlock);
		}
	}

	@Override
	public void update(GameManager _gm, double _deltaTime) {
		//Sets cameras target to the x and y of the character
		_gm.renderer.mainCamera.setCameraTarget(player.gameObjectX, player.gameObjectY);
		
		if((particleSystem != null) && (particleSystem2 != null)){
			if((_gm.renderer.particleHandler.findParticleSystemByID(particleSystem.getParticleSystemID()).isEnabled)
			&& (_gm.renderer.particleHandler.findParticleSystemByID(particleSystem2.getParticleSystemID()).isEnabled)){
				emitRandomColorParticles(_gm);

				float _dist = Distance.getDistance(player.gameObjectX, player.gameObjectY,
				 particleSystem.getSystemXPosition(), particleSystem.getSystemYPosition());
		
				if(_dist > 300){
					if(particleSystem.isEnabled){
						_gm.renderer.particleHandler.disableParticleSystem(particleSystem);
					}
				}else{
					if(!particleSystem.isEnabled){
						_gm.renderer.particleHandler.enableParticleSystem(particleSystem);
					}
				}
		
				if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_1)){
					if(!particleSystem.isEnabled){
						_gm.renderer.particleHandler.enableParticleSystem(particleSystem);
					}else if(particleSystem.isEnabled){
						_gm.renderer.particleHandler.disableParticleSystem(particleSystem);
					}
				}else if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_2)){
					if(!particleSystem2.isEnabled){
						_gm.renderer.particleHandler.enableParticleSystem(particleSystem2);
					}else if(particleSystem2.isEnabled){
						_gm.renderer.particleHandler.disableParticleSystem(particleSystem2);
					}
				}
			}
		}

		//Return to main menu
		if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			_gm.stateManager.setActiveState(Main.MENU_STATE);
		}
	}	
	
	private void emitRandomColorParticles(GameManager _gm){
		float _alpha = RandomHelper.getRandomFloat();
		float _red = RandomHelper.getRandomFloat();
		float _green = RandomHelper.getRandomFloat();
		float _blue = RandomHelper.getRandomFloat();

		Color _colorWAlpha = new Color(_red, _green, _blue, _alpha);

		int _width = RandomHelper.getRandomInt(24, 64);
		int _height = RandomHelper.getRandomInt(24, 64);

		Particle _particle = new Particle(_width, _height, ParticleShape.ROUNDED_SQUARE, _colorWAlpha);
		
		Particle _particle2 = new Particle(_width, _height, ParticleShape.CIRCLE, _colorWAlpha);


		int _yVel = -RandomHelper.getRandomInt(1, 5);
		int _xVel = 0;

		int _picked = RandomHelper.getRandomInt(0, 1);

		if(_picked == 1){
			_xVel = -RandomHelper.getRandomInt(1, 5);
		}else if(_picked == 0){
			_xVel = RandomHelper.getRandomInt(1, 5);
		}

		particleSystem2.particleStartingYVelocity = _yVel;
		particleSystem2.particleStartingXVelocity = _xVel;

		particleSystem2.particleStartingXPosition = RandomHelper.getRandomInt(8, WindowHandler.windowWidth + 900);

		particleSystem2.particleStartingYPosition = WindowHandler.windowHeight - _height - 50;

		particleSystem2.emitParticles(_particle2);

		particleSystem.particleStartingYVelocity = _yVel;
		particleSystem.particleStartingXVelocity = _xVel;

		particleSystem.particleStartingXPosition = RandomHelper.getRandomInt(10, WindowHandler.windowWidth + 400);

		particleSystem.particleStartingYPosition = WindowHandler.windowHeight - _height;

		particleSystem.emitParticles(_particle);
	}

	@Override
	public void render(GameManager _gm, Renderer _r) {
	}
	
	@Override
	public void onActive(GameManager _gm) {
		_gm.showDebugInformation = true;
		player.gameObjectX = 300;
		player.gameObjectY = 100;
		player.gameObjectXVelocity = 0;
		player.gameObjectYVelocity = 0;
	}
}
