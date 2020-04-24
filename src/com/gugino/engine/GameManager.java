/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine;

import com.gugino.engine.abstractinterfacers.Game;
import com.gugino.engine.gameloops.RenderLoop;
import com.gugino.engine.gameloops.UpdateLoop;
import com.gugino.engine.graphics.GraphicsRenderer;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.states.StateManager;

public class GameManager implements Runnable{
	
	//Booleans for if the game is running and if it should render this frame
	public boolean isRunning = false, shouldRender = false;
	
	//WindowHandler object
	public WindowHandler windowHandler;
	
	//GraphicsRenderer object
	public GraphicsRenderer graphicsRenderer;
	
	//StateManager object
	public StateManager stateManager;
	
	//Object of the abstract class Game
	public Game currentGame;
	
	//Object to the games update loop
	private UpdateLoop updateLoop;
	
	//Object to the games render loop
	private RenderLoop renderLoop;
	
	//Games main thread
	private Thread mainThread;
	
	//Constructor for GameManager which takes in the current abstract game created and, a width and height for the window and uses a default title
	public GameManager(int _windowWidth, int _windowHeight, Game _game) {
		//Creates the game window using default title and defined width/height
		windowHandler = new WindowHandler(WindowHandler.DEFAULT_WINDOW_TITLE, _windowWidth, _windowHeight);
		
		//Sets the created game to the currentGame object
		currentGame = _game;
		
		//Runs start method
		start();
	}
	
	//Constructor for GameManager which takes the current abstract game created, a string for the window title and a width and height for the window
	public GameManager(String _windowTitle, int _windowWidth, int _windowHeight, Game _game) {
		//Creates the game window using defined title, and width/height
		windowHandler = new WindowHandler(_windowTitle, _windowWidth, _windowHeight);
		
		//Sets the created game to the currentGame object
		currentGame = _game;
		
		//Runs start method
		start();
	}
	
	public synchronized void start() {
		
		//Creates the main thread and sets this class as the target
		mainThread = new Thread(this);
		
		//Creates new instance of the GraphicsRenderer
		graphicsRenderer = new GraphicsRenderer();
		
		stateManager = new StateManager(this);
		
		//Creates new instance of UpdateLoop
		updateLoop = new UpdateLoop();
		
		//Creates new instance of RenderLoop
		renderLoop = new RenderLoop();
		
		//Starts the main thread
		mainThread.start();
	}
	
	public synchronized void stop() {
		try {
			//Stops the main thread
			mainThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Runs when main thread starts
	@Override
	public void run() {
		//Sets thats the game is running is equal to true
		isRunning = true;
		
		//Starts the update loop
		updateLoop.start(this, graphicsRenderer);
	}

	//Getter to access the render loop
	public RenderLoop getRenderLoop() {
		return renderLoop;
	}
}
