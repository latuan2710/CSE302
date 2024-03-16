package Assignment2;

import java.io.IOException;
import java.io.RandomAccessFile;

import Assignment1.ReferResult;

public class VirtualMemoryManager {
	private int pageSize = 256;
	private int pageNumber = 256;
	private int frameNumber = 64;
	private int tlbEntryNum = 16;
	private TLB tlb;
	private PageTable pageTable;
	private byte[] physicalMemory;
	private RandomAccessFile backingStore = null;

	public VirtualMemoryManager(int pageSize, int pageNum, int frameNum, int tlbEntryNum, String pageReplacementName,
			String backStoreFileName) throws Exception {
		this.pageSize = pageSize;
		this.pageNumber = pageNum;
		this.frameNumber = frameNum;
		this.tlbEntryNum = tlbEntryNum;
		this.tlb = new TLB(tlbEntryNum, pageReplacementName);
		this.pageTable = new PageTable(pageReplacementName, frameNum);
		this.physicalMemory = new byte[pageSize * frameNum];
		this.backingStore = new RandomAccessFile("BACKING_STORE.bin", "r");
	}

	public byte read(int logicalAddress) {
		int page = logicalAddress / this.pageSize;
		int offset = logicalAddress % this.pageSize;

		int frame = this.tlb.getFrame(page);
		if (frame != -1) {
			return this.physicalMemory[frame * this.pageSize + offset];
		}

		ReferResult result = this.pageTable.refer(page);
		if (result.getReplacePage() != page) {

		} else {
			frame = result.getIndex();
		}

		this.tlb.update(page, frame);
		return this.physicalMemory[frame * this.pageSize + offset];
	}

	public void shutdown() throws IOException {
		if (this.backingStore != null)
			this.backingStore.close();
	}
}
