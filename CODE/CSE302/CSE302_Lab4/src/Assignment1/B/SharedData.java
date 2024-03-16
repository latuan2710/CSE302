package Assignment1.B;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedData {
	private int value = 0;
	private Lock lock = new ReentrantLock();

	public SharedData() {

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void increase() {
		this.lock.lock();
		try {
			this.value++;
		} finally {
			this.lock.unlock();
		}

	}

	public void decrease() {
		this.lock.lock();
		try {
			this.value--;
		} finally {
			this.lock.unlock();
		}
	}
}
