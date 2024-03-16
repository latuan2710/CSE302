package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;

public class App {

	public static void main(String[] args) {
		int[] availableResources = { 3, 3, 2 };
		int[][] allocation = { { 0, 1, 0 }, { 2, 0, 0 }, { 3, 0, 2 }, { 2, 1, 1 }, { 0, 0, 2 } };
		int[][] maximumDemand = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };

		Banker banker = new Banker(availableResources, maximumDemand, allocation);
		System.out.println(banker.isSafeState());
		int[] request1 = { 1, 0, 2 };
		ArrayList<Integer> result1 = banker.request(1, request1);
		if (result1 != null) {
			System.out.println(
					"Request granted. Available resources after allocation: " + Arrays.toString(banker.getAvailable()));
		} else {
			System.out.println("Request denied.");
		}

		int[] request2 = { 3, 3, 0 };
		ArrayList<Integer> result2 = banker.request(2, request2);
		if (result2 != null) {
			System.out.println(
					"Request granted. Available resources after allocation: " + Arrays.toString(banker.getAvailable()));
		} else {
			System.out.println("Request denied.");
		}

	}

}
