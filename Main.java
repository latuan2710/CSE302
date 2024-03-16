import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ContiguousAllocation ca = new ContiguousAllocation();
		boolean exit  = false;
		String line;
		String[] command;
		Scanner scanner = new Scanner(System.in);
		
		while (exit == false) {
			System.out.print("Allocator>");
			line = scanner.nextLine();
			line = line.toUpperCase().trim();
			command = line.split("\\s+");
			
			if (command[0].equals("STAT")) {
				for (Block b : ca.getBlocks())
					System.out.println(b.toString());
			} else if (command[0].equals("X")) 
				exit = true;
			else if (command[0].equals("SIZE")) {
				if (command.length != 2) {
					System.out.println("Invalid command");
					continue;
				}
				ca.size(Integer.parseInt(command[1]));
			}
			else if (command[0].equals("RQ")) {
				if (command.length != 4) {
					System.out.println("Invalid command");
					continue;
				}
				
			} else {
				System.out.println("Invalid command");
			}
			
			
		}
		System.out.println("Finished.");
	}

}
