package service;

import java.util.concurrent.atomic.AtomicBoolean;

import entity.ExecutionInfo;
import entity.Task;

public class CPU {
	private AtomicBoolean isStop = new AtomicBoolean(false);
	private Thread runningThread = null;

	public ExecutionInfo excute(Task task) {
		int burst = task.getBurst();
		long startTime = System.currentTimeMillis();
		long endTime = startTime + burst;

		long current = startTime;
		this.isStop.set(false);
		this.runningThread = Thread.currentThread();
		while (current < endTime && this.isStop.get() == false) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			current = System.currentTimeMillis();
		}

		int duration = (int) (current - startTime);

		ExecutionInfo info = new ExecutionInfo(startTime, task, burst, duration);
		
		burst = burst - duration;
		burst = burst < 0 ? 0 : burst;
		task.setBurst(burst);

		return info;
	}

	public void interrupt() {
		if (this.isStop.get() == false) {
			this.isStop.set(true);
			if (this.runningThread != null) {
				this.runningThread.interrupt();
			}
		}

	}
}
