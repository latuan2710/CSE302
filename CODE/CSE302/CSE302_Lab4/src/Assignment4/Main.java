package Assignment4;

public class Main {
	public static void main(String[] args) {
		Barrier barrier = new Barrier(3);

		Thread t1 = new Thread() {
			@Override
			public void run() {
				System.out.println("Thread 1 is waiting at the barrier.");
				barrier.await();
				System.out.println("Thread 1 has passed the barrier.");
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				System.out.println("Thread 2 is waiting at the barrier.");
				barrier.await();
				System.out.println("Thread 2 has passed the barrier.");
			}
		};

		Thread t3 = new Thread() {
			@Override
			public void run() {
				System.out.println("Thread 3 is waiting at the barrier.");
				barrier.await();
				System.out.println("Thread 3 has passed the barrier.");
			}
		};

		t1.start();
		t2.start();
		t3.start();
	}
}
