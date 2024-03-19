package Assignment1;

import java.util.List;

public class App {

	public static void main(String[] args) {
		int[] references = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };

		LRUPageReplacement pageReplacement = new LRUPageReplacement(3);
		int pageFaultCount = 0;
		for (int i : references) {
			ReferResult referResult = pageReplacement.refer(i);

			if (referResult.getPage() != referResult.getReplacedPage()) {
				List<PageInfo> quene = pageReplacement.getQuene();
				System.out.println(quene);
				pageFaultCount++;
			}
		}
		System.out.println("Page fault: " + pageFaultCount);
	}

}
