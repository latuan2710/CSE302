package Assignment1;

public class App {

	public static void main(String[] args) {
		int logicalAddress = 19986;
		System.out.println("Page number: " + logicalAddress / (4 * 1024));
		System.out.println("Offset: " + logicalAddress % (4 * 1024));
	}

}
