package Assignment1.C;

import java.util.concurrent.Semaphore;

public class SharedData {
	private int value = 0;
	private final Semaphore semaphore = new Semaphore(1);

	public SharedData() {

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void increase() {
		try {
			semaphore.acquire();
			this.value++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}

	}

	public void decrease() {
		try {
			semaphore.acquire();
			this.value--;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
