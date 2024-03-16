package Assignment3;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
	private Queue<Runnable> tasks;
	private Thread[] threads;
	private boolean isShutdown = false;

	public ThreadPool() {
		threads = new Thread[10];
	}

	public ThreadPool(int size) {
		tasks = new LinkedList<>();
		threads = new Thread[size];
		for (int i = 0; i < size; i++) {
			threads[i] = new Worker("Thread-" + (i + 1));
			threads[i].start();
		}
	}

	public void add(Runnable task) {
		synchronized (getTasks()) {
			getTasks().add(task);
			getTasks().notify();
		}
	}

	public void shutdown() {
		isShutdown = true;
		synchronized (getTasks()) {
			getTasks().notifyAll();
		}
		for (Thread worker : threads) {
			worker.interrupt();
		}
	}

	public Queue<Runnable> getTasks() {
		return tasks;
	}

	private class Worker extends Thread {

		public Worker(String name) {
			super(name);
		}

		@Override
		public void run() {
			while (!isShutdown || !getTasks().isEmpty()) {
				Runnable task = getTasks().poll();
				if (task != null) {
					task.run();
				}
			}
		}
	}
}
