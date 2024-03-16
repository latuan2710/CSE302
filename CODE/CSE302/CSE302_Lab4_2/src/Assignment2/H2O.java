package Assignment2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {
	private int totalH2O = 0;
	private int hydrogenCount = 0;
	private int oxygenCount = 0;
	private int hydrogenUsedCount = 0;
	private int oxygenUsedCount = 0;
	private Lock lock = new ReentrantLock();
	private Condition hydrogenCondition = this.lock.newCondition();
	private Condition oxygenCondition = this.lock.newCondition();

	public H2O() {
	}

	public void hydrogen() throws InterruptedException {
		lock.lock();
		try {
			hydrogenCount++;
			while ((hydrogenCount - hydrogenUsedCount) < 2 || (oxygenCount - oxygenUsedCount) < 1) {
				hydrogenCondition.await();
				if ((hydrogenCount - hydrogenUsedCount) == 0 && (oxygenCount - oxygenUsedCount) == 0) {
					return;
				}
			}

			totalH2O++;
			hydrogenUsedCount += 2;
			oxygenUsedCount += 1;
			oxygenCondition.signalAll();
			hydrogenCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void oxygen() throws InterruptedException {
		lock.lock();
		try {
			oxygenCount++;
			while ((hydrogenCount - hydrogenUsedCount) < 2 || (oxygenCount - oxygenUsedCount) < 1) {
				oxygenCondition.await();
				if ((hydrogenCount - hydrogenUsedCount) == 0 && (oxygenCount - oxygenUsedCount) == 0) {
					return;
				}
			}

			totalH2O++;
			hydrogenUsedCount += 2;
			oxygenUsedCount += 1;
			oxygenCondition.signalAll();
			hydrogenCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public int getH2o() {
		return totalH2O;
	}

	public int getHydrogenCount() {
		return hydrogenCount;
	}

	public int getOxygenCount() {
		return oxygenCount;
	}

}
