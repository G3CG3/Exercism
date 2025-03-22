import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance;
    private boolean isOpen;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount() {
        this.balance = 0.0;
        this.isOpen = false;
    }

    void open() throws BankAccountActionInvalidException {
        lock.lock();
        try {
            if (isOpen) {
                throw new BankAccountActionInvalidException("Account already open");
            }
            balance = 0;
            isOpen = true;
        } finally {
            lock.unlock();
        }
    }

    void close() throws BankAccountActionInvalidException {
        lock.lock();
        try {
            if (!isOpen) {
                throw new BankAccountActionInvalidException("Account not open");
            }
            isOpen = false;
        } finally {
            lock.unlock();
        }
    }

    int getBalance() throws BankAccountActionInvalidException {
        lock.lock();
        try {
            if (!isOpen) {
                throw new BankAccountActionInvalidException("Account closed");
            }
            return (int) balance;
        } finally {
            lock.unlock();
        }
    }

    void deposit(int amount) throws BankAccountActionInvalidException {
        lock.lock();
        try {
            if (!isOpen) {
                throw new BankAccountActionInvalidException("Account closed");
            }
            if (amount < 0) {
                throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
            }
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    void withdraw(int amount) throws BankAccountActionInvalidException {
        lock.lock();
        try {
            if (!isOpen) {
                throw new BankAccountActionInvalidException("Account closed");
            }
            if (amount < 0) {
                throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
            }
            if (amount > balance) {
                throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

}