package Assignment2;

import java.io.IOException;
import java.io.RandomAccessFile;

import Assignment1.ReferResult;

public class VirtualMemoryManager {
	private int pageSize = 256;
	private int pageNum = 256;
	private int frameNum = 64;
	private int tlbEntryNum = 16;
	
	private TLB tlb;
	private PageTable pageTable;
	private byte[] physicalMemory;
	
	private RandomAccessFile backingStore = null;
	
	public VirtualMemoryManager(int pageSize, int pageNum, int frameNum, int tlbEntryNum,
			String pageReplacementName, String backingStoreFileName) throws Exception {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.frameNum = frameNum;
		this.tlbEntryNum = tlbEntryNum;
		
		this.tlb = new TLB(tlbEntryNum, pageReplacementName);
		this.pageTable = new PageTable(frameNum, pageReplacementName);
		
		this.physicalMemory = new byte[pageSize * frameNum];
		
		this.backingStore = new RandomAccessFile(backingStoreFileName, "r");
	}
	
	public byte read(int logicalAddress) {
		int page = logicalAddress / this.pageSize;
		int offset = logicalAddress % this.pageSize;
		
		int frame = this.tlb.getFrame(page);
		if (frame != -1)  // TLB hit
			return this.physicalMemory[frame * this.pageSize + offset];
		
		// TLB miss
		ReferResult result = this.pageTable.refer(page);
		if (result.getReplacedPage() != page) {  // page fault
			frame = handlePageFault(page);
		} else { // NOT page fault
			frame = result.getIndex();
		}
		
		this.tlb.update(page, frame);
		return this.physicalMemory[frame * this.pageSize + offset];
	}
	
	public void shutdown() throws IOException {
		if (this.backingStore != null)
			this.backingStore.close();
	}
	
	private int handlePageFault(int pageNumber) {
        try {
            byte[] pageData = new byte[this.pageSize];
            this.backingStore.seek(pageNumber * this.pageSize);
            this.backingStore.read(pageData);
            
            int frame = this.pageTable.refer(pageNumber).getPage();
            System.arraycopy(pageData, 0, this.physicalMemory, frame * this.pageSize, this.pageSize);
            
            return frame;
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Error handling
        }
    }
}

