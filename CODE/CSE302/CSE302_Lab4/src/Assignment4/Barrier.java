package Assignment4;

public class Barrier {
	private int parties;
	private int count;

	public Barrier(int parties) {
		this.parties = parties;
		this.count = 0;
	}

	public synchronized void await() {
		count++;

		try {
			if (count < parties) {
				wait();
			} else {
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
