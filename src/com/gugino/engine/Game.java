/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine;

import java.awt.event.WindowEvent;

import com.gugino.engine.loops.Renderer;

public abstract class Game {
	//Runs before first update frame
	public abstract void start(GameManager _gm, Renderer _r);
	//Runs every frame
	public abstract void update(GameManager _gm, double _deltaTime);
	//Runs every frame that it should render (Determined by shouldRender boolean in GameManager class)
	public abstract void render(GameManager _gm, Renderer _r);
	
	public void onWindowClose(WindowEvent e) {}
}
