package Assignment2;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isLoop = true;
		ContiguousAllocation ca = new ContiguousAllocation();

		while (isLoop) {
			System.out.print("allocator>");
			String test = sc.nextLine();
			String part[] = test.split(" ");

			switch (part[0].toUpperCase()) {
			case "SIZE":
				ca.setSize(Integer.parseInt(part[1]));
				break;
			case "RQ":
				ca.request(part[1], Integer.parseInt(part[2]), part[3]);
				break;
			case "RL":
				ca.release(part[1]);
				break;
			case "C":
				ca.compact();
				break;
			case "STAT":
				for (Block block : ca.getBlocks()) {
					System.out.println(block);
				}
				break;
			case "X":
				isLoop = false;
				break;

			default:
				System.out.println("Invalid Command");
				break;
			}
		}
		System.out.println("allocator> You are exit");
		sc.close();
	}

}
