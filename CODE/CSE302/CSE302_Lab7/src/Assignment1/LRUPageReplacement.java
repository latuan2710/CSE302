package Assignment1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LRUPageReplacement implements PageReplacementStrategy {
	private final int entryNum;
	private List<PageInfo> quene = new LinkedList<>();
	private LinkedList<Integer> pageRecent = new LinkedList<>();

	public LRUPageReplacement(int entryNum) {
		this.entryNum = entryNum;
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
				pageRecent.remove((Integer) page);
				pageRecent.addFirst(page);
				return result;
			}
		}

		PageInfo pi = null;
		if (this.quene.size() < this.entryNum) {
			pi = new PageInfo(this.quene.size(), page);
			result = new ReferResult(pi.getIndex(), page, -1);
		} else {
			int leastPage = pageRecent.removeLast();
			for (PageInfo pageInfo : quene) {
				if (pageInfo.getPage() == leastPage) {
					pi = pageInfo;
					break;
				}
			}
			this.quene.remove(pi);
			result = new ReferResult(pi.getIndex(), page, pi.getPage());
			pi.setPage(page);
		}

		pageRecent.addFirst(page);
		this.quene.add(pi);
		return result;
	}

}
