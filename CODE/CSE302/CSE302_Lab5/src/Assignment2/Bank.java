package Assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Bank {
	private List<Account> accounts = new ArrayList<>();

	public Bank(int accountNum, int balance) {
		for (int i = 0; i < accountNum; i++) {
			this.accounts.add(new Account(i, balance));
		}
	}

	private Account find(int id) {
		for (Account account : accounts) {
			if (account.getId() == id)
				return account;
		}
		return null;
	}

	public boolean transaction(int fromId, int toId, double amount) throws InterruptedException {
		Account from = this.find(fromId);
		if (from == null)
			return false;
		Account to = this.find(toId);
		if (to == null)
			return false;
		return this.transaction(from, to, amount);
	}

	private boolean transaction(Account from, Account to, double amount) throws InterruptedException {
		Lock fromLock = from.getLock();
		Lock toLock = to.getLock();

		if (System.identityHashCode(fromLock) < System.identityHashCode(toLock)) {
			fromLock.lock();
			toLock.lock();

			from.setBalance(from.getBalance() - amount);
			to.setBalance(to.getBalance() + amount);

			toLock.unlock();
			fromLock.unlock();
		} else {
			toLock.lock();
			fromLock.lock();

			from.setBalance(from.getBalance() - amount);
			to.setBalance(to.getBalance() + amount);

			fromLock.unlock();
			toLock.unlock();
		}
		return true;
	}
}
