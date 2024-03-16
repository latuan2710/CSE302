package Assignment4;

import java.util.Random;

public class MonteCarloPi {
	private static int totalPoints = 0;
	private static int pointsInCircle = 0;

	public static void main(String[] args) throws InterruptedException {
		int totalThreads = 4;
		Thread[] threads = new Thread[totalThreads];

		for (int i = 0; i < totalThreads; i++) {
			threads[i] = new Thread(() -> {
				generateRandomPoints(250000);
			});
			threads[i].start();
		}

		for (Thread thread : threads) {
			thread.join();
		}

		double pi = 4.0 * pointsInCircle / totalPoints;
		System.out.println("Estimated value of π: " + pi);
	}

	private static void generateRandomPoints(int numPoints) {
		Random random = new Random();
		for (int i = 0; i < numPoints; i++) {
			double x = random.nextDouble();
			double y = random.nextDouble();
			double distance = Math.sqrt(x * x + y * y);

			if (distance <= 1.0) {
				synchronized (MonteCarloPi.class) {
					pointsInCircle++;
				}
			}

			synchronized (MonteCarloPi.class) {
				totalPoints++;
			}
		}
	}
}
