package Assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;

public class Main {
	private final static int PAGE_SIZE = 256;
	private final static int PAGE_NUMBER = 256;
	private final static int FRAME_NUMBER = 64;
	private final static int TLB_ENTRY_NUM = 16;

	public static void main(String[] args) throws Exception {
		VirtualMemoryManager manager = new VirtualMemoryManager(PAGE_SIZE, PAGE_NUMBER, FRAME_NUMBER, FRAME_NUMBER,
				"FIFO", "BACKING_STORE.bin");

		RandomAccessFile backingStoreFile = new RandomAccessFile("BACKING_STORE.bin", "r");
		byte[] backingStore = new byte[PAGE_SIZE * PAGE_NUMBER];
		backingStoreFile.read(backingStore, 0, PAGE_SIZE * PAGE_NUMBER);
		backingStoreFile.close();

		BufferedReader addressReader = new BufferedReader(new FileReader("addresses.txt"));
		String line = addressReader.readLine();
		while (line != null) {
			int logicalAddress = Integer.parseInt(line);
			byte value = manager.read(logicalAddress);

			if (value != backingStore[logicalAddress])
				System.err.println(logicalAddress + ": " + value + ", " + backingStore[logicalAddress]);

			line = addressReader.readLine();
		}
		addressReader.close();
		System.out.println("Done");
	}
}
