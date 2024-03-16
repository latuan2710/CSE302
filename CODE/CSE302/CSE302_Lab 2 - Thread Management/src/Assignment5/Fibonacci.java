package Assignment5;

public class Fibonacci {
	int[] value;

	public Fibonacci(int n) {
		this.value = new int[n];
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int num : value) {
			stringBuffer.append(num + " ");
		}

		return stringBuffer.toString();
	}

}
