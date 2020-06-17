/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine;

import com.gugino.engine.gameobjects.GameObjectHandler;
import com.gugino.engine.graphics.WindowHandler;
import com.gugino.engine.input.KeyboardHandler;
import com.gugino.engine.input.MouseHandler;
import com.gugino.engine.listeners.GameWindowListener;
import com.gugino.engine.listeners.ResizeListener;
import com.gugino.engine.loops.Renderer;
import com.gugino.engine.loops.Updater;
import com.gugino.engine.states.StateManager;

public class GameManager implements Runnable{
	
	
	//TODO: Setup thread pooler for easier use and disposal of threads
	//TODO: Move GameObject Collision to its own thread and have collision checking only run if the object is actually colliding with something.
	
	//Booleans for if the game is running and if it should render this frame
	public boolean isRunning = false, shouldRender = false;
	
	//WindowHandler object
	public WindowHandler windowHandler;
	
	public KeyboardHandler keyboardHandler;
	
	public MouseHandler mouseHandler;
	
	//StateManager object
	public StateManager stateManager;
	
	//GameObjectHandler object
	public GameObjectHandler gameObjectHandler;
	
	//Object of the abstract class Game
	public Game currentGame;
	
	//Object to the games update loop
	private Updater updater;
	
	//Object to the games render loop
	public Renderer renderer;
	
	public boolean showDebugInformation = true;

	//Games main thread
	private Thread mainThread;
	
	//Constructor for GameManager which takes in the current abstract game created and, a width and height for the window and uses a default title
	public GameManager(int _windowWidth, int _windowHeight, Game _game) {
		//Creates the game window using default title and defined width/height
		windowHandler = new WindowHandler(WindowHandler.DEFAULT_WINDOW_TITLE, _windowWidth, _windowHeight);
		
		//Creates new instances of the keyboard and mouse handlers
		keyboardHandler = new KeyboardHandler();
		mouseHandler = new MouseHandler();
		
		//Sets the created game to the currentGame object
		currentGame = _game;
		
		//Runs start method
		start();
	}
	
	//Constructor for GameManager which takes the current abstract game created, a string for the window title and a width and height for the window
	public GameManager(String _windowTitle, int _windowWidth, int _windowHeight, Game _game) {
		//Creates the game window using defined title, and width/height
		windowHandler = new WindowHandler(_windowTitle, _windowWidth, _windowHeight);
		
		//Creates new instances of the keyboard and mouse handlers
		keyboardHandler = new KeyboardHandler();
		mouseHandler = new MouseHandler();
		
		//Sets the created game to the currentGame object
		currentGame = _game;
		
		//Runs start method
		start();
	}
	
	public synchronized void start() {
		//Creates the main thread and sets this class as the target
		mainThread = new Thread(this);
		
		//Creates new instance of UpdateLoop
		updater = new Updater();
		
		//Creates new instance of RenderLoop
		renderer = new Renderer();
		
		//Creates new instance of the StateManager
		stateManager = new StateManager(this, renderer);
		
		//Creates new instance of the GameObjectHandler
		gameObjectHandler = new GameObjectHandler(this, renderer);
		
		//Starts the main thread
		mainThread.start();
	}
	
	public synchronized void stop() {
		try {
			//Stops the main thread
			mainThread.join();
			gameObjectHandler.objectCollisionHandler.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Runs when main thread starts
	@Override
	public void run() {
		//Sets thats the game is running is equal to true
		isRunning = true;
				
		//Sets the mouse and keyboard listeners
		windowHandler.windowCanvas.addKeyListener(keyboardHandler);
		windowHandler.windowCanvas.addMouseListener(mouseHandler);
		windowHandler.windowCanvas.addMouseMotionListener(mouseHandler);
		windowHandler.windowCanvas.addMouseWheelListener(mouseHandler);
		
		//Sets the listener for the JFrame window
		windowHandler.windowFrame.addWindowListener(new GameWindowListener(this));
		
		//Sets a listeners for when the window resizes
		windowHandler.windowCanvas.addComponentListener(new ResizeListener(this));
		
		//Starts the update loop
		updater.start(this, renderer);
	}

}
