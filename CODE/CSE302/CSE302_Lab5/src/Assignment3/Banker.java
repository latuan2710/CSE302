package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Banker {
	private int resourceTypeNum;
	private int customerNum;
	private int[] available;
	private int[][] maximum;
	private int[][] allocation;

	public Banker(int[] avail, int[][] max, int[][] alloc) {
		this.available = avail;
		this.maximum = max;
		this.allocation = alloc;
		this.resourceTypeNum = avail.length;
		this.customerNum = max.length;
	}

	public ArrayList<Integer> isSafeState() {
		ArrayList<Integer> result = new ArrayList<>();
		int[] work = Arrays.copyOf(available, resourceTypeNum);
		boolean[] finish = new boolean[customerNum];

		int count = 0;
		while (count < customerNum) {
			for (int i = 0; i < customerNum; i++) {
				if (finish[i] == false && condition2B(work, i)) {
					result.add(i);
					finish[i] = true;
					count++;

					for (int j = 0; j < resourceTypeNum; j++)
						work[j] += allocation[i][j];
				}
			}

			if (result.isEmpty()) {
				return null;
			}
		}

		boolean allFinished = true;
		for (boolean b : finish) {
			if (!b) {
				allFinished = false;
				break;
			}
		}

		return allFinished ? result : null;
	}

	public ArrayList<Integer> request(int custId, int[] request) {
		int[] need = new int[resourceTypeNum];
		for (int i = 0; i < resourceTypeNum; i++) {
			need[i] = maximum[custId][i] - allocation[custId][i];
		}

		for (int i = 0; i < request.length; i++) {
			if (request[i] > need[i]) {
				return null;
			}
		}

		for (int i = 0; i < request.length; i++) {
			if (request[i] > available[i]) {
				return null;
			}
		}

		for (int i = 0; i < request.length; i++) {
			available[i] -= request[i];
			allocation[custId][i] += request[i];
		}

		if (this.isSafeState() != null) {
			return Arrays.stream(available).boxed().collect(Collectors.toCollection(ArrayList::new));
		}

		for (int i = 0; i < request.length; i++) {
			available[i] += request[i];
			allocation[custId][i] -= request[i];
		}

		return null;

	}

	public int[] getAvailable() {
		return available;
	}

	public int[][] getMaximum() {
		return maximum;
	}

	public int[][] getAllocation() {
		return allocation;
	}

	private boolean condition2B(int[] work, int i) {
		for (int j = 0; j < resourceTypeNum; j++) {
			int need = (maximum[i][j] - allocation[i][j]);
			if (need > work[j]) {
				return false;
			}
		}
		return true;
	}
}
