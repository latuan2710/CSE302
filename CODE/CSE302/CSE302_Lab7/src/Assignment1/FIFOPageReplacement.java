package Assignment1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FIFOPageReplacement implements PageReplacementStrategy {
	private final int entryNum;
	private List<PageInfo> quene = new LinkedList<>();
	
	public static void main(String[] args) {
		int[] references = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };

		FIFOPageReplacement pageReplacement = new FIFOPageReplacement(3);
		int pageFaultCount = 0;
		for (int i : references) {
			ReferResult referResult = pageReplacement.refer(i);

			if (referResult.getPage() != referResult.getReplacePage()) {
				List<PageInfo> quene = pageReplacement.getQuene();
				System.out.println(quene);
				pageFaultCount++;
			}
		}
		System.out.println("Page fault: " + pageFaultCount);
	}

	public FIFOPageReplacement(int enryNum) {
		this.entryNum = enryNum;
	}

	public List<PageInfo> getQuene() {
		return quene;
	}

	@Override
	public ReferResult refer(int page) {
		ReferResult result;
		Iterator<PageInfo> iter = this.quene.iterator();
		
		while (iter.hasNext()) {
			PageInfo pi = iter.next();

			if (pi.getPage() == page) {
				result = new ReferResult(pi.getIndex(), page, page);
				return result;
			}
		}
		
		PageInfo pi;
		if (this.quene.size() < this.entryNum) {
			pi = new PageInfo(this.quene.size(), page);
			result = new ReferResult(pi.getIndex(), page, -1);
		} else {
			pi = this.quene.remove(0);
			result = new ReferResult(pi.getIndex(), page, pi.getPage());
			pi.setPage(page);
		}

		this.quene.add(pi);
		return result;
	}

}

class PageInfo {
	private int index;
	private int Page;

	public PageInfo(int index, int page) {
		this.index = index;
		Page = page;
	}

	public int getIndex() {
		return index;
	}

	public int getPage() {
		return Page;
	}

	public void setPage(int page) {
		Page = page;
	}

	@Override
	public String toString() {
		return "(" + index + " , " + Page + ")";
	}
}
