/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.game.states;

import java.util.ArrayList;

import com.gugino.engine.GameManager;
import com.gugino.engine.gameobjects.enums.GameObjectLayers;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.states.GameState;
import com.gugino.engine.util.RandomHelper;
import com.gugino.game.Main;
import com.gugino.game.objects.Block;
import com.gugino.game.objects.Player;
import com.sun.glass.events.KeyEvent;

public class LevelOne extends GameState{
	private Player player;
	
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private int maxBlocks = 500;
	private Block prevBlock = null;

	private int yChangeDelay, yChangeDelayMax = 10;

	public LevelOne(int _stateID) {
		super(_stateID);
		yChangeDelay = yChangeDelayMax;
	}

	@Override
	public void start(GameManager _gm, Renderer _r) {
		player = new Player(300, 100, 64, 64, GameObjectLayers.MIDGROUND, this);
	
		generateBlocks(_gm);

		_gm.gameObjectHandler.addGameObject("Player", player);
	}

	private void generateBlocks(GameManager _gm){
		for (int b = 0; b <= maxBlocks; b++) {
			Block _tempBlock = null;
			if(prevBlock == null){
				_tempBlock = new Block(100, 300, 32, 32, GameObjectLayers.FARGROUND, this);
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

				_tempBlock = new Block(prevBlock.gameObjectX + 32, _blockY, 32, 32, GameObjectLayers.FARGROUND, this);
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
		
		//Return to main menu
		if(_gm.keyboardHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			_gm.stateManager.setActiveState(Main.MENU_STATE);
		}
	}	
	
	@Override
	public void render(GameManager _gm, Renderer _r) {
	}
	
	@Override
	public void onActive(GameManager _gm) {
		_gm.showDebugInformation = false;
	}
}
