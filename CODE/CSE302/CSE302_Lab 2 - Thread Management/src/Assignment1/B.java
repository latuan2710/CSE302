package Assignment1;

public class B {
	public static void main(String[] args) throws InterruptedException {
		SharedData data = new SharedData();

		int times = 1000000;

		IncreaseRunnable increase = new IncreaseRunnable(data, times);
		DecreaseRunnable decrease = new DecreaseRunnable(data, times);

		Thread t1 = new Thread(increase);
		Thread t2 = new Thread(decrease);
		
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("B: Shared Data = " + data.value);
	}
}
