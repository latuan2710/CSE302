package Assignment3;

public class PrimeOutput extends Thread {

	private int limit;

	public PrimeOutput(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void run() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 2; i <= limit; i++) {
			if (checkPrime(i)) {
				stringBuffer.append(i + " ");
			}
		}
		System.out.println(stringBuffer);
	}

	private boolean checkPrime(int number) {

		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0)
				return false;

		}
		return true;
	}
}
