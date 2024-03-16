package Assignment2;

import Assignment1.FIFOPageReplacement;
import Assignment1.LRUPageReplacement;
import Assignment1.PageReplacementStrategy;

public class TLB {
	private int entryNum;
	private int[][] table;
	private PageReplacementStrategy pageReplaceStrategy;

	public TLB(int entryNum, String pageReplacementName) throws Exception {
		this.entryNum = entryNum;
		this.table = new int[entryNum][2];

		if (pageReplacementName.trim().equalsIgnoreCase("FIFO")) {
			this.pageReplaceStrategy = new FIFOPageReplacement(entryNum);
		} else if (pageReplacementName.trim().equalsIgnoreCase("LRU")) {
			this.pageReplaceStrategy = new LRUPageReplacement(entryNum);
		} else {
			throw new Exception("Invalid Page Replacemnent Strategy");
		}

	}

	// return -1 if TLB miss
	public int getFrame(int page) {
		return 0;
	}

	public void update(int page, int frame) {

	}
}
