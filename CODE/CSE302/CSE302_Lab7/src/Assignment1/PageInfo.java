package Assignment1;

public class PageInfo {
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
