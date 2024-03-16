package Assignment3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		PrimeOutput thread = new PrimeOutput(n);
		thread.start();
	}

}
