package Assignment1;

public class A {
	public static void main(String[] args) throws InterruptedException {
		SharedData data = new SharedData();

		int times = 1000000;

		IncreaseRunnable increase = new IncreaseRunnable(data, times);
		DecreaseRunnable decrease = new DecreaseRunnable(data, times);

		Thread t1 = new Thread(increase);
		t1.start();
		t1.join();

		Thread t2 = new Thread(decrease);
		t2.start();
		t2.join();

		System.out.println("A: Shared Data = " + data.value);
	}
}
