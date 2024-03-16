package Assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OldBridge {
	private int carLeft = 0;
	private int carRight = 0;
	private Lock lock = new ReentrantLock();
	private Condition carLeftCond = lock.newCondition();
	private Condition carRightCond = lock.newCondition();
	private List<String> history = new ArrayList<>();

	public void arriveBridge(int direction) {
		lock.lock();

		try {
			if (direction == 0) {
				while (this.carRight > 0 || this.carLeft == 3)
					this.carLeftCond.await();
				this.carLeft++;
				history.add(Thread.currentThread().getName()+": On bridge, dir 0, "+this.carLeft);
			} else {
				while (this.carLeft > 0 || this.carRight == 3)
					this.carRightCond.await();
				this.carRight++;
				history.add(Thread.currentThread().getName()+": On bridge, dir 1, "+this.carRight);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void exitBridge(int direction) {
		lock.lock();

		try {
			if (direction == 0) {
				this.carLeft--;
				history.add(Thread.currentThread().getName()+": Exit bridge, dir 0, "+this.carLeft);
			} else {
				this.carRight--;
				history.add(Thread.currentThread().getName()+": Exit bridge, dir 1, "+this.carRight);
			}
			this.carLeftCond.signal();
			this.carRightCond.signal();
		} finally {
			lock.unlock();
		}
	}

	public List<String> getHistory() {
		return history;
	}

}
