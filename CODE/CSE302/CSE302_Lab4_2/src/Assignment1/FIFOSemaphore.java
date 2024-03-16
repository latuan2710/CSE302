package Assignment1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FIFOSemaphore {
	private int permits;
	private Lock lock = new ReentrantLock();
	private Condition cond = this.lock.newCondition();
	private int waitCount = 0;

	public FIFOSemaphore(int p) {
		this.permits = p;
	}

	public void acquire() throws InterruptedException {
		this.lock.lock();
		try {
			if (this.permits > 0)
				this.permits--;
			else {
				this.waitCount++;
				this.cond.await();
			}
		} finally {
			this.lock.unlock();
		}
	}

	public void release() {
		this.lock.lock();
		try {
			if (this.waitCount > 0) {
				this.waitCount--;
				this.cond.signal();
			} else {
				this.permits++;
			}
		} finally {
			this.lock.unlock();
		}
	}
}