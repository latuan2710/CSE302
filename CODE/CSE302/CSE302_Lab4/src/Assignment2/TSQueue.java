package Assignment2;
import java.util.LinkedList;

public class TSQueue {
	private LinkedList<Integer> queue = new LinkedList<>();

	public synchronized void addLast(int value) {
		queue.addLast(value);
	}

	public synchronized void removeFirst() {
		try {
			queue.removeFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "TSQueue [queue=" + queue + "]";
	}
}
