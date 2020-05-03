/*Created By: Gugino
 *Date Created: May 3, 2020
 */
package com.gugino.engine.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.gugino.engine.GameManager;

public class GameWindowListener implements WindowListener{

	
	private GameManager gameManager;

	public GameWindowListener(GameManager _gm) {
		gameManager = _gm;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		gameManager.currentGame.onWindowClose(e);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

}
