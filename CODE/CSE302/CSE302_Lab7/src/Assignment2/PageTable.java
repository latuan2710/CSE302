package Assignment2;

import Assignment1.FIFOPageReplacement;
import Assignment1.LRUPageReplacement;
import Assignment1.PageReplacementStrategy;
import Assignment1.ReferResult;

public class PageTable {
	private int frameNum;
	private PageReplacementStrategy pageReplaceStrategy;
	
	public PageTable(int frameNum, String pageReplacementName) throws Exception {
		this.frameNum = frameNum;
		
		if (pageReplacementName.trim().equalsIgnoreCase("FIFO") == true)
			this.pageReplaceStrategy = new FIFOPageReplacement(frameNum);
		else if (pageReplacementName.trim().equalsIgnoreCase("LRU") == true)
			this.pageReplaceStrategy = new LRUPageReplacement(frameNum);
		else
			throw new Exception("Invalid Page Replacement Strategy Invalid Name");
	}
	
	public ReferResult refer(int page) {
		return this.pageReplaceStrategy.refer(page);
	}
	
}
