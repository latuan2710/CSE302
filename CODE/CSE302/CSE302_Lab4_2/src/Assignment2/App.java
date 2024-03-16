package Assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		H2O h2o = new H2O();
		Random random = new Random();

		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Runnable hydroIncrement = new Runnable() {

				@Override
				public void run() {
					try {
						h2o.hydrogen();
						Thread.sleep(random.nextInt(100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			threads.add(new Thread(hydroIncrement));
		}
		for (int i = 0; i < 5; i++) {
			Runnable oxyenIncrement = new Runnable() {

				@Override
				public void run() {
					try {
						h2o.oxygen();
						Thread.sleep(random.nextInt(100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			threads.add(new Thread(oxyenIncrement));
		}

		for (int i = 0; i < 5; i++) {
			Runnable hydroIncrement = new Runnable() {

				@Override
				public void run() {
					try {
						h2o.hydrogen();
						Thread.sleep(random.nextInt(100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			threads.add(new Thread(hydroIncrement));
		}

		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}

		System.out.println("Water: " + h2o.getH2o());
		System.out.println("Hydro: " + h2o.getHydrogenCount());
		System.out.println("Oxy: " + h2o.getOxygenCount());

	}

}
