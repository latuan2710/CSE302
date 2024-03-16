package Assignment2;

import Assignment1.FIFOPageReplacement;
import Assignment1.LRUPageReplacement;
import Assignment1.PageReplacementStrategy;
import Assignment1.ReferResult;

public class PageTable {
	private PageReplacementStrategy pageReplaceStrategy;
	private int frameNum;

	public PageTable(String pageReplacementName, int frameNum) throws Exception {
		this.frameNum = frameNum;
		if (pageReplacementName.trim().equalsIgnoreCase("FIFO")) {
			this.pageReplaceStrategy = new FIFOPageReplacement(frameNum);
		} else if (pageReplacementName.trim().equalsIgnoreCase("LRU")) {
			this.pageReplaceStrategy = new LRUPageReplacement(frameNum);
		} else {
			throw new Exception("Invalid Page Replacemnent Strategy");
		}
	}

	public ReferResult refer(int page) {
		return this.pageReplaceStrategy.refer(page);
	}

}
