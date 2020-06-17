/*Created By: Gugino
 *Date Created: Jun 17, 2020
 */
package com.gugino.engine.gameobjects;

import com.gugino.engine.util.debug.Debug;

public class CollisionThread extends Thread{

	protected GameObject object01, object02;
	

	
	public CollisionThread(GameObject _object01, GameObject _object02) {
		this.object01 = _object01;
		this.object02 = _object02;
	}
	
	@Override
	public void run() {
		Debug.printLine("update " + System.currentTimeMillis());
		

	}
	

}
