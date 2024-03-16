package Assignment2;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TSQueue tsQueue = new TSQueue();
		int times = 1000;

		Runnable add = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < times; i++) {
					tsQueue.addLast(i);
				}
			}
		};

		Runnable remove = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < times; i++) {
					tsQueue.removeFirst();
				}
			}
		};

		Thread t1 = new Thread(add);
		Thread t2 = new Thread(remove);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(tsQueue);
	}

}
