package Assignment1;

import java.util.ArrayList;
import java.util.List;

public class App {
	private static int sequence = 0;

	public static void main(String[] args) throws InterruptedException {
		FIFOSemaphore s = new FIFOSemaphore(0);
		List<Thread> threads = new ArrayList<>();

		int n = 10;
		for (int i = 0; i < n; i++) {
			Thread t = new Thread() {

				@Override
				public void run() {
					this.setName("Thread" + App.sequence++);
					System.out.println(this.getName() + " acquires...");
					try {
						s.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(this.getName() + " acquires OK");
				}

			};
			threads.add(t);
		}
		for (Thread t : threads) {
			t.start();
			Thread.sleep(100);
		}
		for (int i = 0; i < n; i++) {
			s.release();
			Thread.sleep(50);
		}

	}

}
