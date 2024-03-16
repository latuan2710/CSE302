package Assignment1.A;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		SharedData data = new SharedData();
		int times = 1000000;

		Runnable increase = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < times; i++) {
					data.increase();
				}

			}
		};
		Runnable decrease = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < times; i++) {
					data.decrease();
				}

			}
		};

		Thread t1 = new Thread(increase);
		Thread t2 = new Thread(decrease);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(data.getValue());

	}
}
