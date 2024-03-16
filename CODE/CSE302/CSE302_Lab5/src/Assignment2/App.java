package Assignment2;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		int times = 10;
		Random random = new Random();
		Bank bank = new Bank(times, 100);

		Thread[] threads = new Thread[times];

		for (int i = 0; i < times; i++) {
			int fromId = random.nextInt(times);
			int toId = random.nextInt(times);

			threads[i] = new Thread() {
				@Override
				public void run() {
					try {
						boolean success = bank.transaction(fromId, toId, times);
						if (success) {
							System.out.println(
									"Transaction from account " + fromId + " to account " + toId + " successful.");
						} else {
							System.out
									.println("Transaction from account " + fromId + " to account " + toId + " failed.");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			};
		}

		for (Thread thread : threads) {
			thread.start();
		}
	}

}