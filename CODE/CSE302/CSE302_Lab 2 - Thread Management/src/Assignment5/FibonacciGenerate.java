package Assignment5;

public class FibonacciGenerate extends Thread {
	private Fibonacci fibonacci;

	public FibonacciGenerate(Fibonacci fibonacci) {
		super();
		this.fibonacci = fibonacci;
	}

	@Override
	public void run() {
		try {
			fibonacci.value[0] = 0;
			fibonacci.value[1] = 1;

			for (int i = 2; i < fibonacci.value.length; i++)
				fibonacci.value[i] = fibonacci.value[i - 1] + fibonacci.value[i - 2];

		} catch (Exception e) {

		}
	}

}
