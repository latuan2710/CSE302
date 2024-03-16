package Assignment3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	private int[] quene;
	private int count = 0;
	private int addIndex = 0;
	private int removeIndex = 0;
	private Lock lock = new ReentrantLock();
	private Condition fullCond = lock.newCondition();
	private Condition emptyCond = lock.newCondition();

	public BoundedBuffer(int size) {
		this.quene = new int[size];
	}

	public void add(int value) throws InterruptedException {
		lock.lock();
		try {
			while (this.count == this.quene.length)
				this.fullCond.await();

			this.quene[this.addIndex] = value;
			this.addIndex = (this.addIndex + 1) % this.quene.length;
			this.count++;
			this.emptyCond.signal();
		} finally {
			lock.unlock();
		}

	}

	public int remove() throws InterruptedException {
		int value = 0;
		lock.lock();
		try {
			if (this.count == 0)
				this.emptyCond.await();

			value = this.quene[this.removeIndex];
			this.removeIndex = (this.removeIndex + 1) % this.quene.length;
			this.count--;
			this.fullCond.signal();
		} finally {
			lock.unlock();
		}
		return value;
	}
}
