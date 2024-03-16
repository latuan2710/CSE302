package Assignment1.A;

public class SharedData {
	private int value = 0;

	public SharedData() {

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public synchronized void increase() {
		this.value++;

	}

	public synchronized void decrease() {
		this.value--;
	}
}
