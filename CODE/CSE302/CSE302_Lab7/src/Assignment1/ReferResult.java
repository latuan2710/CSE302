package Assignment1;

public class ReferResult {
	private int index;// frame
	private int page;
	private int replacePage;

	public ReferResult(int index, int page, int replacePage) {
		this.index = index;
		this.page = page;
		this.replacePage = replacePage;
	}

	public int getIndex() {
		return index;
	}

	public int getPage() {
		return page;
	}

	public int getReplacePage() {
		return replacePage;
	}

}
