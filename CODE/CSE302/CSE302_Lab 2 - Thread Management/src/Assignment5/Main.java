package Assignment5;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Fibonacci fibonacci = new Fibonacci(n);
		FibonacciGenerate thread = new FibonacciGenerate(fibonacci);
		thread.start();

		System.out.println(fibonacci);

		sc.close();

	}

}
