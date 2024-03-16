package Assignment1;

public class IncreaseRunnable implements Runnable {
	private SharedData data;
	private int times;

	public IncreaseRunnable(SharedData d, int t) {
		this.data = d;
		this.times = t;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.times; i++) {
			int v = this.data.value;
			v = v + 1;
			this.data.value = v;
		}
	}
}
