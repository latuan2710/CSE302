package service;

import java.util.concurrent.atomic.AtomicBoolean;

public class Interrupt {
	private AtomicBoolean isStop = new AtomicBoolean(false);
	private Thread interruptThread = null;

	public void interruptAfter(CPU cpu, int quantum) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				long end = start + quantum;
				long current = start;

				while (current < end && Interrupt.this.isStop.get() == false) {
					Thread.yield();
					current = System.currentTimeMillis();
				}

				if (Interrupt.this.isStop.get() == false)
					cpu.interrupt();
			}

		};
		this.interruptThread = thread;
		thread.start();
	}

	public void stop() {
		if (this.isStop.get() == false) {
			this.isStop.set(true);
			if (this.interruptThread != null) {
				this.interruptThread.interrupt();
				try {
					this.interruptThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
