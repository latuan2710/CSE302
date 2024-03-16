package Assignment3;

public class App {

	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool(3);

		for (int i = 0; i < 10; i++) {
			int finalI = i;
			pool.add(() -> {
				System.out.println("Task " + finalI + " running in thread " + Thread.currentThread().getName());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		while (!pool.getTasks().isEmpty()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		pool.shutdown();
	}
}
