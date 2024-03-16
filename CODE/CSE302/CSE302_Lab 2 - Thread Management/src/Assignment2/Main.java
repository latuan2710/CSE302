package Assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Share data = new Share();

		Scanner sc = new Scanner(System.in);

		List<Integer> numbers = new ArrayList<>();
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			numbers.add(sc.nextInt());
		}

		Thread t1 = new Thread(() -> {
			double total = 0;
			for (int num : numbers) {
				total += num;
			}

			data.average = Math.round(total / numbers.size());
		});
		t1.start();
		t1.join();

		Thread t2 = new Thread(() -> {
			for (int num : numbers) {
				data.min = (num < data.min) ? num : data.min;
			}
		});
		t2.start();
		t2.join();

		Thread t3 = new Thread(() -> {
			for (int num : numbers) {
				data.max = (num > data.max) ? num : data.max;
			}
		});
		t3.start();
		t3.join();

		System.out.println(data);

	}
}
