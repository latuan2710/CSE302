package Assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		Bridge bridge = new Bridge();

		int n = 20;
		List<FarmerThread> farmers = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			FarmerThread f = new FarmerThread(bridge, 0);
			farmers.add(f);
		}

		for (int i = 0; i < n; i++) {
			FarmerThread f = new FarmerThread(bridge, 1);
			farmers.add(f);
		}

		for (FarmerThread farmer : farmers) {
			farmer.start();
		}

		for (FarmerThread farmer : farmers) {
			try {
				farmer.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (String mess : bridge.getHistory()) {
			System.out.println(mess);
		}
	}
}

class FarmerThread extends Thread {
	private Bridge bridge;
	private int dir;
	private static int sequence = 0;

	public FarmerThread(Bridge bridge, int dir) {
		this.bridge = bridge;
		this.dir = dir;
		this.setName("Farmer " + FarmerThread.sequence);
		FarmerThread.sequence++;
	}

	@Override
	public void run() {

		try {
			Random rd = new Random();

			Thread.sleep(rd.nextInt(20));

			this.bridge.arriveBridge(this.dir);

			Thread.sleep(10);

			this.bridge.exitBridge(this.dir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}