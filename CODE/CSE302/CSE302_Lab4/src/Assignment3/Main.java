package Assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		BoundedBuffer buffer = new BoundedBuffer(3);

		int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		List<Integer> list = new ArrayList<>();

		Runnable add = new Runnable() {
			@Override
			public void run() {
				Random rd = new Random();
				for (int i = 0; i < numbers.length; i++) {
					try {
						buffer.add(numbers[i]);
						Thread.sleep(rd.nextInt(10));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Runnable remove = new Runnable() {
			@Override
			public void run() {
				Random rd = new Random();
				for (int i = 0; i < numbers.length; i++) {
					try {
						int value = buffer.remove();
						list.add(value);
						Thread.sleep(rd.nextInt(20));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread t1 = new Thread(add);
		Thread t2 = new Thread(remove);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		for (int v : list)
			System.out.print(v + " ");
	}

}
