package Assignment2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private final int id;
	private double balance;
	private Lock lock = new ReentrantLock();

	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public Lock getLock() {
		return lock;
	}

}
