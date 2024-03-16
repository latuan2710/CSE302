package Assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bridge {
	private int farmerLeft = 0;
	private int farmerRight = 0;
	private Lock lock = new ReentrantLock();
	private Condition farmerLeftCond = lock.newCondition();
	private Condition farmerRightCond = lock.newCondition();
	private List<String> history = new ArrayList<>();

	public void arriveBridge(int direction) {
		lock.lock();

		try {
			if (direction == 0) {
				while (this.farmerRight > 0)
					this.farmerLeftCond.await();
				this.farmerLeft++;
				history.add(Thread.currentThread().getName() + ": On bridge, dir 0, " + this.farmerLeft);
			} else {
				while (this.farmerLeft > 0)
					this.farmerRightCond.await();
				this.farmerRight++;
				history.add(Thread.currentThread().getName() + ": On bridge, dir 1, " + this.farmerRight);
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
				this.farmerLeft--;
				history.add(Thread.currentThread().getName() + ": Exit bridge, dir 0, " + this.farmerLeft);
			} else {
				this.farmerRight--;
				history.add(Thread.currentThread().getName() + ": Exit bridge, dir 1, " + this.farmerRight);
			}
			this.farmerLeftCond.signal();
			this.farmerRightCond.signal();
		} finally {
			lock.unlock();
		}
	}

	public List<String> getHistory() {
		return history;
	}
}
