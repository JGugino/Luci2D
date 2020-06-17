/*Created By: Gugino
 *Date Created: Jun 17, 2020
 */
package com.gugino.engine.util.multithreading;

import java.util.LinkedList;

public class ThreadPooler extends ThreadGroup{

	protected String poolID;

	protected boolean poolAlive =  false;
	
	protected LinkedList<Runnable> queuedThreads = new LinkedList<Runnable>();
	
	public ThreadPooler(String _poolID, int _totalThreads) {
		super(_poolID);
		
		setDaemon(true);
		
		this.poolID = _poolID;
		this.poolAlive = true;
		
		for (int _t = 0; _t < _totalThreads; _t++) {
			new PooledThread(this, "thread_" + _t);
		}
	}
	
	public synchronized void runTask(Runnable _task) {
		if(!poolAlive) throw new IllegalStateException("ThreadPool - " + poolID + " is dead!");
		
		if(_task != null) {
			queuedThreads.add(_task);
			notify();
		}
	}
	
	public synchronized void closePool() {
		if(!poolAlive) return;
		
		poolAlive = false;
		queuedThreads.clear();
		interrupt();
	}
	
	public void joinPooledThreads() {
		synchronized(this) {
			poolAlive = false;
			notifyAll();
		}
		
		Thread[] _threads = new Thread[activeCount()];
		int _totalThreads = enumerate(_threads);
		for (int _t = 0; _t < _totalThreads; _t++) {
			try {
				_threads[_t].join();
			} catch (InterruptedException e) {
				//e.printStackTrace();
			};
		}
	}
	
	//Gets the next task to be ran in the queued threads list
	protected synchronized Runnable getNextTask() throws InterruptedException {
		while(queuedThreads.size() == 0) {
			if(!poolAlive) {
				return null;
			}
			wait();
		}
		return queuedThreads.remove(0);
	}
	
	public String getPoolID() {
		return poolID;
	}
}
