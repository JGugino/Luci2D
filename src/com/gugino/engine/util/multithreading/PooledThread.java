/*Created By: Gugino
 *Date Created: Jun 17, 2020
 */
package com.gugino.engine.util.multithreading;

public class PooledThread extends Thread{

	protected String threadID;
	protected ThreadPooler threadPool;
	
	public PooledThread(ThreadPooler _threadPool, String _threadID) {
		super(_threadPool, _threadID);
		this.threadPool = _threadPool;
		this.threadID = _threadID;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			Runnable _task = null;
			try {
				_task = threadPool.getNextTask();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(_task == null) {
				return;
			}else {
				try {
					_task.run();	
				}catch(Throwable _t) {
					threadPool.uncaughtException(this, _t);
				}
			}
		}
	}
}
