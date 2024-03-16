package Assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		OldBridge oldBridge = new OldBridge();

		int n = 20;
		List<CarThread> cars = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			CarThread c = new CarThread(oldBridge, 0);
			cars.add(c);
		}
		
		for (int i = 0; i < n; i++) {
			CarThread c = new CarThread(oldBridge, 1);
			cars.add(c);
		}

		for (CarThread car : cars) {
			car.start();
		}

		for (CarThread car : cars) {
			car.join();
		}
		
		for (String mess : oldBridge.getHistory()) {
			System.out.println(mess);
		}
	}

}

class CarThread extends Thread {
	private OldBridge oldBridge;
	private int dir;
	private static int sequence = 0;

	public CarThread(OldBridge oldBridge, int dir) {
		this.oldBridge = oldBridge;
		this.dir=dir;
		this.setName("Car " + CarThread.sequence);
		CarThread.sequence++;
	}

	@Override
	public void run() {

		try {
			Random rd = new Random();

			Thread.sleep(rd.nextInt(20));

			this.oldBridge.arriveBridge(this.dir);

			Thread.sleep(10);

			this.oldBridge.exitBridge(this.dir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
