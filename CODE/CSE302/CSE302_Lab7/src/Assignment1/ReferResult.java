package Assignment1;

public class ReferResult {
	private int index;// frame
	private int page;
	private int replacedPage;

	public ReferResult(int index, int page, int replacedPage) {
		this.index = index;
		this.page = page;
		this.replacedPage = replacedPage;
	}

	public int getIndex() {
		return index;
	}

	public int getPage() {
		return page;
	}

	public int getReplacedPage() {
		return replacedPage;
	}

}
